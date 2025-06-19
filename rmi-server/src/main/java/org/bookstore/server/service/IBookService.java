package org.bookstore.server.service;


import org.bookstore.server.dto.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBookService extends Remote {
    List<Book> getAllBooks() throws RemoteException; 

    int addBook(Book book) throws RemoteException;

    int updateBook(Book book) throws RemoteException;

    int deleteBook(int id) throws RemoteException;

    List<Book> searchBooks(String keyword) throws RemoteException;
}
