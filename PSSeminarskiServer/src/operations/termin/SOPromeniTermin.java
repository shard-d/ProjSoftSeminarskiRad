
package operations.termin;

import common.domain.DomainObject;
import common.domain.Radnik;
import common.domain.Smena;
import common.domain.Termin;
import common.domain.TerminRadnika;
import common.domain.Uloga;
import common.util.QueryFilter;
import db.DBBroker;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import operations.SystemOperation;

public class SOPromeniTermin extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        if(DBBroker.azurirajSlog(domainObject) == false)
            return false;
        
        long idTermin = ((Termin)domainObject).getIdTermin();
        
        // pattern objekta za pretragu (termin koji ima idTermin taj i taj); ostali podaci u konstruktoru su tu da ne bi imali null exception)
        TerminRadnika stavkaQuery = new TerminRadnika(Uloga.ELEKTRICAR, "", new Radnik(), new Termin(idTermin, LocalDate.MIN, Smena.PRVA));
        stavkaQuery.setIdTermin(idTermin);
        
        // kaze filteru da ne gleda nista osim terminId
        stavkaQuery.setQueryFilter(false, false, false, false, true);
        List<DomainObject> stareStavke = DBBroker.pronadjiSlogove(stavkaQuery);
        
        List<DomainObject> noveStavke = ((Termin)domainObject).getListaTerminaRadnika();
        
        
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
