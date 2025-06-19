module rmi.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires rmi.server;
    requires java.rmi;
    requires java.sql;

    opens org.bookstore.client to javafx.fxml;
    opens org.bookstore.client.controller to javafx.fxml;

    exports org.bookstore.client;
    exports org.bookstore.client.controller;
}