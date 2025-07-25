package com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

    private static final String USER = "Mohammed";
    private static final String DBNAME = "job_search";
    private static final String PASSWORD = "eminence_07";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME + "?useSSL=false";

    public static Connection connect() throws SQLException, ClassNotFoundException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {

         return (Connection) e;

        }
    }


}
