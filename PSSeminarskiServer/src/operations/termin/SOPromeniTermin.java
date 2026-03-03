
package operations.termin;

import common.domain.DomainObject;
import common.domain.Termin;
import common.domain.TerminRadnika;
import common.util.QueryFilter;
import db.DBBroker;
import java.sql.SQLException;
import java.util.List;
import operations.SystemOperation;

public class SOPromeniTermin extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        if(DBBroker.azurirajSlog(domainObject) == false)
            return false;
        
        TerminRadnika stavkaQuery = new TerminRadnika();
        stavkaQuery.setQueryFilter(new QueryFilter(domainObject));
        List<DomainObject> stareStavke = DBBroker.pronadjiSlogove(stavkaQuery);
        
        List<DomainObject> noveStavke = ((Termin)domainObject).getListaTermina();
        
        
        if(stareStavke == null)
            return false;
        
        boolean signal = true;
        for(DomainObject iterator : stareStavke){
            if(signal == false)
                break;
            iterator.setQueryFilter(new QueryFilter(iterator));
            signal = DBBroker.obrisiSlog(iterator);
        }
        for(DomainObject iterator : noveStavke){
            if(signal == false)
                break;
            DBBroker.upisiSlog(iterator);
        }
        return signal; 
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Termin == false)
            return false;
        return domainObject.validateObject();
    }
    
}
