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

    private int idusers;

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
    }

    public void addTableData(ResultSet tableData) {
        try {
            double incomeTotal = 0.0;
            double expenseTotal = 0.0;

            financeTable.getItems().clear();

            while (tableData.next()) {
                Transaction transaction = new Transaction(
                        tableData.getInt("idincome"),
                        tableData.getString("type"),
                        tableData.getDouble("amount"),
                        tableData.getString("date")
                );
                financeTable.getItems().add(transaction);

                if (transaction.getAmount() >= 0) {
                    incomeTotal += transaction.getAmount();
                } else {
                    expenseTotal += transaction.getAmount();
                }
            }

            incomeLabel.setText(Double.toString(incomeTotal));
            expenseLabel.setText(Double.toString(expenseTotal));
            totalLabel.setText(Double.toString(incomeTotal - expenseTotal));

        } catch (SQLException e) {
            showAlert("Error", "Database Error", "An error occurred while accessing the database.");
        }
    }

    public void setidusers(int idusers) {
        this.idusers = idusers;
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet incomeResult = dbHandler.getIncome();
        ResultSet expenseResult = dbHandler.getExpense(idusers);
        populateTableData(incomeResult);
        populateTableData(expenseResult);
    }

    public void populateTableData(ResultSet tableData) {
        try {
            double incomeTotal = 0.0;
            double expenseTotal = 0.0;

            financeTable.getItems().clear();

            while (tableData.next()) {
                Transaction transaction = new Transaction(
                        tableData.getInt("id"), // assuming the column name for ID
                        tableData.getString("type"), // assuming the column name for type
                        tableData.getDouble("amount"),
                        tableData.getString("date")
                );
                financeTable.getItems().add(transaction);

                if (transaction.getAmount() >= 0) {
                    incomeTotal += transaction.getAmount();
                } else {
                    expenseTotal += transaction.getAmount();
                }
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