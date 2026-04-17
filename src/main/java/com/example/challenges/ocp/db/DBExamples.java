package com.example.challenges.ocp.db;

import java.sql.*;

public class DBExamples {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/my_db";
        String query = "SELECT * FROM contact";

        try (Connection connection = DriverManager.getConnection(url, "user", "password");
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
             // int ps.executeUpdate()
             // boolean ps.execute()
             // ResultSet ps.getResultSet()
             // int ps.executeUpdate()
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.printf("%d - %s %s%n", id, firstName, lastName);
            }
            if (connection != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String procedureCall = "{call read_by_name(?)}";

        try (Connection connection = DriverManager.getConnection(url, "user2", "passsss");
             CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
            callableStatement.setString(1, "john");
            boolean hasResults = callableStatement.execute();
            if (hasResults) {
                ResultSet rs = callableStatement.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            } else {
                System.out.println("No results");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
