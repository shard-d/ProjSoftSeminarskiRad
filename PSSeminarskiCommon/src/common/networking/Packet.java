package common.networking;

import java.io.Serializable;

public abstract class Packet implements Serializable{
    protected Object object;

    public Object getObject() {
        return object;
    }

}
