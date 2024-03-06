package com.example.communitask;


public class Post {
    private String userIcon;
    private String username;
    private String title;
    private String content;

    private String location;
    private int rate;
    private long timestamp;

    public Post(String userIcon, String username, String title, String content, int rate, long timestamp, String location) {
        this.userIcon = userIcon;
        this.username = username;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
