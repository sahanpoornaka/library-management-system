package com.lms.library_manager;

import com.lms.library_manager.MainApplication;
import com.lms.library_manager.dbo.Admin;
import com.lms.library_manager.dbo.Member;
import com.lms.library_manager.dbo.User;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddUserController {
    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField fNameText;
    @FXML
    private TextField lNameText;
    @FXML
    private TextField mobileText;
    @FXML
    private CheckBox isAdminCBox;


    public void goToAdminMenu() throws IOException {
        new MainApplication().changeScene("admin-view.fxml");
    }
    @FXML
    public void onAddUserBtnClick() throws IOException {
        User user;
        String username = usernameText.getText();
        String password = passwordText.getText();
        String fName = fNameText.getText();
        String lName = lNameText.getText();
        String mobile = mobileText.getText();

        user = isAdminCBox.isSelected()
                ? new Admin(username, password, fName, lName, mobile)
                : new Member(username, password, fName, lName, mobile);

        boolean isSuccess = user.save();

        if (isSuccess) {
            if (user instanceof Admin) {
                System.out.println("Admin Added Successfully");
            } else {
                System.out.println("Member Added Successfully");
            }
        } else {
            System.out.println("Failed to Add the User");
        }
        goToAdminMenu();
    }
    @FXML
    public void onCancelBtnClick() throws IOException {
        goToAdminMenu();
    }
}
