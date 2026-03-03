/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.networking;

public class RespPacket extends Packet{
        RespType type;

    public RespType getType() {
        return type;
    }
    
    public RespPacket(RespType type,Object object) {
        this.object = object;
        this.type = type;
    }
}
