/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import common.domain.DomainObject;
import common.domain.Termin;
import common.networking.ReqPacket;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danic
 */
public class TerminKontroler extends OpstiKontroler{
    private static TerminKontroler instance = null;
    
    public static TerminKontroler getInstance(){
        if(instance != null)
            return instance;
        return new TerminKontroler();
    }
    public List<DomainObject> vratiListuSviTermin(){
        try {
            socketOutput.writeObject(new ReqPacket(ReqType.GET_ALL_TERMIN, new Termin()));
            RespPacket res = (RespPacket)socketInput.readObject();
            if(res.getObject()== null)
                return new ArrayList<DomainObject>();
            return (List<DomainObject>)res.getObject();
        } catch (IOException ex) {
            System.getLogger(TerminKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(TerminKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    public List<DomainObject> vratiListuTermin(DomainObject domainObject){
        sendRequest(domainObject, ReqType.GET_LIST_TERMIN);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public DomainObject pronadjiTermin(DomainObject domainObject){
        sendRequest(domainObject, ReqType.FIND_TERMIN);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS)){
            return (DomainObject)res.getObject();
        }
        return null;
    }
    
    public boolean promeniTermin(DomainObject domainObject){
        sendRequest(domainObject, ReqType.UPATE_TERMIN);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS)){
            return true;
        }
        return false;
    }
    
    public boolean ubaciTermin(DomainObject domainObject){
        sendRequest(domainObject, ReqType.INSERT_TERMIN);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS)){
            return true;
        }
        return false;
    }
    
    public boolean obrisiTermin(DomainObject domainObject){
        sendRequest(domainObject, ReqType.DELETE_TERMIN);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS)){
            return true;
        }
        return false;
    }
}
