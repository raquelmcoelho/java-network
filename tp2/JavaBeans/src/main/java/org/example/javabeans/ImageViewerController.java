package org.example.javabeans;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ImageViewerController {

    @FXML private ImageView imageView;
    @FXML private Label labelImageCount, labelFolderName, labelTimer;
    @FXML private Button btnSelectDirectory, btnFirst, btnPrevious, btnNext, btnLast;
    @FXML private CheckBox checkLoop, checkDiaporama;
    @FXML private TextField textDelay;

    private final DirectoryObserver directoryObserver = new DirectoryObserver();

    private Timeline diaporamaTimeline;
    private List<File> imageFiles;
    private int currentIndex = 0;

    @FXML
    public void initialize() {
        updateButtonsState();
        directoryObserver.addPropertyChangeListener(evt -> {
            labelImageCount.setText("Total of images: " + evt.getNewValue());
        });
    }

    @FXML
    private void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            labelFolderName.setText(selectedDirectory.getName());
            loadImages(selectedDirectory);
        }

        updateButtonsState();
    }


    private void loadImages(File directory) {
        File[] files = directory.listFiles((dir, name) -> name.matches(".*\\.(jpg|png|jpeg)"));
        if (files != null) {
            imageFiles = Arrays.asList(files);
            directoryObserver.setImageCount(imageFiles.size());
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

    @FXML
    private void toggleDiaporama() {
        if (checkDiaporama.isSelected()) {
            startDiaporama();
        } else {
            stopDiaporama();
        }
    }

    private void startDiaporama() {
        if (imageFiles == null || imageFiles.isEmpty()) {
            checkDiaporama.setSelected(false);
            return;
        }

        double delayInSeconds;
        try {
            delayInSeconds = Double.parseDouble(textDelay.getText());
            if (delayInSeconds <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            textDelay.setText("1");
            delayInSeconds = 1;
        }

        diaporamaTimeline = new Timeline(new KeyFrame(Duration.seconds(delayInSeconds), event -> {
            showNextImage();
        }));
        diaporamaTimeline.setCycleCount(Timeline.INDEFINITE);
        diaporamaTimeline.play();
    }

    private void stopDiaporama() {
        if (diaporamaTimeline != null) {
            diaporamaTimeline.stop();
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
