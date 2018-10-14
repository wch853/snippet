package com.wch.snippet.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * jdbc连接工具
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/snippet?useUnicode=true&characterEncoding=utf8";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
