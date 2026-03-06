/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.domain;
import common.util.QueryFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

public class ServNalog extends DomainObject{
    
    private long idServNalog;
    private LocalDate datum;
    private double ukupnaCena;
    private NacinPlacanja nacinPlacanja;
    private Radnik radnik;
    private Vozilo vozilo;
    private List<DomainObject> stavkaServislista;
    
    public ServNalog() {}

    public ServNalog(long idServNalog, LocalDate datum, double ukupnaCena, NacinPlacanja tipPlacanja, Radnik radnik, Vozilo vozilo, List<DomainObject> stavkaServislista ) {
        this.idServNalog = idServNalog;
        this.datum = datum;
        this.ukupnaCena = ukupnaCena;
        this.nacinPlacanja = tipPlacanja;
        this.radnik = radnik;
        this.vozilo = vozilo;
        this.stavkaServislista = stavkaServislista;
    }

    public ServNalog(LocalDate datum, double ukupnaCena, NacinPlacanja tipPlacanja, Radnik radnik, Vozilo vozilo, List<DomainObject> stavkaServislista) {
        this.idServNalog = 0L;
        this.datum = datum;
        this.ukupnaCena = ukupnaCena;
        this.nacinPlacanja = tipPlacanja;
        this.radnik = radnik;
        this.vozilo = vozilo;
        this.stavkaServislista = stavkaServislista;
    }
    
    public ServNalog(ResultSet rs) throws SQLException{
        this.idServNalog = rs.getLong("servnalog.idServNalog");
        this.datum = rs.getDate("servnalog.datum").toLocalDate();
        this.ukupnaCena = rs.getDouble("servnalog.ukupnaCena");
        this.radnik = new Radnik(rs);
        this.vozilo = new Vozilo(rs);
        this.nacinPlacanja = NacinPlacanja.valueOf(rs.getString("servnalog.nacinPlacanja"));
        int brojKolona = 6 + radnik.getSQLColumnNames(true).length + vozilo.getSQLColumnNames(true).length 
                + vozilo.getModelVozila().getSQLColumnNames(true).length;
        if(rs.getMetaData().getColumnCount() > brojKolona){
            stavkaServislista = new ArrayList<>();
            do {
                //System.out.println("LOOP iznajmljivanje: "+brojKolona);
                if(rs.getDate("stavkaiznajmljivanja.datumIznajmljivanja") == null){
                    break;
                }
                stavkaServislista.add(new StavkaServisa(rs));
                if(this.idServNalog != rs.getLong("servnalog.idServNalog")){
                    rs.previous();
                    break;
                }
            } while(rs.next() != false);
        }
    }

    public long getIdServNalog() {
        return idServNalog;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public List<DomainObject> getStavkaServislista() {
        return stavkaServislista;
    }

    public void setIdServNalog(long idServNalog) {
        this.idServNalog = idServNalog;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public void setStavkaServislista(List<DomainObject> stavkaServislista) {
        this.stavkaServislista = stavkaServislista;
    }
    
    public QueryFilter generateQueryMask(boolean idServNalog, boolean datum, boolean ukupnaCena, boolean nacinPlacanja, boolean idRadnik, boolean idVozilo) {
        return new QueryFilter(this, new boolean[]{idServNalog, datum, ukupnaCena, nacinPlacanja, idRadnik, idVozilo});
    }
    
    @Override
    public String getTableName() {
        return "servnalog";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        if(idRequired)
            return new String[]{"idServNalog", "datum", "ukupnaCena", "nacinPlacanja", "idRadnik", "idVozilo"};
        return new String[]{"datum", "ukupnaCena", "nacinPlacanja", "idRadnik", "idVozilo"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        if(idRequired)
            return new String[]{Long.toString(idServNalog), wrapInQuotes(Date.valueOf(datum).toString()), Double.toString(ukupnaCena), wrapInQuotes(nacinPlacanja.toString()), Long.toString(radnik.getIdRadnik()), Long.toString(vozilo.getIdVozilo())};
        return new String[]{wrapInQuotes(Date.valueOf(datum).toString()), Double.toString(ukupnaCena), wrapInQuotes(nacinPlacanja.toString()), Long.toString(radnik.getIdRadnik()), Long.toString(vozilo.getIdVozilo())};

    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"idServNalog"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idServNalog)};
    }

    @Override
    public boolean validateObject() {
        if(datum == null || radnik == null || vozilo == null || nacinPlacanja == null){
            return false;
        }
        if(idServNalog < 0 || radnik.validateObject() == false || vozilo.validateObject() == false){
            return false;
        }
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        if(rs == null)
            return null;
        List<DomainObject> list = new ArrayList();
        ServNalog currentObject;
        if(rs.next() == false){
            System.out.println("WARNING: empty result set");
            return list;
        }
        while(rs.isAfterLast() == false){
            list.add(new ServNalog(rs));
            rs.next();
        }
        return list;
    }

    @Override
    public boolean equals(DomainObject obj) {
        if(obj == null || obj instanceof ServNalog == false)
            return false;
        ServNalog servnal = (ServNalog)obj;
        return !(this.idServNalog != servnal.idServNalog || this.datum.equals(servnal.datum) == false
                || this.ukupnaCena != servnal.ukupnaCena || this.radnik.equals(servnal.radnik) == false || this.vozilo.equals(servnal.vozilo) == false);
    
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        if(joinList == false)
            return "JOIN radnik ON servnalog.idRadnik = radnik.idRadnik"
                   + "JOIN vozilo ON servnalog.idVozilo = vozilo.idVozilo"
                   + "JOIN modelvozila ON vozilo.idModelVozila = modelvozila.idModelVozila";
        return "JOIN radnik ON servnalog.idRadnik = radnik.idRadnik"
                + "JOIN vozilo ON servnalog.idVozilo = vozilo.idVozilo"
                + "JOIN modelvozila ON vozilo.idModelVozila = modelvozila.idModelVozila"
                + "LEFT JOIN stavkaservisa ON servnalog.idServNalog = stavkaservisa.idServNalog"
                + "LEFT JOIN servisnaoperacija ON servisnaoperacija.idServOper = stavkaservisa.idServOper";
    }
    
}
