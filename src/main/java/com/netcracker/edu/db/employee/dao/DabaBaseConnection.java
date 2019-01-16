package com.netcracker.edu.db.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DabaBaseConnection {
    public static String JDBC_DRIVER;
    public static String URL;
    public static String USER="root";
    public static String PASSWORD="Self1-dependence";
    public static Connection CONNECTION;

    static {
        JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";

        try {
            Class.forName(JDBC_DRIVER);
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally{
//            CONNECTION.close();
//        }
    }
}
