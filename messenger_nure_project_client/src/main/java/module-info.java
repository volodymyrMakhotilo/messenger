module com.example.messenger_nure_project_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.xml.bind;


    opens com.example.messenger_nure_project_client to javafx.fxml;
    exports com.example.messenger_nure_project_client;
    opens com.example.messenger_nure_project_client.models to java.xml.bind;

    exports com.example.messenger_nure_project_client.sockets;
    opens com.example.messenger_nure_project_client.sockets to javafx.fxml;
    exports com.example.messenger_nure_project_client.controlers;
    opens com.example.messenger_nure_project_client.controlers to javafx.fxml;
}