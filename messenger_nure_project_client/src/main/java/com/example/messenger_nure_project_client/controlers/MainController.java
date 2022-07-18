package com.example.messenger_nure_project_client.controlers;

import com.example.messenger_nure_project_client.Application;
import com.example.messenger_nure_project_client.Messenger;
import com.example.messenger_nure_project_client.models.User;
import javafx.event.ActionEvent;
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
import java.util.HashMap;

public class MainController {
    static Messenger messenger;
    private HashMap<String, Chat> chats;
    private Chat currentChat;
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
        currentChat.getOut().getChildren().add(new Label(textAreaInput));
        currentChat.getIn().getChildren().add(new Label());
    }

    @FXML
    public void onMessageIncome(String login, String message) {
        Chat chat = getChat(login);
        chat.getIn().getChildren().add(new Label(message));
        chat.getOut().getChildren().add(new Label());
        setChat(login, chat);
    }

    //To the messenger
    private Chat getChat(String login) {
        if (!chats.containsKey(login)) {
            chats.put(login, new Chat(login, new VBox(), new VBox()));
        }
        return chats.get(login);
    }

    private void setChat(String login, Chat chat) {
        chats.put(login, chat);
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
                if (currentChat != null) {
                    setChat(currentChat.getLogin(), currentChat);
                }

                messenger.openChat(user.getIp(), user.getPort());
                headerLabel.setText("Chat with " + user.getLogin());

                currentChat = getChat(user.getLogin());
                incoming_filed.getChildren().clear();
                incoming_filed.getChildren().add(currentChat.getIn());
                outgoing_filed.getChildren().clear();
                outgoing_filed.getChildren().add(currentChat.getOut());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        userList.getChildren().add(button);
    }

    @FXML
    protected void onLoginButtonPress(ActionEvent event) throws Throwable {
        String login = loginField.getText();
        User[] users = messenger.login(login);
        messenger.setLogin(login);
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
        chats = new HashMap<>();
        messenger = Messenger.getInstance(this);
        if (messenger.getUsers() != null) {
            for (User user : messenger.getUsers()) {
                addUserButton(user);
            }
        }
    }

}