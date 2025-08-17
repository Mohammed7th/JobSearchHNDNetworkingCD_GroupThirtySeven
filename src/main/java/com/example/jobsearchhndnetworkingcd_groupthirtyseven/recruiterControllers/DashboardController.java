package com.example.jobsearchhndnetworkingcd_groupthirtyseven.recruiterControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.RecruiterInfo;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Label postedJobsCount;
    public Label applicantsCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        new RecruiterInfo().getPostedJobCount(postedJobsCount);
        new RecruiterInfo().getApplicantCount(applicantsCount);
    }
}
