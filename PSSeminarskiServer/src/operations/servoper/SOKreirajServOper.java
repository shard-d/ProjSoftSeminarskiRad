
package operations.servoper;

import common.domain.DomainObject;
import common.domain.ServisnaOperacija;
import db.BrokerDB;
import java.sql.SQLException;
import operations.SystemOperation;


public class SOKreirajServOper extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
         if(domainObject == null || domainObject instanceof ServisnaOperacija == false)
            return false;
        return  BrokerDB.upisiSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        return domainObject.validateObject();
    }
    
}
