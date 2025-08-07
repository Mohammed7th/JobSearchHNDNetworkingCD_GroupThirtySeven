package com.example.jobsearchhndnetworkingcd_groupthirtyseven.recruiterControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileInfo implements Initializable {

    public Label nameID;
    public Label contactID;
    public Label roleID;
    public Label emailID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        new Actions().displayInfo(nameID,contactID,roleID,emailID);
    }
}
