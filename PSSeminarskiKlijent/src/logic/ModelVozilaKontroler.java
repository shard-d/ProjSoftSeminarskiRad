
package logic;

import common.domain.DomainObject;
import common.domain.ModelVozila;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.util.ArrayList;
import java.util.List;

public class ModelVozilaKontroler extends OpstiKontroler{
    private static ModelVozilaKontroler instance;

    public static ModelVozilaKontroler getInstance() {
        if(instance == null)
            instance = new ModelVozilaKontroler();
        return instance;
    }
    
     public List<DomainObject> vratiListuSviModelVozila(){
        sendRequest(new ModelVozila(), ReqType.GET_ALL_MODELVOZILA);
         RespPacket res =  recieveResponse();
        return (List<DomainObject>)res.getObject();
    }
    
    public List<DomainObject> vratiListuModelVozila(DomainObject payload){
        sendRequest(payload, ReqType.GET_LIST_MODELVOZILA);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS)){
            return (List<DomainObject>)res.getObject();
        }
        return new ArrayList<DomainObject>();
    }
    
     public boolean kreirajModelVozila(DomainObject obj){
        sendRequest((ModelVozila) obj, ReqType.CREATE_MODELVOZILA);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public DomainObject pronadjiModelVozila(DomainObject obj){
        sendRequest((ModelVozila) obj, ReqType.FIND_MODELVOZILA);
        RespPacket res = recieveResponse();
        return (ModelVozila)res.getObject();
    }
    
    public boolean promeniModelVozila (DomainObject obj){
        sendRequest((ModelVozila) obj, ReqType.UPDATE_MODELVOZILA);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
    
    public boolean obrisiModelVozila (DomainObject obj){
        sendRequest((ModelVozila) obj, ReqType.DELETE_MODELVOZILA);
        RespPacket res = recieveResponse();
        if(res.getType().equals(RespType.SUCCESS))
            return true;
        return false;
    }
}