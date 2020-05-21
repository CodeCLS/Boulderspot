package app.playstore.uClimb.MVP.MVP_Statistics;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private HashMap<String,Integer> sessions_final = new HashMap<>();



    private ArrayList<String> training_sessions_notes = new ArrayList<>();
    private ArrayList<String> boulder_notes = new ArrayList<>();



    private Adapter_Statistics adapter_statistics = new Adapter_Statistics(boulders_grade,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time,training_sessions_notes,sessions_final,training_sessions_time,training_sessions_types,boulders_time,boulders_boulder_final,boulders_tries,competition_array_boulderpoints,competition_array_uid,competition_array_names,boulder_notes);
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
        friends = value;
        if (friends.isEmpty()){
            Toast.makeText(mContext, R.string.freund_brauchen_statistiken, Toast.LENGTH_SHORT).show();
        }



        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Presenter_Login login_presenter = new Presenter_Login();
                String statistics_id = login_presenter.getStatisticsID(mContext);
                Log.d(TAG,"STAT23"+statistics_id);

                String uid = login_presenter.getUID(mContext);
                Log.d(TAG,"friends3" + friends);



                    for (int i = 0; i < friends.size(); i++) {
                        int p = 0;
                        int amount = 0;
                        String uid_friends = friends.get(i).toString();
                        Log.d(TAG, "friends1" + friends.get(i));
                        if ( dataSnapshot.child("User").child(uid_friends).child("StatisticsID").exists() && dataSnapshot.child("User").child(uid_friends).child("Points").exists()
                        && dataSnapshot.child("User").child(uid_friends).child("Name").exists()) {


                            String statistics_ID = dataSnapshot.child("User").child(uid_friends).child("StatisticsID").getValue().toString();
                            Log.d(TAG, "statistics23" + statistics_ID);
                            Log.d(TAG, "points23" + statistics_ID);
                            int points = Integer.parseInt(dataSnapshot.child("User").child(uid_friends).child("Points").getValue().toString());
                            competition_array_uid.add(uid_friends);
                            competition_array_names.add(dataSnapshot.child("User").child(uid_friends).child("Name").getValue().toString());
                            competition_array_boulderpoints.add(points);
                        }

                    }




                for (DataSnapshot posSnapshot : dataSnapshot.child("Statistics").child(statistics_id).child("Boulder_problem").getChildren()) {
                    if ( posSnapshot.child("difficulty").exists()&& posSnapshot.child("Time").exists() && posSnapshot.child("tries_num").exists()
                            && posSnapshot.child("Info").exists()) {
                        String grade = posSnapshot.child("difficulty").getValue().toString();
                        String time = posSnapshot.child("Time").getValue().toString();
                        String tries_num = posSnapshot.child("tries_num").getValue().toString();
                        String notes = posSnapshot.child("Info").getValue().toString();
                        boulder_notes.add(notes);


                        boulders_time.add(time);
                        boulders_tries.add(tries_num);

                        boulders_grade.add(grade);


                    }
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

                    //if (dataSnapshot.child("Statistics").child(statistics_id).child("Workouts").getChildrenCount() == 0){
                    //    if (view_main.getRootView() != null) {
                    //        Log.d(TAG,"viewmain"+view_main);
                    //        Snackbar snackbar = Snackbar.make(view_main.getRootView(), "No workout progress posted yet", BaseTransientBottomBar.LENGTH_SHORT);
                    //        snackbar.setBackgroundTint(mContext.getResources().getColor(R.color.colorPrimaryDark));
                    //        snackbar.show();
//
                    //    }
                    //}

                    for (DataSnapshot postSnapshot : dataSnapshot.child("Statistics").child(statistics_id).child("Workouts").getChildren()) {
                        Log.d(TAG,"dataworkout2" + postSnapshot) ;
                        if (postSnapshot.child("Info").exists() && postSnapshot.child("Hang_Time").exists() && postSnapshot.child("Pause_Time").exists() && postSnapshot.child("Rest_Time").exists()
                        && postSnapshot.child("Sets").exists() && postSnapshot.child("Rounds").exists() && postSnapshot.child("Name").exists() &&postSnapshot.child("Time").exists() ) {


                            String notes = postSnapshot.child("Info").getValue().toString();
                            String train = postSnapshot.child("Hang_Time").getValue().toString();
                            String pause = postSnapshot.child("Pause_Time").getValue().toString();
                            String rest = postSnapshot.child("Rest_Time").getValue().toString();
                            String set = postSnapshot.child("Sets").getValue().toString();
                            String round = postSnapshot.child("Rounds").getValue().toString();
                            String name_from_workout = postSnapshot.child("Name").getValue().toString();
                            String time = postSnapshot.child("Time").getValue().toString();
                            training_sessions_types.add(name_from_workout);
                            training_sessions_time.add(time);
                            sesssions_train_time.add(train);
                            sesssions_pause_time.add(pause);
                            sesssions_rest_time.add(rest);
                            sesssions_rounds_time.add(round);
                            sesssions_sets_time.add(set);
                            training_sessions_notes.add(notes);
                        }

                    }
                for (int i = 0; i< training_sessions_types.size();i++){
                    if (sessions_final.containsKey(training_sessions_types.get(i))){
                        Integer integer = Integer.parseInt(sessions_final.get(training_sessions_types.get(i)).toString());
                        integer++;
                        sessions_final.put(training_sessions_types.get(i),integer);

                    }
                    else{

                        sessions_final.put(training_sessions_types.get(i),1);

                    }
                }

                    Log.d(TAG,"view2"+view);
                   RecyclerView recyclerView = view_main.findViewById(R.id.statistic_rec);
                   recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                   recyclerView.setAdapter(adapter_statistics);
                  //LayoutInflater layoutInflater = LayoutInflater.from(mContext).inflate(R.layout)
                  //RecyclerView recyclerView = view_main
                    Log.d(TAG,"sessions"+boulders_tries);









            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }








}
