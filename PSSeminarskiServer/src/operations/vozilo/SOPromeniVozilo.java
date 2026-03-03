package operations.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import db.DBBroker;
import java.sql.SQLException;
import operations.SystemOperation;


public class SOPromeniVozilo extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        return DBBroker.azurirajSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
         if(domainObject == null || domainObject instanceof Vozilo == false)
            return false;
        return domainObject.validateObject();
    }
    
}
