/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public StavkaServisa(ResultSet rs) throws SQLException{
        this.idServNalog = rs.getLong("stavkaservisa.idServNalog");
        this.rb = rs.getInt("stavkaservisa.rb");
        this.cenaStavke = rs.getDouble("stavkaservisa.cenaStavke");
        this.kolicina = rs.getInt("stavkaservisa.kolicina");
        this.servoper = new ServisnaOperacija(rs);
    }
    
    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
