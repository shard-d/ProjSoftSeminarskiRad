/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

import common.domain.DomainObject;
import db.BrokerDB;
import java.sql.SQLException;

/**
 *
 * @author danic
 */
public class SOKreirajSlog extends SystemOperation{

    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        boolean signal = false;
        signal = BrokerDB.upisiSlog(domainObject);
        return signal;
    }

    @Override
    public boolean checkRestrictions(DomainObject domainObject) throws SQLException {
        switch (domainObject.getTableName()) {
            case "servnalog":
                break;
            default:
                break;
        }
        return true;
    }
    
}
