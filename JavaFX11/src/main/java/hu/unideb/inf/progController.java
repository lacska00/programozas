package hu.unideb.inf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class progController {
    @FXML
    public void footballPage(ActionEvent event) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/footBall.fxml"));
            Stage footballStage = new Stage();
            Scene scene2 = new Scene(root);

            footballStage.setScene(scene2);
            footballStage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
