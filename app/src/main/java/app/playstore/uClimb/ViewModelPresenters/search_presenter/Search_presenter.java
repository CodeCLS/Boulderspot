package app.playstore.uClimb.ViewModelPresenters.search_presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.playstore.uClimb.Adapters.Adapter_search_inner;
import app.playstore.uClimb.R;

public class Search_presenter {
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
    private  ArrayList<String> shared_array= new ArrayList<>();

    private int numbercolumns = 2;



    private Search_presenter search_presenter;

    public Search_presenter() {
        this.search_presenter = search_presenter;
    }
    public void setRec(View view, Context mContext){





        getDatafromfire(view,mContext);
    }

    private void initRec(@NonNull View view, Context mContext) {

        RecyclerView rec = view.findViewById(R.id.rec_search_page);
        rec.setLayoutManager(new GridLayoutManager(mContext,numbercolumns));


        rec.setAdapter(new Adapter_search_inner(URL,Date,Location,info,type_array,mContext));
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
        myRef.child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearArrays();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Log.d(TAG,"datasnapshot"+postSnapshot);


                    String URL_s = postSnapshot.child("Source").getValue().toString();
                    Log.d(TAG,"URL"+URL_s);
                    String type = postSnapshot.child("type").getValue().toString();
                    String time = postSnapshot.child("Time").getValue().toString();
                    String u_ID = postSnapshot.child("User_ID").getValue().toString();
                    String p_ID = postSnapshot.getKey().toString();
                    getArrayData_l_s_s(postSnapshot);
                    addData(URL_s, type, time, u_ID, p_ID);


                }
                initRec(view,mContext);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
