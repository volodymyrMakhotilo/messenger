package com.example.messenger_nure_project_client.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user_message")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserMessage {
    @XmlElement(name = "user_login")
    private String user_login;
    @XmlElement(name = "text")
    private String text;

    UserMessage() {

    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "user_login='" + user_login + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
