package app.playstore.uClimb.ViewModelPresenters;

import java.util.ArrayList;

public class statistics_model {
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
    private ArrayList<String> competition_array_uid;

    public statistics_model() {
    }

    public statistics_model(String statistics_ID, String UID, ArrayList<String> training_sessions_time, ArrayList<String> training_sessions_types, ArrayList<String> training_sessions_amount, String average_grade, ArrayList<String> boulders_times, ArrayList<String> boulders_grade, ArrayList<String> boulders_way_of_accomplishment, ArrayList<String> competition_array_boulder, ArrayList<String> competition_array_uid) {
        Statistics_ID = statistics_ID;
        this.UID = UID;
        this.training_sessions_time = training_sessions_time;
        this.training_sessions_types = training_sessions_types;
        this.training_sessions_amount = training_sessions_amount;
        this.average_grade = average_grade;
        this.boulders_times = boulders_times;
        this.boulders_grade = boulders_grade;
        this.boulders_way_of_accomplishment = boulders_way_of_accomplishment;
        this.competition_array_boulder = competition_array_boulder;
        this.competition_array_uid = competition_array_uid;
    }

    public String getStatistics_ID() {
        return Statistics_ID;
    }

    public void setStatistics_ID(String statistics_ID) {
        Statistics_ID = statistics_ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public ArrayList<String> getTraining_sessions_time() {
        return training_sessions_time;
    }

    public void setTraining_sessions_time(ArrayList<String> training_sessions_time) {
        this.training_sessions_time = training_sessions_time;
    }

    public ArrayList<String> getTraining_sessions_types() {
        return training_sessions_types;
    }

    public void setTraining_sessions_types(ArrayList<String> training_sessions_types) {
        this.training_sessions_types = training_sessions_types;
    }

    public ArrayList<String> getTraining_sessions_amount() {
        return training_sessions_amount;
    }

    public void setTraining_sessions_amount(ArrayList<String> training_sessions_amount) {
        this.training_sessions_amount = training_sessions_amount;
    }

    public String getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(String average_grade) {
        this.average_grade = average_grade;
    }

    public ArrayList<String> getBoulders_times() {
        return boulders_times;
    }

    public void setBoulders_times(ArrayList<String> boulders_times) {
        this.boulders_times = boulders_times;
    }

    public ArrayList<String> getBoulders_grade() {
        return boulders_grade;
    }

    public void setBoulders_grade(ArrayList<String> boulders_grade) {
        this.boulders_grade = boulders_grade;
    }

    public ArrayList<String> getBoulders_way_of_accomplishment() {
        return boulders_way_of_accomplishment;
    }

    public void setBoulders_way_of_accomplishment(ArrayList<String> boulders_way_of_accomplishment) {
        this.boulders_way_of_accomplishment = boulders_way_of_accomplishment;
    }

    public ArrayList<String> getCompetition_array_boulder() {
        return competition_array_boulder;
    }

    public void setCompetition_array_boulder(ArrayList<String> competition_array_boulder) {
        this.competition_array_boulder = competition_array_boulder;
    }

    public ArrayList<String> getCompetition_array_uid() {
        return competition_array_uid;
    }

    public void setCompetition_array_uid(ArrayList<String> competition_array_uid) {
        this.competition_array_uid = competition_array_uid;
    }
}