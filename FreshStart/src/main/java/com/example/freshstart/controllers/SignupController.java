package com.example.freshstart.controllers;

import com.example.freshstart.utils.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMessage;

    @FXML
    private void onSignUpButtonClick(ActionEvent event) throws IOException {
        String userName = username.getText();
        String userPassword = password.getText();

        if (userName.isEmpty() || userPassword.isEmpty()) {
            errorMessage.setText("Username and password cannot be empty");
            return;
        }

        UserDatabase.addUser(userName, userPassword);

        // Debug information
        System.out.println("User added: " + userName);
        System.out.println("File location: " + UserDatabase.getFilePath());

        NavigationUtil.navigateTo(event, "Login.fxml");
    }
}

