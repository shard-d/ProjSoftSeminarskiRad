
package logic;

import common.domain.DomainObject;
import common.domain.ServisnaOperacija;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.util.ArrayList;
import java.util.List;

public class ServOperKontroler extends OpstiKontroler{
    private static ServOperKontroler instance;

    public static ServOperKontroler getInstance() {
        if(instance == null)
            instance = new ServOperKontroler();
        return instance; 
    }
    
    public List<DomainObject> vratiListuSviServOper(){
        sendRequest(new ServisnaOperacija(), ReqType.GET_ALL_SERVOPER);
        RespPacket res =  recieveResponse();
        if(res.getObject() == null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public List<DomainObject> vratiListuServOper(DomainObject payload){
        sendRequest(payload, ReqType.GET_LIST_SERVOPER);
        RespPacket res =  recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public DomainObject pronadjiServOper(DomainObject payload){
        sendRequest(payload, ReqType.FIND_SERVOPER);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return null;
        return (DomainObject)res.getObject();
    }
    
    public boolean krierajServOper(DomainObject payload){
        sendRequest(payload, ReqType.CREATE_SERVOPER);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public boolean promeniServOper(DomainObject payload){
        sendRequest(payload, ReqType.UPDATE_SERVOPER);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public boolean obrisiServOper(DomainObject payload){
        sendRequest(payload, ReqType.DELETE_SERVOPER);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
}
