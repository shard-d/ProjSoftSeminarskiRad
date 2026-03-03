
package operations;

import common.domain.DomainObject;


public abstract class ResultObjectSO extends SystemOperation{
    private DomainObject resultObject;
    
    protected void setResultObject(DomainObject obj){
        this.resultObject = obj;
    }
    
    public DomainObject getResultObject(){
        return this.resultObject;
    }
}
