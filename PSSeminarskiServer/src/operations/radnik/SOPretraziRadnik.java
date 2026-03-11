
package operations.radnik;

import common.domain.DomainObject;
import common.domain.Radnik;
import db.BrokerDB;
import java.sql.SQLException;
import operations.ResultObjectSO;



public class SOPretraziRadnik extends ResultObjectSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultObject(BrokerDB.pronadjiSlog(domainObject));
        if(getResultObject() == null)
            return false;
        return true; 
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Radnik == false)
            return false;
        return domainObject.validateObject();
    }
    
}
