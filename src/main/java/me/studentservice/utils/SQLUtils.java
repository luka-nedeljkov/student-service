package me.studentservice.utils;

import java.sql.*;

public class SQLUtils {

    private Connection connection;

    public void executeQuery(String sql) {
        try {
            connect();
            connection.createStatement().execute(sql);
            disconnect();
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet exequteSelectQuery(String sql) {
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(sql);
            return rs;
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/student_service", "root", "root");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

}
