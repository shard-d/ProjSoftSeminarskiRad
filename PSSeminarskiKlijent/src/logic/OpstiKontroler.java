
package logic;

import com.formdev.flatlaf.FlatLightLaf;
import common.domain.DomainObject;
import common.networking.ReqPacket;
import common.networking.ReqType;
import common.networking.RespPacket;
import common.networking.RespType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.radnik.FormaLogovanje;


public class OpstiKontroler {
    protected int port = 9001;
    protected static String adress = "localhost";
    protected static Socket socket;
    protected static ObjectInputStream socketInput;
    protected static ObjectOutputStream socketOutput; 
    
    public static void main(String[] args) {
        OpstiKontroler opsti = new OpstiKontroler();
        opsti.connect();
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); 
        } catch (UnsupportedLookAndFeelException ex) {
            //System.out.println("Catch");
            System.getLogger(OpstiKontroler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        opsti.initUserInterface();
    }
    
    private void initUserInterface(){
        new FormaLogovanje().setVisible(true);
    }
    
    public boolean connect(){
        try {
            socket = new Socket(adress, port);
            socketInput = new ObjectInputStream(socket.getInputStream());
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OpstiKontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean sendRequest(DomainObject object, ReqType requestType){
        ReqPacket packet = new ReqPacket(requestType, object);
        try {
            socketOutput.writeObject(packet);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OpstiKontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public RespPacket recieveResponse(){
        try {
            Object obj = socketInput.readObject();
            if(obj == null){
                return null;
            }
            RespPacket packet = (RespPacket) obj;
            return packet;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(OpstiKontroler.class.getName()).log(Level.SEVERE, null, ex);
            return new RespPacket(RespType.FAILIURE, null);
        }
    }
}
