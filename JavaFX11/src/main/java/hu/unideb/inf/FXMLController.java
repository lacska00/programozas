package hu.unideb.inf;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private Label invalidLabel;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loginButtonAction() {
        if(userField.getText().isEmpty() == false && passField.getText().isEmpty() == false) {
            invalidLabel.setText("Próbálj belépni!");
        } else {
            invalidLabel.setText("Kérem adjon meg felhasználónevet és jelszót is!");
        }
    }


    @FXML
    public void close(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


    @FXML
    public void validateLogin(ActionEvent event) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM `account` WHERE felhnev = '" + userField.getText() + "' AND jelszo = '" + passField.getText() + "'";
        System.out.println(verifyLogin);

        try {
            Statement statement = connectDB.createStatement();
            ResultSet qResult = statement.executeQuery(verifyLogin);


            while(qResult.next()) {
                if(qResult.getInt(1) == 1) {
                    invalidLabel.setText("Sikerült!");
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/prog.fxml"));
                        Stage progStage = new Stage();
                        Scene scene = new Scene(root);

                        progStage.setScene(scene);
                        progStage.show();
                        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

                    }catch(Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                } else {
                    invalidLabel.setText("Hiba! Helytelen felhasználónév és/vagy jelszó!");
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    public void createAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(root);

            registerStage.setScene(scene);
            registerStage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
