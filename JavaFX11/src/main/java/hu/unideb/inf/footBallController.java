package hu.unideb.inf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class footBallController  implements Initializable {

    @FXML private ListView listView;

    @FXML
    private ListView<String> list;

    private ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void load(ActionEvent event)  throws SQLException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //list.getItems().addAll("Golf Balls","Wedges","Irons","Tees","Driver","Putter");
        try {
            Statement statement = connectDB.createStatement();
            ResultSet qResult = statement.executeQuery("SELECT * FROM `football`");
            while (qResult.next()) {
                items.add(qResult.getString(1));
                System.out.println(qResult.getString(1));
                listView.getItems().add(qResult.getString(1) + " " + qResult.getString(2) + " "  +
                        qResult.getString(3) + " " + qResult.getString(4) + " "  +
                        qResult.getString(5) + " " + qResult.getString(6) + " "  +
                        qResult.getString(7));
            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    @FXML
    public void mainPage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/prog.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
