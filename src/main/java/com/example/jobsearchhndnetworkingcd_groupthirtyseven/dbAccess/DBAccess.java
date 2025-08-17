package com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

    private static final String USER = "Mohammed";
    private static final String DBNAME = "job_search";
    private static final String PASSWORD = "eminence_07";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database!", e);
        }
    }



}
