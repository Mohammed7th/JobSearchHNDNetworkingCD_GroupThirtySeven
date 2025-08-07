package com.example.jobsearchhndnetworkingcd_groupthirtyseven;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.ResultSet;


public class LoginController {

    public TextField usernameID;
    public TextField passwordID;
    Actions actions = new Actions();

    @FXML
    protected void onClickLogin(ActionEvent event) throws Exception {
        AuthService authService = new AuthService();
        boolean loginSuccessful;
        String role = "";

        // Validate input fields first
        if (usernameID.getText().isEmpty() || passwordID.getText().isEmpty()) {
            Actions.showAlertBox("Input Error","Username and password fields cannot be empty.");
            return;
        }

        // Perform login
        loginSuccessful = authService.Login(usernameID.getText(), passwordID.getText());

        if (loginSuccessful) {
            ResultSet rs = authService.getRole();
            if (rs != null && rs.next()) {
                role = rs.getString("userRole");
            } else {
                System.out.println("Role not found.");
            }
        } else {
            Actions.showAlertBox("Authentication Error","Incorrect username or password. Please try again.");
            return;
        }

        // Route user based on role
        String stageName, directory;
        if ("JobSeeker".equals(role)) {
            stageName = "Logged in as JobSeeker";
            directory = "/jobSeeker/JobSeekerHomePage.fxml";
        } else {
            stageName = "Logged in as Recruiter";
            directory = "/recruiter/RecruiterHomePage.fxml";
        }

        actions.nextPage(
                829,
                523,
                directory,
                event,
                "JobSearch: " + stageName
        );
    }


    public void onClickRegister(ActionEvent event) throws IOException {
        actions.nextPage(
                700,
                400,
                "/RegisterFromLogin.fxml",
                event,
                "My New Stage Title"
        );
    }


}