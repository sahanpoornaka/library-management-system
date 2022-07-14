package com.lms.library_manager.dbo;

import javafx.beans.property.SimpleStringProperty;

public class UserModel {

    private SimpleStringProperty username;
    private SimpleStringProperty userType;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty mobile;

    public UserModel(String username, String userType, String fName, String lName, String mobile) {
        this.username = new SimpleStringProperty(username);
        this.userType = new SimpleStringProperty(userType);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.mobile = new SimpleStringProperty(mobile);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getUserType() {
        return userType.get();
    }

    public SimpleStringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public String getfName() {
        return fName.get();
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getlName() {
        return lName.get();
    }

    public SimpleStringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public String getMobile() {
        return mobile.get();
    }

    public SimpleStringProperty mobileProperty() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }
}
