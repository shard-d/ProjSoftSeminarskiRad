
package operations.termin;

import common.domain.DomainObject;
import common.domain.Termin;
import common.domain.TerminRadnika;
import common.util.QueryFilter;
import db.DBBroker;
import java.sql.SQLException;
import java.util.List;
import operations.SystemOperation;


public class SOObrisiTermin extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        TerminRadnika stavkaQuery = new TerminRadnika();
        stavkaQuery.setQueryFilter(new QueryFilter(domainObject));
        List<DomainObject> listStavke = DBBroker.pronadjiSlogove(stavkaQuery);
        boolean signal = true;
        for(DomainObject i : listStavke){
            if(signal == false)
                break;
            i.setQueryFilter(new QueryFilter(i));
            signal = DBBroker.obrisiSlog(i);
        }
        if(signal == false)
            return signal;
        
        return DBBroker.obrisiSlog(domainObject);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Termin == false){
            return false;
        }
        return domainObject.validateObject();
    }
    
}
