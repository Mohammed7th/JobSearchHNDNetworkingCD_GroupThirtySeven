package com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;

import java.sql.PreparedStatement;

public class AuthService {

    public void Register(String username, String password,String role){
       try {
           String query = "INSERT INTO users (username, password,role) VALUES (?, ?,?)";
           PreparedStatement stmt = DBAccess.connect().prepareStatement(query);
           stmt.setString(1, username);
           stmt.setString(2, password);
           stmt.setString(3, role);
           stmt.executeUpdate();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
