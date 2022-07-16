package com.example.messenger_nure_project_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Messenger!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            ClientSocket clientSocket = new ClientSocket();
            new Thread(clientSocket).start();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        launch();
    }
}