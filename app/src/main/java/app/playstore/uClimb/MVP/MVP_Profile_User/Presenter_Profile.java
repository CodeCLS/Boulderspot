package app.playstore.uClimb.MVP.MVP_Profile_User;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Profile.Adapter_Profile;
import app.playstore.uClimb.Adapters.Profile.Adapter_Profile_User_Uploads;
import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Presenter_Profile {
    private static final String TAG = "Profile_presenter";
    private String uid;
    private String stat_uid;
    private StorageReference mStorageRef;
    private String Age;
    private String Name;
    private String profile_img;
    private String Info;
    private String Subscription;
    private String grade;
    private String country;
    private ArrayList follower = new ArrayList();
    private ArrayList  following = new ArrayList();
    private String Height;
    private ArrayList competition = new ArrayList();
    private String account_type;
    private String time_created;
    private String position_lat;
    private String position_long;
    private String position_last_updated;
    private Boolean position_status;
    private String Email;
    private Model_Profile profile_model;
    private Presenter_Login login_presenter;
    private Context mContext;
    private Adapter_Profile profile_adapter;
    private ArrayList<String> arrayList_Source = new ArrayList<>();
    private ArrayList<String> arrayList_type = new ArrayList<>();
    private ArrayList<String> arrayList_info = new ArrayList<>();
    private ArrayList<String> arrayList_place = new ArrayList<>();
    private ArrayList<String> arrayList_uid = new ArrayList<>();
    private ArrayList<String> arrayList_post_id = new ArrayList<>();
    private ArrayList<String> arrayList_time = new ArrayList<>();
    private ProgressBar progressBar;


    public Presenter_Profile(Context mContext) {
        this.profile_model = new Model_Profile();
        this.mContext = mContext;
        this.login_presenter = new Presenter_Login();
        this.uid = login_presenter.getUID(mContext);
        this.stat_uid = login_presenter.getStatisticsID(mContext);


    }
    public void setData(RecyclerView recyclerView){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireData(dataSnapshot,recyclerView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void setRecyclerview(Context mContext, View view){
        RecyclerView recyclerView = view.findViewById(R.id.profile_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        setData(recyclerView);
        Log.d(TAG,"profile_img2" + profile_img);


    }
    public void setRecyclerView(RecyclerView recyclerView,Context mContext){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> post_id = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").child("Posts").getChildren()){
                    post_id.add(postSnapshot.getKey());



                }
                for (int i = 0;i< post_id.size();i++){
                    String id = post_id.get(i).toString();
                    String Source = dataSnapshot.toString();
                    String type = dataSnapshot.child("Posts").child("type").getValue().toString();
                    String info = dataSnapshot.child("Posts").child("Info").getValue().toString();
                    String place = dataSnapshot.child("Posts").child("Place_name").getValue().toString();
                    String time =dataSnapshot.child("Posts").child("Time").getValue().toString();
                    String u_id =dataSnapshot.child("Posts").child("User_ID").getValue().toString();
                    arrayList_info.add(info);
                    arrayList_post_id.add(id);
                    arrayList_uid.add(u_id);
                    arrayList_type.add(type);
                    arrayList_Source.add(Source);
                    arrayList_time.add(time);
                    arrayList_place.add(place);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Adapter_Profile_User_Uploads inner_profile_adapter = new Adapter_Profile_User_Uploads(arrayList_Source,arrayList_type,arrayList_post_id,arrayList_info,arrayList_uid,arrayList_time,arrayList_place);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(inner_profile_adapter);


    }

    private void getDataPosts(RecyclerView recyclerView) {




    }

    private void fireData(DataSnapshot dataSnapshot,RecyclerView recyclerView) {
        Age = dataSnapshot.child("User").child(uid).child("Age").getValue().toString();
        Name = dataSnapshot.child("User").child(uid).child("Name").getValue().toString();
        profile_img = dataSnapshot.child("User").child(uid).child("IMG").getValue().toString();
        Info = dataSnapshot.child("User").child(uid).child("Info").getValue().toString();
        Subscription = dataSnapshot.child("User").child(uid).child("Subscription").getValue().toString();
        grade = dataSnapshot.child("User").child(uid).child("Grade").getValue().toString();
        country = dataSnapshot.child("User").child(uid).child("Country").getValue().toString();
        Height = dataSnapshot.child("User").child(uid).child("Height").getValue().toString();
        Log.d(TAG,"email" + dataSnapshot.child("User").child(uid).child("Email").getValue());
        Email = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Email").getValue()).toString();
        Log.d(TAG,"uidfollowr"+ uid);
        Log.d(TAG,"follower2" + dataSnapshot.child("Following").child("iy9WheRY8CaciMISBllnQMY6FcE2").getValue());
        for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Follower").getChildren()){
            Boolean bool = follower.add(postSnapshot.getValue());


        }
        for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Following").getChildren()){
            Log.d(TAG,"following" + dataSnapshot.child("Following").getChildren());


            Boolean bool = following.add(postSnapshot.getValue());


        }
        for (DataSnapshot postSnapshot: dataSnapshot.child("close_friends_competitiors").getChildren()){
            Boolean bool = competition.add(postSnapshot.getValue());


        }

        account_type = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("account_type").getValue()).toString();
        time_created = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Time_created").getValue()).toString();
        position_lat = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_lat").getValue()).toString();
        position_long = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_lon").getValue()).toString();
        position_last_updated = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_last_Time_updated").getValue()).toString();
        position_status = Boolean.valueOf(Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("position_status").getValue()).toString());
        profile_adapter = new Adapter_Profile(uid, Age,Name,profile_img,Info, country,follower,following,Height,competition
                , Email);
        recyclerView.setAdapter(profile_adapter);




    }

    public Presenter_Profile() {
    }

    public void setProfilePic(Context mContext, ProgressBar progressBar) {
        Log.d(TAG,"mContext" + mContext);
        ((MainActivity) mContext).img(progressBar);
        Log.d(TAG,"progress2"+progressBar);
        this.progressBar = progressBar;
        Log.d(TAG,"progress1"+progressBar);




    }


    public void here_is_image(Uri path,Context mContext,ProgressBar progressBar) {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Presenter_Login login_presenter = new Presenter_Login();
        String uid = login_presenter.getUID(mContext);
        Log.d(TAG,"321323");
        StorageReference riversRef = mStorageRef.child("Sources_Users_Uploads").child(uid).child("PROFILE_PIC");

        riversRef.putFile(path)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressBar.setVisibility(View.GONE);
                                Log.d(TAG,"32132423");

                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference = firebaseDatabase.getReference("");
                                databaseReference.child("User").child(uid).child("IMG").setValue(uri.toString());
                                Toast.makeText(mContext, "Changed Image", Toast.LENGTH_SHORT).show();

                            }
                        });
                        // Get a URL to the uploaded content
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.d(TAG,"3212323");

                        Toast.makeText(mContext, "Task Failed: " + exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {



                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Log.d(TAG,"progress4"+progressBar);


                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress((int) progress);
            }
        });


    }

}

