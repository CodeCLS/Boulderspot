package app.playstore.uClimb.ViewModelPresenters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
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
import java.util.Objects;

import app.playstore.uClimb.Adapters.Adapter_home;
import app.playstore.uClimb.Adapters.Adapter_home_comment;
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
    private boolean status = false;

    private ArrayList<String> array_place = new ArrayList();
    private ArrayList<String> array_user_id = new ArrayList();
    private ArrayList<String> array_name_comment = new ArrayList();
    private ArrayList<String> array_time_comment = new ArrayList();
    private ArrayList<String> array_comment_id = new ArrayList();
    private ArrayList<String> array_comment = new ArrayList();
    private ArrayList<String> array_User_ID = new ArrayList();
    private ArrayList<String> array_post_id = new ArrayList();
    private ArrayList<String> array_type = new ArrayList();
    private ArrayList<String> array_likes = new ArrayList();
    Adapter_home_comment adapter_home_comment = new Adapter_home_comment(array_name_comment,array_comment_id,array_comment,array_User_ID,array_time_comment);
    Adapter_home adapter_home = new Adapter_home(array_time,array_name,array_source_img,array_source,array_info,array_place,array_user_id,array_post_id,array_type,array_likes);




    private home_posts_model model_home;
    private display display;
    private void clearArrayLists() {
        array_type.clear();
        array_post_id.clear();
        array_user_id.clear();
        array_place.clear();
        array_info.clear();
        array_source_img.clear();
        array_source.clear();
        array_likes.clear();
        array_name.clear();
        array_time.clear();


    }

    public home_posts_presenter(display display) {
        this.model_home = new home_posts_model();
        this.display = display;

    }

    private void initRec(View view, Context context) {


        if (array_time.isEmpty()){
            create_snackbar(view,context);
            boolean testrun= false;

            if (testrun){
                array_type.add("IMG");
                array_post_id.add("asdsa");
                array_user_id.add("adsfa");
                array_place.add("plae");
                array_info.add("asda");
                array_source_img.add("ass");
                array_source.add("asda");
                array_likes.add("2");
                array_name.add("Caleb");
                array_time.add("5:00");


            }


        }

        RecyclerView rec;
        rec = view.findViewById(R.id.rec_home);
        rec.setAdapter(adapter_home);
        rec.setLayoutManager(new LinearLayoutManager(context));
    }

    private void create_snackbar(View view,Context context) {
        if (view == null){
            Toast.makeText(context, "Something is going very wrong here:ERROR X12", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d(TAG,"ViewPresenter" + view);

            Snackbar snackbar = Snackbar.make(view, "Go and follow some people on the search page to see things in your feed. : )", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(context.getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();
        }

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
    public void initcomments(RecyclerView rec, Context mContext){


        getcommentdata(mContext,rec,array_name, array_time, array_comment_id, array_comment, array_User_ID);
        rec.setAdapter(adapter_home_comment);
        rec.setLayoutManager(new LinearLayoutManager(mContext));

    }

    private void getcommentdata(Context mContext,RecyclerView rec,ArrayList<String> array_name, ArrayList<String> array_time, ArrayList<String> array_comment_id, ArrayList<String> array_comment, ArrayList<String> array_User_ID) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("Comments").getChildren()){
                    String uid= Objects.requireNonNull(postSnapshot.child("U_ID").getValue()).toString();
                    Log.d(TAG,"uid"+ uid);

                    String name = dataSnapshot.child("User").child(uid).child("Name").getValue().toString();
                    array_name_comment.add(name);
                    Log.d(TAG,"postsnapshotname" + array_name);

                    String time = postSnapshot.child("Time").getValue().toString();
                    Log.d(TAG,"postsnapshottime" + array_time);
                    array_time_comment.add(time);
                    array_comment.add(postSnapshot.child("comment").getValue().toString());
                    String comment_id = postSnapshot.getKey();
                    array_comment_id.add(comment_id);
                    String uid_String = postSnapshot.child("U_ID").getValue().toString();
                    array_User_ID.add(uid_String);


                }
                rec.setAdapter(adapter_home_comment);
                rec.setLayoutManager(new LinearLayoutManager(mContext));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                //TODO mAUth not working (nullpointer)
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
    public boolean liked(String id, String mAuth){
        // Write a message to the database
        DatabaseReference myRef = initfireliked();
        // Read from the database
        setstatusliked(id, mAuth, myRef);


        return status;


    }

    private void setstatusliked(String id, String mAuth, DatabaseReference myRef) {
        myRef.child("Posts").child(id).child("likes").child(mAuth).setValue(mAuth, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                status = true;

            }
        });
    }

    private DatabaseReference initfireliked() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference("");
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
