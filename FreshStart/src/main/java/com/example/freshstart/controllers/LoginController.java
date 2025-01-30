package com.example.freshstart.controllers;

import com.example.freshstart.utils.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMessage;

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        if (UserDatabase.validateUser(enteredUsername, enteredPassword)) {
            NavigationUtil.navigateTo(event, "Dashboard.fxml");
        } else {
            errorMessage.setText("Invalid username or password");
        }
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "Signup.fxml");
    }
}

