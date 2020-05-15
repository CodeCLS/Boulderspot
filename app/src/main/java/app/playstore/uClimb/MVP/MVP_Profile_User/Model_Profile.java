package app.playstore.uClimb.MVP.MVP_Profile_User;

import java.util.ArrayList;

public class Model_Profile {
    private String uid;
    private String stat_uid;
    private String Age;
    private String Name;
    private String profile_img;
    private String Info;
    private String Subscription;
    private String grade;
    private String country;
    private ArrayList follower = new ArrayList();
    private ArrayList  following = new ArrayList();
    private String Height;
    private ArrayList competition = new ArrayList();
    private String account_type;
    private String time_created;
    private String position_lat;
    private String position_long;
    private String position_last_updated;
    private Boolean position_status;

    public Model_Profile() {
    }

    public Model_Profile(String uid, String stat_uid, String age, String name, String profile_img, String info, String subscription, String grade, String country, ArrayList follower, ArrayList following, String height, ArrayList competition, String account_type, String time_created, String position_lat, String position_long, String position_last_updated, Boolean position_status) {
        this.uid = uid;
        this.stat_uid = stat_uid;
        Age = age;
        Name = name;
        this.profile_img = profile_img;
        Info = info;
        Subscription = subscription;
        this.grade = grade;
        this.country = country;
        this.follower = follower;
        this.following = following;
        Height = height;
        this.competition = competition;
        this.account_type = account_type;
        this.time_created = time_created;
        this.position_lat = position_lat;
        this.position_long = position_long;
        this.position_last_updated = position_last_updated;
        this.position_status = position_status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStat_uid() {
        return stat_uid;
    }

    public void setStat_uid(String stat_uid) {
        this.stat_uid = stat_uid;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getSubscription() {
        return Subscription;
    }

    public void setSubscription(String subscription) {
        Subscription = subscription;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList getFollower() {
        return follower;
    }

    public void setFollower(ArrayList follower) {
        this.follower = follower;
    }

    public ArrayList getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList following) {
        this.following = following;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public ArrayList getCompetition() {
        return competition;
    }

    public void setCompetition(ArrayList competition) {
        this.competition = competition;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getTime_created() {
        return time_created;
    }

    public void setTime_created(String time_created) {
        this.time_created = time_created;
    }

    public String getPosition_lat() {
        return position_lat;
    }

    public void setPosition_lat(String position_lat) {
        this.position_lat = position_lat;
    }

    public String getPosition_long() {
        return position_long;
    }

    public void setPosition_long(String position_long) {
        this.position_long = position_long;
    }

    public String getPosition_last_updated() {
        return position_last_updated;
    }

    public void setPosition_last_updated(String position_last_updated) {
        this.position_last_updated = position_last_updated;
    }

    public Boolean getPosition_status() {
        return position_status;
    }

    public void setPosition_status(Boolean position_status) {
        this.position_status = position_status;
    }
}
