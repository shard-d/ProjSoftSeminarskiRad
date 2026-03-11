
package operations.termin;

import common.domain.DomainObject;
import common.domain.Termin;
import db.BrokerDB;
import java.sql.SQLException;
import operations.ResultListSO;


public class SOVratiListuTermin extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(BrokerDB.pronadjiSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject != null && domainObject instanceof Termin)
            return true; // read operacija
        return false;
    }
    
}
