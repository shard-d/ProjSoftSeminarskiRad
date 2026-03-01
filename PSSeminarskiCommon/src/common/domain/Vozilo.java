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

public class Vozilo extends DomainObject{

    private long idVozilo;
    private String nazivVozila;
    private TipVozila tipVozila;
    private ModelVozila modelVozila;

    public Vozilo() {
    }

    public Vozilo(long idVozilo, String nazivVozila, TipVozila tipVozila, ModelVozila modelVozila) {
        this.idVozilo = idVozilo;
        this.nazivVozila = nazivVozila;
        this.tipVozila = tipVozila;
        this.modelVozila = modelVozila;
    }

    public Vozilo(String nazivVozila, TipVozila tipVozila, ModelVozila modelVozila) {
        this.idVozilo = 0L;
        this.nazivVozila = nazivVozila;
        this.tipVozila = tipVozila;
        this.modelVozila = modelVozila;
    }

    public long getIdVozilo() {
        return idVozilo;
    }

    public String getNazivVozila() {
        return nazivVozila;
    }

    public TipVozila getTipVozila() {
        return tipVozila;
    }

    public ModelVozila getModelVozila() {
        return modelVozila;
    }

    public QueryFilter generateQueryMask(boolean idVozilo, boolean nazivVozila, boolean tipVozila, boolean modelVozila){
        return new QueryFilter(this, new boolean[]{idVozilo, nazivVozila, tipVozila, modelVozila});
    }
    
    public Vozilo(ResultSet rs) throws SQLException{
        this.idVozilo = rs.getLong("vozilo.idVozilo");
        this.nazivVozila = rs.getString("vozilo.nazivVozila");
        this.tipVozila = TipVozila.valueOf(rs.getString("vozilo.tipVozila"));
        this.modelVozila = new ModelVozila(rs);
    }
    
    @Override
    public String getTableName() {
        return "vozilo";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        if(idRequired)
            return new String[]{"idVozilo", "nazivVozila", "tipVozila", "idModelVozila"};
        return new String[]{"nazivVozila", "tipVozila", "idModelVozila"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        if(idRequired)
            return new String[]{Long.toString(idVozilo), wrapInQuotes(nazivVozila), wrapInQuotes(tipVozila.toString()), Long.toString(modelVozila.getIdModelVozila())};
        return new String[]{wrapInQuotes(nazivVozila), wrapInQuotes(tipVozila.toString()), Long.toString(modelVozila.getIdModelVozila())};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"idVozilo"}; 
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idVozilo)}; 
    }

    @Override
    public boolean validateObject() {
        if(nazivVozila == null || tipVozila == null || modelVozila == null)
            return false;
        if(idVozilo < 0)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        if(rs == null)
            return null;
        List<DomainObject> resultList = new ArrayList<>();
        while(rs.next()){
            resultList.add(new Vozilo(rs));
        }
        return resultList;
    }

    @Override
    public boolean equals(DomainObject obj) {
        if(obj == null || obj instanceof Vozilo == false)
            return false;
        Vozilo vozilo = (Vozilo) obj;
        boolean signal = !(idVozilo != vozilo.idVozilo || nazivVozila.equals(vozilo.nazivVozila) == false || tipVozila.equals(vozilo.tipVozila) == false ||modelVozila.equals(vozilo.modelVozila) == false); 
        return signal;
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        return "JOIN modelvozila ON vozilo.idModelVozila = modelvozila.idModelVozila";
    }
    
}
