
package operations;

import common.domain.DomainObject;
import db.DBBroker;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danic
 */
public class SOPretraziSlogove extends SystemOperation{
   
    private List<DomainObject> resultList;

    public List<DomainObject> getResultList() {
        return resultList;
    }
    
    @Override
    public boolean execute(DomainObject domainObject) throws SQLException {
        boolean signal = false;
        resultList = DBBroker.pronadjiSlogove(domainObject);
        if(resultList != null)
            signal = true;
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
        // READ OPERATION
        return true;
    }
    
}
