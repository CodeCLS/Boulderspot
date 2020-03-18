package app.playstore.uClimb.ViewModelPresenters;

import java.util.ArrayList;

public class statistics_presenter {
    private String Statistics_ID;
    private String UID;
    private ArrayList<String> training_sessions_time;
    private ArrayList<String> training_sessions_types;
    private ArrayList<String> training_sessions_amount;
    private String average_grade;
    private ArrayList<String> boulders_times;
    private ArrayList<String> boulders_grade;
    private ArrayList<String> boulders_way_of_accomplishment;
    private ArrayList<String> competition_array_boulder;
    private statistics_model statistics_model;


    public statistics_presenter() {
        statistics_model = new statistics_model();
    }
    public void setData(){


    }
}
