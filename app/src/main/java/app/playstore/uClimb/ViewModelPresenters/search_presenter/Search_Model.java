package app.playstore.uClimb.ViewModelPresenters.search_presenter;

import java.util.ArrayList;

public class Search_Model {
    private ArrayList<String> URL= new ArrayList<>();
    private  ArrayList<String> Date   = new ArrayList<>();
    private  ArrayList<String> Location= new ArrayList<>();;
    private  ArrayList<String> info= new ArrayList<>();
    private  ArrayList<String> type_array= new ArrayList<>();


    public Search_Model(ArrayList<String> URL, ArrayList<String> date, ArrayList<String> location, ArrayList<String> info, ArrayList<String> type_array) {
        this.URL = URL;
        Date = date;
        Location = location;
        this.info = info;
        this.type_array = type_array;
    }

    public Search_Model() {

    }

    public ArrayList<String> getURL() {
        return URL;
    }

    public void setURL(ArrayList<String> URL) {
        this.URL = URL;
    }

    public ArrayList<String> getDate() {
        return Date;
    }

    public void setDate(ArrayList<String> date) {
        Date = date;
    }

    public ArrayList<String> getLocation() {
        return Location;
    }

    public void setLocation(ArrayList<String> location) {
        Location = location;
    }

    public ArrayList<String> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }

    public ArrayList<String> getType_array() {
        return type_array;
    }

    public void setType_array(ArrayList<String> type_array) {
        this.type_array = type_array;
    }
}
