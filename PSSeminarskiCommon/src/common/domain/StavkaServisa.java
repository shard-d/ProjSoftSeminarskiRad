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
public class StavkaServisa extends DomainObject{

    private long idServNalog;
    private int rb;
    private double cenaStavke;
    private int kolicina;
    private ServisnaOperacija servoper;

    public StavkaServisa() {
    }

    public StavkaServisa(long idServNalog, int rb, double cenaStavke, int kolicina, ServisnaOperacija servoper) {
        this.idServNalog = idServNalog;
        this.rb = rb;
        this.cenaStavke = cenaStavke;
        this.kolicina = kolicina;
        this.servoper = servoper;
    }

    public StavkaServisa(int rb, double cenaStavke, int kolicina, ServisnaOperacija servoper) {
        this.rb = rb;
        this.cenaStavke = cenaStavke;
        this.kolicina = kolicina;
        this.servoper = servoper;
    }
    
    public StavkaServisa(long idServNalog, double cenaStavke, int kolicina, ServisnaOperacija servoper) {
        this.idServNalog = idServNalog;
        this.cenaStavke = cenaStavke;
        this.kolicina = kolicina;
        this.servoper = servoper;
    }
    
    public StavkaServisa(ResultSet rs) throws SQLException{
        this.idServNalog = rs.getLong("stavkaservisa.idServNalog");
        this.rb = rs.getInt("stavkaservisa.rb");
        this.cenaStavke = rs.getDouble("stavkaservisa.cenaStavke");
        this.kolicina = rs.getInt("stavkaservisa.kolicina");
        this.servoper = new ServisnaOperacija(rs);
    }

    public QueryFilter generateQueryMask(boolean idServNalog, boolean rb, boolean cenaStavke, boolean kolicina, boolean idServOper){
        return new QueryFilter(this, new boolean[]{idServNalog, rb, cenaStavke, kolicina, idServOper});
    }
    
    public long getIdServNalog() {
        return idServNalog;
    }

    public int getRb() {
        return rb;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public ServisnaOperacija getServoper() {
        return servoper;
    }
    
    @Override
    public String getTableName() {
        return "stavkaservisa";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        return new String[]{"idServNalog", "rb", "cenaStavke", "kolicina", "idServOper"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        String idServOper= "";
        if(servoper != null){
            idServOper = Long.toString(servoper.getIdServOper());
        }
        return new String[]{Long.toString(idServNalog), Integer.toString(rb), Double.toString(cenaStavke), Integer.toString(kolicina), idServOper};
    
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"idServNalog", "rb"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idServNalog), Integer.toString(rb)};
    }

    @Override
    public boolean validateObject() {
        if(servoper == null)
            return false;
        if(idServNalog < 0 || servoper.getIdServOper() < 0 || cenaStavke < 0 || rb<0)
            return false;
        if(servoper.validateObject() == false)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            list.add(new StavkaServisa(rs));
        }
        return list;
    }

    @Override
    public boolean equals(DomainObject obj) {
        if(obj == null || obj instanceof StavkaServisa)
            return false;
        StavkaServisa sta = (StavkaServisa) obj;
        return !(idServNalog != sta.idServNalog || rb != sta.rb || cenaStavke != sta.cenaStavke || servoper.equals(sta.servoper) == false);
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        return "JOIN servisnaoperacija on stavkaservisa.idServOper = servisnaoperacija.idServOper";
    }
    
}
