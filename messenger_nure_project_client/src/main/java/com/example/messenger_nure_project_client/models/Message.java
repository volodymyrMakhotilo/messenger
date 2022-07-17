package com.example.messenger_nure_project_client.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @XmlElement(name = "user")
    public User[] users;

    Message(){}

    @Override
    public String toString() {
        return "Message{" +
                "users=" + Arrays.toString(users) +
                '}';
    }

    public User[] getUsers() {
        return users;
    }
}
