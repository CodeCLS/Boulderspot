package app.playstore.boulderspot.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;
import androidx.transition.TransitionManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Objects;

import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.R;
import app.playstore.boulderspot.Views.Navigation_bar;

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
        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fmelody-jacob-hb8v-SZm7VY-unsplash.jpg?alt=media&token=d9daa0e9-a575-4c6a-8143-e19acc2b1649");
        grade.add("7+");
        place.add("Berta");
        name.add("Caleb");
        info.add("Calebs info");
        video_url.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FNameYoWantToAdd?alt=media&token=00ab2c0b-6397-4e9a-8c04-c22b3da1e496");
        ID_User.add("asfdafs");
        likes.add("2");
        post.add("adsdads");

        setRec(view);


        floatingAction(view);


        //      NotificationCompat.Builder mBuilder =
        //            new NotificationCompat.Builder(getContext())
        //                    .setSmallIcon(R.mipmap.boulderspot_logo)
        //                    .setContentTitle("My notification")
        //                    .setContentText("Hello World!");


        //    // Gets an instance of the NotificationManager service//

        //    NotificationManager mNotificationManager =

        //            (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        //    // When you issue multiple notifications about the same type of event,
        //    // it’s best practice for your app to try to update an existing notification
        //    // with this new information, rather than immediately creating a new notification.
        //    // If you want to update this notification at a later date, you need to assign it an ID.
        //    // You can then use this ID whenever you issue a subsequent notification.
        //    // If the previous notification is still visible, the system will update this existing notification,
        //    // rather than create a new one. In this example, the notification’s ID is 001//


        //            mNotificationManager.notify(001, mBuilder.build());

    }





    private String getnamedata(final String User_ID) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name_fire =dataSnapshot.child(User_ID).child("Anzeigename").toString();
                URL = dataSnapshot.child(User_ID).child("User_ID").getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return name_fire;
    }


    private DatabaseReference setnewUserref(String user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference(user);
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



        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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
