package com.example.jobsearchhndnetworkingcd_groupthirtyseven.models;

public class User {
    private static String userName;
    private static int userID;


    public static void setUserNameAndID(String name, int ID) {
        userName = name;
        userID = ID;
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
