package com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthService {
    Actions actions = new Actions();
    private String username;
    private String password;
    private int userID;

    public void Register(String username, String password, String role, String email, int phoneNumber) {
        try {
            String query = "INSERT INTO users (username, password,role,email,phoneNumber) VALUES (?, ?,?,?,?)";
            PreparedStatement stmt = DBAccess.connect().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setString(3, email);
            stmt.setInt(3, phoneNumber);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Login(String username, String password) {

        try {
            String query = "SELECT * FROM users WHERE username =? AND password =?";
            PreparedStatement stmt = DBAccess.connect().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            this.username = username;
            this.password = password;

            if (resultSet.next()) {
                String userName = resultSet.getString("username");
                int iD = resultSet.getInt("id");
                String email = resultSet.getString("email");
                int contact = resultSet.getInt("phoneNumber");
                User.setUserNameAndID(userName, iD, email, contact);
                return true;
            }

        } catch (Exception e) {
            System.out.println("LoginException: " + e);

        }
        return false;
    }

    public ResultSet getRole() {
        try {
            String query = "SELECT role FROM users WHERE username =? AND password =?";
            PreparedStatement stmt = DBAccess.connect().prepareStatement(query);
            stmt.setString(1, this.username);
            stmt.setString(2, this.password);
            return stmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("GetRoleException: " + e);
        }
        return null;
    }

    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Click OK to confirm.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            actions.nextPage(700, 400, "/Login.fxml", event, "Login to JobSearch");
        }
    }
}
