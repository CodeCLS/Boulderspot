package app.playstore.boulderspot.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.DB.FeedReaderDBHelper;
import app.playstore.boulderspot.Models.Home_Model;
import app.playstore.boulderspot.R;

public class Home_Fragment extends Fragment {
    //Log value
    private static final String TAG = "Home Fragment";

    //All ArrayLists
    private List<String> itemIds = new ArrayList<String>();
    private int i = 0;

    private  ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> place   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    private  ArrayList<String> likes = new ArrayList<>();
    private FeedReaderDBHelper feedReaderDBHelper;

    //Adapter home is init
    private  Adapter_home adapter_home = new Adapter_home(IMG_URL,grade,place,name,info,maker,video_url,ID_User,ID_video,likes,getContext());
    //Firebase values are init
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");
    private DatabaseReference myRef_posts = database.getReference("User");
    //Recycler view is established
    private RecyclerView rec;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //readDB();
        Log.d(TAG,"Data added" + feedReaderDBHelper.addData("Caleb Seeling")
        );


        getSQL_Following_data();


        floatingAction(view);
        setRec(view);
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

    private void getSQL_Following_data() {
        Cursor cursor = feedReaderDBHelper.getData();
        while(cursor.moveToNext()) {
            itemIds.add(cursor.getString(1));

        }
        cursor.close();
        search_user_posts();
        Log.d(TAG,"Data catched" + itemIds);

    }

    private void search_user_posts() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){

                    Log.d(TAG,"number_SQL_DATA" + itemIds.get(i));
                    Log.d(TAG,"post_child" + postsnapshot.child("Name").getValue());
                    if (Objects.requireNonNull(postsnapshot.child("Name").getValue()).equals(itemIds.get(i))){
                        getspecificData(dataSnapshot , postsnapshot.getKey());
                        Log.d(TAG,"exists");
                    }
                    else{

                        Log.d(TAG,"doesnt exist");
                    }
                }
                i++;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        assert fragmentManager != null;
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, mFragment).commit();
    }

    private void setRec(@NonNull View view) {
        rec = view.findViewById(R.id.rec_home);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       feedReaderDBHelper= new FeedReaderDBHelper(context);


    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("connected");
                } else {
                    Toast.makeText(getContext(), "No Server connection. Try again later", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();


    }

    private void getData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                onDataChangeCode(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }

    private void onDataChangeCode(DataSnapshot dataSnapshot) {
        clearArrayLists();
        for (DataSnapshot ignored : dataSnapshot.getChildren()){
            getDatafor_getData();


        }
    }

    private void getDatafor_getData() {
       // getspecificData(postsnapshot);


        setValuesofModel();


        setRec();
    }

    private void setRec() {
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setAdapter(adapter_home);
    }

    private void setValuesofModel() {
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
    }

    private void getspecificData(final DataSnapshot datasnapshot1 , final String key) {
        myRef_posts.child(key).child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                    logs_getting_data(dataSnapshot, postsnapshot, datasnapshot1, key);

                    // if (Objects.requireNonNull(postsnapshot.child(listData.get(i)).child("type").getValue()).equals("Video"))
                    adding_data_to_arraylists(postsnapshot, datasnapshot1, key);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        }

    private void logs_getting_data(@NonNull DataSnapshot dataSnapshot, DataSnapshot postsnapshot, DataSnapshot datasnapshot1, String key) {
        Log.d(TAG,"Datasnapshot" + dataSnapshot);
        Log.d(TAG,"Datasnapshot1"+datasnapshot1);
        Log.d(TAG,"STepOneData"+datasnapshot1.child(key).child("Posts"));
        Log.d(TAG,"post_data" + Objects.requireNonNull(postsnapshot.getValue()).toString());
    }

    private void adding_data_to_arraylists(DataSnapshot postsnapshot, DataSnapshot datasnapshot1, String key) {
        info.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("Description").getValue()).toString());
        video_url.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("Content").getValue()).toString());
        ID_video.add(postsnapshot.getKey());
        likes.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("Likes").getValue()).toString());
        place.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("Place").getValue()).toString());
        ID_User.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("Uploader").getValue()).toString());
        grade.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("grade").getValue()).toString());
        maker.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("maker").getValue()).toString());
        IMG_URL.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("IMG").getValue()).toString());
        name.add(Objects.requireNonNull(datasnapshot1.child(key).child("Posts").child(postsnapshot.getKey()).child("Name").getValue().toString()));
    }


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
        ID_video.clear();
        place.clear();
        likes.clear();
        IMG_URL.clear();
        grade.clear();
        ID_User.clear();
        name.clear();
        maker.clear();
    }








    }
