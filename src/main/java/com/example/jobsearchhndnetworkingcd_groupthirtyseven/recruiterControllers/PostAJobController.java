package com.example.jobsearchhndnetworkingcd_groupthirtyseven.recruiterControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.RecruiterInfo;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PostAJobController implements Initializable {

    RecruiterInfo recruiterInfo = new RecruiterInfo();

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public TextField JobTitleInput;

    @FXML
    public TextArea JobDespriptionInput;

    @FXML
    public Button SubmitJobBtn;

    @FXML
    public VBox cardContainerID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int userID = User.getUserID();
        recruiterInfo.retrieveAndAddCardInfo(userID, cardContainerID);
        scrollPane.setFitToWidth(true);
    }

    public void onClickSubmitJob(ActionEvent event) {
        String jobTitle = JobTitleInput.getText();
        String jobDescription = JobDespriptionInput.getText();
        int userIdentification = User.getUserID();


        if (jobTitle == null || jobTitle.isEmpty() || jobDescription == null || jobDescription.isEmpty()) {
            Actions.showAlertBox("Input Error", "All fields Should be filled");
            return;
        }

//        if (userIdentification == null) {
//            userIdentification = Actions.getUserIdentity();
//        }

        try {

            recruiterInfo.postJobToDb(jobTitle, jobDescription, userIdentification);
            cardContainerID.getChildren().clear();
            recruiterInfo.retrieveAndAddCardInfo(userIdentification, cardContainerID);
            JobTitleInput.setText("");
            JobDespriptionInput.setText("");
            Actions.showAlertBox("Success", "Successfully submitted Job Openning");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }
}
