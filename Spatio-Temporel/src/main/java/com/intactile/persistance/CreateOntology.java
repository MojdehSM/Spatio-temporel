package com.intactile.persistance;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

/**
*
* @author Mojdeh
*/
public class CreateOntology {

	public static void CreateOntologyFromFile(String filename, String syntax) {

		// model input
		Model modelOrigin = FileManager.get().loadModel(filename, syntax);

		// get persistence model
		IPersistance persistance = PersistanceFactory.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB);

		// model output
		OntModel modelClone = persistance.getModel();

		// put the statement of model input in persistence model output
		StmtIterator stmts = modelOrigin.listStatements();
		while (stmts.hasNext()) {
			Statement stmt = stmts.next();
			System.out.println(stmt.toString());
			modelClone.add(stmt);
		}
		// System.err.println(modelClone.listClasses().toList().size());
		modelClone.close();
	}

	public static void CreateOntologyFromFiles(String[] filenames, String syntax) {

		// get persistence model
		IPersistance persistance = PersistanceFactory.getCurrentPersistance(PersistanceFactory.PersistanceType.TDB);

		// model output
		OntModel modelClone = persistance.getModel();

		for (String filename : filenames) {
			// model input
			Model modelOrigin = FileManager.get().loadModel(filename, syntax);

			// put the statement of model input in persistence model output
			StmtIterator stmts = modelOrigin.listStatements();
			while (stmts.hasNext()) {
				Statement stmt = stmts.next();
				System.out.println(stmt.toString());
				modelClone.add(stmt);
			}
		}
		// System.err.println(modelClone.listClasses().toList().size());
		modelClone.close();
	}

	public static OntModel model = null;

	public static void CreateMemOntologyFromFiles(String[] filenames, String syntax) {
		if (model == null)
			model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

		for (String filename : filenames) {
			// model input
			Model modelOrigin = FileManager.get().loadModel(filename, syntax);

			// put the statement of model input in persistence model output
			StmtIterator stmts = modelOrigin.listStatements();
			while (stmts.hasNext()) {
				Statement stmt = stmts.next();
				System.out.println(stmt.toString());
				model.add(stmt);
			}
		}
	}
}
