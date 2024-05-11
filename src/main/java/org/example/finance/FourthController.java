package org.example.finance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FourthController extends Application {

    private int userId;

    @FXML
    private TableView<Transaction> financeTable;

    @FXML
    private TableColumn<Transaction, Integer> IdColumn;

    @FXML
    private TableColumn<Transaction, String> typeColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private Label incomeLabel;

    @FXML
    private Label expenseLabel;

    @FXML
    private Label totalLabel;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(org.example.finance.FourthController.class.getResource("app_3.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Financial Management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn11;

    @FXML
    void initialize() {
        backBtn.setOnAction(event -> {
            openNewScene("app.fxml");
        });

        exitBtn11.setOnAction(event -> {
            System.exit(0);
        });

        loadTransactions();
    }

    private void loadTransactions() {
        try {
            DatabaseHandler dbHandler = new DatabaseHandler();
            ResultSet incomeResult = dbHandler.getIncome(userId);
            ResultSet expenseResult = dbHandler.getExpense(userId);

            double incomeTotal = 0.0;
            double expenseTotal = 0.0;

            financeTable.getItems().clear();

            while (incomeResult.next()) {
                Transaction transaction = new Transaction(
                        incomeResult.getInt("idincome"),
                        incomeResult.getString("type"),
                        incomeResult.getDouble("amount"),
                        incomeResult.getString("date")
                );
                financeTable.getItems().add(transaction);
                incomeTotal += incomeResult.getDouble("amount");
            }

            while (expenseResult.next()) {
                Transaction transaction = new Transaction(
                        expenseResult.getInt("idexpense"),
                        expenseResult.getString("type"),
                        -expenseResult.getDouble("amount"), // у витратах від'ємне значення
                        expenseResult.getString("date")
                );
                financeTable.getItems().add(transaction);
                expenseTotal += expenseResult.getDouble("amount");
            }

            incomeLabel.setText(Double.toString(incomeTotal));
            expenseLabel.setText(Double.toString(expenseTotal));
            totalLabel.setText(Double.toString(incomeTotal - expenseTotal));

        } catch (SQLException e) {
            showAlert("Error", "Database Error", "An error occurred while accessing the database.");
        }
    }

    public void openNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) backBtn.getScene().getWindow();
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
