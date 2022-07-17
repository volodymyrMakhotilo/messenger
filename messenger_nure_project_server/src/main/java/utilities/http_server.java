package utilities;

import layers.Service;
import models.User;
import xml.XML;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class http_server {
    private static XML xml;
    static Service service;
    static XML_Marshaller xmlMarshaller;
    private static ArrayList<SocketProcessor> sockets = new ArrayList<>();

    public http_server(Service service) {
        try {
            xml = new XML();
            xmlMarshaller = new XML_Marshaller();
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

            socket.processLogin(xmlMarshaller);

            sockets.add(socket);
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
                    writeResponse("200");
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

        public void writeResponse(String response) throws Throwable {
            os.write(response + "\n");
            os.flush();
        }

        public String readRequest() throws Throwable {
            System.out.println("Reading request......");
            String word = is.readLine();
            System.out.println(word);
            return word;
        }

        public void processLogin(XML_Marshaller XML_Marshaller) throws Throwable {
            String request = readRequest();
            User user = XML_Marshaller.parseUser(request);
            if (service.checkUser(user.getLogin())) {
                updateUsers(user);
            } else {
                service.addUser(user.getLogin(), user.getIp(), user.getPort());
            }
            System.out.println("Gonna answer on Log!");
            writeResponse(getUsers());
        }
    }

    public static String getUsers() {
        String message = "";
        List<User> users = service.getUsers();
            for (User user : users) {
                message += xml.getUser(user.getLogin(), user.getIp(), user.getPort());
            }
        return xml.wrapMessage(message);
    }

    public static void updateUsers(User user) throws Throwable {
        for (SocketProcessor socket : sockets) {
            socket.writeResponse(xml.getUser(user.getLogin(), user.getIp(), user.getPort()));
        }
    }
}