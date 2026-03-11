
package operations.servnalog;

import common.domain.DomainObject;
import common.domain.ServNalog;
import db.BrokerDB;
import java.sql.SQLException;
import operations.ResultListSO;

/**
 *
 * @author danic
 */
public class SOVratiListuServNalog extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(BrokerDB.pronadjiSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ServNalog == false)
            return false;
        return true;
    }
    
}
