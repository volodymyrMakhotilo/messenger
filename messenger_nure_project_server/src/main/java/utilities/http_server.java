package utilities;

import layers.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class http_server {
    static Service service;

    public http_server(Service service) {
        try {
            this.service = service;
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


            System.err.println("Client " + num + " is here!");
            SocketProcessor socket = new SocketProcessor(s);
            socket.processLogin(s.getInetAddress(), s.getPort());
            new Thread(socket).start();
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

        public void writeResponse() throws Throwable {
            for (int i = 0; i < 10; i++) {
                String response = "Hi, client\n";
                os.write(response);
                os.flush();
            }
        }

        public String readRequest() throws Throwable {
            System.out.println("Reading request......");
            String word = is.readLine();
            System.out.println(word);
            return word;
        }

        public void processLogin(InetAddress ip, int port) throws Throwable {
            String login = readRequest();
            if (service.checkUser(login)) {

            } else {
                service.addUser(login, ip.toString(), port);
            }
            writeResponse();
        }


    }
}