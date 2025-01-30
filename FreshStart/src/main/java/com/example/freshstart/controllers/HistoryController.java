package com.example.freshstart.controllers;

import com.example.freshstart.models.History;
import com.example.freshstart.models.HistoryManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private ListView<HBox> historyListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadHistory();
    }

    private void loadHistory() {
        for (History history : HistoryManager.getInstance().getHistoryList()) {
            HBox historyItem = createHistoryItem(history);
            historyListView.getItems().add(historyItem);
        }
    }

    private HBox createHistoryItem(History history) {
        ImageView imageView = new ImageView(new Image(history.getImageUrl()));
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

        VBox infoBox = new VBox(5);
        Label colorLabel = new Label(history.getTShirtColor() + " T-Shirt");
        colorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Label dateLabel = new Label("Tried on: " + history.getTriedOnDate().format(formatter));
        dateLabel.setStyle("-fx-font-size: 12px;");

        infoBox.getChildren().addAll(colorLabel, dateLabel);
        HBox.setHgrow(infoBox, Priority.ALWAYS);

        HBox historyItem = new HBox(15);
        historyItem.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4; -fx-background-radius: 5;");
        historyItem.getChildren().addAll(imageView, infoBox);

        historyItem.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    openTryOnPage(history.getTShirtColor(), history.getImageUrl(), event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return historyItem;
    }

    private void openTryOnPage(String tShirtColor, String imageUrl, javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/freshstart/tryon.fxml"));
        Parent tryOnParent = loader.load();
        TryOnController tryOnController = loader.getController();

        // Get the corresponding try-on preview image name
        String tryOnPreviewUrl = getTryOnPreviewUrl(imageUrl);

        // Pass all three parameters to setTShirtInfo
        tryOnController.setTShirtInfo(tShirtColor, imageUrl, tryOnPreviewUrl);

        Scene scene = new Scene(tryOnParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private String getTryOnPreviewUrl(String originalUrl) {
        // Extract the filename from the original URL
        String filename = originalUrl.substring(originalUrl.lastIndexOf("/") + 1);
        String previewFilename = filename.replace(".png", "men.jpg");
        return originalUrl.substring(0, originalUrl.lastIndexOf("/") + 1) + previewFilename;
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "Dashboard.fxml");
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "Login.fxml");
    }
}

