package com.example.messenger_nure_project_client.sockets;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    private java.net.Socket s;
    private BufferedReader is;
    private BufferedWriter os;

    private static ClientSocket instance;

    private ClientSocket() throws IOException {
        s = new Socket("localhost", 8080);
        this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
        login();
    }

    public static ClientSocket getInstance() throws IOException {
        if (instance == null) {
            instance = new ClientSocket();
        }
        return instance;
    }

    public LocalServer login() {

          System.out.println(s.getInetAddress());
          System.out.println(s.getPort());
          return null;
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
        return word;
    }
}




