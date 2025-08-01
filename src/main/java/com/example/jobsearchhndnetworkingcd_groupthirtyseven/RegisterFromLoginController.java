package com.example.jobsearchhndnetworkingcd_groupthirtyseven;



import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication.AuthService;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterFromLoginController implements Initializable {


    @FXML
    public ChoiceBox<String> onChoiceBox;
    public TextField registerUsername;
    public TextField registerUserPassword;

    Actions actions = new Actions();

    //function for adding the user types to the choiceBox
    public void initialize(URL location, ResourceBundle resources){
        onChoiceBox.getItems().addAll("JobSeeker", "Recruiter");
    }

    //function to register user
    public void getRegistered() {
        AuthService authService = new AuthService();
        String username =registerUsername.getText();
        String password =registerUserPassword.getText();
        String choice= onChoiceBox.getValue();
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                choice == null || choice.isEmpty()) {

            // Show alert to user
            Actions.showAlertBox("Input Error","All fields must be filled out.");
            return; // stop registration
        }

// Proceed if valid

       try {
           authService.Register(username,password,choice);
       } catch (Exception e) {
           System.out.println("Exception with authService: "+e);
       }
    }

    public void onClickRegister(ActionEvent event) throws IOException {
       getRegistered();

        String directory;
        String stageName;

        String checkChoice =onChoiceBox.getValue();
        if ("JobSeeker".equals(checkChoice)){
            stageName ="Logged in as JobSeeker";
            actions.nextPage(
                    829,
                    523,
                    "/jobSeeker/JobSeekerHomePage.fxml",
                    event,
                    "JobSearch: " + stageName
            );
        }else{
            stageName ="Logged in as Recruiter";
            actions.nextPage(
                    829,
                    523,
                    "/recruiter/RecruiterHomePage.fxml",
                    event,
                    "JobSearch: " + stageName
            );
        }

    }

    public void onClickLogin(ActionEvent event) throws IOException {
        actions.nextPage(
                700,
                400,
                "/Login.fxml",
                event,
                "My New Stage Title"
        );

    }



}
