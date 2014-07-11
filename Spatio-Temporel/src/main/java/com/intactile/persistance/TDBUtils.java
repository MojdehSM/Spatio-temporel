package com.intactile.persistance;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;


/**
*
* @author Mojdeh
*/
public class TDBUtils implements IPersistance{

	final static String directory = "data";


	@Override
	public OntModel getModel() {
		Dataset ds = TDBFactory.createDataset(directory);
		Model model = ds.getDefaultModel();
		OntModel ontModel = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM, model);
		//ontModel.add(model);
		// dataAcessor.add(ontModel);----> Fuseki
		return ontModel;
	}

	/**
	 * a new ontology is created
	 */
	@Override
	public void createModel() {
		Dataset ds = TDBFactory.createDataset(directory);
		Model model = ds.getDefaultModel();
		ds.close();	
	}

	@Override
	public void emptyModel() {
		File data = new File(directory);

        if (data.exists()) {
            try {
                FileUtils.deleteDirectory(data);
            } catch (IOException ex) {
                Logger.getLogger(TDBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		
	}
	
}
