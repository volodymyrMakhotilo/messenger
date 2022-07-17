package xml;

public class XML {
    String header;

    public XML() {
        header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
    }

    public String getUser(String login,String ip,int port){
        String user = String.format("<user> <login>%s</login> <ip>%s</ip> <port>%s</port></user>",login,ip,port);
        return user;
    }

    public String wrapMessage(String message){
        return header+"<message>"+message+"</message>";
    }




}
