package com.example.jobsearchhndnetworkingcd_groupthirtyseven.models;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions.Actions;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.time.LocalDateTime;

public class RecruiterInfo  {
    private int jobID;
    private String jobTitle;
    private String jobDescription;
    private LocalDateTime dateTime;
    private int userID;

    public void postJobToDb(String jobTitle, String jobDescription, int userID) {
        try {
            String query = "INSERT INTO jobs(jobTitle, jobDescription, userID) VALUES (?, ?, ?)";
            PreparedStatement ps = DBAccess.connect().prepareStatement(query);
            ps.setString(1, jobTitle);
            ps.setString(2, jobDescription);
            ps.setInt(3, userID);

            ps.executeUpdate();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to post job: " + e.getMessage(), e);
        }
    }

    public void retrieveAndAddCardInfo(int userId, VBox cardContainerID) {
        String query = "SELECT jobTitle, jobDescription, dateTime FROM jobs WHERE userID = ?";

        try (Connection conn = DBAccess.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String title = rs.getString("jobTitle");
                String description = rs.getString("jobDescription");
                LocalDateTime dateTime = rs.getTimestamp("dateTime").toLocalDateTime();
                String formattedTime = dateTime.toString(); // Or format as needed

                addCard(title, description, formattedTime, cardContainerID);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void addCard(String title, String desc, String time, VBox cardContainerID) {
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: black; -fx-border-radius: 10; -fx-padding: 10; -fx-background-color: #f2f2f2;");
        card.setPadding(new Insets(10));
        card.setPrefWidth(300);

        // Delete button
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> {
            boolean confirmed = Actions.confirm("Delete?", "Are you sure you want to delete this job?");
            if (!confirmed) return;
            deleteJobFromDB(title);

            cardContainerID.getChildren().remove(card);
        });

        HBox topBar = new HBox(deleteButton);
        topBar.setAlignment(Pos.TOP_RIGHT);

        // Labels
        Label titleLabel = new Label("Job Title: " + title);
        Label descLabel = new Label("Job Description: " + desc);
        Label timeLabel = new Label(time);
        timeLabel.setAlignment(Pos.BOTTOM_RIGHT);

        // Add to card
        card.getChildren().addAll(topBar, titleLabel, descLabel, timeLabel);

        // Add card to UI
        cardContainerID.getChildren().add(card);
    }

    public void deleteJobFromDB(String jobTitle) {
      int  userId =User.getUserID();
        String sql = "DELETE FROM jobs WHERE jobTitle = ? AND userID = ?";
        try (Connection conn = DBAccess.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, jobTitle);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
