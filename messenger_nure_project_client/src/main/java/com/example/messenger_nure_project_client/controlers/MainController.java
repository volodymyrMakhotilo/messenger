package com.example.messenger_nure_project_client.controlers;

import com.example.messenger_nure_project_client.Application;
import com.example.messenger_nure_project_client.Messenger;
import com.example.messenger_nure_project_client.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    static Messenger messenger;
    @FXML
    private VBox userList;
    @FXML
    private TextField loginField;
    @FXML
    private TextField message_field;
    @FXML
    private VBox incoming_filed;
    @FXML
    private VBox outgoing_filed;
    @FXML
    private Label headerLabel;

    @FXML
    protected void onSendButtonPress() {
        String textAreaInput = message_field.getText();
        messenger.sendMessage(textAreaInput);
        outgoing_filed.getChildren().add(new Label(textAreaInput));
        incoming_filed.getChildren().add(new Label());
    }

    @FXML
    public void onMessageIncome(String message) {
        incoming_filed.getChildren().add(new Label(message));
        outgoing_filed.getChildren().add(new Label());
    }

    @FXML
    public void onNewUser(User user) {
        messenger.addUser(user);
        addUserButton(user);
    }

    @FXML
    private void addUserButton(User user) {
        Button button = new Button(user.getLogin());
        button.setOnAction(e -> {
            try {
                messenger.openChat(user.getIp(),user.getPort());
                headerLabel.setText("Chat with " + user.getLogin());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        userList.getChildren().add(button);
    }

    @FXML
    protected void onLoginButtonPress(ActionEvent event) throws Throwable {
        User[] users = messenger.login(loginField.getText());
        System.out.println(users);
        messenger.setUsers(users);
        switchScene(event);
        messenger.clientListen();
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
        messenger = Messenger.getInstance(this);
        if (messenger.getUsers() != null) {
            for (User user : messenger.getUsers()) {
                addUserButton(user);
            }
        }
    }

}