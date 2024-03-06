package com.example.communitask;



public class UserDataManager {

    private static UserDataManager instance;
    private String username;
    private String email;
    private String icon;

    private String userId;

    private UserDataManager() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized UserDataManager getInstance() {
        if (instance == null) {
            instance = new UserDataManager();
        }
        return instance;
    }

    public void setUserData(String username, String email, String icon) {
        this.username = username;
        this.email = email;
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
