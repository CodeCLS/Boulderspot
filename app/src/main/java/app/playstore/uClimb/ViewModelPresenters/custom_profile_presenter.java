package app.playstore.uClimb.ViewModelPresenters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Custom_profile_Adapter;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

public class custom_profile_presenter {
    private static final String TAG = "custom_profile_prese";
    private String uid;
    private String stat_uid;
    private String Age;
    private String Name;
    private String profile_img;
    private String Info;
    private String Subscription;
    private String grade;
    private String country;
    private ArrayList<String> follower = new ArrayList<String>();
    private ArrayList<String> following = new ArrayList<String>();
    private String Height;
    private String account_type;
    private String time_created;
    private ArrayList<String> posts = new ArrayList<>();

    private int columns = 2;
    custom_profile_model custom_profile_model;

    public custom_profile_presenter(String uid) {
        this.custom_profile_model = new custom_profile_model();
        this.uid = uid;
        Log.d(TAG,"username" + uid);


    }

    public custom_profile_presenter() {
    }



    public void init(Context mContext, View view){
        fetchData(mContext,view);




    }

    private void fetchData(Context mContext,View view) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"uid:" +uid);
                stat_uid = Objects.requireNonNull(dataSnapshot.child(uid).child("StatisticsID").getValue()).toString();
                Age = Objects.requireNonNull(dataSnapshot.child(uid).child("Age").getValue()).toString();
                Name = Objects.requireNonNull(dataSnapshot.child(uid).child("Name").getValue()).toString();
                profile_img = Objects.requireNonNull(dataSnapshot.child(uid).child("IMG").getValue()).toString();
                Info = Objects.requireNonNull(dataSnapshot.child(uid).child("Info").getValue()).toString();
                Subscription = Objects.requireNonNull(dataSnapshot.child(uid).child("Subscription").getValue()).toString();
                grade = Objects.requireNonNull(dataSnapshot.child(uid).child("Grade").getValue()).toString();
                country = Objects.requireNonNull(dataSnapshot.child(uid).child("Country").getValue()).toString();
                for (DataSnapshot postSnapshot: dataSnapshot.child("Follower").getChildren()){
                    follower.add(Objects.requireNonNull(postSnapshot.getValue()).toString());

                }
                for (DataSnapshot postSnapshot: dataSnapshot.child("Following").getChildren()){
                    following.add(Objects.requireNonNull(postSnapshot.getValue()).toString());

                }
                for (DataSnapshot postSnapshot: dataSnapshot.child("Posts").getChildren()){
                    posts.add(postSnapshot.getValue().toString());

                }
                Height = Objects.requireNonNull(dataSnapshot.child(uid).child("Height").getValue()).toString();
                time_created = Objects.requireNonNull(dataSnapshot.child(uid).child("Time_created").getValue()).toString();
                account_type = Objects.requireNonNull(dataSnapshot.child(uid).child("account_type").getValue()).toString();
                Log.d(TAG,"Height" + Height);
                Custom_profile_Adapter profile_adapter = new Custom_profile_Adapter(uid,stat_uid,Age,Name,profile_img,Info,Subscription,grade,country,follower,following,Height,account_type,time_created,posts);


                RecyclerView rec = view.findViewById(R.id.custom_profile_rec);
                rec.setLayoutManager(new LinearLayoutManager(mContext));
                rec.setAdapter(profile_adapter);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void follow_new_user(Context mContext,String id_user) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        login_presenter login_presenter = new login_presenter();
        String id = login_presenter.getUID(mContext);
        databaseReference.child("User").child(id).child("Following").child(id_user).setValue(id_user);
    }
}
