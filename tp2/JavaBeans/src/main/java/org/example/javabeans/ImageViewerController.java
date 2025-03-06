package org.example.javabeans;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ImageViewerController {

    @FXML private ImageView imageView;
    @FXML private Label labelImageCount;
    @FXML private Button btnFirst, btnPrevious, btnNext, btnLast;
    @FXML private CheckBox checkLoop, checkDiaporama;
    @FXML private TextField textDelay;

    private List<File> imageFiles;
    private int currentIndex = 0;

    @FXML
    public void initialize() {
        updateButtonsState();
    }

    @FXML
    private void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            loadImages(selectedDirectory);
        }

        updateButtonsState();
    }

    private void loadImages(File directory) {
        File[] files = directory.listFiles((dir, name) -> name.matches(".*\\.(jpg|png|jpeg)"));
        if (files != null) {
            imageFiles = Arrays.asList(files);
            labelImageCount.setText("Total of images: " + imageFiles.size());
            if (!imageFiles.isEmpty()) {
                currentIndex = 0;
                showImage();
            }
        }
    }

    @FXML
    private void showFirstImage() {
        if (imageFiles != null && !imageFiles.isEmpty()) {
            currentIndex = 0;
            showImage();
        }
    }

    @FXML
    private void showLastImage() {
        if (imageFiles != null && !imageFiles.isEmpty()) {
            currentIndex = imageFiles.size() - 1;
            showImage();
        }
    }

    @FXML
    private void showNextImage() {
        if (imageFiles != null && !imageFiles.isEmpty()) {
            if (currentIndex < imageFiles.size() - 1) {
                currentIndex++;
            } else if (checkLoop.isSelected()) {
                currentIndex = 0;
            }
            showImage();
        }
    }

    @FXML
    private void showPreviousImage() {
        if (imageFiles != null && !imageFiles.isEmpty()) {
            if (currentIndex > 0) {
                currentIndex--;
            } else if (checkLoop.isSelected()) {
                currentIndex = imageFiles.size() - 1;
            }
            showImage();
        }
    }

    private void showImage() {
        if (imageFiles != null && !imageFiles.isEmpty()) {
            imageView.setImage(new Image(imageFiles.get(currentIndex).toURI().toString()));
        }
    }

    private void updateButtonsState() {
        boolean hasImages = imageFiles != null && !imageFiles.isEmpty();
        btnFirst.setDisable(!hasImages);
        btnPrevious.setDisable(!hasImages);
        btnNext.setDisable(!hasImages);
        btnLast.setDisable(!hasImages);
    }
}
