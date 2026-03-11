
package operations.radnik;

import common.domain.DomainObject;
import common.domain.Radnik;
import db.BrokerDB;
import java.sql.SQLException;
import operations.SystemOperation;

public class SOPrijaviRadnik extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        return BrokerDB.prijavaKorisnika(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Radnik == false){
            return false;
        }
        return true;
    }
    
}
