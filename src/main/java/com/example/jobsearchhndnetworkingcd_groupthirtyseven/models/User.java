package com.example.jobsearchhndnetworkingcd_groupthirtyseven.models;

public class User {
    private static String userName;
    private static int userID;
    private static String userEmail;
    private static int phoneNumber;
    private static String role;


    public static void setUserNameAndID(String name, int ID, String email, int phoneContact, String userRole) {
        userName = name;
        userID = ID;
        userEmail = email;
        phoneNumber = phoneContact;
        role = userRole;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public static String getRole(){
        return role;
    }
    public static String getEmail() {
        return userEmail;
    }

    public static int getPhoneNumber() {
        return phoneNumber;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getUserID() {
        return userID;
    }

    public static void clear() {
        userID = 0;
        userName = null;
    }


}
