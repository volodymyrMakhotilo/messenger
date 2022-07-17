package com.example.messenger_nure_project_client;

import com.example.messenger_nure_project_client.models.User;
import com.example.messenger_nure_project_client.sockets.ClientSocket;
import com.example.messenger_nure_project_client.sockets.LocalServer;
import com.example.messenger_nure_project_client.xml.XML;

public class Messenger {
    private static Messenger instance;
    private ClientSocket clientSocket;
    private User[] users;
    // private LocalServer localServer;
    private XML xml;

    private Messenger() throws Throwable {
        try {
            clientSocket = ClientSocket.getInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static Messenger getInstance() throws Throwable {
        if (instance == null) {
            instance = new Messenger();
        }
        return instance;
    }


    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User[] login(String login) throws Throwable {
        LocalServer localServer = new LocalServer();
        xml = new XML();
        User[] users = clientSocket.login(xml.getUser(login, localServer.getIP(), localServer.getPort()));
        localServer.startServer();
        return users;
    }

    public void sendMessage(String message) {
        try {
            System.out.println(clientSocket.writeRequest(message));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
