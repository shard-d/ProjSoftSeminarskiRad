/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.util;

import common.domain.DomainObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author danic
 */
public class QueryFilter implements Serializable{
    
    // instanca objekta koji se trazi
    private DomainObject object;
    // odredjuje koja polja klase ce biti razmatrana u pretrazi
    private boolean[] criteriaMask;

//    public DomainObject getObject() {
//        return object;
//    }

    public QueryFilter(DomainObject object) {
        this.object = object;
    }

    public DomainObject getObject() {
        return object;
    }
    
    
    
    
    // maska za svaki objekat bi trebalo da je negde u dokumentaciji
    public QueryFilter(DomainObject object, boolean[] criteriaMask) {
        this.object = object;
        this.criteriaMask = criteriaMask;
    }
    
    // vraca WHERE klauzulu za primenu u SQL upitu
    public String createSQLWhereClause(){
        
        if(criteriaMask == null){
            String clause = "WHERE ";
            for(int i = 0; i < object.getSQLPrimaryKeyColumnNames().length; i++){
                if(i != 0)
                    clause += " AND ";
                clause += object.getTableName()+"."+object.getSQLPrimaryKeyColumnNames()[i]+"="+object.getSQLPrimaryKeyColumnValues()[i];
            }
            return clause;
        }
        
        String clause = "WHERE ";
        String[] unfilteredColumnArray = object.getSQLColumnNames(true);
        String[] unfilteredValueArray = object.getSQLColumnValues(true);
        boolean firstAttribute = true;
        int arrayIndex = 0;
        while(arrayIndex < unfilteredColumnArray.length){
            if(criteriaMask[arrayIndex] == false){
                arrayIndex++;
                continue;
            }
            if(firstAttribute == false){
                clause += " AND ";
            }
            clause += object.getTableName()+"."+unfilteredColumnArray[arrayIndex] + "=" + unfilteredValueArray[arrayIndex];
            //clause += unfilteredColumnArray[arrayIndex] + "=" + unfilteredValueArray[arrayIndex]; // hmm zasto bih ovo ikad uradio
            firstAttribute = false;

            arrayIndex++;
        }
        //System.out.println("RESOLVED WHERE: "+clause);
        return clause;
    }
    
    
    
    // DEPRECATED
    public String createSQLSetClause(){
        String clause = "SET ";
        String[] unfilteredColumnArray = object.getSQLColumnNames(true);
        String[] unfilteredValueArray = object.getSQLColumnValues(true);
        int arrayIndex = 0;
        while(arrayIndex < unfilteredColumnArray.length){
            if(criteriaMask[arrayIndex] == false){
                arrayIndex++;
                continue;
            }
            if(arrayIndex > 0){
                clause += " , ";
            }
            clause += unfilteredColumnArray[arrayIndex] + "=" + unfilteredValueArray[arrayIndex];

            arrayIndex++;
        }
        return clause;
        
    }
 
}

