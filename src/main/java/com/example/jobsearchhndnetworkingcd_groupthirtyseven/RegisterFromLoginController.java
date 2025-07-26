package com.example.jobsearchhndnetworkingcd_groupthirtyseven;



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


    public void initialize(URL location, ResourceBundle resources){
        onChoiceBox.getItems().addAll("JobSeeker", "Recruiter");
    }



    public void getRegistered() {
        AuthService authService = new AuthService();
        String username =registerUsername.getText();
        String password =registerUserPassword.getText();
        String choice= onChoiceBox.getValue();
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                choice == null || choice.isEmpty()) {

            // Show alert to user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled out.");
            alert.showAndWait();

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
            directory = "JobSeekerHomePage.fxml";
            System.out.println(getClass().getResource(directory));
            nextPage("JobSeekerHomePage.fxml", event, "JobSearch: " + stageName);
        }else{
            stageName ="Logged in as Recruiter";
            directory = "RecruiterHomePage.fxml";
            System.out.println(getClass().getResource(directory));
            nextPage("RecruiterHomePage.fxml", event, "JobSearch: " + stageName);
        }

    }

    public void onClickLogin(ActionEvent event) throws IOException {
        nextPage("Login.fxml", event, "My New Stage Title");

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
