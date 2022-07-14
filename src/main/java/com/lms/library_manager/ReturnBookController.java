package com.lms.library_manager;

import com.lms.library_manager.dbo.Issue;
import com.lms.library_manager.dbo.Return;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class ReturnBookController {
    @FXML
    private TextField issueIdText;
    @FXML
    private DatePicker returnDatePicker;
    @FXML
    private TextField fineText;

    public void goToAdminMenu() throws IOException {
        new MainApplication().changeScene("admin-view.fxml");
    }
    @FXML
    public void onReturnBookBtnClick() throws IOException {

        int issuedId = Integer.parseInt(issueIdText.getText());
        double fine = Double.parseDouble(fineText.getText());
        LocalDate returnDate = returnDatePicker.getValue();

        Return issue = new Return(issuedId, returnDate, fine);
        boolean isSuccess = issue.save();
        if (isSuccess) {
            System.out.println("Book Return Successfully");
        } else {
            System.out.println("Failed to Return the Book");
        }
        goToAdminMenu();
    }
    @FXML
    public void onCancelBtnClick() throws IOException {
        goToAdminMenu();
    }
}
