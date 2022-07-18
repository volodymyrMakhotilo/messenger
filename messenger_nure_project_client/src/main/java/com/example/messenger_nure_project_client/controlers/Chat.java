package com.example.messenger_nure_project_client.controlers;

import javafx.scene.layout.VBox;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class Chat {
    private String login;
    private VBox in;
    private VBox out;

    public String getLogin() {
        return login;
    }

    public VBox getIn() {
        return in;
    }

    public VBox getOut() {
        return out;
    }

    Chat(String login, VBox in, VBox out) {
        this.login = login;
        this.in = in;
        this.out = out;
        shape();
    }

    private void shape() {
        in.setAlignment(BOTTOM_CENTER);
        in.setLayoutY(5.0);
        in.setPrefHeight(437.0);
        in.setPrefWidth(183.0);
        out.setAlignment(BOTTOM_CENTER);
        out.setLayoutX(173.0);
        out.setLayoutY(5.0);
        out.setPrefHeight(437.0);
        out.setPrefWidth(183.0);
    }
}
