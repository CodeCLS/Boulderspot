package app.playstore.uClimb.ViewModelPresenters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Profile_Adapter;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

public class profile_presenter {
    private static final String TAG = "Profile_presenter";
    private String uid;
    private String stat_uid;
    private String Age;
    private String Name;
    private String profile_img;
    private String Info;
    private String Subscription;
    private String grade;
    private String country;
    private ArrayList follower = new ArrayList();
    private ArrayList  following = new ArrayList();
    private String Height;
    private ArrayList competition = new ArrayList();
    private String account_type;
    private String time_created;
    private String position_lat;
    private String position_long;
    private String position_last_updated;
    private Boolean position_status;
    private String Email;
    private profile_model profile_model;
    private app.playstore.uClimb.ViewModelPresenters.login.login_presenter login_presenter;
    private Context mContext;
    private Profile_Adapter profile_adapter;
    public profile_presenter(Context mContext) {
        this.profile_model = new profile_model();
        this.mContext = mContext;
        this.login_presenter = new login_presenter();
        this.uid = login_presenter.getUID(mContext);
        this.stat_uid = login_presenter.getStatisticsID(mContext);


    }
    public void setData(RecyclerView recyclerView){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireData(dataSnapshot,recyclerView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void setRecyclerview(Context mContext, View view){
        RecyclerView recyclerView = view.findViewById(R.id.profile_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        setData(recyclerView);
        Log.d(TAG,"profile_img2" + profile_img);


    }

    private void fireData(DataSnapshot dataSnapshot,RecyclerView recyclerView) {
        Age = dataSnapshot.child("User").child(uid).child("Age").getValue().toString();
        Name = dataSnapshot.child("User").child(uid).child("Name").getValue().toString();
        profile_img = dataSnapshot.child("User").child(uid).child("IMG").getValue().toString();
        Info = dataSnapshot.child("User").child(uid).child("Info").getValue().toString();
        Subscription = dataSnapshot.child("User").child(uid).child("Subscription").getValue().toString();
        grade = dataSnapshot.child("User").child(uid).child("Grade").getValue().toString();
        country = dataSnapshot.child("User").child(uid).child("Country").getValue().toString();
        Height = dataSnapshot.child("User").child(uid).child("Height").getValue().toString();
        Log.d(TAG,"email" + dataSnapshot.child("User").child(uid).child("Email").getValue());
        Email = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Email").getValue()).toString();
        Log.d(TAG,"uidfollowr"+ uid);
        Log.d(TAG,"follower2" + dataSnapshot.child("Following").child("iy9WheRY8CaciMISBllnQMY6FcE2").getValue());
        for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Follower").getChildren()){
            Boolean bool = follower.add(postSnapshot.getValue());


        }
        for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Following").getChildren()){
            Log.d(TAG,"following" + dataSnapshot.child("Following").getChildren());


            Boolean bool = following.add(postSnapshot.getValue());


        }
        for (DataSnapshot postSnapshot: dataSnapshot.child("close_friends_competitiors").getChildren()){
            Boolean bool = competition.add(postSnapshot.getValue());


        }

        account_type = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("account_type").getValue()).toString();
        time_created = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Time_created").getValue()).toString();
        position_lat = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_lat").getValue()).toString();
        position_long = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_lon").getValue()).toString();
        position_last_updated = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_last_Time_updated").getValue()).toString();
        position_status = Boolean.valueOf(Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("position_status").getValue()).toString());
        profile_adapter = new Profile_Adapter(uid,stat_uid,Age,Name,profile_img,Info,Subscription,grade,country,follower,following,Height,competition
                ,account_type,time_created,position_lat,position_long,position_last_updated,position_status,Email);
        recyclerView.setAdapter(profile_adapter);




    }


}
