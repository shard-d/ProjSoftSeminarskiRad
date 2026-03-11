
package operations.modelvozila;

import common.domain.DomainObject;
import common.domain.ModelVozila;
import db.BrokerDB;
import java.sql.SQLException;
import operations.ResultListSO;

public class SOVratiListuSviModelVozila extends ResultListSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(BrokerDB.vratiSveSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ModelVozila == false)
            return false;
        return true; 
    }
    
}
