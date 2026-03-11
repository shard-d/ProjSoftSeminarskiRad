
package operations.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import db.BrokerDB;
import java.sql.SQLException;
import operations.ResultListSO;

public class SOVratiListuVozilo extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(BrokerDB.pronadjiSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Vozilo == false || domainObject.isQueryFilterSet()== false)
            return false;
        return true;
    }
    
}
