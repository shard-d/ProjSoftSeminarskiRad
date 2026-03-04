
package logic;

import common.domain.DomainObject;
import common.domain.ServNalog;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.util.ArrayList;
import java.util.List;

public class SevrNalogKontroler extends Opsti {
    private static SevrNalogKontroler instance;

    public static SevrNalogKontroler getInstance() {
        if(instance == null){
            instance = new SevrNalogKontroler();
        }
        return instance;
    }
    
    public List<DomainObject> vratiListuSviSevrNalog(){
        sendRequest(new ServNalog(), ReqType.GET_ALL_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public List<DomainObject> vratiListuSevrNalog(DomainObject payload){
        sendRequest(payload, ReqType.GET_LIST_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public boolean kreirajSevrNalog(DomainObject payload){
        sendRequest(payload, ReqType.CREATE_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public boolean promeniSevrNalog(DomainObject payload){
        sendRequest(payload, ReqType.UPDATE_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public DomainObject pronadjiSevrNalog(DomainObject payload){
        sendRequest(payload, ReqType.FIND_SERVNALOG);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return null;
        return (DomainObject)res.getObject();
    }
}
