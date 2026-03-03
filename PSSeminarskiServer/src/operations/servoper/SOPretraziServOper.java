
package operations.servoper;

import common.domain.DomainObject;
import common.domain.ServisnaOperacija;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultObjectSO;



public class SOPretraziServOper extends ResultObjectSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultObject(DBBroker.pronadjiSlog(domainObject));
        if(getResultObject() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ServisnaOperacija == false)
            return false;
        return domainObject.validateObject();
    }
    
}
