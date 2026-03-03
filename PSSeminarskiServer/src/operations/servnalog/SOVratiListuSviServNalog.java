
package operations.servnalog;

import common.domain.DomainObject;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultListSO;


public class SOVratiListuSviServNalog extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(DBBroker.vratiSveSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        return true;
    }
    
}
