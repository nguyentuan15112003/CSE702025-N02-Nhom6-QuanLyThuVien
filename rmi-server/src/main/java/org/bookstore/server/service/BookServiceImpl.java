package org.bookstore.server.service;


import org.bookstore.server.dto.Book;
import org.bookstore.server.util.DbConnect;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class BookServiceImpl extends UnicastRemoteObject
        implements IBookService {
    private final DbConnect dbConnect = DbConnect.getInstance();


    public BookServiceImpl() throws Exception {
        super();
    }

    @Override
    public List<Book> getAllBooks() {
        final String sql = """
                select * from books
                """;

        return dbConnect.executeQuery(sql).stream()
                .map(Book::mapToBook)
                .toList();
    }

    @Override
    public int addBook(Book book) {
        if (book == null) {
            return 0;
        }

        final String sql = """
                insert into books(title, author, publisher, publish_date, category, price, quantity)
                values('%s', '%s', '%s', '%s', '%s', %s, %s)
                """.formatted(
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationDate(),
                book.getCategory(),
                book.getPrice(),
                book.getQuantity()
        );

        return dbConnect.executeUpdate(sql);
    }

    @Override
    public int updateBook(Book book) {
        if (book == null) {
            return 0;
        }

        final String sql = """
                update books
                set title = '%s',
                    author = '%s',
                    publisher = '%s',
                    publish_date = '%s',
                    category = '%s',
                    price = %s,
                    quantity = %s
                where id = %s
                """.formatted(
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublicationDate(),
                book.getCategory(),
                book.getPrice(),
                book.getQuantity(),
                book.getId()
        );

        return dbConnect.executeUpdate(sql);
    }

    @Override
    public int deleteBook(int id) {
        final String sql = """
                delete from books
                where id = %s
                """.formatted(id);

        return dbConnect.executeUpdate(sql);
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> books = getAllBooks();

        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                        || book.getPublisher().toLowerCase().contains(keyword.toLowerCase())
                        || book.getCategory().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }


}
