package org.example.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String url = "jdbc:mysql://localhost:3306/stocklidercom";
    private final static String user = "root";
    private final static  String password = "123lidercom456";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
