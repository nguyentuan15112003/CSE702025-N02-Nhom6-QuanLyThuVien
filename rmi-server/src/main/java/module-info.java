module rmi.server {
    requires java.rmi;
    requires java.sql;
    requires static lombok;
    
    exports org.bookstore.server.dto;
    exports org.bookstore.server.service;
    exports org.bookstore.server;
    
}