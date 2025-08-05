package com.example.jobsearchhndnetworkingcd_groupthirtyseven.models;

public class User {
    private static String userName;
    private static int userID;
    private static String userEmail;
    private static int phoneNumber;


    public static void setUserNameAndID(String name, int ID, String email, int phoneContact) {
        userName = name;
        userID = ID;
        userEmail = email;
        phoneNumber = phoneContact;
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
