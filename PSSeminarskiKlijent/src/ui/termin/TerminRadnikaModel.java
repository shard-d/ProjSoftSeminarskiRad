/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.termin;

import common.domain.DomainObject;
import common.domain.Radnik;
import common.domain.TerminRadnika;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class TerminRadnikaModel extends AbstractTableModel{

     List<DomainObject> list;

    public TerminRadnikaModel(List<DomainObject> listZaposleniTermin) {
        this.list = listZaposleniTermin;
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
        TerminRadnika terminRad = (TerminRadnika)list.get(rowIndex);
        Radnik radnik = terminRad.getRadnik();
        String idRad = Long.toString(radnik.getIdRadnik());
        switch (columnIndex) {
            case 0:
                return idRad;
            case 1:
                return radnik.getIme() +" "+ radnik.getPrezime();
            case 2:
                return terminRad.getUloga();
            case 3:
                return terminRad.getTermin().getDatumTermina();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] names = new String[]{"idRadnik","Ime i prezime","Uloga", "Datum"};
        return names[column];
    }
    
    
    
}
