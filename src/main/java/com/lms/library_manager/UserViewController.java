package com.lms.library_manager;

import com.lms.library_manager.dbo.DBConnector;
import com.lms.library_manager.dbo.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UserViewController implements Initializable{
    @FXML
    private TableView<UserModel> userViewTable;
    @FXML
    private TableColumn<UserModel, String> username;
    @FXML
    private TableColumn<UserModel, String> userType;
    @FXML
    private TableColumn<UserModel, String> fName;
    @FXML
    private TableColumn<UserModel, String> lName;
    @FXML
    private TableColumn<UserModel, String> mobile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        userType.setCellValueFactory(new PropertyValueFactory<>("UserType"));
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        lName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));

        //add your data to the table here.
        ObservableList<UserModel> usersList = loadData();
        userViewTable.setItems(usersList);
    }

    public void goToAdminMenu() throws IOException {
        new MainApplication().changeScene("admin-view.fxml");
    }

    public void onBackBtnClick() throws IOException {
        goToAdminMenu();
    }

    public ObservableList<UserModel> loadData(){

        ObservableList<UserModel> userList = FXCollections.observableArrayList();

        try{
            try (Connection con = new DBConnector().getConnection()) {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM users");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String userType = rs.getString("user_type");
                    String fName = rs.getString("first_name");
                    String lName = rs.getString("last_name");
                    String mobile = rs.getString("mobile");
                    userList.add(new UserModel(username, userType, fName, lName, mobile));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return userList;
    }


}
