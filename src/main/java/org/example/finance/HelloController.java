package org.example.finance;

import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import org.example.finance.animation.Shake;
import javafx.application.Application;
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

public class HelloController extends Application {

    private int idusers;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Financial Management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button exitBtn11;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            setidusers(idusers);
            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            } else{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Помилка реєстрації");
                alert.setHeaderText(null);
                alert.setContentText("Будь ласка, заповніть всі поля.");

                alert.showAndWait();
            }
        });

        exitBtn11.setOnAction(event -> {
            System.exit(0);
        });

        loginSignUpButton.setOnAction(event -> {
            openNewScene("signUp.fxml", idusers);
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User(0, loginText, "", "", loginPassword);
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while(true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if(counter >= 1) {
            openNewScene("app.fxml", this.idusers);
        } else {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.PlayAnim();
            userPassAnim.PlayAnim();
        }
    }

    public void openNewScene(String window, int idusers) {
        loginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(SignUpController.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (window.equals("app_2.fxml")) {
            ThirdController thirdController = loader.getController();
            thirdController.setidusers(idusers);
        }
        if (window.equals("user_panel.fxml")) {
            FifthController fifthController = loader.getController();
            fifthController.setidusers(idusers);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setidusers(int idusers) {
        this.idusers = idusers;
        DatabaseHandler dbHandler = new DatabaseHandler();
    }

    public static void main(String[] args) {
        launch();
    }
}
