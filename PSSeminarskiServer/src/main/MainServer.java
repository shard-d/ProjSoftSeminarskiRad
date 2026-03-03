
package main;

import db.DBBroker;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import multithreading.ServerThread;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Server started...");
        ServerThread serverThread;
        DBBroker.connect();
        System.out.println("Database connection established"); 
        try {
            serverThread = new ServerThread(9001); //port za SERVER
            serverThread.start();
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
