package org.bookstore.server;


import org.bookstore.server.service.BookServiceImpl;
import org.bookstore.server.service.IBookService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            IBookService bookService = new BookServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("bookService", bookService);
            System.out.println("🚀 Server is running...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
