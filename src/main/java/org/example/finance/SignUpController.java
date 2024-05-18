package org.example.finance;

import javafx.application.Application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignUpController extends Application {

    private int idusers; // Зберігатиме ідентифікатор користувача

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Financial Management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button backBtn;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signUpButton;

    @FXML
    private Button exitBtn11;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpName;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            signUpNewUser();
        });

        backBtn.setOnAction(event -> { // Обробник подій для кнопки "Назад"
            openNewScene("hello-view.fxml", idusers);
        });

        exitBtn11.setOnAction(event -> {
            System.exit(0); // Цей код завершує програму з кодом виходу 0
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpLastName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();

        // Перевірка на порожні поля
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            showAlert("Помилка реєстрації", null, "Будь ласка, заповніть всі поля.");
            return;
        }

        User user = new User(idusers, firstName, lastName, userName, password);
        ResultSet userRow = dbHandler.getUser(user);

        try {
            if (!userRow.next()) {
                dbHandler.signUpUser(user);
                // Отримання ідентифікатора користувача після реєстрації
                ResultSet resSet = dbHandler.getUser(user);
                int idusers = 0;
                while (resSet.next()) {
                    idusers = resSet.getInt("idusers");
                }
                openNewScene("app.fxml", idusers);
            } else {
                showAlert("Реєстрація", null, "Користувач вже зареєстрований.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void openNewScene(String window, int idusers) {
        signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (window.equals("app_2.fxml")) {
            ThirdController third = loader.getController();
            third.setidusers(idusers);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    // Метод для отримання ідентифікатора користувача після реєстрації
    public int getidusers() {
        return idusers;
    }
}
