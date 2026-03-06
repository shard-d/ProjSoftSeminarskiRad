/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.servnalog;

import common.domain.DomainObject;
import common.domain.ServNalog;
import db.DBBroker;
import java.sql.SQLException;
import java.util.List;
import operations.SystemOperation;

/**
 *
 * @author danic
 */
public class SOKreirajServNalog extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        if(domainObject instanceof ServNalog == false){
            System.out.println("ERROR: system operation class mismatch");
            return false;
        }
        DBBroker.upisiSlog(domainObject);
        List<DomainObject> listaSlabihObjekata = ((ServNalog)domainObject).getStavkaServislista();
        for(DomainObject iterator : listaSlabihObjekata){
            DBBroker.upisiSlog(iterator);
        }
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        return domainObject.validateObject();
    }
    
}
