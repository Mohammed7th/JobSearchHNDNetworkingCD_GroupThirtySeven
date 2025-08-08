package com.example.jobsearchhndnetworkingcd_groupthirtyseven.jobSeekerControllers;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.JobSeekerInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchResultsScreen implements Initializable {
    private  String searchItem;

    @FXML
    public VBox contentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println(searchItem);
        new JobSeekerInfo().searchForJobs(searchItem,contentPane);
    }


    public void setSearchItem(String searchItemValue) {
        this.searchItem = searchItemValue;
    }


}
