package com.example.jobsearchhndnetworkingcd_groupthirtyseven.recruiterControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileInfo implements Initializable {
    @FXML
    public Label nameID;

    @FXML
    public Label contactID;

    @FXML
    public Label roleID;

    @FXML
    public Label emailID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        try{
            Actions.displayInfoForNewUser(nameID,contactID,roleID,emailID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Actions.displayInfo(nameID,contactID,roleID,emailID);
    }
}
