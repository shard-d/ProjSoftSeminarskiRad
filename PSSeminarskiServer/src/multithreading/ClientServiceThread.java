/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreading;

import common.domain.DomainObject;
import common.networking.ReqPacket;
import common.networking.RespPacket;
import common.networking.RespType;
import ctrl.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danic
 */
public class ClientServiceThread extends Thread{
        private int clientID;
    private Socket socket;

    private ObjectInputStream socketInput;
    private ObjectOutputStream socketOutput;
    private ReqPacket recievedPacket;
    
     public ClientServiceThread(int clientID, Socket socket) throws IOException, ClassNotFoundException{
        this.clientID = clientID;
        this.socket = socket;

        socketOutput = new ObjectOutputStream(socket.getOutputStream());
        socketInput = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void run() {
        try {
            openDataStream();
        } catch (IOException ex) {
            if(ex.getMessage().equals("Connection reset")){
                System.out.println("Client disconnected");
                return;
            }
            Logger.getLogger(ClientServiceThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientServiceThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void openDataStream()throws IOException, ClassNotFoundException{
        boolean signal;
        DomainObject ObjectQueryResult;
        List<DomainObject> ListQueryResult;
        while(true){
            if(socket.isConnected() == false)
                return;
            recievedPacket = (ReqPacket) socketInput.readObject();
            DomainObject packet = (DomainObject)recievedPacket.getObject();
            signal = false;
            ObjectQueryResult = null;
            ListQueryResult = null;
            try {
                switch (recievedPacket.getType()) {
                    case CREATE_SERVNALOG:
                        signal = Controller.getInstance().kreirajServNalog(packet);
                        handleSignalResponse(signal);
                        break;
                    case FIND_SERVNALOG:
                        ObjectQueryResult = Controller.getInstance().pretraziServNalog(packet);
                        handleObjectResponse(ObjectQueryResult);
                        break;
                    case UPDATE_SERVNALOG:
                        signal = Controller.getInstance().promeniServNalog(packet);
                        handleSignalResponse(signal);
                        break;
                    case GET_LIST_SERVNALOG:
                        ListQueryResult = Controller.getInstance().vratiListuServNalog(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    case GET_ALL_SERVNALOG:
                        ListQueryResult = Controller.getInstance().vratiListuSviServNalog(packet);
                        handleListResponse(ListQueryResult);
                        break;
                        
                    case CREATE_VOZILO:
                        signal = Controller.getInstance().kreirajVozilo(packet);
                        handleSignalResponse(signal);
                        break;
                    case FIND_VOZILO:
                        ObjectQueryResult = Controller.getInstance().pretraziVozilo(packet);
                        handleObjectResponse(ObjectQueryResult);
                        break;
                    case UPDATE_VOZILO:
                        signal = Controller.getInstance().promeniVozilo(packet);
                        handleSignalResponse(signal);
                        break;
                    case DELETE_VOZILO:
                        signal = Controller.getInstance().obrisiVozilo(packet);
                        handleSignalResponse(signal);
                        break;
                    case GET_LIST_VOZILO:
                        ListQueryResult = Controller.getInstance().vratiListuVozilo(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    case GET_ALL_VOZILO:
                        ListQueryResult = Controller.getInstance().vratiListuSviVozilo(packet);
                        handleListResponse(ListQueryResult);
                        break;
                        
                    case LOGIN_RADNIK:
                        signal = Controller.getInstance().prijaviRadnik(packet);
                        handleSignalResponse(signal);
                        break;
                    case CREATE_RADNIK:
                        signal = Controller.getInstance().kreirajRadnik(packet);
                        handleSignalResponse(signal);
                        break;
                    case FIND_RADNIK:
                        ObjectQueryResult = Controller.getInstance().pretraziRadnik(packet);
                        handleObjectResponse(ObjectQueryResult);
                        break;
                    case UPDATE_RADNIK:
                        signal = Controller.getInstance().promeniRadnik(packet);
                        handleSignalResponse(signal);
                        break;
                    case DELETE_RADNIK:
                        signal = Controller.getInstance().obrisiRadnik(packet);
                        handleSignalResponse(signal);
                        break;
                    case GET_LIST_RADNIK:
                        ListQueryResult = Controller.getInstance().vratiListuRadnik(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    case GET_ALL_RADNIK:
                        ListQueryResult = Controller.getInstance().vratiListuSviRadnik(packet);
                        handleListResponse(ListQueryResult);
                        break;
                        
                    case CREATE_SERVOPER:
                        signal = Controller.getInstance().kreirajServOper(packet);
                        handleSignalResponse(signal);
                        break;
                    case FIND_SERVOPER:
                        ObjectQueryResult = Controller.getInstance().pretraziServOper(packet);
                        handleObjectResponse(ObjectQueryResult);
                        break;
                    case UPDATE_SERVOPER:
                        signal = Controller.getInstance().promeniServOper(packet);
                        handleSignalResponse(signal);
                        break;
                    case DELETE_SERVOPER:
                        signal = Controller.getInstance().obrisiServOper(packet);
                        handleSignalResponse(signal);
                        break;
                    case GET_LIST_SERVOPER:
                        ListQueryResult = Controller.getInstance().vratiListuServOper(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    case GET_ALL_SERVOPER:
                        ListQueryResult = Controller.getInstance().vratiListuSviServOper(packet);
                        handleListResponse(ListQueryResult);
                        break;
                        
                    case CREATE_MODELVOZILA:
                        signal = Controller.getInstance().kreirajModelVozila(packet);
                        handleSignalResponse(signal);
                        break;
                    case FIND_MODELVOZILA:
                        ObjectQueryResult = Controller.getInstance().pretraziModelVozila(packet);
                        handleObjectResponse(ObjectQueryResult);
                        break;
                    case UPDATE_MODELVOZILA:
                        signal = Controller.getInstance().promeniModelVozila(packet);
                        handleSignalResponse(signal);
                        break;
                    case DELETE_MODELVOZILA:
                        signal = Controller.getInstance().obrisiModelVozila(packet);
                        handleSignalResponse(signal);
                        break;
                    case GET_LIST_MODELVOZILA:
                        ListQueryResult = Controller.getInstance().vratiListuModelVozila(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    case GET_ALL_MODELVOZILA:
                        ListQueryResult = Controller.getInstance().vratiListuSviModelVozila(packet);
                        handleListResponse(ListQueryResult);
                        break;
                        
                    case INSERT_TERMIN:
                        signal = Controller.getInstance().ubaciTermin(packet);
                        handleSignalResponse(signal);
                        break;
                    case FIND_TERMIN:
                        ObjectQueryResult = Controller.getInstance().pretraziTermin(packet);
                        handleObjectResponse(ObjectQueryResult);
                        break;
                    case UPATE_TERMIN:
                        signal = Controller.getInstance().promeniTermin(packet);
                        handleSignalResponse(signal);
                        break;
                    case DELETE_TERMIN:
                        signal = Controller.getInstance().obrisiTermin(packet);
                        handleSignalResponse(signal);
                        break;
                    case GET_LIST_TERMIN:
                        ListQueryResult = Controller.getInstance().vratiListuTermin(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    case GET_ALL_TERMIN:
                        ListQueryResult = Controller.getInstance().vratiListuSviTermin(packet);
                        handleListResponse(ListQueryResult);
                        break;
                    default:
                        throw new AssertionError(recievedPacket.getType().name());
                }
            } catch (SQLException e) {
                System.out.println("DEBUG: SQL ERROR");
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("ErrorCode: " + e.getErrorCode());
                //e.printStackTrace(); // izgleda ruzno kao da je kod los a nije realno
                handleSignalResponse(false);
            }

        }
    }
    private void handleSignalResponse(boolean signal) throws IOException{
        if(signal == true){
            socketOutput.writeObject(new RespPacket(RespType.SUCCESS, null));
            return;
        }
        socketOutput.writeObject(new RespPacket(RespType.FAILIURE, null));
    }
    
    public void handleObjectResponse(DomainObject object) throws IOException{
        if(object != null){
            socketOutput.writeObject(new RespPacket(RespType.SUCCESS, object));
            return;
        }
        socketOutput.writeObject(new RespPacket(RespType.FAILIURE, null));
    }
    
    public void handleListResponse(List<DomainObject> list) throws IOException{
        if(list != null){
            socketOutput.writeObject(new RespPacket(RespType.SUCCESS, list));
            return;
        }
        socketOutput.writeObject(new RespPacket(RespType.FAILIURE, null));
    }
                        
    
}
