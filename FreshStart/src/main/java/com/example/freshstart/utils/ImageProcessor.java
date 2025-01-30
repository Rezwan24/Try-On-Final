package com.example.freshstart.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.File;

public class ImageProcessor {

    public static Image overlayTShirt(Image userImage, Image tShirtImage) {
        int width = (int) userImage.getWidth();
        int height = (int) userImage.getHeight();

        WritableImage result = new WritableImage(width, height);
        PixelWriter writer = result.getPixelWriter();
        PixelReader userReader = userImage.getPixelReader();
        PixelReader tShirtReader = tShirtImage.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color userColor = userReader.getColor(x, y);
                Color tShirtColor = tShirtReader.getColor(x, y);

// // If the t-shirt pixel is not transparent, use it; otherwise, use the user image pixel
                if (tShirtColor.getOpacity() > 0.1) {
                    writer.setColor(x, y, tShirtColor);
                } else {
                    writer.setColor(x, y, userColor);
                }
            }
        }

        return result;
    }
// Create a new writable image with the target dimensions
    public static Image resizeImage(Image image, int targetWidth, int targetHeight) {
        WritableImage resizedImage = new WritableImage(targetWidth, targetHeight);
        PixelWriter pixelWriter = resizedImage.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();

        double ratioX = image.getWidth() / targetWidth;
        double ratioY = image.getHeight() / targetHeight;
// Iterate through each pixel of the target image.
        for (int y = 0; y < targetHeight; y++) {
            for (int x = 0; x < targetWidth; x++) {
                int srcX = (int) (x * ratioX);
                int srcY = (int) (y * ratioY);
                Color color = pixelReader.getColor(srcX, srcY);
                pixelWriter.setColor(x, y, color);
            }
        }

        return resizedImage;
    }
}

