/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import common.domain.DomainObject;
import common.domain.Radnik;
import common.domain.Uloga;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author danic
 */
public class BrokerDB {
    static Connection connection = null;
    private static Statement statement;
    private static ResultSet rs;
    
    public static void connect(){
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("src/resources/config.properties")) {
            if (in == null) {
                System.out.println("config.properties nije pronadjen");
                return;
            }

            props.load(in);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            connection.setAutoCommit(false);

        } catch (Exception ex) {
            Logger.getLogger(BrokerDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static synchronized boolean commitTransaction(){
        try {
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BrokerDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static synchronized boolean rollbackTransaction(){
        try {
            connection.rollback();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BrokerDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static synchronized boolean upisiSlog(DomainObject domainObject) throws SQLException{
        // INSERT INTO TABLE01 (COLUMN1, COLUMN2) VALUES (VALUE1,VALUE2)
        String columnsString = "";
        String valuesString = "";
        String[] columnNamesArray = domainObject.getSQLColumnNames(false); 
        String[] valueStringsArray = domainObject.getSQLColumnValues(false);
        for(int i = 0 ; i < columnNamesArray.length ; i++){
            if(i != 0){
                columnsString += ",";
                valuesString += ",";
            }
            columnsString += columnNamesArray[i];
            if(valueStringsArray[i] != null){
                
                valuesString += valueStringsArray[i];
                continue;
            }
            valuesString += "NULL"; // resava problem kolona koje mogu biti NULL
            
        }
        String query = "INSERT INTO "+ domainObject.getTableName()+ " ("+ columnsString + ") VALUES (" + valuesString +") ";
        boolean signal = false;
        System.out.println(query); //DEBUG
        if(statement.executeUpdate(query)>0){
                signal = true;
        }
        return signal;
    }
    
    public static synchronized List<DomainObject> pronadjiSlogove(DomainObject domainObject) throws SQLException{
        List<DomainObject> lista;
        if(domainObject.isQueryFilterSet()== false){
            System.out.println("DEBUG missing query mask");
            return null;
        }
        String query = "SELECT * FROM "+ domainObject.getTableName()+" "+domainObject.getSQLJoinClause(true)+" "+domainObject.getQueryFilter().createSQLWhereClause();
        System.out.println("DEBUG: "+ query);
        BrokerDB.rs = statement.executeQuery(query);
        lista = domainObject.generateList(rs);
        return lista;
    }
    
    public static synchronized boolean obrisiSlog(DomainObject domainObject) throws SQLException{
        String query = "DELETE FROM "+domainObject.getTableName() + " " + domainObject.getQueryFilter().createSQLWhereClause();
        System.out.println("DEBUG: "+query);
        if(statement.executeUpdate(query)==1) // rows affected
            return true;
        System.out.println("ROWS AFFECTED CHECK");
        return false;
    }
    
    public static synchronized DomainObject pronadjiSlog(DomainObject domainObject) throws SQLException{
        String query = "SELECT * FROM "+domainObject.getTableName();
        query +=" "+domainObject.getSQLJoinClause(true)+" ";
        query += " WHERE ";
        String[] columns = domainObject.getSQLPrimaryKeyColumnNames(), values = domainObject.getSQLPrimaryKeyColumnValues();
        for(int i = 0; i < columns.length ; i++){
            if(i != 0 )
                query += " AND ";
            query += domainObject.getTableName()+"."+columns[i]+"="+values[i];
        }
        System.out.println("DEBUG: "+query);
        rs = statement.executeQuery(query);
        if(rs.next() == false){
            return null;
        }
        rs.previous();
        return domainObject.generateList(rs).get(0);
    }
    
    public static synchronized  boolean azurirajSlog(DomainObject domainObject) throws SQLException{
        String query = "UPDATE " + domainObject.getTableName() + " ";
        query += domainObject.getSQLSetClause() + " ";
        String[] columnsPK = domainObject.getSQLPrimaryKeyColumnNames();
        String[] valuesPK = domainObject.getSQLPrimaryKeyColumnValues();
        query += "WHERE ";
        for(int i = 0; i < columnsPK.length ; i++){
            if(i != 0){
                query += " AND ";
            }
            query += columnsPK[i]+"="+valuesPK[i];
        }
        System.out.println("DEBUG: "+ query);
        statement.executeUpdate(query);
        return true;
    }
    
    public static synchronized List<DomainObject> vratiSveSlogove(DomainObject domainObject) throws SQLException{
        List<DomainObject> lista;
        String query = "SELECT * FROM " + domainObject.getTableName() + " " + domainObject.getSQLJoinClause(false);
//        String query = "SELECT * FROM psseminarski." + domainObject.getTableName();
        System.out.println("DEBUG: "+query);
        rs = statement.executeQuery(query);
        lista = domainObject.generateList(rs);
        return lista;
    }
    
    public static synchronized boolean prijavaKorisnika(DomainObject domainObject) throws SQLException{
        Radnik radnik = (Radnik) domainObject;
        String usernameInput = radnik.getKorisnickoIme();
        String passwordInput = radnik.getSifra();
        String query = "SELECT * FROM "+radnik.getTableName() + " WHERE KorisnickoIme = '"+usernameInput+"'";
        System.out.println("DEBUG: "+query);
        rs = statement.executeQuery(query);
        if(rs.next() == false || rs.getString("KorisnickoIme").equals(usernameInput) == false || rs.getString("Sifra").equals(passwordInput) == false){
            return false;
        }
        System.out.println("broker vraca true");
        return true;
    }
}
