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
public class ModelVozila extends DomainObject{

    private long idModelVozila;
    private String nazivModela;
    private String nazivMarke;
    private int godiste;

    public ModelVozila() {}
 
    public long getIdModelVozila() {
        return idModelVozila;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public String getNazivMarke() {
        return nazivMarke;
    }

    public int getGodiste() {
        return godiste;
    }

    

    public ModelVozila(long idModelVozila, String nazivModela, String nazivMarke, int godiste) {
        this.idModelVozila = idModelVozila;
        this.nazivModela = nazivModela;
        this.nazivMarke = nazivMarke;
        this.godiste = godiste;
    }

    public ModelVozila(String nazivModela, String nazivMarke, int godiste) {
        this.idModelVozila = 0L;
        this.nazivModela = nazivModela;
        this.nazivMarke = nazivMarke;
        this.godiste = godiste;
    }
    
    public ModelVozila(ResultSet rs) throws SQLException{
        this.idModelVozila = rs.getLong("modelvozila.idModelVozila");
        this.nazivModela = rs.getString("modelvozila.nazivModela");
        this.nazivMarke = rs.getString("modelvozila.nazivMarke");
        this.godiste = rs.getInt("modelvozila.godiste");
    }

    public QueryFilter generateQueryMask(boolean idModelVozila, boolean nazivModela, boolean nazivMarke, boolean godiste){
        return new QueryFilter(this, new boolean[]{idModelVozila, nazivModela, nazivMarke, godiste});
    }
    
    @Override
    public String getTableName() {
        return "modelvozila";
    }

    @Override
    public String[] getSQLColumnNames(boolean idRequired) {
        if(idRequired)
            return new String[]{"idModelVozila", "nazivModela", "nazivMarke", "godiste"};
        return new String[]{"nazivModela", "nazivMarke", "godiste"};
    }

    @Override
    public String[] getSQLColumnValues(boolean idRequired) {
        if(idRequired){
            return new String[]{Long.toString(idModelVozila), wrapInQuotes(nazivModela), wrapInQuotes(nazivMarke),Integer.toString(godiste)};
        }
        return new String[]{wrapInQuotes(nazivModela), wrapInQuotes(nazivMarke),Integer.toString(godiste)};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnNames() {
        return new String[]{"idModelVozila"};
    }

    @Override
    public String[] getSQLPrimaryKeyColumnValues() {
        return new String[]{Long.toString(idModelVozila)};
    }

    @Override
    public boolean validateObject() {
        if(nazivModela == null || nazivModela.equals("") || nazivMarke == null || nazivMarke.equals(""))
            return false;
        if(idModelVozila < 0 || godiste <= 1970)
            return false;
        return true;
    }

    @Override
    public List<DomainObject> generateList(ResultSet rs) throws SQLException {
        if(rs == null)
            return null;
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            list.add(new ModelVozila(rs.getLong("idModelVozila"), 
                    rs.getString("nazivModela"), 
                    rs.getString("nazivMarke"), 
                    rs.getInt("godiste")));
        }
        return list;
    }

    @Override
    public boolean equals(DomainObject obj) {
        if(obj == null || obj instanceof ModelVozila)
            return false;
        ModelVozila model = (ModelVozila)obj;
        return !(idModelVozila != model.idModelVozila || nazivModela.equals(model.nazivModela) == false || 
                nazivMarke.equals(model.nazivMarke) == false|| godiste != model.godiste);
    }

    @Override
    public String getSQLJoinClause(boolean joinList) {
        return "";
    }

    @Override
    public String toString() {
        return nazivModela +" "+ nazivMarke + " " + godiste;
    }
}
