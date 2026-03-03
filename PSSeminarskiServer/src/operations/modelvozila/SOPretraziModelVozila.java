
package operations.modelvozila;

import common.domain.DomainObject;
import common.domain.ModelVozila;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultObjectSO;


public class SOPretraziModelVozila extends ResultObjectSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultObject(DBBroker.pronadjiSlog(domainObject));
        if(getResultObject() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ModelVozila == false)
            return false;
        return domainObject.validateObject();
    }
    
}
