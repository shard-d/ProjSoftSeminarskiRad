/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.servnalog;

import common.domain.DomainObject;
import common.domain.ServNalog;
import common.domain.StavkaServisa;
import common.util.QueryFilter;
import db.DBBroker;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import operations.SystemOperation;

/**
 *
 * @author danic
 */
public class SOPromeniServNalog extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        if(DBBroker.azurirajSlog(domainObject) == false)
            return false;
        List<DomainObject> noveStavke = ((ServNalog)domainObject).getStavkaServisa();
        StavkaServisa stavkaQuery;
        if(noveStavke != null && noveStavke.isEmpty() == false){
            stavkaQuery = (StavkaServisa)noveStavke.get(0); // ako lista nije prazna
        }else{
            long id = ((ServNalog)domainObject).getIdServNalog();
            stavkaQuery = new StavkaServisa(id, 0, id, 0, null);
        }
        
        stavkaQuery.setQueryFilter(stavkaQuery.generateQueryMask(true, false, false, false, false));
        List<DomainObject> stareStavke = DBBroker.pronadjiSlogove(stavkaQuery);
        if(stareStavke == null) // potpuno legitimno da bude prazna lista
            return false;
        boolean signal = true;
        for(DomainObject iterator : stareStavke){
            if(signal == false)
                return false;
            iterator.setQueryFilter(new QueryFilter(iterator));
            signal = DBBroker.obrisiSlog(iterator);
        }
        for(DomainObject iterator : noveStavke){
            if(signal == false)
                return false;
            iterator.setQueryFilter(new QueryFilter(iterator));
            System.out.println(((StavkaServisa)iterator).getIdServNalog());
            DBBroker.upisiSlog(iterator);
        }
        return signal;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
         if(domainObject == null || domainObject instanceof ServNalog == false)
            return false;
        return domainObject.validateObject();
    }
    
}
