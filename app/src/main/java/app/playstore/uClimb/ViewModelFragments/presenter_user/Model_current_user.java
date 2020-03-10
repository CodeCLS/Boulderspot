package app.playstore.uClimb.ViewModelFragments.presenter_user;

import java.util.ArrayList;

public class Model_current_user {
    private String mAuth_current_user;
    private String city_current_user;
    private String img_profile_source_current_user;
    private ArrayList<String> following_current_user;
    private ArrayList<String> followers_current_user;
    private String subscription_current_user;
    private String info_current_user;
    private String Name_current_user;
    private String height_current_user;

    public Model_current_user() {
    }

    public Model_current_user(String mAuth_current_user, String city_current_user, String img_profile_source_current_user, ArrayList<String> following_current_user, ArrayList<String> followers_current_user, String subscription_current_user, String info_current_user, String name_current_user, String height_current_user) {
        this.mAuth_current_user = mAuth_current_user;
        this.city_current_user = city_current_user;
        this.img_profile_source_current_user = img_profile_source_current_user;
        this.following_current_user = following_current_user;
        this.followers_current_user = followers_current_user;
        this.subscription_current_user = subscription_current_user;
        this.info_current_user = info_current_user;
        Name_current_user = name_current_user;
        this.height_current_user = height_current_user;
    }

    public String getmAuth_current_user() {
        return mAuth_current_user;
    }

    public void setmAuth_current_user(String mAuth_current_user) {
        this.mAuth_current_user = mAuth_current_user;
    }

    public String getCity_current_user() {
        return city_current_user;
    }

    public void setCity_current_user(String city_current_user) {
        this.city_current_user = city_current_user;
    }

    public String getImg_profile_source_current_user() {
        return img_profile_source_current_user;
    }

    public void setImg_profile_source_current_user(String img_profile_source_current_user) {
        this.img_profile_source_current_user = img_profile_source_current_user;
    }

    public ArrayList<String> getFollowing_current_user() {
        return following_current_user;
    }

    public void setFollowing_current_user(ArrayList<String> following_current_user) {
        this.following_current_user = following_current_user;
    }

    public ArrayList<String> getFollowers_current_user() {
        return followers_current_user;
    }

    public void setFollowers_current_user(ArrayList<String> followers_current_user) {
        this.followers_current_user = followers_current_user;
    }

    public String getSubscription_current_user() {
        return subscription_current_user;
    }

    public void setSubscription_current_user(String subscription_current_user) {
        this.subscription_current_user = subscription_current_user;
    }

    public String getInfo_current_user() {
        return info_current_user;
    }

    public void setInfo_current_user(String info_current_user) {
        this.info_current_user = info_current_user;
    }

    public String getName_current_user() {
        return Name_current_user;
    }

    public void setName_current_user(String name_current_user) {
        Name_current_user = name_current_user;
    }

    public String getHeight_current_user() {
        return height_current_user;
    }

    public void setHeight_current_user(String height_current_user) {
        this.height_current_user = height_current_user;
    }
}
