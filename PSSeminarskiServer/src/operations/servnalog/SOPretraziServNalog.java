
package operations.servnalog;

import common.domain.DomainObject;
import common.domain.ServNalog;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultObjectSO;

/**
 *
 * @author danic
 */
public class SOPretraziServNalog extends ResultObjectSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultObject(DBBroker.pronadjiSlog(domainObject));
        if(getResultObject() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ServNalog == false)
            return false;
        return domainObject.validateObject();
    }
    
}
