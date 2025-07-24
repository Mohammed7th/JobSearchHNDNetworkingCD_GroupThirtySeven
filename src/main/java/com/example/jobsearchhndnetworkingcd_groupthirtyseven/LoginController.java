package com.example.jobsearchhndnetworkingcd_groupthirtyseven;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onClickLogin
            () {
      //  welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onClickRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterFromLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = fxmlLoader.load();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();

    }


}