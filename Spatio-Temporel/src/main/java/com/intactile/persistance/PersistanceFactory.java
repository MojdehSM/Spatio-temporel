package com.intactile.persistance;

/**
*
* @author Mojdeh
*/
public class PersistanceFactory {

public static PersistanceType kind = PersistanceType.Memory ;
    
    public enum PersistanceType{
        Memory,
        TDB,
        SDB,
    }
    public static IPersistance getCurrentPersistance(PersistanceType t){
        switch(t){
                
            case TDB:
                return new TDBUtils();
            
            case Memory:
            case SDB:
                //return new SDBUtils();
        }
        return null;
    }
}
