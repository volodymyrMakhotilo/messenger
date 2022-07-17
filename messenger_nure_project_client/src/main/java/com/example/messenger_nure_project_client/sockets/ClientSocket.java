package com.example.messenger_nure_project_client.sockets;

import com.example.messenger_nure_project_client.models.User;
import com.example.messenger_nure_project_client.utilities.XML_Marshaller;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    private XML_Marshaller xmlMarshaller;
    private java.net.Socket s;
    private BufferedReader is;
    private BufferedWriter os;

    private static ClientSocket instance;

    private ClientSocket() throws Throwable {
        xmlMarshaller = new XML_Marshaller();
        s = new Socket("localhost", 8080);
        this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
    }

    public static ClientSocket getInstance() throws Throwable {
        if (instance == null) {
            instance = new ClientSocket();
        }
        return instance;
    }

    public void reconnect(String ip, int port) throws IOException {
        s = new Socket(ip, port);
        this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
    }

    public User[] login(String message) throws Throwable {
        System.out.println("Complete!");
        return xmlMarshaller.parseUsers(writeRequest(message));
    }

    public void close() throws IOException {
        s.close();
        System.err.println("Client processing finished");
    }

    public String writeRequest(String s) throws Throwable {
        os.write(s + " \n");
        os.flush();
        return readInput();
    }

    private String readInput() throws Throwable {
        String word = is.readLine();
        System.out.println("Input was gotten!");
        return word;
    }
}




