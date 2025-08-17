package com.example.jobsearchhndnetworkingcd_groupthirtyseven.recruiterControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.RecruiterInfo;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicantsController implements Initializable {

    @FXML
    public VBox contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        new RecruiterInfo().retrieveApplicantsForRecruiter(User.getUserID(),contentPane);

    }
}
