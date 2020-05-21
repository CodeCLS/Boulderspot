package app.playstore.uClimb.MVP.MVP_Workout;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.util.Collections;
import java.util.logging.Logger;

import app.playstore.uClimb.Adapters.Custom_Adapter;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;

public class Presenter_Workout {
    private static final String TAG = "Presenter_Workout";
    private ArrayList<String> post_id = new ArrayList<>();
    private ArrayList<String>  u_id = new ArrayList<>();
    private ArrayList<String>  info = new ArrayList<>();
    private ArrayList<String>  Source_Video = new ArrayList<>();
    private ArrayList<String>  IMG = new ArrayList<>();
    private ArrayList<String>  Name = new ArrayList<>();
    private ArrayList<String>  Likes = new ArrayList<>();
    private ArrayList<String>  Time = new ArrayList<>();


    private ArrayList<String> post_id_user = new ArrayList<>();
    private ArrayList<String>  u_id_User = new ArrayList<>();
    private ArrayList<String>  info_User = new ArrayList<>();
    private ArrayList<String>  Source_Video_User = new ArrayList<>();
    private ArrayList<String>  IMG_User = new ArrayList<>();
    private ArrayList<String>  Name_User = new ArrayList<>();
    private ArrayList<String>  Likes_User = new ArrayList<>();
    private ArrayList<String>  Type_User = new ArrayList<>();
    private ArrayList<String>  Time_User = new ArrayList<>();




    public void initRecWorkout(Context context, View view,String type) {
        getDataWorkout(context,view,type);




    }
    public void getDataUser(Context context,View view){
        ArrayList<DataSnapshot> posts_User = new ArrayList<DataSnapshot>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        Presenter_Login presenter_login = new Presenter_Login();
        String uid = presenter_login.getUID(context);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("User").child(uid).child("Custom_Uploads").getChildren()){
                    posts_User.add(dataSnapshot1);

                }


                for (DataSnapshot posts2 : posts_User){
                    if (posts2.child("Info").exists() &&posts2.child("Source").exists() && posts2.exists() &&posts2.child("Likes").exists() && posts2.child("Type").exists()
                    &&posts2.child("Time").exists() && dataSnapshot.child("User").child(uid).child("IMG").exists() && dataSnapshot.child("User").child(uid).child("Name").exists() ){
                        post_id_user.add(posts2.getKey());
                        u_id_User.add(uid);
                        info_User.add(posts2.child("Info").getValue().toString());

                        Source_Video_User.add(posts2.child("Source").getValue().toString());
                        IMG_User.add(dataSnapshot.child("User").child(uid).child("IMG").getValue().toString());
                        Name_User.add(dataSnapshot.child("User").child(uid).child("Name").getValue().toString());
                        Likes_User.add(String.valueOf(posts2.child("Likes").getChildrenCount()));
                        Type_User.add(String.valueOf(posts2.child("Type").getValue()));
                        Time_User.add(String.valueOf(posts2.child("Time").getValue()));

                    }



                }
                Log.d(TAG,"context23" + context);
                RecyclerView recyclerView = view.findViewById(R.id.rec_custom_profile_training);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                Custom_Adapter custom_adapter = new Custom_Adapter(post_id_user,u_id_User,info_User,Source_Video_User,IMG_User,Name_User,Likes_User,Type_User,Time_User);

                recyclerView.setAdapter(custom_adapter);


            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void getDataWorkout(Context context,View view,String type){
        ArrayList<DataSnapshot> posts = new ArrayList<DataSnapshot>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Custom_Uploads").child(type).exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.child("Custom_Uploads").child(type).getChildren()) {
                        posts.add(dataSnapshot1);

                    }

                    Collections.shuffle(posts);
                    for (DataSnapshot posts2 : posts) {
                        if (posts2.exists() && posts2.child("User_ID").exists() && posts2.child("Info").exists() && posts2.child("Source").exists() && dataSnapshot.child("User").child(posts2.child("User_ID").getValue().toString()).child("IMG").exists()
                        && dataSnapshot.child("User").child(posts2.child("User_ID").getValue().toString()).child("Name").exists() && posts2.child("Likes").exists() && posts2.child("Time").exists()) {
                            Log.d(TAG, "Type23: " + type + " post: " + posts2.getKey());
                            post_id.add(posts2.getKey());
                            u_id.add(posts2.child("User_ID").getValue().toString());
                            info.add(posts2.child("Info").getValue().toString());
                            Source_Video.add(posts2.child("Source").getValue().toString());
                            IMG.add(dataSnapshot.child("User").child(posts2.child("User_ID").getValue().toString()).child("IMG").getValue().toString());
                            Name.add(dataSnapshot.child("User").child(posts2.child("User_ID").getValue().toString()).child("Name").getValue().toString());
                            Likes.add(String.valueOf(posts2.child("Likes").getChildrenCount()));
                            Time.add(String.valueOf(posts2.child("Time").getValue().toString()));
                        }

                    }
                    Log.d(TAG, "context23" + context);
                    RecyclerView recyclerView = view.findViewById(R.id.rec_custom_workouts);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    Custom_Adapter custom_adapter = new Custom_Adapter(post_id, u_id, info, Source_Video, IMG, Name, Likes, type, Time);

                    recyclerView.setAdapter(custom_adapter);

                }
            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void liked(String s, ImageView likes_img,String type,Context context) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Presenter_Login presenter_login = new Presenter_Login();
        String uid = presenter_login.getUID(context);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"type23: " + type + "   post_id" + s + "   uid" + uid);
                if (dataSnapshot.child("Custom_Uploads").child(type).child(s).child("Likes").child(uid).exists()){
                    likes_img.setImageResource(R.mipmap.like_active);

                }
                else {
                    likes_img.setImageResource(R.mipmap.like_passive);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void user_likes(String s, ImageView likes_img, String intent_type, Context context) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Presenter_Login presenter_login = new Presenter_Login();
        String uid = presenter_login.getUID(context);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Custom_Uploads").child(intent_type).child(s).child("Likes").child(uid).exists()){
                    if (dataSnapshot.child("Custom_Uploads").child(intent_type).child(s).child("User_ID").exists() ) {
                        String id = dataSnapshot.child("Custom_Uploads").child(intent_type).child(s).child("User_ID").getValue().toString();
                        databaseReference.child("Custom_Uploads").child(intent_type).child(s).child("Likes").child(uid).removeValue();
                        databaseReference.child("User").child(id).child("Custom_Uploads").child(s).child("Likes").child(uid).removeValue();

                        likes_img.setImageResource(R.mipmap.like_passive);
                    }

                }
                else {
                    if (dataSnapshot.child("Custom_Uploads").child(intent_type).child(s).child("User_ID").exists()) {
                        String id = dataSnapshot.child("Custom_Uploads").child(intent_type).child(s).child("User_ID").getValue().toString();
                        databaseReference.child("Custom_Uploads").child(intent_type).child(s).child("Likes").child(uid).setValue(uid);
                        databaseReference.child("User").child(id).child("Custom_Uploads").child(s).child("Likes").child(uid).setValue(uid);


                        likes_img.setImageResource(R.mipmap.like_active);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void delete(String s, String intent_type, Context context) {
        Presenter_Login presenter_login = new Presenter_Login();
        String  id = presenter_login.getUID(context);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Custom_Uploads").child(intent_type).child(s).removeValue();
        databaseReference.child("User").child(id).child("Custom_Uploads").child(s).removeValue();
        Toast.makeText(context, "Gelöscht", Toast.LENGTH_SHORT).show();
    }
}