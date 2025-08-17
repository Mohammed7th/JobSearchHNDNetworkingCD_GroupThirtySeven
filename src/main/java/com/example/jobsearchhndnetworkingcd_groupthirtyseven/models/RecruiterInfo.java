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
    private int userID = User.getUserID();

    public void postJobToDb(String jobTitle, String jobDescription, int userID) {
        try {
            String query = "INSERT INTO jobs(jobTitle, jobDescription, userID) VALUES (?, ?, ?)";
            PreparedStatement ps = DBAccess.connect().prepareStatement(query);
            ps.setString(1, jobTitle);
            ps.setString(2, jobDescription);
            ps.setInt(3, userID);

            ps.executeUpdate();
        } catch (RuntimeException | SQLException e) {
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

        } catch (SQLException e) {
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


        card.getChildren().addAll(topBar, titleLabel, descLabel, timeLabel);


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

    public void getPostedJobCount(Label postedJobCount){
        String query= "SELECT COUNT(*) FROM jobs WHERE userID = ?";
        try {
            PreparedStatement pS = DBAccess.connect().prepareStatement(query);
            pS.setInt(1,userID);
            ResultSet rs = pS.executeQuery();
            if (rs.next()){
                int countJobs = rs.getInt(1);
                postedJobCount.setText(String.valueOf(countJobs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getApplicantCount(Label applicantCount){
        String query= "SELECT COUNT(a.applicationID) FROM applications a JOIN jobs j ON a.jobID = j.jobID WHERE j.userID = ?";
        try {
            PreparedStatement pS = DBAccess.connect().prepareStatement(query);
            pS.setInt(1,userID);
            ResultSet rs = pS.executeQuery();
            if (rs.next()){
                int countJobs = rs.getInt(1);
                applicantCount.setText(String.valueOf(countJobs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void retrieveApplicantsForRecruiter(int recruiterId, VBox cardContainerID) {
        String query = """
                SELECT\s
                    u.userName AS applicant_name,
                    u.phoneNumber AS applicant_contact,
                    u.email AS applicant_email,
                    j.jobTitle AS job_title,
                    a.applicationDate AS application_date
                FROM applications a
                JOIN jobs j\s
                    ON a.jobID = j.jobID
                JOIN users u\s
                    ON a.userID = u.id
                WHERE j.userID = ?
               \s""";

        try (Connection conn = DBAccess.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, recruiterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String applicantName = rs.getString("applicant_name");
                String applicantContact = rs.getString("applicant_contact");
                String applicantEmail = rs.getString("applicant_email");
                String jobTitle = rs.getString("job_title");
                LocalDateTime applicationDate = rs.getTimestamp("application_date").toLocalDateTime();
                String formattedDate = applicationDate.toString();


                addCard(applicantName, applicantContact, applicantEmail, jobTitle, formattedDate, cardContainerID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCard(String applicantName, String applicantContact, String applicantEmail,
                         String jobTitle, String applicationDate, VBox cardContainerID) {

        VBox card = new VBox(5);
        card.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9; -fx-background-radius: 5;");

        Label nameLabel = new Label("Name: " + applicantName);
        Label contactLabel = new Label("Contact: " + applicantContact);
        Label emailLabel = new Label("Email: " + applicantEmail);
        Label jobLabel = new Label("Applied for: " + jobTitle);
        Label dateLabel = new Label("Applied on: " + applicationDate);

        card.getChildren().addAll(nameLabel, contactLabel, emailLabel, jobLabel, dateLabel);

        cardContainerID.getChildren().add(card);
    }


}
