
package ui.general;

import common.domain.DomainObject;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author danic
 */
public class CBModelDomainObject extends DefaultComboBoxModel<String>{
    public CBModelDomainObject(List<DomainObject> list) {
        super();
        for (DomainObject obj : list) {
            addElement(obj.toString()); 
            // apsolutno se uveri da sve domenske klase imaju toString()
        }
    }
    
    public CBModelDomainObject(){
        super();
    }
}
