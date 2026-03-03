/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import db.DBBroker;
import java.sql.SQLException;
import operations.ResultObjectSO;

/**
 *
 * @author danic
 */
public class SOPretraziVozilo extends ResultObjectSO{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        setResultObject(DBBroker.pronadjiSlog(domainObject));
        if(getResultObject() == null)
            return false;
        return true;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        if(domainObject == null || domainObject instanceof Vozilo == false)
            return false;
        return true; // read operacija
    }
    
}
