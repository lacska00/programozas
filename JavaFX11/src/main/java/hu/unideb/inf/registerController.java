package hu.unideb.inf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class registerController {

    @FXML
    private TextField accountName;

    @FXML
    private TextField fullName;

    @FXML
    private PasswordField passWord;

    @FXML
    private PasswordField passWordVerify;
    @FXML
    private Label message;

    @FXML
    private void registerButtonAction(ActionEvent event) {
        if(passWord.getText().equals(passWordVerify.getText()) && accountName.getText().isEmpty() == false && fullName.getText().isEmpty() == false) {
            registerUser();
        } else if(passWord.getText().equals(passWordVerify.getText())){
            message.setText("Minden mező kitöltése kötelező!");
        } else {
            message.setText("A jelszó nem egyezik!");
        }

    }

    @FXML
    private void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String teljesNev = fullName.getText();
        String felhasznaloNev = accountName.getText();
        String jelszo = passWord.getText();

        String insertField = "INSERT INTO account(teljesnev, felhnev, jelszo) VALUES ('";
        String stringValues = teljesNev + "','"  + felhasznaloNev + "','" + jelszo + "')";
        String insertToRegister = insertField + stringValues;
        System.out.println(insertToRegister);

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            message.setText("Sikeres regisztráció!");
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    @FXML
    public void backToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Stage loginStage = new Stage();
            Scene scene = new Scene(root);

            loginStage.setScene(scene);
            loginStage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
