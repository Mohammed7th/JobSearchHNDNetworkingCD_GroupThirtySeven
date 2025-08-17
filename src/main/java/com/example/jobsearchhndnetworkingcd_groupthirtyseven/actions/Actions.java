package com.example.jobsearchhndnetworkingcd_groupthirtyseven.actions;

import com.example.jobsearchhndnetworkingcd_groupthirtyseven.dbAccess.DBAccess;
import com.example.jobsearchhndnetworkingcd_groupthirtyseven.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actions {
    private static String userName;
    private static String role;
    private static String email;
    private static int contact;
    private static int userIdentity;
    private static boolean isDisplayInfoEmpty;

    public static void setDetails(String name, String jobRole, String mail, int phone) {
        userName = name;
        role = jobRole;
        email = mail;
        contact = phone;
    }

    public static String getUsername() {
        return userName;
    }

    public static String getRole() {
        return role;
    }

    public static String getEmail() {
        return email;
    }

    public static int getContact() {
        return contact;
    }

    public static boolean confirm(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    public void nextPage(int width, int height, String name, ActionEvent event, String My_New_Stage_Title) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/jobsearchhndnetworkingcd_groupthirtyseven" + name));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = fxmlLoader.load();
        stage.setTitle(My_New_Stage_Title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    public static void showAlertBox(String errorType, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(errorType);
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public void resetButtonStyles(Button btnOne, Button btnTwo, Button btnThree, Button btnFour) {
        btnOne.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
        btnTwo.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
        btnThree.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
        btnFour.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color:#dc2f02;" +
                        "-fx-border-width:4;" +
                        "-fx-border-radius:5;"
        );
    }

    public static void displayInfo(Label nameID, Label contactID, Label roleID, Label emailID) {
        String name = User.getUserName();
        String contact = String.valueOf(User.getPhoneNumber());
        String role = User.getRole();
        String email = User.getEmail();

        if (name == null || name.isEmpty() || contact.isEmpty() || role.isEmpty() || email.isEmpty()) {
            isDisplayInfoEmpty = true;
            return;
        }

        nameID.setText(name);
        contactID.setText(String.valueOf(contact));
        roleID.setText(role);
        emailID.setText(email);
    }

    public static void displayInfoForNewUser(Label nameID, Label contactID, Label roleID, Label emailID) {
        String query = "SELECT username, userRole,email, phoneNumber FROM users ORDER BY id DESC LIMIT 1";
        try {
            PreparedStatement preparedStatement = DBAccess.connect().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("username");
                String jobRole = resultSet.getString("userRole");
                String phone = resultSet.getString("phoneNumber");
                String emailText = resultSet.getString("email");


                nameID.setText(name);
                contactID.setText(String.valueOf(phone));
                roleID.setText(jobRole);
                emailID.setText(emailText);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void fetchNewUserId(String name, String passValue) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? ORDER BY id DESC LIMIT 1";
        try {
            PreparedStatement preparedStatement = DBAccess.connect().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, passValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User.setUserID(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    public static boolean isIsDisplayInfoEmpty() {
        return isDisplayInfoEmpty;
    }
}
