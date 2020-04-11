package app.playstore.uClimb.ViewModelPresenters;

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

import app.playstore.uClimb.Adapters.Adapter_statistic;
import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

public class statistics_presenter {
    private static final String TAG = "statistics_presenter";
    private String Statistics_ID;
    private String UID;
    private ArrayList<String> training_sessions_time = new ArrayList<>();
    private ArrayList<String> training_sessions_types = new ArrayList<>();
    private ArrayList<String> training_sessions_amount = new ArrayList<>();
    private ArrayList<String> boulders_time = new ArrayList<>();
    private ArrayList<String> boulders_grade = new ArrayList<>();
    private ArrayList<String> boulders_tries = new ArrayList<>();
    private ArrayList<String> competition_array_boulder = new ArrayList<>();
    private ArrayList<Integer> competition_array_boulderpoints = new ArrayList<>();
    private ArrayList<String> competition_array_names = new ArrayList<>();

    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();


    private ArrayList<String> competition_array_uid = new ArrayList<>();
    private String average_grade;

    private statistics_model statistics_model;

    private ArrayList<String> training_sessions_notes = new ArrayList<>();
    private ArrayList<String> boulder_notes = new ArrayList<>();
    private Adapter_statistic adapter_statistic = new Adapter_statistic(training_sessions_time,training_sessions_types,training_sessions_amount,boulders_time,boulders_grade,boulders_tries,competition_array_boulderpoints,competition_array_uid,competition_array_names,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time,training_sessions_notes,boulder_notes);
    private View view;



    public statistics_presenter(View view) {
        statistics_model = new statistics_model();
        this.view = view;
    }
    public void setData(View view, Context mContext){
        addDatatostatistic(mContext);
        RecyclerView recyclerView = view.findViewById(R.id.statistic_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter_statistic);


    }

    private void addDatatostatistic(Context mContext) {
        getFireDataSessions(mContext);
        getFireDataBoulders(mContext);
        getFireDataCompetition(mContext);

        //training_sessions_time.add("6:00");
        //training_sessions_types.add("Liegestütze");
        //training_sessions_amount.add("5");
        //boulders_time.add("5");
        //competition_array_uid.add("caleb");
        //boulders_grade.add("5V");
        //boulders_tries.add("Flash");
        //competition_array_boulder.add("V5");







    }

    private void getFireDataCompetition(Context mContext) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                login_presenter login_presenter = new login_presenter();
                String statistics_id = login_presenter.getStatisticsID(mContext);
                String uid = login_presenter.getUID(mContext);
                ArrayList friendscompeting= login_presenter.getFriendsCompeting(mContext);
                for (int i = 0; i < friendscompeting.size();i++){
                    int p = 0;
                    String uid_friends = friendscompeting.get(i).toString();
                    for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid_friends).child("Accomplished_Boulder").getChildren()){
                        String boulder = postSnapshot.getKey();
                        String times = postSnapshot.getValue().toString();
                        int points = points_system(boulder);
                        points =points*Integer.parseInt(times);
                        p = p+ points;

                    }
                    competition_array_boulderpoints.add(p);
                    competition_array_uid.add(uid_friends);




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void getFireDataBoulders(Context mContext) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Statistics");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                login_presenter login_presenter = new login_presenter();
                String statistics_id = login_presenter.getStatisticsID(mContext);
                for (DataSnapshot posSnapshot : dataSnapshot.child(statistics_id).child("Boulder_problem").getChildren()) {
                    String grade = posSnapshot.child("difficulty").getValue().toString();
                    String time = posSnapshot.child("Time").getValue().toString();
                    String tries_num = posSnapshot.child("tries_num").getValue().toString();
                    String notes = posSnapshot.child("Notes").getValue().toString();
                    boulder_notes.add(notes);


                    boulders_grade.add(grade);
                    boulders_time.add(time);
                    boulders_tries.add(tries_num);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

            private void getFireDataSessions(Context mContext) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Statistics");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                login_presenter login_presenter = new login_presenter();
                String statistics_id = login_presenter.getStatisticsID(mContext);
                Log.d(TAG,"statistics_id" + statistics_id);
                Log.d(TAG,"dataworkout" + dataSnapshot.child(statistics_id).child("Workouts").getChildrenCount()) ;
                if (dataSnapshot.child(statistics_id).child("Workouts").getChildrenCount() == 0){
                    Snackbar snackbar = Snackbar.make(view,"No progress posted yet", BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(mContext.getResources().getColor(R.color.colorPrimaryDark));
                    snackbar.show();

                }

                for (DataSnapshot postSnapshot : dataSnapshot.child(statistics_id).child("Workouts").getChildren()) {
                    Log.d(TAG,"dataworkout2" + postSnapshot) ;


                    String notes = postSnapshot.child("Notes").getValue().toString();
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
}
