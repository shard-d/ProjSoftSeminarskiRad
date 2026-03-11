
package logic;

import common.domain.DomainObject;
import common.domain.ServNalog;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.util.ArrayList;
import java.util.List;

public class ServNalogKontroler extends OpstiKontroler {
    private static ServNalogKontroler instance;

    public static ServNalogKontroler getInstance() {
        if(instance == null){
            instance = new ServNalogKontroler();
        }
        return instance;
    }
    
    public List<DomainObject> vratiListuSviServNalog(){
        sendRequest(new ServNalog(), ReqType.GET_ALL_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public List<DomainObject> vratiListuServNalog(DomainObject payload){
        sendRequest(payload, ReqType.GET_LIST_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public boolean kreirajServNalog(DomainObject payload){
        sendRequest(payload, ReqType.CREATE_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public boolean promeniServNalog(DomainObject payload){
        sendRequest(payload, ReqType.UPDATE_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public DomainObject pronadjiServNalog(DomainObject payload){
        sendRequest(payload, ReqType.FIND_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return null;
        return (DomainObject)res.getObject();
    }
}
