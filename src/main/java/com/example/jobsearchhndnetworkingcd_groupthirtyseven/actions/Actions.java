package com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Actions {
    public void nextPage(int width,int height,String name, ActionEvent event, String My_New_Stage_Title) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven"+name));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = fxmlLoader.load();
        stage.setTitle(My_New_Stage_Title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    public static void showAlertBox(String errorType,String errorMessage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(errorType);
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
