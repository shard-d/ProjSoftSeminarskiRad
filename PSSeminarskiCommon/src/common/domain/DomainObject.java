
package common.domain;

import common.util.QueryFilter;
import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author danic
 */
public abstract class DomainObject implements Serializable{
    
    public abstract String getTableName();
    
    public abstract String[] getSQLColumnNames(boolean idRequired);
    
    public abstract String[] getSQLColumnValues(boolean idRequired); 
    
    public abstract String[] getSQLPrimaryKeyColumnNames();
    
    public abstract String[] getSQLPrimaryKeyColumnValues();
    
    public abstract boolean validateObject();
    
    public abstract List<DomainObject> generateList(ResultSet rs) throws SQLException;
    
    protected QueryFilter queryFilter = null;
    
    public boolean isQueryFilterSet(){
        return queryFilter != null ? true : false;
    }
    
    public QueryFilter getQueryFilter() {
        return queryFilter;
    }

    public void setQueryFilter(QueryFilter queryFilter) {
        this.queryFilter = queryFilter;
    }
    
    public abstract boolean equals(DomainObject domainObject);
    
    public abstract String getSQLJoinClause(boolean joinList);
    
    public String getSQLSetClause(){
        String[] columns = getSQLColumnNames(true);
        String[] values = getSQLColumnValues(true);
        String clause = "SET ";
        for(int i = 0; i < columns.length ; i++){
            if(i != 0){
                clause += ",";
            }
            clause += columns[i] + "=" + values[i];
        }
        return clause;
    }
    
    // metoda konvertuje string u SQL string literal pogodan za upis u bazu
    protected String wrapInQuotes(String input){
        String output;
        output = "'"+input+"'";
        return output;
    }
}