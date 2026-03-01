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

public class ServisnaOperacija extends DomainObject{

    private long idServOper;
    private String nazivServOper;
    private TipServOper tipServOper;
    private double cenaServOper;

    public ServisnaOperacija() {}

    public ServisnaOperacija(long idServOper, String nazivServOper, TipServOper tipServOper, double cenaServOper) {
        this.idServOper = idServOper;
        this.nazivServOper = nazivServOper;
        this.tipServOper = tipServOper;
        this.cenaServOper = cenaServOper;
    }

    public ServisnaOperacija(String nazivServOper, TipServOper tipServOper, double cenaServOper) {
        this.idServOper = 0L;
        this.nazivServOper = nazivServOper;
        this.tipServOper = tipServOper;
        this.cenaServOper = cenaServOper;
    }
    
    public ServisnaOperacija(ResultSet rs) throws SQLException{
        this.idServOper = rs.getLong("servisnaoperacija.idServOper");
        this.nazivServOper = rs.getString("servisnaoperacija.nazivServOper");
        this.tipServOper = TipServOper.valueOf(rs.getString("servisnaoperacija.tipServOper"));
        this.cenaServOper = rs.getDouble("servisnaoperacija.cenaServOper");
    }
    public QueryFilter generateQueryMask(boolean idServOper, boolean nazivServOper, boolean tipServOper, boolean cenaServOper){
        return new QueryFilter(this, new boolean[]{idServOper, nazivServOper, tipServOper, cenaServOper});
    }
    
    @Override
    public String getTableName() {
        return "servisnaoperacija";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        if(idRequired)
            return new String[]{"idServOper", "nazivServOper", "tipServOper", "cenaServOper"};
        return new String[]{"nazivServOper", "tipServOper", "cenaServOper"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        if(idRequired)
            return new String[]{Long.toString(idServOper),
                                wrapInQuotes(nazivServOper),
                                wrapInQuotes(tipServOper.toString()),
                                Double.toString(cenaServOper)};
        return new String[]{wrapInQuotes(nazivServOper),
                            wrapInQuotes(tipServOper.toString()),
                            Double.toString(cenaServOper)};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"idServOper"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idServOper)};
    }

    @Override
    public boolean validateObject() {
        if(nazivServOper == null || tipServOper == null)
            return false;
        if(idServOper < 0 || cenaServOper < 0)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
         if(rs == null)
            return null;
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            list.add(new ServisnaOperacija(rs));
        }
        return list;
    }

    @Override
    public boolean equals(DomainObject obj) {
        if(obj == null || obj instanceof ServisnaOperacija)
            return false;
        ServisnaOperacija servoper = (ServisnaOperacija) obj;
        return !(idServOper != servoper.idServOper || nazivServOper.equals(servoper.nazivServOper) == false || tipServOper.equals(servoper.tipServOper) == false
                || cenaServOper != servoper.cenaServOper);
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        return "";
    }
    
}
