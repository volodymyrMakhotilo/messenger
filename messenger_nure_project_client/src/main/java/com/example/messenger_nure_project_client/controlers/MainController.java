package com.example.messenger_nure_project_client.controlers;

import com.example.messenger_nure_project_client.Application;
import com.example.messenger_nure_project_client.Messenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    static Messenger messenger;

    @FXML
    private TextField loginField;
    @FXML
    private TextField message_field;
    @FXML
    private VBox incoming_filed;
    @FXML
    private VBox outgoing_filed;

    @FXML
    protected void onSendButtonPress() {
        String textAreaInput = message_field.getText();
        messenger.sendMessage(textAreaInput);
        outgoing_filed.getChildren().add(new Label(textAreaInput));
    }

    @FXML
    protected void onMessageIncome() {

    }

    @FXML
    protected void onLoginButtonPress(ActionEvent event) throws IOException {
        switchScene(event);
        messenger.sendMessage(loginField.getText());
    }

    private void switchScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Application.class.getResource("main_view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void initialize() throws Throwable {
        //get model
        messenger = new Messenger();
        //link Model with View
       /*   accountHolder.textProperty().bind(account.accountHolderProperty());
       // accountBalance.textProperty().bind(account.accountBalanceProperty().asString());
        //accountNumber.textProperty().bind(account.accountNumberProperty().asString());

     //link Controller to View - ensure only numeric input (integers) in text field
        amountTextField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().matches("\\d+") || change.getText().equals("")) {
                return change;
            } else {
                change.setText("");
                change.setRange(
                        change.getRangeStart(),
                        change.getRangeStart()
                );
                return change;
            }
        }));*/
    }

}