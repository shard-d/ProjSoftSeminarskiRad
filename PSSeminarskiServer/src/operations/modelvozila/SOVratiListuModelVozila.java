
package operations.modelvozila;

import common.domain.DomainObject;
import common.domain.ModelVozila;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultListSO;

public class SOVratiListuModelVozila extends ResultListSO{
    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultList(DBBroker.pronadjiSlogove(domainObject));
        if(getResultList() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof ModelVozila == false){
            //System.out.println("MESTO FAILOVALO RESTRIKCIJE U SYS OP");
            return false;
        }
        //return domainObject.validateObject();
        return true; // read operacija
    }
    
}
