
package logic;

import common.domain.DomainObject;
import common.domain.Vozilo;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.util.ArrayList;
import java.util.List;

public class VoziloKontroler extends Opsti{
     private static VoziloKontroler instance;

    public static VoziloKontroler getInstance() {
        if(instance == null){
            instance = new VoziloKontroler();
        }
        return instance;
    }
    
    public List<DomainObject> vratiListuSviVozilo(){
        sendRequest(new Vozilo(), ReqType.GET_ALL_VOZILO);
        RespPacket res = recieveResponse();
        if(res.getObject()== null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public List<DomainObject> vratiListuVozilo(DomainObject payload){
        sendRequest(payload, ReqType.GET_LIST_VOZILO);
        RespPacket res = recieveResponse();
        if(res.getObject() == null)
            return new ArrayList<DomainObject>();
        return (List<DomainObject>)res.getObject();
    }
    
    public boolean kreirajNoviVozilo(DomainObject payload){
        sendRequest(payload, ReqType.CREATE_VOZILO);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public DomainObject pronadjiVozilo(DomainObject payload){
        sendRequest(payload, ReqType.FIND_VOZILO);
        RespPacket res = recieveResponse();
        if(res.getObject() == null)
            return null;
        return (DomainObject)res.getObject();
    }
    
    public boolean obrisiVozilo(DomainObject payload){
        sendRequest(payload, ReqType.DELETE_VOZILO);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public boolean promeniVozilo(DomainObject payload){
        sendRequest(payload, ReqType.UPDATE_VOZILO);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
}
