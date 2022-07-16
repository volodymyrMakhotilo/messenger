package com.example.messenger_nure_project_client;

import java.io.*;
import java.net.Socket;


public class ClientSocket implements Runnable {
    private java.net.Socket s;
    private BufferedReader is;
    private BufferedWriter os;

    public ClientSocket() throws IOException {
        s = new Socket("localhost", 8080);
        this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
        this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
    }

    public void run() {
        try {
            System.out.println("Connection established");
            writeRequest("");
            System.out.println("Request sent");
            readInput();
            System.out.println("Response gotten");
        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            try {
                s.close();
            } catch (Throwable t) {
                /*do nothing*/
            }
        }
        System.err.println("Client processing finished");
    }

    public void writeRequest(String s) throws Throwable {
        os.write(s + " \n");
        os.flush();
    }

    private void readInput() throws Throwable {
        String word = is.readLine();
        System.out.println(word);
    }
}




