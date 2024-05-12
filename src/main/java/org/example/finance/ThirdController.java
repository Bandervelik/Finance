package org.example.finance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ThirdController extends Application {

    private int userId;

    @FXML
    private Button enterBtn;

    @FXML
    private Button SaveButton;

    @FXML
    private Button SaveButton1;

    @FXML
    private Button exitBtn11;

    @FXML
    private Button backBtn;

    @FXML
    private MenuButton incomeTypeMenuButton;

    @FXML
    private MenuButton expenseTypeMenuButton;

    @FXML
    private TextField amount;

    @FXML
    private TextField amount1;

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker date1;

    private DatabaseHandler databaseHandler;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app_2.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("Financial Management");
        stage.setScene(scene);
        stage.show();
        databaseHandler = new DatabaseHandler(); // Ініціалізація об'єкта databaseHandler
    }

    @FXML
    void initialize() {

        SaveButton.setOnAction(event -> {
            saveIncome(userId);
        });

        SaveButton1.setOnAction(event -> {
            saveExpense(userId);
        });

        backBtn.setOnAction(event -> {
            openNewScene("app.fxml");
        });

        if (exitBtn11 != null) {
            exitBtn11.setOnAction(event -> {
                System.exit(0);
            });
        }

        // Додайте обробник події для кожного MenuItem у MenuButton для доходів
        for (MenuItem item : incomeTypeMenuButton.getItems()) {
            item.setOnAction(event -> {
                incomeTypeMenuButton.setText(item.getText());
            });
        }
        for (MenuItem item : expenseTypeMenuButton.getItems()) {
            item.setOnAction(event -> {
                expenseTypeMenuButton.setText(item.getText());
            });
        }
    }
    DatabaseHandler dbHandler = new DatabaseHandler();
    private void openNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Помилка", "Помилка завантаження FXML", "Під час завантаження файлу FXML сталася помилка.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void saveExpense(int userId) {


        String type = expenseTypeMenuButton.getText();
        String amount1Text = amount1.getText();
        LocalDate localDate1 = date1.getValue();
        String date1String = localDate1.toString();

        // Перевірка, чи всі поля заповнені
        if (type.isEmpty() || amount1Text.isEmpty() || date1String.isEmpty()) {
            showAlert("Помилка", "Відсутні дані", "Будь ласка, заповніть усі поля.");
            return;
        }

        try {
            double amount1Value = Double.parseDouble(amount1Text);
            // Створення об'єкта доходу з отриманими даними
            Expense expense = new Expense(userId, type, amount1Value, date1String);
            // Збереження доходу в базу даних
            dbHandler.addExpense(expense);
            showAlert("Успіх", "Витрати збережено", "Інформацію про витрату успішно збережено.");
        } catch (NumberFormatException e) {
            showAlert("Помилка", "Неправильна сума", "Будь ласка, введіть правильну суму витрати.");
        }
    }

    private void saveIncome(int userId) {

        String type = incomeTypeMenuButton.getText();
        String amountText = amount.getText();
        LocalDate localDate = date.getValue();
        String dateString = localDate.toString();

        // Перевірка, чи всі поля заповнені
        if (type.isEmpty() || amountText.isEmpty() || dateString.isEmpty()) {
            showAlert("Помилка", "Відсутні дані", "Будь ласка, заповніть усі поля.");
            return;
        }

        try {
            double amountValue = Double.parseDouble(amountText);
            // Створення об'єкта доходу з отриманими даними
            Income income = new Income(userId, type, amountValue, dateString);
            // Збереження доходу в базу даних
            dbHandler.addIncome(income);
            showAlert("Успіх", "Дохід збережено", "Інформацію про прибуток успішно збережено.");
        } catch (NumberFormatException e) {
            showAlert("Помилка", "Неправильна сума", "Будь ласка, введіть правильну суму для доходу.");
        }
    }



}
