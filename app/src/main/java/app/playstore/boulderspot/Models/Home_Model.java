package app.playstore.boulderspot.Models;

import java.util.ArrayList;

public class Home_Model {


    private static ArrayList<String> IMG_URL= new ArrayList<>();
    private static  ArrayList<String> grade   = new ArrayList<>();
    private static  ArrayList<String> place   = new ArrayList<>();
    private static  ArrayList<String> name = new ArrayList<>();
    private static  ArrayList<String> info= new ArrayList<>();;
    private static  ArrayList<String> maker  = new ArrayList<>();
    private static  ArrayList<String> video_url= new ArrayList<>();
    private static  ArrayList<String> ID_User  = new ArrayList<>();
    private static  ArrayList<String> ID_video = new ArrayList<>();
    private static  ArrayList<String> likes = new ArrayList<>();


    public static ArrayList<String> getImgUrl() {
        return IMG_URL;
    }

    public static void setImgUrl(ArrayList<String> imgUrl) {
        IMG_URL = imgUrl;
    }

    public static ArrayList<String> getGrade() {
        return grade;
    }

    public static void setGrade(ArrayList<String> grade) {
        Home_Model.grade = grade;
    }

    public static ArrayList<String> getPlace() {
        return place;
    }

    public static void setPlace(ArrayList<String> place) {
        Home_Model.place = place;
    }

    public static ArrayList<String> getName() {
        return name;
    }

    public static void setName(ArrayList<String> name) {
        Home_Model.name = name;
    }

    public static ArrayList<String> getInfo() {
        return info;
    }

    public static void setInfo(ArrayList<String> info) {
        Home_Model.info = info;
    }

    public static ArrayList<String> getMaker() {
        return maker;
    }

    public static void setMaker(ArrayList<String> maker) {
        Home_Model.maker = maker;
    }

    public static ArrayList<String> getVideo_url() {
        return video_url;
    }

    public static void setVideo_url(ArrayList<String> video_url) {
        Home_Model.video_url = video_url;
    }

    public static ArrayList<String> getID_User() {
        return ID_User;
    }

    public static void setID_User(ArrayList<String> ID_User) {
        Home_Model.ID_User = ID_User;
    }

    public static ArrayList<String> getID_video() {
        return ID_video;
    }

    public static void setID_video(ArrayList<String> ID_video) {
        Home_Model.ID_video = ID_video;
    }

    public static ArrayList<String> getLikes() {
        return likes;
    }

    public static void setLikes(ArrayList<String> likes) {
        Home_Model.likes = likes;
    }
}
