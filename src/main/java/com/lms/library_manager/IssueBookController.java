package com.lms.library_manager;

import com.lms.library_manager.dbo.Book;
import com.lms.library_manager.dbo.Issue;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class IssueBookController {
    @FXML
    private TextField isbnText;
    @FXML
    private TextField usernameText;
    @FXML
    private DatePicker issueDatePicker;
    @FXML
    private DatePicker dueDatePicker;

    public void goToAdminMenu() throws IOException {
        new MainApplication().changeScene("admin-view.fxml");
    }
    @FXML
    public void onIssueBookBtnClick() throws IOException {

        String isbn = isbnText.getText();
        String username = usernameText.getText();
        LocalDate issueDate = issueDatePicker.getValue();
        LocalDate dueDate = dueDatePicker.getValue();

        Issue issue = new Issue(isbn, username, issueDate, dueDate);
        boolean isSuccess = issue.save();
        if (isSuccess) {
            System.out.println("Book Issued Successfully");
        } else {
            System.out.println("Failed to Issue the Book");
        }
        goToAdminMenu();
    }
    @FXML
    public void onCancelBtnClick() throws IOException {
        goToAdminMenu();
    }
}
