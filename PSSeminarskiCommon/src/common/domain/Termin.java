/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.domain;

import common.util.QueryFilter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danic
 */
public class Termin extends DomainObject{

    private long idTermin;
    private LocalDate datumTermina;
    private Smena smena;

    private List<DomainObject> listaTerminaRadnika;
    
    public Termin() {
    }

    
    public Termin(long idTermin, LocalDate datumTermina, Smena smena) {
        this.idTermin = idTermin;
        this.datumTermina = datumTermina;
        this.smena = smena;
    }
    
    public Termin(ResultSet rs) throws SQLException{
        long id = rs.getLong("termin.idTermin");
        LocalDate datum = rs.getDate("termin.datumTermina").toLocalDate();
        Smena smena = Smena.valueOf(rs.getString("termin.smena"));
        
        this.idTermin = id;
        this.datumTermina = datum;
        this.smena = smena;
        
        if(rs.getMetaData().getColumnCount() > 3){
            listaTerminaRadnika = new ArrayList<>();
            do{
                if(rs.getLong("termin.idTermin") != id){
                    // neophodno da se ne preskoci record pri generisanju liste
                    if(rs.isAfterLast() == false)
                        rs.previous();
                    break;
                }
                if(rs.getString("radnik.ime") != null){ // hack (imamo LEFT JOIN u upitu)
                    //listaTermin.add(new TerminRadnika(this, new Radnik(rs), rs.getDouble("zaposlenitermin.iznosDnevnice")));
                }

            }while(rs.next());
        }
    }

    public Termin(LocalDate datumTermina, Smena smena, List<DomainObject> listaTermina) {
        this.idTermin = 0L;
        this.datumTermina = datumTermina;
        this.smena = smena;
        this.listaTerminaRadnika = listaTermina;
    }

    public Termin(long idTermin, LocalDate datumTermina, Smena smena, List<DomainObject> listaTermina) {
        this.idTermin = idTermin;
        this.datumTermina = datumTermina;
        this.smena = smena;
        this.listaTerminaRadnika = listaTermina;
    }
    
    public QueryFilter generateQueryMask(boolean idTermin, boolean datumTermina, boolean smena){
        return new QueryFilter(this, new boolean[]{idTermin, datumTermina, smena});
    }

    public long getIdTermin() {
        return idTermin;
    }

    public LocalDate getDatumTermina() {
        return datumTermina;
    }

    public Smena getSmena() {
        return smena;
    }

    public List<DomainObject> getListaTerminaRadnika() {
        return listaTerminaRadnika;
    }

    public void setListaTerminaRadnika(List<DomainObject> listaTerminaRadnika) {
        this.listaTerminaRadnika = listaTerminaRadnika;
    }
    
    
    
    @Override
    public String getTableName() {
        return "termin";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
         if(idRequired){
            return new String[]{"idTermin", "datumTermina", "smena"};
        }
        return new String[]{"datumTermina","smena"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        if(idRequired){
            return new String[]{Long.toString(idTermin), wrapInQuotes(Date.valueOf(datumTermina).toString()), wrapInQuotes(smena.toString())};
        }
        return new String[]{wrapInQuotes(Date.valueOf(datumTermina).toString()), wrapInQuotes(smena.toString())};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"idTermin"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idTermin)};
    }

    @Override
    public boolean validateObject() {
        if(idTermin < 0)
            return false;
        if(datumTermina == null || smena == null)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        if(rs == null)
            return null;
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Termin(rs));
        }
        return list;
    }

    @Override
    public boolean equals(DomainObject domainObject) {
        if(domainObject == null || domainObject instanceof Termin == false)
            return false;
        Termin ter = (Termin) domainObject;
        if(ter.idTermin != idTermin || ter.datumTermina.equals(datumTermina) == false 
                || ter.smena.equals(smena) == false || ter.listaTerminaRadnika.equals(listaTerminaRadnika) == false)
            return false;
        return true;
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        if(joinList == true)
            return "LEFT JOIN terminradnika ON termin.idTermin = terminradnika.idTermin "
                    +"LEFT JOIN radnik ON radnik.idRadnik = terminradnika.idRadnik";
        //return "LEFT JOIN zaposlenitermin ON termindezurstva.idTerminDezurstva = zaposlenitermin.idTerminDezurstva";
        return "";
    }
    
    
    @Override
    public String toString() {
        return datumTermina.toString() + ": "+ smena.toString();
    }
    
}
