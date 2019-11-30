package app.playstore.boulderspot.Models;

public class Model_home {

    //user bar
    public String Username;
    public String follower;
    public String friends;
    public String Grade;
    public String IMG_URL;
    public Boolean notifications;
    public int notifications_size;

    //navigation_bar
    public int position;
    public int color;


    //user bar getters and setters

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getIMG_URL() {
        return IMG_URL;
    }

    public void setIMG_URL(String IMG_URL) {
        this.IMG_URL = IMG_URL;
    }

    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    public int getNotifications_size() {
        return notifications_size;
    }

    public void setNotifications_size(int notifications_size) {
        this.notifications_size = notifications_size;
    }
    //navigation_bar getters and setters

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
