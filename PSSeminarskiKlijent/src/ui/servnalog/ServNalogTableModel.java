/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.servnalog;

import common.domain.DomainObject;
import common.domain.ServNalog;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class ServNalogTableModel extends AbstractTableModel{

    List<DomainObject> list;

    public ServNalogTableModel(List<DomainObject> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ServNalog servnalog = (ServNalog)list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return servnalog.getIdServNalog();
            case 1:
                return servnalog.getDatum().toString();
            case 2:
                return Double.toString(servnalog.getUkupnaCena());
            case 3:
                return servnalog.getNacinPlacanja().toString();
            case 4:
                return servnalog.getRadnik().getIdRadnik();
            case 5:
                return servnalog.getVozilo().getIdVozilo();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "idServNalog";
            case 1:
                return "Datum podnošenja";
            case 2:
                return "Ukupna cena";
            case 3:
                return "Način plaćanja";
            case 4:
                return "idRadnik";
            case 5:
                return "idVozilo";
            default:
                return "";
        } 
    }
    
    
}
