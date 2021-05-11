package sample.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public Connection connection;

    public Connection getConnection(){
        String dbName = "bank";
        String userName = "root";
        String userPassword = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/"+dbName,userName,userPassword);
            System.out.println("Connected successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
