/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.termin;

import common.domain.DomainObject;
import common.domain.Termin;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class TerminTableModel extends AbstractTableModel{

     List<DomainObject> list;

    public TerminTableModel(List<DomainObject> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Termin termin = (Termin)list.get(rowIndex);
        
        switch(columnIndex){
        
            case 0:
                return termin.getIdTermin();
            case 1:
                return termin.getDatumTermina().toString();
            case 2:
                return termin.getSmena().toString();
            
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "TerminID";
            case 1:
                return "Datum termina";
            case 2:
                return "Smena";
            default:
                return "";
        }
    }
    
    
    
}
