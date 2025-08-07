package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.JobSeekerInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    public Label appliedJobsCount;
    public Label availableJobsCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        new JobSeekerInfo().getAvailableJobCount(availableJobsCount);
        new JobSeekerInfo().getAppliedJobCount(appliedJobsCount);
    }
}
