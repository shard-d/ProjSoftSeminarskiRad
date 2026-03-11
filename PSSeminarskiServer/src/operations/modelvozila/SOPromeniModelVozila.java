
package operations.modelvozila;

import common.domain.DomainObject;
import common.domain.ModelVozila;
import db.BrokerDB;
import java.sql.SQLException;
import operations.SystemOperation;

public class SOPromeniModelVozila extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        return BrokerDB.azurirajSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ModelVozila == false)
            return false;
        return domainObject.validateObject();
    }
    
}
