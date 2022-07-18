package com.example.messenger_nure_project_client;

import com.example.messenger_nure_project_client.controlers.MainController;
import com.example.messenger_nure_project_client.models.User;
import com.example.messenger_nure_project_client.sockets.ClientSocket;
import com.example.messenger_nure_project_client.sockets.LocalServer;
import com.example.messenger_nure_project_client.xml.XML;

import java.io.IOException;
import java.net.Socket;

public class Messenger {
    private MainController mainController;
    private static Messenger instance;
    private static ClientSocket clientSocket;
    private User[] users;
    private static LocalServer localServer;
    private XML xml;

    private Messenger(MainController mainController) throws Throwable {
        try {
            this.mainController = mainController;
            clientSocket = ClientSocket.getInstance(mainController);
            localServer = new LocalServer(mainController, this);
            xml = new XML();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public static Messenger getInstance(MainController mainController) throws Throwable {
        if (instance == null) {
            instance = new Messenger(mainController);
        }
        clientSocket.setMainController(mainController);
        localServer.setMainController(mainController);
        return instance;
    }


    public User[] getUsers() {
        return users;
    }

    public void clientListen() {
        clientSocket.listen();
    }

    public void addUser(User user) {
        if (users != null) {
            User[] newArr = new User[users.length + 1];
            for (int i = 0; i < users.length; i++) {
                newArr[i] = users[i];
            }
            newArr[users.length] = user;
            users = newArr;
        } else {
            users = new User[1];
            users[0] = user;
        }
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User[] login(String login) throws Throwable {
        User[] users = clientSocket.login(xml.getUser(login, localServer.getIP(), localServer.getPort()));
        localServer.startServer();
        return users;
    }

    public void changeClientSocket(Socket socket) {
        clientSocket.setSocket(socket);
    }

    public void openChat(String ip, int port) throws IOException {
        clientSocket.reconnect(ip, port);
    }

    public void sendMessage(String message) {
        try {
            clientSocket.writeRequest(message);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
