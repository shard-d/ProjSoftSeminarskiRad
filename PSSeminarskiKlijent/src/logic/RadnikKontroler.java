
package logic;

import common.domain.DomainObject;
import common.domain.Radnik;
import common.networking.ReqPacket;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import common.util.QueryFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danic
 */
public class RadnikKontroler extends OpstiKontroler{
    private static RadnikKontroler instance = null;
    
     public static RadnikKontroler getInstance(){
        if(instance != null)
            return instance;
        return new RadnikKontroler();
    }
     
    public boolean prijaviRadnik(DomainObject obj){
        try {
            socketOutput.writeObject(new ReqPacket(ReqType.LOGIN_RADNIK, obj));
            RespPacket res = (RespPacket)socketInput.readObject(); 
            //System.out.println(res.getType().toString());
            if(res.getType().equals(RespType.FAILIURE))
                return false;
            System.out.println("vraca true");
            return true;
        } catch (Exception ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }
    
    public boolean kreirajRadnik(DomainObject obj){
        try {
            socketOutput.writeObject(new ReqPacket(ReqType.CREATE_RADNIK, obj));
            RespPacket res = (RespPacket) socketInput.readObject();
            if(res.getType().equals(RespType.SUCCESS))
                return true;
            return false;
        } catch (Exception ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }
    
    public List<DomainObject> vratiListuSviRadnik(){
        try {
            socketOutput.writeObject(new ReqPacket(ReqType.GET_ALL_RADNIK, new Radnik()));
            RespPacket res = (RespPacket)socketInput.readObject();
            if(res.getObject() == null)
                return new ArrayList<DomainObject>();
            return (List<DomainObject>)res.getObject();
        } catch (IOException ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    public List<DomainObject> vratiListuRadnik(DomainObject obj){
        try {
            //System.out.println("QUERY MASK EXISTS? " + obj.isQueryMaskSet());
            socketOutput.writeObject(new ReqPacket(ReqType.GET_LIST_RADNIK, obj));
            RespPacket res = (RespPacket)socketInput.readObject();
            if(res.getObject()== null)
                return new ArrayList<DomainObject>();
            return (List<DomainObject>)res.getObject();
            
        } catch (IOException ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    public DomainObject pretraziRadnik(DomainObject obj){
        try {
            socketOutput.writeObject(new ReqPacket(ReqType.FIND_RADNIK, obj));
            RespPacket res = (RespPacket)socketInput.readObject();
            if(res.getType().equals(RespType.FAILIURE))
                return null;
            return (DomainObject)res.getObject();
        } catch (IOException ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(RadnikKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    public boolean obrisiRadnik(DomainObject obj){
        obj.setQueryFilter(new QueryFilter(obj));
        sendRequest(obj, ReqType.DELETE_RADNIK);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.FAILIURE)){
            return false;
        }
        return true;
    }
    
    public boolean promeniRadnik(DomainObject obj){
        sendRequest(obj, ReqType.UPDATE_RADNIK);
        RespPacket res = recieveResponse();
        
        if(res.getType().equals(RespType.FAILIURE)){
            return false;
        }
        return true;
    }
}
