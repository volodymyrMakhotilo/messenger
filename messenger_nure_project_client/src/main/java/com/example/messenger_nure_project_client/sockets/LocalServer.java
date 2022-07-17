package com.example.messenger_nure_project_client.sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalServer {
    static ServerSocket ss;

    public LocalServer() {
        try {
            ss = new ServerSocket(0);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                int num = 0;

                while (true) {
                    try {
                        Socket s = null;


                        System.out.println("1");
                        s = ss.accept();
                        System.out.println("2");

                        num++;

                        System.err.println("Client " + num + " is here!");
                        new Thread(new SocketProcessor(s)).start();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public int getPort() {
        return ss.getLocalPort();
    }

    public String getIP() {
        return ss.getInetAddress().toString();
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
                while (true) {
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
