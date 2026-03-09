/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.servoper;

import common.domain.DomainObject;
import common.domain.ServisnaOperacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class ServOperTableModel extends AbstractTableModel{

    List<DomainObject> list;

    public ServOperTableModel(List<DomainObject> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ServisnaOperacija so = (ServisnaOperacija) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return so.getIdServOper();
            case 1:
                return so.getNazivServOper();
            case 2:
                return so.getTipServOper();
            case 3:
                return so.getCenaServOper();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] names = new String[]{"idServOper", "Naziv servisne operacije", "Tip servisne operacije", "Cena"};
        return names[column];
    }
    
    
    
}
