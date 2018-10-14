package com.wch.snippet.jdbc;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

    @Test
    public void test1() {
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SHOW TABLES;";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
