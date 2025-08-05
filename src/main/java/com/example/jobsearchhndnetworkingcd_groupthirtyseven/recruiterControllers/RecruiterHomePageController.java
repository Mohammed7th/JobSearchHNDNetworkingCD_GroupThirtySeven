package com.example.jobsearchhndnetworkingcd_groupthirtyseven.recruiterControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class RecruiterHomePageController {
    public AnchorPane contentPane;
    public Button RDashboardBtn;
    public Button RPostAJobBtn;
    public Button RProfileInfo;
    public Button RApplicants;
    Actions actions = new Actions();




    public void onClickRDashboardBtn(ActionEvent event) throws IOException {
        actions.resetButtonStyles(RDashboardBtn,RPostAJobBtn,RProfileInfo,RApplicants);
        RDashboardBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/recruiter/Dashboard.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickRPostAJobBtn(ActionEvent event) throws IOException {
        actions.resetButtonStyles(RDashboardBtn,RPostAJobBtn,RProfileInfo,RApplicants);
        RPostAJobBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/recruiter/PostAJob.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickRProfileInfo(ActionEvent event) throws IOException {
        actions.resetButtonStyles(RDashboardBtn,RPostAJobBtn,RProfileInfo,RApplicants);
       RProfileInfo.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/recruiter/ProfileInfo.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickRApplicantsBtn(ActionEvent event) throws IOException {
        actions.resetButtonStyles(RDashboardBtn,RPostAJobBtn,RProfileInfo,RApplicants);
        RApplicants.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/recruiter/Applicants.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickRLogoutBtn(ActionEvent event) throws IOException {
        AuthService authService = new AuthService();
        authService.logout(event);
    }
}
