package com.example.freshstart.controllers;

import com.example.freshstart.models.HistoryManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DashboardController {

    private static final String IMAGE_BASE_PATH = "/com/example/freshstart/image/";

    // Map to store polo shirt images and their corresponding try-on preview images
    private static final Map<String, String> TRYON_IMAGE_MAPPING = new HashMap<>() {{
        put("blackpolo.png", "blackpolomen.jpg");
        put("redpolo.png", "redpolomen.jpg");
        put("greenpolo.png", "greenpolomen.jpg");
        put("purplepolo.png", "purplepolomen.jpg");
        put("whitepolo.png", "whitepolomen.jpg");
        put("brownpolo.png", "brownpolomen.jpg");
    }};

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

    @FXML
    private void onTryOnButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "tryon.fxml");
    }

    @FXML
    private void onAnyTryOnButtonClick(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        AnchorPane productPane = (AnchorPane) clickedButton.getParent();

        // Get the t-shirt color from the Label
        Label colorLabel = (Label) productPane.getChildren().stream()
                .filter(node -> node instanceof Label)
                .findFirst()
                .orElse(null);

        // Get the ImageView containing the t-shirt image
        ImageView imageView = (ImageView) productPane.getChildren().stream()
                .filter(node -> node instanceof ImageView)
                .findFirst()
                .orElse(null);

        if (colorLabel != null && imageView != null) {
            String tShirtColor = colorLabel.getText().split(" ")[0];

            // Get the image URL and extract the filename
            String imageUrl = imageView.getImage().getUrl();
            String imageName = getImageNameFromUrl(imageUrl);

            // Get the corresponding try-on preview image name
            String tryOnImageName = TRYON_IMAGE_MAPPING.get(imageName);
            if (tryOnImageName == null) {
                System.err.println("Error: No try-on image mapping found for " + imageName);
                return;
            }

            // Create resource paths for both images
            String shirtResourcePath = IMAGE_BASE_PATH + imageName;
            String tryOnResourcePath = IMAGE_BASE_PATH + tryOnImageName;

            // Verify both resources exist
            URL shirtUrl = getClass().getResource(shirtResourcePath);
            URL tryOnUrl = getClass().getResource(tryOnResourcePath);

            if (shirtUrl == null || tryOnUrl == null) {
                System.err.println("Error: Could not find required image resources");
                return;
            }

            // Add to history with the proper resource paths
            HistoryManager.getInstance().addHistory(tShirtColor, shirtUrl.toExternalForm());

            // Load the try-on page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/freshstart/tryon.fxml"));
            Parent tryOnParent = loader.load();

            // Get the controller and set both the t-shirt and try-on preview information
            TryOnController tryOnController = loader.getController();
            tryOnController.setTShirtInfo(tShirtColor, shirtUrl.toExternalForm(), tryOnUrl.toExternalForm());

            // Show the try-on page
            Scene scene = new Scene(tryOnParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    private String getImageNameFromUrl(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
}