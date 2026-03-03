
package multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread extends Thread{
    private int port;
    private ServerSocket serverSocket;

    private List<Socket> clientList = new ArrayList<>();
    
    public ServerThread(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }
    
    
    
    @Override
    public void run() {
        while(true){
            try {
                Socket clientSocket;
                System.out.println("Server accepting clients...");
                clientSocket = serverSocket.accept();
                System.out.println("Client connected!");
                clientList.add(clientSocket);
                new ClientServiceThread(clientList.indexOf(clientSocket), clientSocket).start();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
