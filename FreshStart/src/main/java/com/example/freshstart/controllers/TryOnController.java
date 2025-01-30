package com.example.freshstart.controllers;

import com.example.freshstart.utils.ImageProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TryOnController {

    @FXML
    private ImageView tShirtImageView;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label tShirtLabel;

    private Image tShirtImage;
    private Image tryOnPreviewImage;
    private String currentTShirtUrl;
    private String currentTryOnPreviewUrl;

    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "Dashboard.fxml");
    }

    @FXML
    private void onHistoryButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "history.fxml");
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "Login.fxml");
    }

    public void setTShirtInfo(String color, String shirtImageUrl, String tryOnPreviewUrl) {
        if (tShirtLabel != null) {
            tShirtLabel.setText(color + " T-Shirt");
        }

        if (shirtImageUrl != null && tShirtImageView != null) {
            try {
                // Load both the t-shirt image and the try-on preview
                URL shirtResourceUrl = new URL(shirtImageUrl);
                URL previewResourceUrl = new URL(tryOnPreviewUrl);

                tShirtImage = new Image(shirtResourceUrl.toExternalForm());
                tryOnPreviewImage = new Image(previewResourceUrl.toExternalForm());

                currentTShirtUrl = shirtImageUrl;
                currentTryOnPreviewUrl = tryOnPreviewUrl;

                // Display the t-shirt image initially
                tShirtImageView.setImage(tShirtImage);
            } catch (Exception e) {
                System.err.println("Error loading images: " + e.getMessage());
            }
        }
    }

    @FXML
    private void onCameraButtonClick(ActionEvent event) {
        // Since we have a pre-defined try-on preview, we'll use that instead of camera input
        if (tryOnPreviewImage != null) {
            processAndNavigateToResult(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Preview Not Available");
            alert.setContentText("The try-on preview is not available for this item.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onFileUploadButtonClick(ActionEvent event) {
        // Similar to camera click, we'll use the pre-defined preview
        if (tryOnPreviewImage != null) {
            processAndNavigateToResult(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Preview Not Available");
            alert.setContentText("The try-on preview is not available for this item.");
            alert.showAndWait();
        }
    }

    private void processAndNavigateToResult(ActionEvent event) {
        try {
            // Use the pre-defined try-on preview image
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/freshstart/result.fxml"));
            Parent resultParent = loader.load();
            ResultController resultController = loader.getController();
            resultController.setImage(tryOnPreviewImage);

            Scene scene = new Scene(resultParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.err.println("Error navigating to result page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        if (userImageView == null) {
            System.err.println("Warning: userImageView not injected by FXML");
        }
        if (tShirtImageView == null) {
            System.err.println("Warning: tShirtImageView not injected by FXML");
        }
        if (tShirtLabel == null) {
            System.err.println("Warning: tShirtLabel not injected by FXML");
        }
    }
}