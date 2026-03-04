/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.servnalog;

import common.domain.DomainObject;
import common.domain.StavkaServisa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class StavkaServisaTableModel extends AbstractTableModel{

    List<DomainObject> list;

    public StavkaServisaTableModel(List<DomainObject> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaServisa stvk = (StavkaServisa)list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return stvk.getIdServNalog();
            case 1:
                return stvk.getRb();
            case 2:
                return Double.toString(stvk.getCenaStavke());
            case 3:
                return stvk.getKolicina();
            case 4:
                return stvk.getServoper().getIdServOper();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "IDServNalog";
            case 1:
                return "Redni broj";
            case 2:
                return "Cena stavke";
            case 3:
                return "Količina";
            case 4:
                return "idServOper";
            default:
                return "";
        }
    }
    
    
    
}
