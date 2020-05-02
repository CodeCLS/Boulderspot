package app.playstore.uClimb.MVP.MVP_Statistics;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import app.playstore.uClimb.Adapters.Statistics.Adapter_Statistics;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Presenter_Statistics {
    private static final String TAG = "statistics_presenter";
    private String Statistics_ID;
    private String UID;
    private static ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> training_sessions_time = new ArrayList<>();
    private ArrayList<String> training_sessions_types = new ArrayList<>();
    private ArrayList<String> training_sessions_amount = new ArrayList<>();
    private ArrayList<String> boulders_time = new ArrayList<>();
    private ArrayList<String> boulders_grade = new ArrayList<>();
    private ArrayList<String> boulders_tries = new ArrayList<>();
    private ArrayList<Integer> competition_array_boulderpoints = new ArrayList<>();
    private ArrayList<String> competition_array_names = new ArrayList<>();

    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();
    private static Boolean callback = false;


    private ArrayList<String> competition_array_uid = new ArrayList<>();
    private String average_grade;

    private Model_Statistics statistics_model;
    private HashMap<String,Integer> boulders_boulder_final = new HashMap<>();


    private ArrayList<String> training_sessions_notes = new ArrayList<>();
    private ArrayList<String> boulder_notes = new ArrayList<>();
    private Adapter_Statistics adapter_statistics = new Adapter_Statistics(training_sessions_time,training_sessions_types,training_sessions_amount,boulders_time,boulders_boulder_final,boulders_tries,competition_array_boulderpoints,competition_array_uid,competition_array_names,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time,training_sessions_notes,boulder_notes);
    private View view;


    public Presenter_Statistics(View view) {
        statistics_model = new Model_Statistics();
        this.view = view;
    }

    public Presenter_Statistics() {
        Log.d(TAG,"firends1");

    }

    public void setData(View view, Context mContext){
        Log.d(TAG,"firends2");
        Presenter_Login login_presenter = new Presenter_Login();
        login_presenter.getFriendsCompeting(mContext,view);
        //training_sessions_time.add("6:00");
        //training_sessions_types.add("Liegestütze");
        //training_sessions_amount.add("5");
        //boulders_time.add("5");
        //competition_array_uid.add("caleb");
        //boulders_grade.add("5V");
        //boulders_tries.add("Flash");
        //competition_array_boulder.add("V5");


    }


    public void getFireData(Context mContext, ArrayList value,View view_main) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        Log.d(TAG,"friends3" + friends);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Presenter_Login login_presenter = new Presenter_Login();
                String statistics_id = login_presenter.getStatisticsID(mContext);
                String uid = login_presenter.getUID(mContext);
                Log.d(TAG,"friends" + friends);
                friends = value;


                    for (int i = 0; i < friends.size(); i++) {
                        int p = 0;
                        int amount = 0;
                        String uid_friends = friends.get(i).toString();
                        Log.d(TAG, "friends1" + friends.get(i));


                        String statistics_ID = dataSnapshot.child("User").child(uid_friends).child("StatisticsID").getValue().toString();
                        Log.d(TAG,"statistics"+statistics_ID);
                        Log.d(TAG, "points" + statistics_ID);
                        int points =Integer.parseInt(dataSnapshot.child("User").child(uid_friends).child("Points").getValue().toString());
                        competition_array_uid.add(uid_friends);
                        competition_array_names.add(dataSnapshot.child("User").child(uid_friends).child("Name").getValue().toString());
                        competition_array_boulderpoints.add(points);

                    }




                for (DataSnapshot posSnapshot : dataSnapshot.child("Statistics").child(statistics_id).child("Boulder_problem").getChildren()) {
                    String grade = posSnapshot.child("difficulty").getValue().toString();
                        String time = posSnapshot.child("Time").getValue().toString();
                        String tries_num = posSnapshot.child("tries_num").getValue().toString();
                        String notes = posSnapshot.child("Info").getValue().toString();
                        boulder_notes.add(notes);


                        boulders_time.add(time);
                        boulders_tries.add(tries_num);

                        boulders_grade.add(grade);





                    }
                for (int i = 0; i< boulders_grade.size();i++){
                    if (boulders_boulder_final.containsKey(boulders_grade.get(i))){
                        Integer integer = Integer.parseInt(boulders_boulder_final.get(boulders_grade.get(i)).toString());
                        integer++;
                        boulders_boulder_final.put(boulders_grade.get(i),integer);

                    }
                    else{

                        boulders_boulder_final.put(boulders_grade.get(i),1);

                    }
                }

                    Log.d(TAG,"workoutssize"+dataSnapshot.child("Statistics").child(statistics_id).child("Workouts").getChildrenCount());

                    if (dataSnapshot.child("Statistics").child(statistics_id).child("Workouts").getChildrenCount() == 0){
                        if (view_main.getRootView() != null) {
                            Log.d(TAG,"viewmain"+view_main);
                            Snackbar snackbar = Snackbar.make(view_main.getRootView(), "No workout progress posted yet", BaseTransientBottomBar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(mContext.getResources().getColor(R.color.colorPrimaryDark));
                            snackbar.show();

                        }
                    }

                    for (DataSnapshot postSnapshot : dataSnapshot.child("Statistics").child(statistics_id).child("Workouts").getChildren()) {
                        Log.d(TAG,"dataworkout2" + postSnapshot) ;


                        String notes = postSnapshot.child("Info").getValue().toString();
                        String train = postSnapshot.child("Train").getValue().toString();
                        String pause = postSnapshot.child("Pause").getValue().toString();
                        String rest =postSnapshot.child("Rest").getValue().toString();
                        String set =postSnapshot.child("Sets").getValue().toString();
                        String round =postSnapshot.child("Rows").getValue().toString();
                        sesssions_train_time.add(train);
                        sesssions_pause_time.add(pause);
                        sesssions_rest_time.add(rest);
                        sesssions_rounds_time.add(round);
                        sesssions_sets_time.add(set);
                        training_sessions_notes.add(notes);
                        if (training_sessions_types.contains(postSnapshot.child("Title").getValue().toString())) {
                            int index = training_sessions_types.indexOf(postSnapshot.child("Title").getValue().toString());
                            int amount = Integer.parseInt(training_sessions_amount.get(index)) + 1;
                            training_sessions_amount.add(index, String.valueOf(amount));

                        } else {
                            String name_from_workout = postSnapshot.child("Title").getValue().toString();
                            String amount = "1";
                            String time = postSnapshot.child("Time").getValue().toString();
                            training_sessions_amount.add(amount);
                            training_sessions_types.add(name_from_workout);
                            training_sessions_time.add(time);

                        }
                    }
                    Log.d(TAG,"sessions"+boulders_tries);
                   //competition_array_boulderpoints.add(p);
                   //competition_array_uid.add(uid_friends);
                    Log.d(TAG,"view"+view);
                   RecyclerView recyclerView = view_main.findViewById(R.id.statistic_rec);
                   recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                   Log.d(TAG,"ADAPTER:" + training_sessions_time+training_sessions_notes+training_sessions_types+training_sessions_amount+boulders_time+boulders_grade);
                   recyclerView.setAdapter(adapter_statistics);
                    Log.d(TAG,"sessions"+boulders_tries);









            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }






    private int points_system(String competition_grade_s){
        int points = 0;

        if (competition_grade_s.equals("V1") || competition_grade_s.equals("V2")) {
            points++;
        }
        if (competition_grade_s.equals("V3") || competition_grade_s.equals("V4")){
           points = points +2;
       }
       if (competition_grade_s.equals("V5") || competition_grade_s.equals("V6")){
           points = points +5;
       }
       if (competition_grade_s.equals("V7") || competition_grade_s.equals("V8")){
           points = points +8;
       }
       if (competition_grade_s.equals("V9") || competition_grade_s.equals("V10")){
           points = points +11;
       }
       if (competition_grade_s.equals("V11") || competition_grade_s.equals("V12")){
           points = points +14;
       }
       if (competition_grade_s.equals("V13") || competition_grade_s.equals("V14")){
           points = points +18;
       }
       if (competition_grade_s == "V15" || competition_grade_s == "V16"){
           points = points +20;
       }
       if (competition_grade_s == "V17"){
           points = points +25;
       }
       Log.d(TAG,"points_presenter" + points);
       return points;

    }

    public void setBoulderRec(RecyclerView rec, Context mContext, RecyclerView.Adapter adapter_statistics_boulder) {
        rec.setLayoutManager(new LinearLayoutManager(mContext));
        Log.d(TAG,"Hallo123");
        rec.setAdapter(adapter_statistics_boulder);
    }
}
