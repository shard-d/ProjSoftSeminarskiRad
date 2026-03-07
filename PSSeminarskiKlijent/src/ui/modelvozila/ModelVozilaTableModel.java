/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.modelvozila;

import common.domain.DomainObject;
import common.domain.ModelVozila;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class ModelVozilaTableModel extends AbstractTableModel{

    List<DomainObject> list;

    public ModelVozilaTableModel(List<DomainObject> list) {
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
        ModelVozila mv = (ModelVozila) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return mv.getIdModelVozila();
            case 1:
                return mv.getNazivModela();
            case 2:
                return mv.getNazivMarke();
            case 3:
                return mv.getGodiste();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] str = new String[]{"idModelVozila", "Naziv modela", "Naziv marke", "Godište"};
        return str[column];
    }
    
}
