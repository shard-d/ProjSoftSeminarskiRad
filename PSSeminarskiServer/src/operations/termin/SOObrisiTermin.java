
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
        
        Termin termin = (Termin) domainObject;
        termin.generateQueryMask(true, false, false);
        
        if(termin.getListaTerminaRadnika().isEmpty()) return DBBroker.obrisiSlog(termin);
        TerminRadnika terminQuery = (TerminRadnika)termin.getListaTerminaRadnika().get(0);
        terminQuery.setQueryFilter(false, false, false, false, true);
        
        List<DomainObject> listStavke = DBBroker.pronadjiSlogove(terminQuery);
        boolean signal = true;
        TerminRadnika tr = (TerminRadnika) termin.getListaTerminaRadnika().get(0);
        tr.setIdTermin(termin.getIdTermin());
        tr.setQueryFilter(false, false, false, false, true);
        signal = DBBroker.obrisiSlog(tr); // moze biti vise slogova
        
        if(signal == false)
            return signal;
        
        return DBBroker.obrisiSlog(termin);
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Termin == false){
            return false;
        }
        return domainObject.validateObject();
    }
    
}
