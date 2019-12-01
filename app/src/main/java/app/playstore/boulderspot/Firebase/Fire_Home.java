package app.playstore.boulderspot.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import app.playstore.boulderspot.Models.Model_home;

public class Fire_Home {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");



    private ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    private  ArrayList<String> likes = new ArrayList<>();


    public Fire_Home() {




    }
}
