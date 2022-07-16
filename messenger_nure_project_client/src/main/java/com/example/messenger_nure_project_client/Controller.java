package com.example.messenger_nure_project_client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    private Label testLabel;
    @FXML
    private TextField message_field;
    @FXML
    private VBox incoming_filed;
    @FXML
    private VBox outgoing_filed;

    @FXML
    protected void onSendButtonPress() {
        outgoing_filed.getChildren().add(new Label(message_field.getText()));
    }
    @FXML
    protected void onMessageIncome(){

    }
}