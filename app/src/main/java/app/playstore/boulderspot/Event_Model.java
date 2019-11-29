package app.playstore.boulderspot;

import java.sql.Time;
import java.util.Date;

public class Event_Model {
    public static String IMG_URL;
    public static String Date;
    public static String Title_boulderhalle;
    public static String Location;
    public static String costs;
    public static String min_age;
    public static String Grade;
    public static String event_name;
    public static String time;
    public static String info;
    public static int size;
//test
    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        Event_Model.size = size;
    }

    public static String getImgUrl() {
        return IMG_URL;
    }

    public static void setImgUrl(String imgUrl) {
        IMG_URL = imgUrl;
    }

    public static String getDate() {
        return Date;
    }

    public static void setDate(String date) {
        Date = date;
    }

    public static String getTitle_boulderhalle() {
        return Title_boulderhalle;
    }

    public static void setTitle_boulderhalle(String title_boulderhalle) {
        Title_boulderhalle = title_boulderhalle;
    }

    public static String getLocation() {
        return Location;
    }

    public static void setLocation(String location) {
        Location = location;
    }

    public static String getCosts() {
        return costs;
    }

    public static void setCosts(String costs) {
        Event_Model.costs = costs;
    }

    public static String getMin_age() {
        return min_age;
    }

    public static void setMin_age(String min_age) {
        Event_Model.min_age = min_age;
    }

    public static String getGrade() {
        return Grade;
    }

    public static void setGrade(String grade) {
        Grade = grade;
    }

    public static String getEvent_name() {
        return event_name;
    }

    public static void setEvent_name(String event_name) {
        Event_Model.event_name = event_name;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Event_Model.time = time;
    }

    public static String getInfo() {
        return info;
    }

    public static void setInfo(String info) {
        Event_Model.info = info;
    }
}
