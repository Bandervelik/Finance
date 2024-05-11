
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

public class FifthController extends Application {

    private int userId;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(org.example.finance.FifthController.class.getResource("app_4.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Financial Management");
        stage.setScene(scene);
        stage.show();}

    @FXML
    private Button zvitBtn;

    @FXML
    private Button enterBtn;

    @FXML
    private Button geBtn;

    @FXML
    private Button exitBtn11;

    @FXML
    private Button backBtn;

    @FXML
    void initialize() {
        // Обробник події для кнопки "Назад"
        backBtn.setOnAction(event -> {
            // Відкриття нової сцени при натисканні кнопки "Назад"
            openNewScene("app.fxml", userId);
        });

        // Обробник події для кнопки "Вихід"
        if (exitBtn11 != null) {
            exitBtn11.setOnAction(event -> {
                // Додайте код для виходу з програми або інші дії, які потрібно виконати перед виходом
                System.exit(0); // Цей код завершує програму з кодом виходу 0
            });
        }


    }
    public void openNewScene(String fxmlFile, int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            // Получение текущего Stage из сцены, на которой находится backBtn
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error loading FXML", "An error occurred while loading the FXML file.");
        }
    }

    // Метод для отображения диалогового окна с сообщением об ошибке
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}