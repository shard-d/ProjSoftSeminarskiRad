
package operations;

import common.domain.DomainObject;
import java.util.List;


public abstract class ResultListSO extends SystemOperation{
    private List<DomainObject> resultList;
    public List<DomainObject> getResultList(){
        return resultList;
    }
    protected void setResultList(List<DomainObject> resultList){
        this.resultList = resultList; 
    }
}
