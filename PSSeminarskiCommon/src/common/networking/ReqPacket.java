package common.networking;
import common.domain.DomainObject;

public class ReqPacket extends Packet{
    private ReqType type;

    public ReqPacket(ReqType type, DomainObject object) {
        this.type = type;
        this.object = object;
    }

    public ReqType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        String description = "PACKET INFO\n\n";
        description+="Packet type: "+getType();
        description+="\nObject: "+object.toString();
        return description;
    }
}
