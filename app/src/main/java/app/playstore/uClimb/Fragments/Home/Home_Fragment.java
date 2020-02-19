package app.playstore.uClimb.Fragments.Home;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Scene;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Adapter_home;
import app.playstore.uClimb.Fragments.video_upload_fragment;
import app.playstore.uClimb.R;

public class Home_Fragment extends Fragment {
    private static final String TAG = "Home Fragment";
    private String name_fire;
    private String URL;
    private FirebaseAuth mAuth;
    final Bundle bundle = new Bundle();
    private ArrayList<String> post = new ArrayList<>();


    private ArrayList<String> IMG_URL = new ArrayList<>();
    private ArrayList<String> grade = new ArrayList<>();
    private ArrayList<String> place = new ArrayList<>();
    private ArrayList<String> seen_by = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> info = new ArrayList<>();

    private ArrayList<String> video_url = new ArrayList<>();
    private ArrayList<String> ID_User = new ArrayList<>();
    private ArrayList<String> likes = new ArrayList<>();

    //Adapter home is init
    private Adapter_home adapter_home = new Adapter_home(IMG_URL, grade, place, name, info,  video_url, ID_User, likes,post, getContext());
    //Firebase values are init
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");
    //Recycler view is established


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.main_page, container, false);

    }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //readDB();
        IMG_URL.clear();
        name.clear();
        info.clear();
        video_url.clear();
        ID_User.clear();
        likes.clear();
        post.clear();
        grade.clear();
        place.clear();

        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fmelody-jacob-hb8v-SZm7VY-unsplash.jpg?alt=media&token=d9daa0e9-a575-4c6a-8143-e19acc2b1649");
        grade.add("7+");
        place.add("Berta");
        name.add("Caleb");
        info.add("Calebs info");
        video_url.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FUltimateCheerfulCanine-mobile.mp4?alt=media&token=c132bed7-a412-4e99-a475-d0a0c8a8c89c");
        ID_User.add("asfdafs");
        likes.add("2");
        post.add("adsdads");

        setRec(view);


        floatingAction(view);


    }



    private void floatingAction(@NonNull View view) {
        FloatingActionButton plus_img = view.findViewById(R.id.plus_img);
        plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransactiontoUpload();
            }
        });
    }

    private void fragmentTransactiontoUpload() {
        video_upload_fragment mFragment = new video_upload_fragment();
        FragmentManager fragmentManager = getFragmentManager();
        Scene mScene;

        assert fragmentManager != null;



        fragmentManager.beginTransaction().addToBackStack("video").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container_fragment, mFragment).commit();
    }

    private void setRec(@NonNull View view) {
        RecyclerView rec;

        rec = view.findViewById(R.id.rec_home);
        rec.setAdapter(adapter_home);

        rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG,"my"+myRef);

    }



    @Override
    public void onResume(){
        super.onResume();


    }














    private void logs_getting_data(@NonNull DataSnapshot dataSnapshot, DataSnapshot postsnapshot, DataSnapshot datasnapshot1, String key) {
        Log.d(TAG,"Datasnapshot" + dataSnapshot);
        Log.d(TAG,"Datasnapshot1"+datasnapshot1);
        Log.d(TAG,"STepOneData"+datasnapshot1.child(key).child("Posts"));
        Log.d(TAG,"post_data" + Objects.requireNonNull(postsnapshot.getValue()).toString());
    }



        //
        //
        //
        //



    //info.add(Objects.requireNonNull(postsnapshot.child("Description").getValue()).toString());
        //video_url.add(Objects.requireNonNull(postsnapshot.child("Content").getValue()).toString());
        //ID_video.add(postsnapshot.getKey());
        //likes.add(Objects.requireNonNull(postsnapshot.child("Likes").getValue()).toString());
        //place.add(Objects.requireNonNull(postsnapshot.child("Place").getValue()).toString());
        //ID_User.add(Objects.requireNonNull(postsnapshot.child("Uploader").getValue()).toString());
        //grade.add(Objects.requireNonNull(postsnapshot.child("grade").getValue()).toString());
        //maker.add(Objects.requireNonNull(postsnapshot.child("maker").getValue()).toString());
        //IMG_URL.add(Objects.requireNonNull(postsnapshot.child("IMG").getValue()).toString());
        //name.add(Objects.requireNonNull(postsnapshot.child("Name").getValue()).toString());


    private void clearArrayLists() {
        info.clear();
        video_url.clear();
        place.clear();
        likes.clear();
        IMG_URL.clear();
        grade.clear();
        ID_User.clear();
        name.clear();



    }


}
