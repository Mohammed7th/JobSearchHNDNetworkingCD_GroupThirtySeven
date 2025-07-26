package com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private String username;
    private String password;

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

    public boolean Login(String username, String password){

        try{
            String query = "SELECT * FROM users WHERE username =? AND password =?";
            PreparedStatement stmt = DBAccess.connect().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultSet= stmt.executeQuery();
            this.username = username;
            this.password = password;
           return resultSet.next();
        } catch (Exception e) {
            System.out.println("LoginException: "+e);

        }
        return false;
    }

    public ResultSet getRole(){
        try {
            String query = "SELECT role FROM users WHERE username =? AND password =?";
            PreparedStatement stmt = DBAccess.connect().prepareStatement(query);
            stmt.setString(1,this.username);
            stmt.setString(2,this.password);
            return stmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("GetRoleException: "+e);
        }
        return null;
    }
}
