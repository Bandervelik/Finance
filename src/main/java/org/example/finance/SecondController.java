package org.example.finance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondController extends Application {

    private int userId;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SecondController.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Financial Management");
        stage.setScene(scene);
        stage.show();}

    @FXML
    private Button zvitBtn;

    @FXML
    private Button enterBtn;

    @FXML
    private Button zilBtn;

    @FXML
    private Button exitBtn11;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backBtn;

    @FXML
    void initialize() {
        initializeButtonHandlers();
    }

    private void initializeButtonHandlers() {
        if (backBtn != null) {
            backBtn.setOnAction(event -> loadNewScene("hello-view.fxml", userId));
        }

        if (enterBtn != null) {
            enterBtn.setOnAction(event -> loadNewScene("app_2.fxml", userId));
        }

        if (zvitBtn != null) {
            zvitBtn.setOnAction(event -> loadNewScene("app_3.fxml", userId));
        }
        if (zilBtn != null) {
            zilBtn.setOnAction(event -> loadNewScene("app_4.fxml", userId));
        }
        if (exitBtn11 != null) {
            exitBtn11.setOnAction(event -> {
                System.exit(0);
            });
        }
    }

    private void loadNewScene(String fxmlFile, int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) enterBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error loading FXML", "An error occurred while loading the FXML file.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
