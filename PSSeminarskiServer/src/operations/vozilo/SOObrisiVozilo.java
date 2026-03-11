package operations.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import db.BrokerDB;
import java.sql.SQLException;
import operations.SystemOperation;


public class SOObrisiVozilo extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        return BrokerDB.obrisiSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Vozilo == false){
            return false;
        }
        return domainObject.validateObject();
    }
    
}
