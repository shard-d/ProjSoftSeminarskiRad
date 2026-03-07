/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.vozilo;

import common.domain.DomainObject;
import common.domain.Vozilo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class VoziloTableModel extends AbstractTableModel{

    private List<DomainObject> list;

    public VoziloTableModel(List<DomainObject> list) {
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
        Vozilo v = (Vozilo) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return v.getIdVozilo();
            case 1:
                return v.getNazivVozila();
            case 2:
                return v.getTipVozila();
            case 3:
                return v.getModelVozila().getIdModelVozila();
            default:
                return "";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String[] names = new String[]{"idVozilo", "Naziv vozila", "Tip Vozila", "idModelVozila"};
        return names[column];
    }
    
    
}
