package com.example.jobsearchhndnetworkingcd_groupthirtyseven;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import javafx.stage.Stage;

public class RegisterFromLoginController {


    public void onClickLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = fxmlLoader.load();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();

    }

    public void onClickRegister() {

    }
}
