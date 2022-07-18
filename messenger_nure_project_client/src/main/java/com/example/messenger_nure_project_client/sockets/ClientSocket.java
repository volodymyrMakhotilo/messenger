package com.example.messenger_nure_project_client.sockets;

import com.example.messenger_nure_project_client.controlers.MainController;
import com.example.messenger_nure_project_client.models.User;
import com.example.messenger_nure_project_client.utilities.XML_Marshaller;
import com.example.messenger_nure_project_client.xml.XML;
import javafx.application.Platform;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;

public class ClientSocket {
    MainController mainController;
    private XML_Marshaller xmlMarshaller;
    private java.net.Socket s;
    private BufferedReader is;
    private BufferedWriter os;
    private XML xml;

    private static ClientSocket instance;

    private ClientSocket(MainController mainController,XML xml) throws Throwable {
        xmlMarshaller = new XML_Marshaller();
        this.mainController = mainController;
        s = new Socket("localhost", 8080);
        this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
    }

    public static ClientSocket getInstance(MainController mainController,XML xml) throws Throwable {
        if (instance == null) {
            instance = new ClientSocket(mainController,xml);
        }
        return instance;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setSocket(Socket s) {
        this.s = s;
    }

    public void reconnect(String ip, int port) throws IOException {
        s = new Socket("localhost", port);
        this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
    }

    public User[] login(String message) throws Throwable {
        System.out.println("Complete!");
        writeRequest(message);
        return xmlMarshaller.parseUsers(readInput());
    }

    public void close() throws IOException {
        s.close();
        System.err.println("Client processing finished");
    }

    public void writeRequest(String s) throws Throwable {
        os.write(s + " \n");
        os.flush();
    }

    private String readInput() throws Throwable {
        String word = is.readLine();
        System.out.println("Input was gotten!");
        return word;
    }

    public void listen() {
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Reading request......");
                        String message = null;
                        message = is.readLine();

                        User user = xmlMarshaller.parseUsers(message)[0];
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                mainController.onNewUser(user);
                            }
                        });
                        System.out.println(message);
                    }
                } catch (IOException | JAXBException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }
}




