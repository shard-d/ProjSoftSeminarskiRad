
package operations.termin;

import common.domain.DomainObject;
import common.domain.Termin;
import common.domain.TerminRadnika;
import db.DBBroker;
import java.sql.SQLException;
import java.util.List;
import operations.SystemOperation;

public class SOUbaciTermin extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        if(DBBroker.upisiSlog(domainObject) == false)
            return false;
        List<DomainObject> listaTerm = ((Termin)domainObject).getListaTerminaRadnika();
        if(listaTerm == null)
            return true;
        for(DomainObject iterator : listaTerm){
            TerminRadnika obj = (TerminRadnika) iterator;
            if(DBBroker.upisiSlog(obj) == false)
                return false;
        }
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
         if(domainObject == null || domainObject instanceof Termin == false)
            return false;
        return domainObject.validateObject();
    }
    
}
