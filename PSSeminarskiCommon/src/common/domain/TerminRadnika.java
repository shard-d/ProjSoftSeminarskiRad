/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.domain;

import common.util.QueryFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danic
 */
public class TerminRadnika extends DomainObject{

    private long ID;
    private Uloga uloga;
    private String napomena;
    private Radnik radnik;
    private Termin termin;
    private long idRadnik;
    private long idTermin;
    
    
    public TerminRadnika() {
    }

    public TerminRadnika(long ID, Uloga uloga,  String napomena, Radnik radnik, Termin termin) {
        this.ID = ID;
        this.uloga = uloga;
        this.napomena = napomena;
        this.radnik = radnik;
        this.termin = termin;
    }

    public TerminRadnika(long ID, Uloga uloga, String napomena, Radnik radnik, Termin termin, long idRadnik, long idTermin) {
        this.ID = ID;
        this.uloga = uloga;
        this.napomena = napomena;
        this.radnik = radnik;
        this.termin = termin;
        this.idRadnik = idRadnik;
        this.idTermin = idTermin;
    }
    
    public long getID() {
        return ID;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public String getNapomena() {
        return napomena;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Termin getTermin() {
        return termin;
    }

    public TerminRadnika(ResultSet rs) throws SQLException {
        ID = rs.getInt("ID");
        uloga = Uloga.valueOf(rs.getString("uloga"));
        napomena = rs.getString("napomena");
        radnik = new Radnik();
        termin = new Termin();
        idRadnik = radnik.getIdRadnik();
        idTermin = termin.getIdTermin();   
    }

    public void setQueryFilter(boolean id, boolean uloga, boolean idRadnik, boolean idTermin) {
        queryFilter = new QueryFilter(this, new boolean[] {id, uloga, idRadnik, idTermin}) ;
    }
    
    
    @Override
    public String getTableName() {
        return "terminradnika";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        return new String[]{"ID", "uloga", "napomena", "idRadnik", "idTermin"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        return new String[]{Long.toString(ID), uloga.toString() , napomena, Long.toString(idRadnik), Long.toString(idTermin)};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"ID"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(ID)};
    }

    @Override
    public boolean validateObject() {
        if(ID<0 || uloga == null || idRadnik<0 || idTermin<0)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        if(rs == null)
            return null;
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            list.add(new TerminRadnika(rs.getLong("ID"),
                Uloga.valueOf(rs.getString("uloga")),
                rs.getString("napomena"),
                new Radnik(rs.getLong("terminradnik.idRadnik"), "", "", "", "", "", "", ""),
                new Termin(rs.getLong("terminradnik.idTermin"), LocalDate.MIN, Smena.PRVA)));
        }
        return list;
    }

    @Override
    public boolean equals(DomainObject obj) {
         if(obj == null || obj instanceof TerminRadnika == false)
            return false;
        TerminRadnika terRad = (TerminRadnika) obj;
        if(ID != terRad.ID || uloga != terRad.uloga || !napomena.equals(terRad.napomena) || idRadnik != terRad.idRadnik || idTermin != terRad.idTermin)
            return false;
        return true;
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        if(queryFilter.getObject() instanceof TerminRadnika)
            return " JOIN termin ON termin.idTermin = terminradnika.idTermin ";
        return "";
    }
    
}