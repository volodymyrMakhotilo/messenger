package com.example.messenger_nure_project_client;

import com.example.messenger_nure_project_client.sockets.ClientSocket;
import com.example.messenger_nure_project_client.sockets.LocalServer;

public class Messenger {
    private ClientSocket clientSocket;
    private LocalServer localServer;
    public Messenger() throws Throwable {
        try {
            clientSocket = ClientSocket.getInstance();
          //  localServer = clientSocket.

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        try {
            System.out.println(clientSocket.writeRequest(message));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
