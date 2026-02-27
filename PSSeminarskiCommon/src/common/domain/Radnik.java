/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.domain;

import common.util.QueryFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danic
 */
public class Radnik extends DomainObject{
    private long idRadnik;
    private String ime;
    private String prezime;
    private String kontaktTel;
    private String adresa;
    private String email;
    private String KorisnickoIme;
    private String Sifra;

    public Radnik() {
    }

    public Radnik(String KorisnickoIme, String Sifra) {
        this.KorisnickoIme = KorisnickoIme;
        this.Sifra = Sifra;
    }

    public Radnik(Long idRadnik, String ime, String prezime, String kontaktTel, String adresa, String email, String KorisnickoIme, String Sifra) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTel = kontaktTel;
        this.adresa = adresa;
        this.email = email;
        this.KorisnickoIme = KorisnickoIme;
        this.Sifra = Sifra;
    }

    public Radnik (ResultSet rs) throws SQLException{
        this.idRadnik = rs.getLong("radnik.idRadnik");
        this.ime = rs.getString("radnik.ime");
        this.prezime = rs.getString("radnik.prezime");
        this.kontaktTel = rs.getString("radnik.kontaktTel");
        this.adresa = rs.getString("radnik.adresa");
        this.email = rs.getString("radnik.email");
        this.KorisnickoIme = rs.getString("radnik.KorisnickoIme");
        this.Sifra = rs.getString("radnik.Sifras");
    }

    public Radnik(String ime, String prezime, String kontaktTel, String adresa, String email, String KorisnickoIme, String Sifra) {
        this.idRadnik = 0L;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTel = kontaktTel;
        this.adresa = adresa;
        this.email = email;
        this.KorisnickoIme = KorisnickoIme;
        this.Sifra = Sifra;
    }
    
    
    
    public Long getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(Long idRadnik) {
        this.idRadnik = idRadnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontaktTel() {
        return kontaktTel;
    }

    public void setKontaktTel(String kontaktTel) {
        this.kontaktTel = kontaktTel;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return KorisnickoIme;
    }

    public void setKorisnickoIme(String KorisnickoIme) {
        this.KorisnickoIme = KorisnickoIme;
    }

    public String getSifra() {
        return Sifra;
    }

    public void setSifra(String Sifra) {
        this.Sifra = Sifra;
    }

    public QueryFilter generateQueryMask(boolean id, boolean ime, boolean prezime, boolean kontaktTel, boolean adresa, boolean email, boolean korisnickoIme, boolean sifra){
        return new QueryFilter(this,new boolean[]{id, ime, prezime, kontaktTel, adresa, email, korisnickoIme, sifra});
    }
    
    @Override
    public String getTableName() {
        return "radnik";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        if(idRequired){
            return new String[]{"idRadnik", "ime", "prezime", "kontaktTel", "adresa", "email", "KorisnickoIme", "Sifra"};
        }
        return new String[]{"ime", "prezime", "kontaktTel", "adresa", "email", "KorisnickoIme", "Sifra"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        if(idRequired){
            return new String[]{Long.toString(idRadnik), wrapInQuotes(ime), wrapInQuotes(prezime), wrapInQuotes(kontaktTel), wrapInQuotes(adresa), wrapInQuotes(email), wrapInQuotes(KorisnickoIme), wrapInQuotes(Sifra)};
        }
        return new String[]{wrapInQuotes(ime), wrapInQuotes(prezime), wrapInQuotes(kontaktTel), wrapInQuotes(adresa), wrapInQuotes(email), wrapInQuotes(KorisnickoIme), wrapInQuotes(Sifra)};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
       return new String[]{"idRadnik"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idRadnik)};
    }

    @Override
    public boolean validateObject() {
        if(ime == null || prezime == null || kontaktTel == null || adresa == null || email == null || KorisnickoIme == null || Sifra == null){
            return false;
        }
        if(ime.equals("") || prezime.equals("") || kontaktTel.equals("") || adresa.equals("") || email.equals("") ||  KorisnickoIme.equals("") || Sifra.equals("") ){
            return false;
        }
        if(idRadnik < 0){
            return false;
        }
        if (!email.matches("^[A-Za-z0-9.]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return false;
        }
        if(Sifra.matches("^[a-zA-Z0-9]+$") == false){
            return false;
        }
        if(kontaktTel.matches("^\\+?[0-9]+$") == false)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        List<DomainObject> lista = new ArrayList<>();
        while(rs.next()){
            long id = rs.getLong("idRadnik");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String kontaktTel = rs.getString("kontaktTel");
            String adresa = rs.getString("adresa");
            String email = rs.getString("email");
            String korisnickoIme = rs.getString("KorisnickoIme");
            String sifra = rs.getString("Sifra");
            lista.add(new Radnik(id, ime, prezime, kontaktTel, adresa, email, korisnickoIme, sifra));
        }
        return lista;
    }

    @Override
    public boolean equals(DomainObject domainObject) {
        if(domainObject == null || domainObject instanceof Radnik == false){
            return false;
        }
        Radnik rad = (Radnik) domainObject;
        if(rad.idRadnik != idRadnik){
            return false;
        }
        return true;
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        String res = ""; 
        if(getQueryFilter() != null && getQueryFilter().getObject() instanceof Termin){
            res+="JOIN terminradnika ON radnik.idRadnik = terminradnika.idRadnik JOIN termin "
                    + "ON termin.idTermin = terminranika.idTermin";
        }
        return res;
    }
    
     @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
}
