package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.JobSeekerInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    public Label appliedJobsCount;

    @FXML
    public Label availableJobsCount;

    @FXML
    public AnchorPane container;

    @FXML
    public TextField searchKeyword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new JobSeekerInfo().getAvailableJobCount(availableJobsCount);
        new JobSeekerInfo().getAppliedJobCount(appliedJobsCount);
    }

    public void onClickSearch(ActionEvent event) throws IOException {
        String searchWord = searchKeyword.getText();
        if (searchWord == null || searchWord.isEmpty()) {
            Actions.showAlertBox("Input Error", "search field should not be empty");
            return;
        }


        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven/jobSeeker/SearchResultsScreen.fxml")));
            SearchResultsScreen controller = new SearchResultsScreen();
            controller.setSearchItem(searchWord);
            loader.setController(controller);
            Parent searchResultsUI = loader.load();

            container.getChildren().clear();
            container.getChildren().add(searchResultsUI);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
