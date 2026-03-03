
package operations;

import common.domain.DomainObject;
import db.DBBroker;
import java.sql.SQLException;

public abstract class SystemOperation {
     private DBBroker brokerDB = new DBBroker();

    public DBBroker getBrokerDB() {
        return brokerDB;
    }
    public boolean processTransaction(DomainObject domainObject) throws SQLException{
        boolean signal = false;
        String divider = "================================================================";
        System.out.println(divider+"\nDEBUG transakcija pocinje");
        if(checkRestrictions(domainObject) == true){
            System.out.println("DEBUG restrikcije ok");
            signal = execute(domainObject);
        }
        System.out.println("DEBUG signal izvrsenja: "+signal);
        if(signal == true){
            System.out.println("DEBUG transakcija commit\n"+divider);
            signal = DBBroker.commitTransaction();
        }else{
            System.out.println("DEBUG transakcija rollback\n"+divider);
            DBBroker.rollbackTransaction();
        }
        return signal;
    }
    
    public abstract boolean execute(DomainObject domainObject) throws SQLException;

    public abstract boolean checkRestrictions(DomainObject domainObject) throws SQLException;
}
