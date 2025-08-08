package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.authentication.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class JobSeekerHomePageController implements Initializable {
    @FXML
    public AnchorPane contentPane;

    @FXML
    public Button JSDashboardBtn;

    @FXML
    public Button JSApplyForJobsBtn;

    @FXML
    public Button JSProfileInfoBtn;

    @FXML
    public Button JSAppliedJobsBtn;

    Actions actions = new Actions();

    public void initialize(URL url, ResourceBundle resourceBundle){
        JSDashboardBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/Dashboard.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSDashboardBtn() throws IOException {
        actions.resetButtonStyles(JSDashboardBtn,JSApplyForJobsBtn,JSProfileInfoBtn,JSAppliedJobsBtn);
        JSDashboardBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/Dashboard.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSApplyForJobsBtn() throws IOException {
        actions.resetButtonStyles(JSDashboardBtn,JSApplyForJobsBtn,JSProfileInfoBtn,JSAppliedJobsBtn);
        JSApplyForJobsBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/ApplyForJob.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSProfileInfoBtn(ActionEvent event) throws IOException {
        actions.resetButtonStyles(JSDashboardBtn,JSApplyForJobsBtn,JSProfileInfoBtn,JSAppliedJobsBtn);
        JSProfileInfoBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/ProfileInfo.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSAppliedJobsBtn(ActionEvent event) throws IOException {
        actions.resetButtonStyles(JSDashboardBtn,JSApplyForJobsBtn,JSProfileInfoBtn,JSAppliedJobsBtn);
        JSAppliedJobsBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/AppliedJobs.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSLogout(ActionEvent event) throws IOException {
        AuthService authService = new AuthService();
        authService.logout(event);
    }
}
