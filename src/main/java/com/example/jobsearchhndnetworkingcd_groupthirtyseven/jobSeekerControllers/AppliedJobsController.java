package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.JobSeekerInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AppliedJobsController implements Initializable {

    @FXML
    public VBox cardContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        new JobSeekerInfo().retrieveAppliedJobs(cardContainer);

    }
}
