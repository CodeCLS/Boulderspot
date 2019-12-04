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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.Models.Home_Model;
import app.playstore.boulderspot.R;

public class Home_Fragment extends Fragment {

    private static final String TAG = "Home Fragment";
    private ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private FloatingActionButton plus_img;
    private  ArrayList<String> place   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private RecyclerView rec;

    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();

    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    private  ArrayList<String> likes = new ArrayList<>();
    final Adapter_home adapter_home = new Adapter_home(IMG_URL,grade,place,name,info,maker,video_url,ID_User,ID_video,likes,getContext());

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Videos");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plus_img = view.findViewById(R.id.plus_img);
        plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                video_upload_fragment mFragment = new video_upload_fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, mFragment).commit();
            }
        });
         rec = view.findViewById(R.id.rec_home);
        final Adapter_home adapter_home = new Adapter_home(IMG_URL,grade,place,name,info,maker,video_url,ID_User,ID_video,likes,getContext());
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
getData();










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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onStart() {
        super.onStart();


    }
    @Override
    public void onResume(){
        super.onResume();


    }

    public void getData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                info.clear();
                video_url.clear();
                ID_video.clear();
                place.clear();
                likes.clear();;
                IMG_URL.clear();
                grade.clear();
                ID_User.clear();
                name.clear();
                maker.clear();
                for (DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                    info.add(postsnapshot.child("Description").getValue().toString());
                    video_url.add(postsnapshot.child("Content").getValue().toString());
                    ID_video.add(postsnapshot.getKey().toString());
                    likes.add(postsnapshot.child("Likes").getValue().toString());
                    place.add(postsnapshot.child("Place").getValue().toString());
                    ID_User.add(postsnapshot.child("Uploader").getValue().toString());
                    grade.add(postsnapshot.child("grade").getValue().toString());
                    maker.add(postsnapshot.child("maker").getValue().toString());
                    IMG_URL.add(postsnapshot.child("IMG").getValue().toString());
                    name.add(postsnapshot.child("Name").getValue().toString());


                    Home_Model.setInfo(info);
                    Home_Model.setVideo_url(video_url);
                    Home_Model.setID_video(ID_video);
                    Home_Model.setLikes(likes);
                    Home_Model.setPlace(place);
                    Home_Model.setID_User(ID_User);
                    Home_Model.setGrade(grade);
                    Home_Model.setMaker(maker);
                    Home_Model.setImgUrl(IMG_URL);
                    Home_Model.setName(name);


                    rec.setLayoutManager(new LinearLayoutManager(getContext()));
                    rec.setAdapter(adapter_home);










                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter_home.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter_home.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter_home.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter_home.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                adapter_home.notifyDataSetChanged();
            }
        });
    }
}
