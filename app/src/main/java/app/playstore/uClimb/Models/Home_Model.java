package app.playstore.uClimb.Models;

import java.util.ArrayList;

public class Home_Model {


    private static ArrayList<String> URL= new ArrayList<>();
    private static  ArrayList<String> place   = new ArrayList<>();
    private static  ArrayList<String> name = new ArrayList<>();
    private static  ArrayList<String> info= new ArrayList<>();;
    private static  ArrayList<String> ID_User  = new ArrayList<>();
    private static  ArrayList<String> ID_Source = new ArrayList<>();




    public static ArrayList<String> getURL() {
        return URL;
    }

    public static void setURL(ArrayList<String> URL) {
        Home_Model.URL = URL;
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

    public static ArrayList<String> getID_User() {
        return ID_User;
    }

    public static void setID_User(ArrayList<String> ID_User) {
        Home_Model.ID_User = ID_User;
    }

    public static ArrayList<String> getID_Source() {
        return ID_Source;
    }

    public static void setID_Source(ArrayList<String> ID_Source) {
        Home_Model.ID_Source = ID_Source;
    }
}
