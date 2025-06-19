package org.bookstore.client.client;


import org.bookstore.server.dto.Book;
import org.bookstore.server.service.IBookService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class BookClient {
    private static IBookService bookService;

    public static void connectToServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            bookService = (IBookService) registry.lookup("bookService");
            System.out.println("✅ Connected to RMI Server!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Book> getAllBooks() {
        try {
            return bookService.getAllBooks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int addBook(Book book) {
        try {
            return bookService.addBook(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int deleteBook(int id) {
        try {
            return bookService.deleteBook(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int updateBook(Book book) {
        try {
            return bookService.updateBook(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Book> searchBooks(String keyword) {
        try {
            return bookService.searchBooks(keyword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
