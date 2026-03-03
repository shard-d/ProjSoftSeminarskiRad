
package operations.radnik;

import common.domain.DomainObject;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultListSO;


public class SOVratiListuRadnik extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(DBBroker.pronadjiSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true; 
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        return true;
    }
    
}
