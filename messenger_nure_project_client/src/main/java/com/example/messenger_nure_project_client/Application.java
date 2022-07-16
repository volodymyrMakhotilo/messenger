package com.example.messenger_nure_project_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginFXML = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(loginFXML.load());
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Throwable {
        launch();
    }
}