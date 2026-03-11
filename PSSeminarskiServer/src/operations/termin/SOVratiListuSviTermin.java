
package operations.termin;

import common.domain.DomainObject;
import common.domain.Termin;
import db.BrokerDB;
import java.sql.SQLException;
import operations.ResultListSO;

public class SOVratiListuSviTermin extends ResultListSO{
    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(BrokerDB.vratiSveSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Termin == false)
            return false;
//        return domainObject.validateObject();
        return true;
    }
    
}
