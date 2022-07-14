package com.lms.library_manager;

import com.lms.library_manager.dbo.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddBookController {
    @FXML
    private TextField isbnText;
    @FXML
    private TextField titleText;
    @FXML
    private TextField authorText;

    public void goToAdminMenu() throws IOException {
        new MainApplication().changeScene("admin-view.fxml");
    }
    @FXML
    public void onAddBookBtnClick() throws IOException {
        Book book = new Book(isbnText.getText(), titleText.getText(), authorText.getText());
        boolean isSuccess = book.save();
        if (isSuccess) {
            System.out.println("Book Added Successfully");
        } else {
            System.out.println("Failed to Add the Book");
        }
        goToAdminMenu();
    }
    @FXML
    public void onCancelBtnClick() throws IOException {
        goToAdminMenu();
    }
}
