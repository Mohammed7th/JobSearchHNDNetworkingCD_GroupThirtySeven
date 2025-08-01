package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class JobSeekerHomePageController {
    @FXML
    public AnchorPane contentPane;

    @FXML
    public Button JSDashboardBtn;

    @FXML
    public Button JSApplyForJobsBtn;

    @FXML
    public Button JSProfileInfoBtn;

    private void resetButtonStyles() {
        JSDashboardBtn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
        JSApplyForJobsBtn.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
        JSProfileInfoBtn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
    }

    public void onClickJSDashboardBtn() throws IOException {
        resetButtonStyles();
        JSDashboardBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/Dashboard.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSApplyForJobsBtn() throws IOException {
        resetButtonStyles();
        JSApplyForJobsBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/ApplyForJob.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSProfileInfoBtn(ActionEvent event) throws IOException {
        resetButtonStyles();
        JSProfileInfoBtn.setStyle("-fx-background-color: #DC2F02; -fx-text-fill: white;");
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/ProfileInfo.fxml")));
        contentPane.getChildren().setAll(pane);
    }

    public void onClickJSLogout(ActionEvent event) {
    }
}
