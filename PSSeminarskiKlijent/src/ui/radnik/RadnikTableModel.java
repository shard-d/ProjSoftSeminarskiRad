/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.radnik;

import common.domain.DomainObject;
import common.domain.Radnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danic
 */
public class RadnikTableModel extends AbstractTableModel{

    private List<DomainObject> list;

    public RadnikTableModel(List list) {
        this.list = list;
    }
    
    private final String[] columnNames = {
        "ID",
        "Ime",
        "Prezime",
        "Kontakt tel",
        "Adresa",
        "Email",
        "Username",
        "Password"
    };
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Radnik radnik = (Radnik) list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return radnik.getIdRadnik();
            case 1:
                return radnik.getIme();
            case 2:
                return radnik.getPrezime();
            case 3:
                return radnik.getKontaktTel();
            case 4:
                return radnik.getAdresa();
            case 5:
                return radnik.getEmail();
            case 6:
                return radnik.getKorisnickoIme();
            case 7:
                return radnik.getSifra();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
}
