package com.intactile.main;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.intactile.persistance.CreateOntology;

/**
 * Hello world!
 * 
 */
public class MainST {
	public static void main(String[] args) {
		CreateOntologyFromOntologyFile();
		Interogation();
	}

	public static void Interogation() {
		Model model = CreateOntology.model;

		Query query = QueryFactory.create(req);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			ResultSetFormatter.out(System.out, results);
		} finally {
			qexec.close();
		}
	}

	public static void CreateOntologyFromOntologyFile() {
		String[] files = { "./resources/STOntologie.owl",
				"./resources/TOntologie.owl", "./resources/SOntologie.owl" };
		CreateOntology.CreateMemOntologyFromFiles(files, "RDF/XML");

		String[] files2 = { "./resources/Ship", "./resources/Port" };
		CreateOntology.CreateMemOntologyFromFiles(files2, "N3");

	}

	static String prefix = "PREFIX afn: <http://jena.hpl.hp.com/ARQ/function#>"
			+ "PREFIX fn: <http://www.w3.org/2005/xpath-functions#>"
			+ "PREFIX geo: <http://www.opengis.net/ont/geosparql#>"
			+ "PREFIX geof: <http://www.opengis.net/def/function/geosparql/>"
			+ "PREFIX gml: <http://www.opengis.net/ont/gml#>"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
			+ "PREFIX par: <http://parliament.semwebcentral.org/parliament#>"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX sf: <http://www.opengis.net/ont/sf#>"
			+ "PREFIX time: <http://www.w3.org/2006/time#>"
			+ "PREFIX units: <http://www.opengis.net/def/uom/OGC/1.0/>"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
			+ ""
			+ "PREFIX myST:<http://www.IntactileDESGN.com/ontologies/2014/STOntologie#>"
			+ "PREFIX myS:<http://www.IntactileDESGN.com/ontologies/2014/SOntologie#>"
			+ "PREFIX myT:<http://www.IntactileDESGN.com/ontologies/2014/TOntologie#>"
			+ "PREFIX mySx:<http://www.IntactileDESGN.com/ontologies/2014/Example#> "
			+ "" + "PREFIX spatial: <http://jena.apache.org/spatial#>";

	static String req = prefix + " SELECT ?port ?ship ?when ?position WHERE  "
			+ "{" + "?ship rdfs:label \"Alpha\" ."
			+ "?ship rdf:type myS:Ship ."
			+ "?ship myST:isTimeSliceOf ?timeslice ." + ""
			+ "?timeslice geo:hasGeometry  ?gposition ."
			+ "?timeslice myST:atTime  ?tposition ."
			+ "?tposition myT:timeValue  ?when ."
			+ "?gposition geo:asWKT ?position ."
			+ "?gposition myS:pointSpeed ?speed ." + ""
			+ "?port rdfs:label \"Port bastia\" ."
			+ "?port rdf:type myS:Port ." + "?port geo:hasGeometry ?geoPort ."
			+ "?geoPort geo:asWKT ?wktPort ." + ""
			+ "?position spatial:withinCircle(42.689615 9.469331 10 'km')"
			+ "}";
}
