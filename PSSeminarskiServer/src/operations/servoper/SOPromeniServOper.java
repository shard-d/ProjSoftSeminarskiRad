
package operations.servoper;

import common.domain.DomainObject;
import common.domain.ServisnaOperacija;
import db.BrokerDB;
import java.sql.SQLException;
import operations.SystemOperation;

public class SOPromeniServOper extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        return BrokerDB.azurirajSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ServisnaOperacija == false)
            return false;
        return domainObject.validateObject();
    }
    
}
