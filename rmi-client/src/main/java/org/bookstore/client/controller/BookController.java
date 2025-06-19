package org.bookstore.client.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.bookstore.client.client.BookClient;
import org.bookstore.client.util.Notification;
import org.bookstore.client.util.Validator;
import org.bookstore.server.dto.Book;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    public TextField txtId;
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtPublisher;
    public TextField txtCategory;
    public TextField txtPrice;
    public TextField txtQuantity;
    public DatePicker dpPublishDate;
    public TextField txtKeyword;
    @FXML
    private TableView<Book> tbBooks;

    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colTitle;
    @FXML
    private TableColumn colAuthor;
    @FXML
    private TableColumn colPublisher;
    @FXML
    private TableColumn colPublishDate;
    @FXML
    private TableColumn colCategory;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colQuantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBooks(BookClient.getAllBooks());
    }

    private void loadBooks(List<Book> books) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        tbBooks.setItems(FXCollections.observableList(books));

    }

    public void onClickAdd(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        String category = txtCategory.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        String publishDate = dpPublishDate.getValue().toString();

        if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || category.isEmpty() || price.isEmpty() || quantity.isEmpty() || publishDate.isEmpty()) {
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        if(!Validator.isValid(Validator.IS_INTEGER, quantity)){
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Số lượng phải là số nguyên");
            return;
        }

        Book book = Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .category(category)
                .price(new BigDecimal(price))
                .quantity(Integer.parseInt(quantity))
                .publicationDate(Date.valueOf(publishDate))
                .build();


        int result = BookClient.addBook(book);

        if (result > 0) {
            Notification.showAlert(Alert.AlertType.INFORMATION, "Thông báo", null, "✅ Thêm sách thành công");
            loadBooks(BookClient.getAllBooks());
        } else {
            System.out.println("❌ Failed to add book!");
        }
    }

    public void onClickUpdate(ActionEvent actionEvent) {
        Book selectedBook = tbBooks.getSelectionModel().getSelectedItem();

        if (selectedBook == null) {
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Vui lòng chọn sách cần cập nhật");
            return;
        }
        
        
        Integer id = selectedBook.getId();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        String category = txtCategory.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        String publishDate = dpPublishDate.getValue().toString();

        if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || category.isEmpty() || price.isEmpty() || quantity.isEmpty() || publishDate.isEmpty() || id == null) {
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if(!Validator.isValid(Validator.IS_INTEGER, quantity)){
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Số lượng phải là số nguyên");
            return;
        }

        Book book = Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .publisher(publisher)
                .category(category)
                .price(new BigDecimal(price))
                .quantity(Integer.parseInt(quantity))
                .publicationDate(Date.valueOf(publishDate))
                .build();

        int result = BookClient.updateBook(book);

        if (result > 0) {
            Notification.showAlert(Alert.AlertType.INFORMATION, "Thông báo", null, "✅ Cập nhật sách thành công");
            loadBooks(BookClient.getAllBooks());
        } else {
            System.out.println("❌ Failed to update book!");
        }
    }


    public void onClickDelete(ActionEvent actionEvent) {
        Book selectedBook = tbBooks.getSelectionModel().getSelectedItem();

        if (selectedBook == null || selectedBook.getId() == null) {
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Vui lòng chọn sách cần xoá");
            return;
        }

        Integer id = selectedBook.getId();

        if (!Notification.showConfirmation("Bạn có chắc chắn muốn xóa sách này?")) {
            return;
        }

        int result = BookClient.deleteBook(id);

        if (result > 0) {
            loadBooks(BookClient.getAllBooks());
            Notification.showAlert(Alert.AlertType.INFORMATION, "Thông báo", null, "✅ Xoá sách thành công");
        } else {
            System.out.println("❌ Failed to delete book!");
        }
    }


    public void onClickRefresh(ActionEvent actionEvent) {
        txtId.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
        txtPublisher.setText("");
        txtQuantity.setText("");
        txtTitle.setText("");
        dpPublishDate.setValue(null);
        tbBooks.getSelectionModel().clearSelection();
        txtKeyword.setText("");
        loadBooks(BookClient.getAllBooks());
    }

    public void rowClicked(MouseEvent mouseEvent) {
        Book book = tbBooks.getSelectionModel().getSelectedItem();

        txtId.setText(String.valueOf(book.getId()));
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtPublisher.setText(book.getPublisher());
        txtCategory.setText(book.getCategory());
        dpPublishDate.setValue(book.getPublicationDate().toLocalDate());
        txtPrice.setText(book.getPrice().toString());
        txtQuantity.setText(String.valueOf(book.getQuantity()));

    }

    public void onClickSearch(ActionEvent actionEvent) {
        String keyword = txtKeyword.getText();

        if (keyword.isEmpty()) {
            Notification.showAlert(Alert.AlertType.ERROR, "Lỗi", null, "❌ Vui lòng nhập từ khóa tìm kiếm");
            return;
        }

        List<Book> books = BookClient.searchBooks(keyword);

        if (books.isEmpty()) {
            Notification.showAlert(Alert.AlertType.INFORMATION, "Thông báo", null, "❌ Không tìm thấy sách nào");
        } else {
            loadBooks(books);
        }
    }
}
