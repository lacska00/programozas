package hu.unideb.inf;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {

        //java.sql.Connection conn = null;

        String databaseName = "teszt";
        String databaseUser = "root";
        String databasePassword  = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //conn = (java.sql.Connection) DriverManager.getConnection("jdbc.mysql://localhost:3306/adatb","root","");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);


        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
