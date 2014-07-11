package com.intactile.persistance;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * 
 * @author Mojdeh
 */
public class MemoryUtils implements IPersistance {

	OntModel memory = null;

	@Override
	public OntModel getModel() {
		return memory;
	}

	@Override
	public void createModel() {
	
	}

	@Override
	public void emptyModel() {
	
	}
	
	
}

