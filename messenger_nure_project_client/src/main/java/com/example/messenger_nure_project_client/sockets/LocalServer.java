package com.example.messenger_nure_project_client.sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalServer {
    public LocalServer() {
        try {
            createServer();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void createServer() throws Throwable {
        ServerSocket ss = new ServerSocket(8080);

        int num = 0;

        while (true) {
            Socket s = ss.accept();

            num++;

            System.err.println("Client "+ num + " is here!");
            new Thread(new SocketProcessor(s)).start();
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket s;
        private BufferedReader is;
        private BufferedWriter os;

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.is = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            this.os = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));

        }

        public void run() {
            try {
                while(true) {
                    System.out.println("Connection established");
                    readRequest();
                    System.out.println("Response gotten");
                    writeResponse();
                    System.out.println("Request sent");
                }
            } catch (Throwable t) {
                /*do nothing*/
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                    /*do nothing*/
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse() throws Throwable {
            for (int i = 0; i < 10; i++) {
                String response = "Hi, client\n";
                os.write(response);
                os.flush();
            }
        }

        private void readRequest() throws Throwable {
            System.out.println("Reading request......");
            String word = is.readLine();
            System.out.println(word);
        }
    }
}