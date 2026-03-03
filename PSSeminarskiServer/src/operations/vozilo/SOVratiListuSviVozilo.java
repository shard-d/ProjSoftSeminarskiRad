
package operations.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultListSO;

public class SOVratiListuSviVozilo extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(DBBroker.vratiSveSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Vozilo == false)
            return false;
        return true;
    }
    
}
