package com.example.freshstart.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationUtil {

    public static void navigateTo(ActionEvent event, String fxmlFile) throws IOException {
        // Load the FXML file
        // - Uses the class loader to find the resource file in the specified path
        // - Parses the FXML file and creates a graph of Java objects
        Parent parent = FXMLLoader.load(NavigationUtil.class.getResource("/com/example/freshstart/" + fxmlFile));

        // Create a new Scene with the loaded FXML content
        // - A Scene is a container for all content in a stage
        Scene scene = new Scene(parent);

        // Get the current Stage (window)
        // - event.getSource() returns the object (e.g., Button) that triggered the event
        // - ((Node) event.getSource()) casts it to a Node
        // - .getScene() gets the Scene that this Node is part of
        // - .getWindow() gets the Window that contains this Scene
        // - (Stage) casts the Window to a Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new Scene on the Stage
        // - This replaces the current content of the window with the new Scene
        window.setScene(scene);

        // Display the updated Stage
        // - Ensures that the window is visible with the new content
        window.show();
    }
}