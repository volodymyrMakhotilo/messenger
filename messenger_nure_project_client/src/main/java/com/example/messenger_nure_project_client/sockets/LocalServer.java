package com.example.messenger_nure_project_client.sockets;

import com.example.messenger_nure_project_client.Messenger;
import com.example.messenger_nure_project_client.controlers.MainController;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalServer {
    static ServerSocket ss;
    Messenger messenger;
    static MainController mainController;

    public LocalServer(MainController mainController,Messenger messenger) {
        try {
            this.messenger = messenger;
            this.mainController = mainController;
            ss = new ServerSocket(0);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void setMainController(MainController mainController) {
        LocalServer.mainController = mainController;
    }

    public void startServer() {
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                int num = 0;
                while (true) {
                    try {
                        Socket s = null;

                        s = ss.accept();

                        messenger.changeClientSocket(s);

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
                    System.out.println("Request gotten");
                }
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                   t.printStackTrace();
                }
            }
            System.err.println("Client processing finished");
        }

        private void readRequest() throws Throwable {
            System.out.println("Reading request......");
            String message = is.readLine();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
            mainController.onMessageIncome(message);
                }
            });
            System.out.println(message);
        }
    }
}
