
package operations.radnik;

import common.domain.DomainObject;
import common.domain.Radnik;
import db.DBBroker;
import java.sql.SQLException;
import operations.SystemOperation;

public class SOPrijaviRadnik extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        return DBBroker.prijavaKorisnika(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Radnik == false){
            return false;
        }
        return true;
    }
    
}
