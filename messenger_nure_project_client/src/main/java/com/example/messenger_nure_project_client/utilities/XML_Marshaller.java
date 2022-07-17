package com.example.messenger_nure_project_client.utilities;
import com.example.messenger_nure_project_client.models.Message;
import com.example.messenger_nure_project_client.models.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XML_Marshaller {


    public User[] parseUsers(String xml) throws JAXBException {
        System.out.println(xml);

        JAXBContext jc = JAXBContext.newInstance(Message.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Message message = (Message) unmarshaller.unmarshal(new StringReader(xml));

        System.out.println(message);

        return message.getUsers();






    }












}