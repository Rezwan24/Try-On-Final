package com.example.freshstart.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ResultController {

    @FXML
    private ImageView selectedImageView;

    public void setImage(Image image) {
        selectedImageView.setImage(image);
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) throws IOException {
        NavigationUtil.navigateTo(event, "tryon.fxml");
    }
}

