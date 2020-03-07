package app.playstore.uClimb.ViewModelFragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.playstore.uClimb.Adapters.Adapter_home;
import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.Models.Model_home;
import app.playstore.uClimb.R;

public class home_posts_presenter  {
    private static final String TAG = "presenter";

    private ArrayList<String> following= new ArrayList<>();
    private ArrayList<String> following_ID= new ArrayList<>();
    private ArrayList<String> posts= new ArrayList<>();
    List<String> l = new ArrayList<String>();
    private ArrayList<String> array_time = new ArrayList();
    private ArrayList<String> array_name = new ArrayList();
    private ArrayList<String> array_source_img = new ArrayList();
    private ArrayList<String> array_source = new ArrayList();
    private ArrayList<String> array_info = new ArrayList();
    private ArrayList<String> array_place = new ArrayList();
    private ArrayList<String> array_user_id = new ArrayList();
    private ArrayList<String> array_post_id = new ArrayList();
    private ArrayList<String> array_type = new ArrayList();
    private ArrayList<String> array_likes = new ArrayList();
    Adapter_home adapter_home = new Adapter_home(array_time,array_name,array_source_img,array_source,array_info,array_place,array_user_id,array_post_id,array_type,array_likes);




    private home_posts_model model_home;
    private display display;
    private void clearArrayLists() {


    }

    public home_posts_presenter(display display) {
        this.model_home = new home_posts_model();
        this.display = display;

    }

    public void setRecylcerviewandData(View view, Context context){




        initRec(view, context);


    }

    private void initRec(View view, Context context) {
        array_time.add("6:00");
        array_name.add("Caleb");
        array_source.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FVID_20200128_180436.mp4?alt=media&token=08b6fd4d-3fc1-47e8-9638-83a7309901bb");
        array_source_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F2019-Boulderwelt-Regensburg-Event-Bouldern-Klettern-Bouldergame-Catch-Ya-Match-25.jpg?alt=media&token=3dfa8e9a-47f4-40d9-a777-f6287d397685");
        array_info.add("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
        array_place.add("Berta Block Boulderhalle");
        array_user_id.add("daoasds");
        array_post_id.add("asdads");
        array_type.add("video");
        array_likes.add("2");
        RecyclerView rec;
        rec = view.findViewById(R.id.rec_home);
        rec.setAdapter(adapter_home);
        rec.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setData(View view,String mAuth,Context context){

        DatabaseReference myRef = addDatatoArray_postID(mAuth);
        for_loop_posts(myRef);
        sortList();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getallpostdata(dataSnapshot,view,context);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Bundle bundle = createBundle();
        display.allPostData(bundle);


    }

    private void getallpostdata(@NonNull DataSnapshot dataSnapshot,View view,Context context) {
        clearArrayLists();

        for (int i = 0;i< l.size();i++) {


            for (DataSnapshot postSnapshot : dataSnapshot.child("Posts").getChildren()) {

                if (postSnapshot.child("Time").getValue().equals(l.get(i))){
                    addDatato_arrays(dataSnapshot, postSnapshot);


                }

            }
        }
        initRec(view,context);

    }

    private void addDatato_arrays(@NonNull DataSnapshot dataSnapshot, DataSnapshot postSnapshot) {
        String time =postSnapshot.child("Time").getValue().toString();
        String name = dataSnapshot.child("User").child(postSnapshot.child("User_ID").getValue().toString()).child("Name").getValue().toString();
        String profile_img = dataSnapshot.child("User").child(postSnapshot.child("User_ID").getValue().toString()).child("Profil_IMG").getValue().toString();
        String source = postSnapshot.child("Source").getValue().toString();
        String info = postSnapshot.child("Info").getValue().toString();
        String place = postSnapshot.child("Place").getValue().toString();
        String uID = postSnapshot.child("User_ID").getValue().toString();
        String post_ID = postSnapshot.getKey().toString();
        String type = postSnapshot.child("type").getValue().toString();
        String likes = String.valueOf(postSnapshot.child("likes").getChildrenCount());

        array_time.add(time);
        array_name.add(name);
        array_source_img.add(profile_img);
        array_source.add(source);
        array_info.add(info);
        array_place.add(place);
        array_user_id.add(uID);
        array_post_id.add(post_ID);
        array_type.add(type);
        array_likes.add(likes);
    }

    private Bundle createBundle() {
        home_posts_model home_posts_model = new home_posts_model(array_time,array_name,array_source_img,array_source,array_info,array_place,array_user_id,array_post_id,array_type,array_likes);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("array_time",array_time);
        bundle.putStringArrayList("array_name",array_name);
        bundle.putStringArrayList("array_source_img",array_source_img);
        bundle.putStringArrayList("array_source",array_source);
        bundle.putStringArrayList("array_info",array_info);
        bundle.putStringArrayList("array_place",array_place);
        bundle.putStringArrayList("array_uID",array_user_id);
        bundle.putStringArrayList("array_pID",array_post_id);
        bundle.putStringArrayList("array_type",array_type);
        bundle.putStringArrayList("array_likes",array_likes);
        return bundle;
    }

    private DatabaseReference addDatatoArray_postID(String mAuth) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        DatabaseReference myRef_posts = database.getReference("Posts");
        DatabaseReference myRef_null = database.getReference("");



        // Read from the database
        myRef_null.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"mAuth2"+ mAuth);
                for(DataSnapshot postsnapshot: dataSnapshot.child(mAuth).child("Following").getChildren()){
                    String post_value = postsnapshot.getValue().toString();
                    following.add(post_value);
                    Log.d(TAG,"following_data" + following);
                    Log.d(TAG,"post_value" + post_value);


                }
                for(int i= 0;i<following.size();i++){
                    final String ID_instance = following_ID.get(i);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot postSnapshot: dataSnapshot.child(ID_instance).child("posts").getChildren()){
                                String post_val = postSnapshot.getValue().toString();
                                posts.add(post_val);
                                Log.d(TAG,"posts_data" + posts);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return myRef_null;
    }

    private void sortList() {
        Collections.sort(l, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                try {
                    return new SimpleDateFormat("hh:mm a").parse(o1).compareTo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'").parse(o2));
                } catch (ParseException e) {
                    return 0;
                }
            }
        });
    }

    private void for_loop_posts(DatabaseReference myRef_posts) {
        for (int i = 0;i < posts.size();i++){
            String postID = posts.get(i);
            myRef_posts.child("Posts").child(postID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String time_val_current = dataSnapshot.child(postID).child("Time").getValue().toString();
                    l.add(time_val_current);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
    }




    public interface display{
        void allPostData(Bundle bundle);


    }


}
