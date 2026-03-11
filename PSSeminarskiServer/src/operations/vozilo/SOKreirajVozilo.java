
package operations.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import db.BrokerDB;
import java.sql.SQLException;
import operations.SystemOperation;

/**
 *
 * @author danic
 */
public class SOKreirajVozilo extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Vozilo == false)
            return false;
        return BrokerDB.upisiSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
         return domainObject.validateObject();
    }
    
}
