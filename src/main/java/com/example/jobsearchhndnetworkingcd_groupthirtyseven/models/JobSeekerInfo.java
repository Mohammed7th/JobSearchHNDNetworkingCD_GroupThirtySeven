package com.example.jobsearchhndnetworkingcd_groupthirtyseven.models;


import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class JobSeekerInfo {
    private final int userID = User.getUserID();

    public void getJobs(VBox container) {
        String query = "SELECT jobID, jobTitle, jobDescription, dateTime, users.userName " +
                "FROM jobs JOIN users ON jobs.userID = users.id";

        try (Connection conn = DBAccess.connect();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int jobId = rs.getInt("jobID");
                String title = rs.getString("jobTitle");
                String description = rs.getString("jobDescription");
                String recruiterName = rs.getString("userName");
                String dateTime = rs.getTimestamp("dateTime").toLocalDateTime().toString();

                addJobCardForSeeker(jobId, title, description, recruiterName, dateTime, container);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addJobCardForSeeker(int jobId, String title, String desc, String postedBy, String time, VBox container) {
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: black; -fx-border-radius: 10; -fx-padding: 10; -fx-background-color: #f9f9f9;");
        card.setPadding(new Insets(10));
        card.setPrefWidth(300);

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        applyButton.setOnAction(e -> {
            boolean confirmed = Actions.confirm("Apply?", "Are you sure you want to apply to this job?");
            if (!confirmed) return;

            applyToJob(jobId);
            //applyButton
            container.getChildren().remove(card);
        });

        HBox topBar = new HBox(applyButton);
        topBar.setAlignment(Pos.TOP_RIGHT);

        Label titleLabel = new Label("Job Title: " + title);
        Label descLabel = new Label("Description: " + desc);
        Label posterLabel = new Label("Posted by: " + postedBy);
        Label timeLabel = new Label("Date: " + time);
        timeLabel.setAlignment(Pos.BOTTOM_RIGHT);

        card.getChildren().addAll(titleLabel, descLabel, posterLabel, timeLabel, applyButton);
        container.getChildren().add(card);
    }

    public void applyToJob(int jobID) {
        try {
            String query = "INSERT INTO applications (userID, jobID) VALUES (?, ?)";
            try (Connection conn = DBAccess.connect();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, userID);
                ps.setInt(2, jobID);
                ps.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void retrieveAppliedJobs(VBox container) {
        String query = "SELECT j.*, a.applicationDate, u.userName " +
                "FROM jobs j " +
                "JOIN applications a ON j.jobID = a.jobID " +
                "JOIN users u ON j.userID = u.id " +
                "WHERE a.userID = ?";

        try (Connection conn = DBAccess.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int jobId = resultSet.getInt("jobID");
                String title = resultSet.getString("jobTitle");
                String description = resultSet.getString("jobDescription");
                String postedBy = resultSet.getString("userName");
                LocalDateTime dateTime = resultSet.getTimestamp("applicationDate").toLocalDateTime();
                String formattedTime = dateTime.toString();

                addCardsToShowRetrievedJobs(title, description, formattedTime, container, jobId, postedBy);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCardsToShowRetrievedJobs(String title, String desc, String time, VBox cardContainerID, int jobID, String postedBy) {
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: black; -fx-border-radius: 10; -fx-padding: 10; -fx-background-color: #f2f2f2;");
        card.setPadding(new Insets(10));
        card.setPrefWidth(300);

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        cancelButton.setOnAction(e -> {
            boolean confirmed = Actions.confirm("Cancel?", "Are you sure you want to cancel this application?");
            if (!confirmed) return;

            deleteJobApplicationFromDB(jobID);
            cardContainerID.getChildren().remove(card);
        });

        HBox topBar = new HBox(cancelButton);
        topBar.setAlignment(Pos.TOP_RIGHT);

        Label titleLabel = new Label("Job Title: " + title);
        Label descLabel = new Label("Job Description: " + desc);
        Label posterLabel = new Label("Posted by: " + postedBy);
        Label timeLabel = new Label("Application Date: " + time);
        timeLabel.setAlignment(Pos.BOTTOM_RIGHT);

        card.getChildren().addAll(topBar, titleLabel, descLabel, posterLabel, timeLabel);
        cardContainerID.getChildren().add(card);
    }

    public void deleteJobApplicationFromDB(int jobID) {
        String sql = "DELETE FROM applications WHERE jobID = ? AND userID = ?";
        try (Connection conn = DBAccess.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobID);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAvailableJobCount(Label availableJobCount){
        String query= "SELECT COUNT(*) FROM jobs";
        try {
            PreparedStatement pS = DBAccess.connect().prepareStatement(query);
            ResultSet rs = pS.executeQuery();
            if (rs.next()){
                int countJobs = rs.getInt(1);
                availableJobCount.setText(String.valueOf(countJobs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void getAppliedJobCount(Label appliedJobCount){
        String query= "SELECT COUNT(*) FROM applications WHERE userID = ?";
        try {
            PreparedStatement pS = DBAccess.connect().prepareStatement(query);
            pS.setInt(1,userID);
            ResultSet rs = pS.executeQuery();
            if (rs.next()){
                int countJobs = rs.getInt(1);
               appliedJobCount.setText(String.valueOf(countJobs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

