package com.example.messenger_nure_project_client.xml;

public class XML {
    String header;

    public XML() {
        header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    }

    public String getMessage(String login,String message) {
        String user = String.format("<user_message> <user_login>%s</user_login> <text>%s</text></user_message>",login,message);
        return header+user;
    }
    public String getUser(String login,String ip,int port){
        String user = String.format("<user> <login>%s</login> <ip>%s</ip> <port>%s</port></user>",login,ip,port);
        return header+user;
    }

}
