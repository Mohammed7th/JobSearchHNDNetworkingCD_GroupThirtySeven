package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileInfoController implements Initializable {
    @FXML
    public Label nameID;
    public Label contactID;
    public Label roleID;
    public Label emailID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
       new Actions().displayInfo(nameID,contactID,roleID,emailID);
    }
}
