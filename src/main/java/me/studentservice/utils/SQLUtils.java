package me.studentservice.utils;

import java.sql.*;

public class SQLUtils {

    private static SQLUtils instance;

    private Connection connection;

    private SQLUtils() {}

    public static SQLUtils getInstance() {
        if(instance == null) {
            instance = new SQLUtils();
        }
        return instance;
    }

    public void createTables(String sql) {
        try {
            connect();
            connection.createStatement().execute(sql);
            disconnect();
        } catch(SQLException ignored) {}
    }

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
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
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
