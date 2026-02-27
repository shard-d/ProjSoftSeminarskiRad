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
    private int idRadnik;
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

    public Radnik(int idRadnik, String ime, String prezime, String kontaktTel, String adresa, String email, String KorisnickoIme, String Sifra) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTel = kontaktTel;
        this.adresa = adresa;
        this.email = email;
        this.KorisnickoIme = KorisnickoIme;
        this.Sifra = Sifra;
    }

    public int getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(int idRadnik) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validateObject() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        List<DomainObject> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idRadnik");
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
