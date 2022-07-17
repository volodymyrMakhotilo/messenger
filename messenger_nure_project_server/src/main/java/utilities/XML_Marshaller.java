package utilities;

import models.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XML_Marshaller {
    static User user;

    public User parseUser(String message) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(message));

        System.out.println(user);

        return user;
    }




    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("user")) {
                String login = attributes.getValue("login");
                String ip = attributes.getValue("ip");
                String port = attributes.getValue("port");
                user = new User(login, ip, Integer.parseInt(port));
            }
        }
    }
}