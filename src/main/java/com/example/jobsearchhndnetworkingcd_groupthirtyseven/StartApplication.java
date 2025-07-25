package com.example.jobsearchhndnetworkingcd_groupthirtyseven;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.JobSeekerInfo;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.RecruiterInfo;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("JobSearch");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //new DBAccess().accessDb();
        launch();
    }
}