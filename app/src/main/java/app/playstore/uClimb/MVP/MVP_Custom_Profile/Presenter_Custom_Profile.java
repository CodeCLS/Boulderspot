package app.playstore.uClimb.MVP.MVP_Custom_Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Profile.Adapter_Profile_Custom_User_Page;
import app.playstore.uClimb.Obsolete.Adapter_Profile_User_Uploads;
import app.playstore.uClimb.Adapters.Public_Spinner_Base_Profiles;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Presenter_Custom_Profile {
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
    Model_Custom_Profile custom_profile_model;
    private ArrayList<String> Source = new ArrayList<String>();
    private ArrayList<String> type = new ArrayList<String>();
    private ArrayList<String> info = new ArrayList<String>();
    private ArrayList<String> posts_inner = new ArrayList<String>();
    private ArrayList<String> uid_inner = new ArrayList<String>();
    private ArrayList<String> time = new ArrayList<String>();
    private ArrayList<String> place = new ArrayList<String>();
    Presenter_Login presenter_login = new Presenter_Login();





    public Presenter_Custom_Profile(String uid) {
        this.custom_profile_model = new Model_Custom_Profile();
        this.uid = uid;
        Log.d(TAG,"username" + uid);


    }

    public Presenter_Custom_Profile() {
    }



    public void init(Context mContext, View view){
        fetchData(mContext,view);




    }

    private void fetchData(Context mContext,View view) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"uid2323" + uid + " "+dataSnapshot.child(uid).child("StatisticsID").exists() + dataSnapshot.child(uid).child("Age").exists()+dataSnapshot.child(uid).child("Name").exists()
                +dataSnapshot.child(uid).child("Info").exists()+dataSnapshot.child(uid).child("Subscription").exists()+dataSnapshot.child(uid).child("Grade").exists()
                +dataSnapshot.child(uid).child("Country").exists() + dataSnapshot.child("Follower").exists()+dataSnapshot.child(uid).child("Following").exists()+ dataSnapshot.child(uid).child("Posts").exists());

                if (dataSnapshot.child(uid).child("StatisticsID").exists() && dataSnapshot.child(uid).child("Age").exists() && dataSnapshot.child(uid).child("Name").exists()
                        && dataSnapshot.child(uid).child("Info").exists() && dataSnapshot.child(uid).child("Subscription").exists() && dataSnapshot.child(uid).child("Grade").exists() && dataSnapshot.child(uid).child("Country").exists()
                        ){

                    Log.d(TAG, "uid23:" + uid);
                stat_uid = Objects.requireNonNull(dataSnapshot.child(uid).child("StatisticsID").getValue()).toString();
                Age = Objects.requireNonNull(dataSnapshot.child(uid).child("Age").getValue()).toString();
                Name = Objects.requireNonNull(dataSnapshot.child(uid).child("Name").getValue()).toString();
                profile_img = Objects.requireNonNull(dataSnapshot.child(uid).child("IMG").getValue()).toString();
                Info = Objects.requireNonNull(dataSnapshot.child(uid).child("Info").getValue()).toString();
                Subscription = Objects.requireNonNull(dataSnapshot.child(uid).child("Subscription").getValue()).toString();
                grade = Objects.requireNonNull(dataSnapshot.child(uid).child("Grade").getValue()).toString();
                country = Objects.requireNonNull(dataSnapshot.child(uid).child("Country").getValue()).toString();
                for (DataSnapshot postSnapshot : dataSnapshot.child("Follower").getChildren()) {
                    follower.add(Objects.requireNonNull(postSnapshot.getValue()).toString());

                }
                for (DataSnapshot postSnapshot : dataSnapshot.child(uid).child("Following").getChildren()) {
                    following.add(Objects.requireNonNull(postSnapshot.getValue()).toString());

                }
                for (DataSnapshot postSnapshot : dataSnapshot.child(uid).child("Posts").getChildren()) {
                    posts.add(postSnapshot.getValue().toString());
                    Log.d(TAG, "post3" + posts);

                }


                Height = Objects.requireNonNull(dataSnapshot.child(uid).child("Height").getValue()).toString();
                time_created = Objects.requireNonNull(dataSnapshot.child(uid).child("Time_created").getValue()).toString();
                account_type = Objects.requireNonNull(dataSnapshot.child(uid).child("account_type").getValue()).toString();
                Log.d(TAG, "post2" + uid);
                Adapter_Profile_Custom_User_Page profile_adapter = new Adapter_Profile_Custom_User_Page(uid, stat_uid, Age, Name, profile_img, Info, Subscription, grade, country, follower, following, Height, account_type, time_created, posts);
                RecyclerView rec = view.findViewById(R.id.custom_profile_rec);
                rec.setLayoutManager(new LinearLayoutManager(mContext));
                rec.setAdapter(profile_adapter);


            }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void follow_new_user(Context mContext, String id_user, boolean b,Button btn,Button btn_friend) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        if (b){
            String uid = presenter_login.getUID(mContext);
            databaseReference.child("User").child(uid).child("Following").child(id_user).removeValue();
            databaseReference.child("User").child(uid).child("Friends").child(id_user).removeValue();

            databaseReference.child("User").child(id_user).child("Follower").child(uid).removeValue();
            isFollowing(id_user,mContext,btn,btn_friend);



        }
        else{


            String id = presenter_login.getUID(mContext);
            Log.d(TAG,"uid"+id);
            Log.d(TAG,"id_user"+id_user);


            databaseReference.child("User").child(id).child("Following").child(id_user).setValue(id_user);
            databaseReference.child("User").child(id_user).child("Follower").child(id).setValue(id);
            isFollowing(id_user,mContext,btn,btn_friend);



        }

    }

    public void isFollowing(String uid, Context mContext, Button button,Button btn_friend) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String u_iD = presenter_login.getUID(mContext);
                Boolean status_user_existence = dataSnapshot.child("User").child(u_iD).child("Following").child(uid).exists();
                Log.d(TAG,"Status_user_experience" + status_user_existence);
                if (status_user_existence){
                    Adapter_Profile_Custom_User_Page adapter_profile_custom_user_page = new Adapter_Profile_Custom_User_Page();

                    adapter_profile_custom_user_page.setFollowing(true,button,mContext,uid,btn_friend);

                }
                else{
                    Adapter_Profile_Custom_User_Page adapter_profile_custom_user_page = new Adapter_Profile_Custom_User_Page();

                    adapter_profile_custom_user_page.setFollowing(false,button,mContext,uid,btn_friend);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getSource(Context mContext,int position, ImageView img, SimpleExoPlayerView exoplayer, ProgressBar progress, Presenter_Custom_Profile custom_profile, int holdertype, ArrayList posts,Button more,String uid) {
        ArrayList source = new ArrayList<String>();
        ArrayList type = new ArrayList<String>();

        Log.d(TAG,"4324");
        position = position;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        int finalPosition = position;
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String id = posts.get(finalPosition).toString();

                if (dataSnapshot.child("Posts").child(id).child("Source").exists() &&dataSnapshot.child("Posts").child(id).child("type").exists()  ) {
                    for (int i = 0; i < posts.size(); i++) {
                        Log.d(TAG, " source: " + posts + " position: " + finalPosition);
                        source.add(dataSnapshot.child("Posts").child(id).child("Source").getValue().toString());
                        type.add(dataSnapshot.child("Posts").child(id).child("type").getValue().toString());




                    }

                    Adapter_Profile_Custom_User_Page custom_user_page = new Adapter_Profile_Custom_User_Page();
                    custom_user_page.addPosts(mContext, finalPosition, img, exoplayer, progress, holdertype, source, type,more,posts,uid);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setFollowing(String uid, boolean b) {
        if (b){

        }
        else {

        }
    }

    public void getUserData(Context mContext, Spinner Follower_Spinner, Spinner Following_Spinner, String uid, TextView txt_Follower,TextView txt_Following) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        ArrayList Follower = new ArrayList<String>();
        ArrayList Follower_img = new ArrayList<String>();
        ArrayList Follower_name = new ArrayList<String>();

        ArrayList Following = new ArrayList<String>();
        ArrayList Following_img = new ArrayList<String>();
        ArrayList Following_name = new ArrayList<String>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Follower").getChildren()){
                    Log.d(TAG,"follower21" + postSnapshot.getKey());
                    if (dataSnapshot.child("User").child(postSnapshot.getKey()).child("IMG").exists()) {
                        String img = dataSnapshot.child("User").child(postSnapshot.getKey()).child("IMG").getValue().toString();
                        Follower_img.add(img);
                    }
                    if (dataSnapshot.child("User").child(postSnapshot.getKey()).child("Name").exists()) {
                        Follower.add(postSnapshot.getKey());

                        String name = dataSnapshot.child("User").child(postSnapshot.getKey()).child("Name").getValue().toString();

                        Follower_name.add(name);
                    }



                }
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Following").getChildren()){
                    if (dataSnapshot.child("User").child(postSnapshot.getKey()).child("IMG").exists()){
                        String img = dataSnapshot.child("User").child(postSnapshot.getKey()).child("IMG").getValue().toString();
                        Following_img.add(img);



                    }
                    if (dataSnapshot.child("User").child(postSnapshot.getKey()).child("Name").exists()){
                        Following.add(postSnapshot.getKey());
                        String name = dataSnapshot.child("User").child(postSnapshot.getKey()).child("Name").getValue().toString();
                        Following_name.add(name);

                    }

                }
                txt_Follower.setText(Follower.size()+" Follower");
                txt_Following.setText(Following.size()+" Following");
                Following_Spinner.setAdapter(new Public_Spinner_Base_Profiles(Following_img,Following_name,Following));
                Follower_Spinner.setAdapter(new Public_Spinner_Base_Profiles(Follower_img,Follower_name,Follower));
                Following_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //TODO Fragment transaction

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                Follower_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //TODO Fragment transaction

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addFriend(String uid, Context mContext, Button btn_friend) {
        String id = presenter_login.getUID(mContext);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            //TODO add if for if btn pressed and add friend boolean
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                btn_friend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataSnapshot.child("User").child(id).child("Friends").child(uid).exists() || dataSnapshot.child("User").child(uid).child("Invites_Friends").child(uid).exists()){
                            databaseReference.child("User").child(id).child("Friends").child(uid).removeValue();
                            databaseReference.child("User").child(uid).child("Invites_Friends").child(uid).removeValue();


                            setFriendImage(false,btn_friend);
                            addFriend(uid,mContext,btn_friend);

                        }
                        else{
                            databaseReference.child("User").child(uid).child("Invites_Friends").child(id).setValue(id);

                            setFriendImage(true,btn_friend);
                            addFriend(uid,mContext,btn_friend);



                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void isFriend(String uid, Context mContext, Button btn_friend) {
        String id = presenter_login.getUID(mContext);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            //TODO add if for if btn pressed and add friend boolean
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("User").child(id).child("Friends").child(uid).exists()){
                    setFriendImage(true,btn_friend);

                }
                else{
                    setFriendImage(false,btn_friend);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setFriendImage(boolean b,Button btn_friend) {
        if (b){
            btn_friend.setText("Added as Friend");

        }
        else{
            btn_friend.setText("Add as Friend");

        }


    }
}
