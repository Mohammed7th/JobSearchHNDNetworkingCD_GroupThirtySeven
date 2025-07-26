package com.example.jobsearchhndnetworkingcd_groupthirtyseven;



import com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;

public class LoginController {


    public TextField usernameID;
    public TextField passwordID;

    @FXML
    protected void onClickLogin(ActionEvent event) throws Exception {
        AuthService authService = new AuthService();
        boolean loginSuccessful;
        String role = "";

        // Validate input fields first
        if (usernameID.getText().isEmpty() || passwordID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Username and password fields cannot be empty.");
            alert.showAndWait();
            return;
        }

        // Perform login
        loginSuccessful = authService.Login(usernameID.getText(), passwordID.getText());

        if (loginSuccessful) {
            ResultSet rs = authService.getRole();
            if (rs != null && rs.next()) {
                role = rs.getString("role");
            } else {
                System.out.println("Role not found.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Authentication Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username or password. Please try again.");
            alert.showAndWait();
            return;
        }

        // Route user based on role
        String stageName, directory;
        if ("JobSeeker".equals(role)) {
            stageName = "Logged in as JobSeeker";
            directory = "JobSeekerHomePage.fxml";
        } else {
            stageName = "Logged in as Recruiter";
            directory = "RecruiterHomePage.fxml";
        }

        nextPage(directory, event, "JobSearch: " + stageName);
    }


    public void onClickRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterFromLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = fxmlLoader.load();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();

    }

    private void nextPage(String name, ActionEvent event, String My_New_Stage_Title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = fxmlLoader.load();
        stage.setTitle(My_New_Stage_Title);
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
    }
}