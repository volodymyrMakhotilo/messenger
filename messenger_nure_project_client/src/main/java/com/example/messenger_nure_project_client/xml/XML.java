package com.example.messenger_nure_project_client.xml;

public class XML {
    String header;

    public XML() {
        header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    }

    public String getMessage() {
        //    <message type = "send_message" sender="00" receiver = "01" content = "Test" ></message>
        return "";
    }
    public String getUser(String login,String ip,int port){
        String user = String.format("<user> <login>%s</login> <ip>%s</ip> <port>%s</port></user>",login,ip,port);
        return header+user;
    }

}
