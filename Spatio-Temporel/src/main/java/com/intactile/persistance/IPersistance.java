package com.intactile.persistance;

import com.hp.hpl.jena.ontology.OntModel;

/**
*
* @author Mojdeh
*/
public interface IPersistance {

   /**
    * Get Model
    * @return 
    */
   OntModel getModel();

   /**
    * Creation tables
    */
   void createModel();

   /**
    * Delete tables
    */
   void emptyModel();
}

