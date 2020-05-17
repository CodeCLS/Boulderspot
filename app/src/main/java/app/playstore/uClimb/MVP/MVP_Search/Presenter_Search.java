package app.playstore.uClimb.MVP.MVP_Search;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;

import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import app.playstore.uClimb.Adapters.Search.Adapter_Search_Items;
import app.playstore.uClimb.R;

public class Presenter_Search {
    private static final String TAG = "Search_presenter";
    private ArrayList<String> URL= new ArrayList<>();
    private  ArrayList<String> Date   = new ArrayList<>();
    private  ArrayList<String> Location= new ArrayList<>();;
    private  ArrayList<String> info= new ArrayList<>();
    private  ArrayList<String> type_array= new ArrayList<>();
    private  ArrayList<String> user_id_array= new ArrayList<>();
    private  ArrayList<String> post_id_array= new ArrayList<>();
    private  ArrayList<String> likes_array= new ArrayList<>();
    private  ArrayList<String> saved_array= new ArrayList<>();
    ArrayList<String> posts = new ArrayList<>();
    ArrayList<String> posts_ = new ArrayList<>();


    ArrayList<String> user = new ArrayList<>();
    private  ArrayList<String> shared_array= new ArrayList<>();

    private int numbercolumns = 4;



    private Presenter_Search search_presenter;

    public Presenter_Search() {
        this.search_presenter = search_presenter;
    }
    public void setRec(View view, Context mContext){





        getDatafromfire(view,mContext);
    }

    private void initRec(@NonNull View view, Context mContext) {

        RecyclerView rec = view.findViewById(R.id.rec_search_main);
        rec.setLayoutManager(new GridLayoutManager(mContext,numbercolumns));


        rec.setAdapter(new Adapter_Search_Items(URL,Date,Location,info,type_array,mContext,user_id_array,post_id_array));
        Log.d(TAG,"recyclerview" + URL + " rec:" + rec);



    }

    private void clearArrays() {
        URL.clear();
        Date.clear();
        Location.clear();
        info.clear();
        type_array.clear();
        user_id_array.clear();
        post_id_array.clear();
        likes_array.clear();
        saved_array.clear();
        shared_array.clear();
    }

    private void getDatafromfire(View view,Context mContext){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                clearArrays();
                addUsertoArray(dataSnapshot, user);

                populate_arrays(dataSnapshot);
                Collections.shuffle(posts_);
                Log.d(TAG,"sorted233" + posts_);


               //random_numb_index_generator();
               //try {
               //    random_user_picker(dataSnapshot,user);
               //} catch (ParseException e) {
               //    e.printStackTrace();
               //}


                addDataWork(dataSnapshot, posts_, view, mContext);



                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private int random_numb_index_generator() {
        int user_public_count = (int) user.size();
        Log.d(TAG,"User_Size" + user_public_count);
        int rand = 0;
        if (user_public_count != 0) {
            Random random = new Random();
            rand = random.nextInt(user_public_count);

        }
        return rand;
    }



    private void random_user_picker(@NonNull DataSnapshot dataSnapshot, @NonNull ArrayList<String> user) throws ParseException {





                int rand = random_numb_index_generator();


                Log.d(TAG, "13231233 " + rand + " user: " + user);




                for (int i = 0; i < user.size(); i++) {
                    if (i == rand) {
                        Log.d(TAG,"23453453 " + user.get(i) + "   " + user);


                        get_newest_post(dataSnapshot, i);




                    }


                }


    }

    private void populate_arrays(@NonNull DataSnapshot dataSnapshot) {


        for (int i =0; i< user.size();i++){
            for (DataSnapshot post : dataSnapshot.child("User").child(user.get(i)).child("Posts").getChildren()){
                posts_.add(post.getKey());
                Log.d(TAG,"posts_32 " + posts_);

            }




        }
    }

    private void get_newest_post(@NonNull DataSnapshot dataSnapshot, int i) throws ParseException {
        String newest_post = null;
        long newest_post_time = 1;
        Log.d(TAG,"posts33: " + posts);

        for (DataSnapshot post: dataSnapshot.child("User").child(user.get(i)).child("Posts").getChildren()){
            if (posts_.contains(post.getKey())) {
                String time = dataSnapshot.child("Posts").child(post.getKey()).child("Time").getValue().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                Date date = sdf.parse(time);
                long millis = date.getTime();
                if (newest_post_time < millis) {
                    newest_post = post.getKey();
                    newest_post_time = millis;
                }
                Log.d(TAG, "132313-4" + newest_post + " user " + user.get(i));
            }

        }
        Log.d(TAG,"Newest_post: " + newest_post + " User: " + user);


        if (newest_post != null){
            Log.d(TAG, "132313-6" + posts + " user " + user.get(i) + " post: " + newest_post);

            posts.add(newest_post);
            posts_.remove(newest_post);
            user.remove(i);
            Log.d(TAG,"User23" + user);

            if (user.isEmpty()){
                Log.d(TAG, "132313-1");

                if (!posts_.isEmpty()){
                    addUsertoArray(dataSnapshot,user);
                    random_user_picker(dataSnapshot,user);
                    Log.d(TAG, "132313-2");




                }
                else{

                    Log.d(TAG, "132313-5" + posts +posts_);



                }



            }
            else {
                Log.d(TAG, "132313-3" + user + " new: " + newest_post);

                random_user_picker(dataSnapshot,user);
            }


        }
        else{
            user.remove(i);
            Log.d(TAG,"user: " + user);
            //posts_.remove(newest_post);
            random_user_picker(dataSnapshot,user);


        }





    }

    private void addUsertoArray(@NonNull DataSnapshot dataSnapshot, ArrayList<String> user) {
        for (DataSnapshot post:dataSnapshot.child("Public_Users").getChildren()){
            user.add(post.getKey());

        }
    }

    private void getPosts(@NonNull DataSnapshot dataSnapshot, ArrayList<String> posts, DataSnapshot dataSnapshot1) {
        int i = 0;
        for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(dataSnapshot1.getValue().toString()).child("Posts").getChildren()){
            if (i!=0){

            }
            else{
                if (!posts.contains(postSnapshot.getKey())){
                    posts.add(postSnapshot.getKey());
                    i++;

                }

            }

        }
    }

    private void addDataWork(@NonNull DataSnapshot dataSnapshot, ArrayList<String> posts, View view, Context mContext) {
        Log.d(TAG,"posts23: " + posts);
        for (int g =0; g< posts.size();g++){
            Log.d(TAG,"Posts: " + posts);
            DataSnapshot postSnapshot = dataSnapshot.child("Posts").child(posts.get(g));

            String URL_s = Objects.requireNonNull(postSnapshot.child("Source").getValue()).toString();
            Log.d(TAG,"URL"+URL_s);
            String type = Objects.requireNonNull(postSnapshot.child("type").getValue()).toString();
            String time = Objects.requireNonNull(postSnapshot.child("Time").getValue()).toString();
            String u_ID = Objects.requireNonNull(postSnapshot.child("User_ID").getValue()).toString();
            String p_ID = postSnapshot.getKey();
            getArrayData_l_s_s(postSnapshot);
            addData(URL_s, type, time, u_ID, p_ID);


        }
        initRec(view,mContext);
    }

    private void addData(String URL_s, String type, String time, String u_ID, String p_ID) {
        URL.add(URL_s);
        type_array.add(type);
        Date.add(time);
        user_id_array.add(u_ID);
        post_id_array.add(p_ID);
        Location.add("Place");
    }

    private void getArrayData_l_s_s(DataSnapshot postSnapshot) {
        for (DataSnapshot postSnapshot_2: postSnapshot.child("likes").getChildren()){
            String u_liked = postSnapshot_2.getValue().toString();
            likes_array.add(u_liked);
        }
        for (DataSnapshot postSnapshot_2: postSnapshot.child("saved").getChildren()){
            String u_saved = postSnapshot_2.getValue().toString();
            saved_array.add(u_saved);
        }
        for (DataSnapshot postSnapshot_2: postSnapshot.child("shared").getChildren()){
            String u_shared = postSnapshot_2.getValue().toString();
            shared_array.add(u_shared);
        }
    }


}
