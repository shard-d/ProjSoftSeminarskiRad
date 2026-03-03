
package operations.radnik;

import common.domain.DomainObject;
import common.domain.Radnik;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultListSO;


public class SOVratiListuSviRadnik extends ResultListSO{
    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(DBBroker.vratiSveSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Radnik == false)
            return false;
        return true;
    }
    
}
