
package ctrl;

import common.domain.DomainObject;
import common.domain.ServNalog;
import db.BrokerDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import multithreading.ServerThread;
import operations.SOKreirajSlog;
import operations.SOPretraziSlogove;
import operations.SystemOperation;
import operations.modelvozila.SOKreirajModelVozila;
import operations.modelvozila.SOObrisiModelVozila;
import operations.modelvozila.SOPretraziModelVozila;
import operations.modelvozila.SOPromeniModelVozila;
import operations.modelvozila.SOVratiListuModelVozila;
import operations.modelvozila.SOVratiListuSviModelVozila;
import operations.radnik.SOKreirajRadnik;
import operations.radnik.SOObrisiRadnik;
import operations.radnik.SOPretraziRadnik;
import operations.radnik.SOPrijaviRadnik;
import operations.radnik.SOPromeniRadnik;
import operations.radnik.SOVratiListuRadnik;
import operations.radnik.SOVratiListuSviRadnik;
import operations.servnalog.SOKreirajServNalog;
import operations.servnalog.SOPretraziServNalog;
import operations.servnalog.SOPromeniServNalog;
import operations.servnalog.SOVratiListuServNalog;
import operations.servnalog.SOVratiListuSviServNalog;
import operations.servoper.SOKreirajServOper;
import operations.servoper.SOObrisiServOper;
import operations.servoper.SOPretraziServOper;
import operations.servoper.SOPromeniServOper;
import operations.servoper.SOVratiListuServOper;
import operations.servoper.SOVratiListuSviServOper;
import operations.termin.SOObrisiTermin;
import operations.termin.SOPretraziTermin;
import operations.termin.SOPromeniTermin;
import operations.termin.SOUbaciTermin;
import operations.termin.SOVratiListuSviTermin;
import operations.termin.SOVratiListuTermin;
import operations.vozilo.SOKreirajVozilo;
import operations.vozilo.SOObrisiVozilo;
import operations.vozilo.SOPretraziVozilo;
import operations.vozilo.SOPromeniVozilo;
import operations.vozilo.SOVratiListuSviVozilo;
import operations.vozilo.SOVratiListuVozilo;
import ui.ServerForm;


public class Controller {
    public static void main(String[] args) {
        new ServerForm().setVisible(true);
        
    }
    public static void connect(){
        System.out.println("Server started...");
        ServerThread serverThread;
        BrokerDB.connect();
        System.out.println("Database connection established"); 
        
        try {
            serverThread = new ServerThread(9001);
            serverThread.start();
        } catch (IOException ex) {
            System.getLogger(Controller.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
            
        
    }
    
    private static Controller instance;

    public Controller() {
    }
    
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
            return instance;
        }
        else{
            return instance;
        }
    }
    /*=============================SERV===NALOG=====================================*/
    public boolean kreirajServNalog(DomainObject domainObject) throws SQLException{
        SOKreirajServNalog so = new SOKreirajServNalog();
        boolean signal = false; // recimo da je ovo nuzno ako pukne procesuiranje transakcije
        signal = so.processTransaction(domainObject);
        return signal;
    }
    public DomainObject pretraziServNalog(DomainObject domainObject) throws SQLException{
        ServNalog res = null;
        SOPretraziServNalog so = new SOPretraziServNalog();
        if(so.processTransaction(domainObject) == false){
            return res;
        }
        DomainObject obj = so.getResultObject();
        res = (ServNalog)obj;
        return res;
    }
    public boolean promeniServNalog(DomainObject domainObject) throws SQLException{
        SOPromeniServNalog so = new SOPromeniServNalog();
        boolean signal = false;
        signal = so.processTransaction(domainObject);
        return signal;
    }
    public List<DomainObject> vratiListuServNalog(DomainObject domainObject) throws SQLException{
        SOVratiListuServNalog so = new SOVratiListuServNalog();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    public List<DomainObject> vratiListuSviServNalog(DomainObject domainObject) throws SQLException{
        SOVratiListuSviServNalog so = new SOVratiListuSviServNalog();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    /*==================================VOZILO================================*/
    public boolean kreirajVozilo(DomainObject domainObject) throws SQLException{
        SOKreirajVozilo so = new SOKreirajVozilo();
        return so.processTransaction(domainObject);
    }
    public boolean obrisiVozilo(DomainObject domainObject) throws SQLException{
        SOObrisiVozilo so = new SOObrisiVozilo();
        return so.processTransaction(domainObject);
    }
    public boolean promeniVozilo(DomainObject domainObject) throws SQLException{
        SOPromeniVozilo so = new SOPromeniVozilo();
        return so.processTransaction(domainObject);
    }
    public DomainObject pretraziVozilo(DomainObject domainObject) throws SQLException{
        SOPretraziVozilo so = new SOPretraziVozilo();
        if(so.processTransaction(domainObject))
            return so.getResultObject();
        return null;
    }
    public List<DomainObject> vratiListuVozilo(DomainObject domainObject) throws SQLException{
        SOVratiListuVozilo so = new SOVratiListuVozilo();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    public List<DomainObject> vratiListuSviVozilo(DomainObject domainObject) throws SQLException{
        SOVratiListuSviVozilo so = new SOVratiListuSviVozilo();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    /*===============================Servisna====operacija===================================*/
    public boolean kreirajServOper(DomainObject domainObject) throws SQLException{
        SOKreirajServOper so = new SOKreirajServOper();
        return so.processTransaction(domainObject);
    }
    public boolean obrisiServOper(DomainObject domainObject) throws SQLException{
        SOObrisiServOper so = new SOObrisiServOper();
        return so.processTransaction(domainObject);
    }
    public DomainObject pretraziServOper(DomainObject domainObject) throws SQLException{
        SOPretraziServOper so = new SOPretraziServOper();
        if(so.processTransaction(domainObject))
            return so.getResultObject();
        return null;
    }
    public boolean promeniServOper(DomainObject domainObject) throws  SQLException{
        SOPromeniServOper so = new SOPromeniServOper();
        return so.processTransaction(domainObject);
    }
    public List<DomainObject> vratiListuServOper(DomainObject domainObject) throws SQLException{
        SOVratiListuServOper so = new SOVratiListuServOper();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    public List<DomainObject> vratiListuSviServOper(DomainObject domainObject) throws SQLException{
        SOVratiListuSviServOper so = new SOVratiListuSviServOper();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    /*===============================MODEL===VOZILA===================================*/
    public boolean kreirajModelVozila(DomainObject domainObject) throws SQLException{
        SOKreirajModelVozila so = new SOKreirajModelVozila();
        return so.processTransaction(domainObject);
    }
    public boolean obrisiModelVozila(DomainObject domainObject) throws SQLException{
        SOObrisiModelVozila so = new SOObrisiModelVozila();
        return so.processTransaction(domainObject);
    }
    public DomainObject pretraziModelVozila(DomainObject domainObject) throws SQLException{
        SOPretraziModelVozila so = new SOPretraziModelVozila();
        if(so.processTransaction(domainObject))
            return so.getResultObject();
        return null;
    }
    public boolean promeniModelVozila(DomainObject domainObject) throws SQLException{
        SOPromeniModelVozila so = new SOPromeniModelVozila();
        return so.processTransaction(domainObject);
    }
    public List<DomainObject> vratiListuModelVozila(DomainObject domainObject) throws SQLException{
        SOVratiListuModelVozila so = new SOVratiListuModelVozila();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    public List<DomainObject> vratiListuSviModelVozila(DomainObject domainObject) throws SQLException{
        SOVratiListuSviModelVozila so = new SOVratiListuSviModelVozila();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    /*===============================TERMIN===================================*/
    public boolean obrisiTermin(DomainObject domainObject) throws SQLException{
        SOObrisiTermin so = new SOObrisiTermin();
        return so.processTransaction(domainObject);
    }
    public DomainObject pretraziTermin(DomainObject domainObject) throws SQLException{
        SOPretraziTermin so = new SOPretraziTermin();
        if(so.processTransaction(domainObject))
            return so.getResultObject();
        return null;
    }
    public boolean promeniTermin(DomainObject domainObject) throws SQLException{
        SOPromeniTermin so = new SOPromeniTermin();
        return so.processTransaction(domainObject);
    }
    public boolean ubaciTermin(DomainObject domainObject) throws SQLException{
        SOUbaciTermin so = new SOUbaciTermin();
        return so.processTransaction(domainObject);
    }    
    public List<DomainObject> vratiListuSviTermin(DomainObject domainObject) throws SQLException{
        SOVratiListuSviTermin so = new SOVratiListuSviTermin();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    public List<DomainObject> vratiListuTermin(DomainObject domainObject) throws SQLException{
        SOVratiListuTermin so = new SOVratiListuTermin();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    /*===============================RADNIK===================================*/
    public boolean kreirajRadnik(DomainObject domainObject) throws SQLException{
        SOKreirajRadnik so = new SOKreirajRadnik();
        return so.processTransaction(domainObject);
    }
    public boolean obrisiRadnik(DomainObject domainObject) throws SQLException{
        SOObrisiRadnik so = new SOObrisiRadnik();
        return so.processTransaction(domainObject);
    }
    public DomainObject pretraziRadnik(DomainObject domainObject) throws SQLException{
        SOPretraziRadnik so = new SOPretraziRadnik();
        if(so.processTransaction(domainObject))
            return so.getResultObject();
        return null;
    }
    public boolean prijaviRadnik(DomainObject domainObject) throws SQLException{
        SOPrijaviRadnik so = new SOPrijaviRadnik();
        return so.processTransaction(domainObject);
    }
    public boolean promeniRadnik(DomainObject domainObject) throws SQLException{
        SOPromeniRadnik so = new SOPromeniRadnik();
        return so.processTransaction(domainObject);
    }
    public List<DomainObject> vratiListuSviRadnik(DomainObject domainObject) throws SQLException{
        SOVratiListuSviRadnik so = new SOVratiListuSviRadnik();
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    public List<DomainObject> vratiListuRadnik(DomainObject domainObject) throws SQLException{
        SOVratiListuRadnik so = new SOVratiListuRadnik();
        //System.out.println("QUERY MASK EXISTS? " + domainObject.isQueryMaskSet());
        if(so.processTransaction(domainObject))
            return so.getResultList();
        return null;
    }
    
    //nisam sigurna da cu ove koristiti ali neka stoje ovde za sad
    public boolean kreirajSlog(DomainObject domainObject) throws SQLException{
        SystemOperation sistemskaOperacija = new SOKreirajSlog();
        boolean signal = false;
        signal = sistemskaOperacija.processTransaction(domainObject);
        return signal;
    }
    public List<DomainObject> pretraziSlogove(DomainObject domainObject) throws SQLException{
        SOPretraziSlogove so = new SOPretraziSlogove();
        boolean signal = false;
        signal = so.processTransaction(domainObject);
        if (signal == true){
            return so.getResultList();
        }
        return null;
    }
    
}
