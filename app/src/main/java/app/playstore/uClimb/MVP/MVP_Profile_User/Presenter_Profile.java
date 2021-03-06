package app.playstore.uClimb.MVP.MVP_Profile_User;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Profile.Adapter_Profile;
import app.playstore.uClimb.MVP.MVP_Video_Upload.Presenter_Video;
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
    String points;

    private String Subscription;
    private String grade;
    private String country;
    private ArrayList follower = new ArrayList();
    private ArrayList  following = new ArrayList();
    private ArrayList  friends = new ArrayList();

    private String Height;
    private ArrayList friends_id = new ArrayList();
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
    private ArrayList<String> arrayList_likes = new ArrayList<>();
    private ArrayList<String> arrayList_comments= new ArrayList<>();


    private ProgressBar progressBar;


    public Presenter_Profile(Context mContext) {
        this.profile_model = new Model_Profile();
        this.mContext = mContext;
        this.login_presenter = new Presenter_Login();
        this.uid = login_presenter.getUID(mContext);
        this.stat_uid = login_presenter.getStatisticsID(mContext);


    }
    public void setData(RecyclerView recyclerView,Context mContext){

                getDataPosts(mContext,recyclerView);



    }
    public void setRecyclerview(Context mContext, View view){

        RecyclerView recyclerView = view.findViewById(R.id.profile_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        setData(recyclerView,mContext);

        Log.d(TAG,"profile_img2" + profile_img);


    }
    public void getDataPosts(Context mContext ,RecyclerView recyclerView2){
        Log.d(TAG,"postid2");
        ArrayList<String> post_id = new ArrayList<>();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Posts").getChildren()){
                    post_id.add(postSnapshot.getKey());
                    Log.d(TAG,"postid"+post_id);



                }
                for (int i = 0;i< post_id.size();i++){
                    String id = post_id.get(i).toString();

                    if (dataSnapshot.child("Posts").child(id).child("Source").exists() && dataSnapshot.child("Posts").child(id).child("type").exists()
                    && dataSnapshot.child("Posts").child(id).child("Info").exists() && dataSnapshot.child("Posts").child(id).child("Place_name").exists()
                    && dataSnapshot.child("Posts").child(id).child("Time").exists() && dataSnapshot.child("Posts").child(id).child("User_ID").exists()
                    && dataSnapshot.child("Posts").child(id).child("likes").exists() &&dataSnapshot.child("Posts").child(id).child("comments").exists()) {
                        Log.d(TAG, "id21" + id);


                        String Source = dataSnapshot.child("Posts").child(id).child("Source").getValue().toString();
                        String type = dataSnapshot.child("Posts").child(id).child("type").getValue().toString();
                        String info = dataSnapshot.child("Posts").child(id).child("Info").getValue().toString();
                        String place = dataSnapshot.child("Posts").child(id).child("Place_name").getValue().toString();
                        String time = dataSnapshot.child("Posts").child(id).child("Time").getValue().toString();
                        String u_id = dataSnapshot.child("Posts").child(id).child("User_ID").getValue().toString();
                        String likes = String.valueOf(dataSnapshot.child("Posts").child(id).child("likes").getChildrenCount());
                        String commments = String.valueOf(dataSnapshot.child("Posts").child(id).child("comments").getChildrenCount());


                        arrayList_info.add(info);
                        arrayList_post_id.add(id);
                        arrayList_uid.add(u_id);
                        arrayList_type.add(type);
                        arrayList_Source.add(Source);
                        arrayList_time.add(time);
                        arrayList_place.add(place);
                        arrayList_comments.add(commments);
                        arrayList_likes.add(likes);
                    }


                }
                fireData(dataSnapshot,recyclerView2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    private void fireData(DataSnapshot dataSnapshot,RecyclerView recyclerView) {
        if (dataSnapshot.child("User").child(uid).child("Age").exists() &&dataSnapshot.child("User").child(uid).child("Name").exists()
        &&dataSnapshot.child("User").child(uid).child("IMG").exists() &&  dataSnapshot.child("User").child(uid).child("Info").exists()
        && dataSnapshot.child("User").child(uid).child("Subscription").exists() &&dataSnapshot.child("User").child(uid).child("Grade").exists()
        && dataSnapshot.child("User").child(uid).child("Country").exists() && dataSnapshot.child("User").child(uid).child("Height").exists()
        && dataSnapshot.child("User").child(uid).child("Email").exists()
        && dataSnapshot.child("User").child(uid).child("account_type").exists() && dataSnapshot.child("User").child(uid).child("Time_created").exists()
        && dataSnapshot.child("User").child(uid).child("Points").exists() && dataSnapshot.child("User").child(uid).child("Position").child("Position_lat").exists()
        &&dataSnapshot.child("User").child(uid).child("Position").child("Position_lon").exists() &&dataSnapshot.child("User").child(uid).child("Position").child("Position_last_Time_updated").exists()
        && dataSnapshot.child("User").child(uid).child("Position").child("position_status").exists()) {
            Age = dataSnapshot.child("User").child(uid).child("Age").getValue().toString();
            Name = dataSnapshot.child("User").child(uid).child("Name").getValue().toString();
            profile_img = dataSnapshot.child("User").child(uid).child("IMG").getValue().toString();
            Info = dataSnapshot.child("User").child(uid).child("Info").getValue().toString();
            Subscription = dataSnapshot.child("User").child(uid).child("Subscription").getValue().toString();
            grade = dataSnapshot.child("User").child(uid).child("Grade").getValue().toString();
            country = dataSnapshot.child("User").child(uid).child("Country").getValue().toString();
            Height = dataSnapshot.child("User").child(uid).child("Height").getValue().toString();
            Log.d(TAG, "email" + dataSnapshot.child("User").child(uid).child("Email").getValue());
            Email = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Email").getValue()).toString();
            Log.d(TAG, "uidfollowr" + uid);
            Log.d(TAG, "follower2" + dataSnapshot.child("Following").child("iy9WheRY8CaciMISBllnQMY6FcE2").getValue());
            for (DataSnapshot postSnapshot : dataSnapshot.child("User").child(uid).child("Follower").getChildren()) {
                Boolean bool = follower.add(postSnapshot.getValue());


            }
            for (DataSnapshot postSnapshot : dataSnapshot.child("User").child(uid).child("Following").getChildren()) {
                Log.d(TAG, "following" + dataSnapshot.child("Following").getChildren());


                Boolean bool = following.add(postSnapshot.getValue());


            }
            for (DataSnapshot postSnapshot : dataSnapshot.child("User").child(uid).child("Friends").getChildren()) {
                Boolean bool = friends_id.add(postSnapshot.getValue());
                Log.d(TAG, "Friends43_" + friends_id);


            }
            Log.d(TAG, "Friends3_" + friends_id);

            account_type = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("account_type").getValue()).toString();
            time_created = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Time_created").getValue()).toString();
            points = dataSnapshot.child("User").child(uid).child("Points").getValue().toString();
            position_lat = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_lat").getValue()).toString();
            position_long = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_lon").getValue()).toString();
            position_last_updated = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("Position_last_Time_updated").getValue()).toString();
            position_status = Boolean.valueOf(Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Position").child("position_status").getValue()).toString());
            profile_adapter = new Adapter_Profile(uid, Age, Name, profile_img, Info, country, follower, following, Height, arrayList_Source, arrayList_type, arrayList_info, arrayList_place, arrayList_uid, arrayList_post_id, arrayList_time, arrayList_likes, arrayList_comments, Email, friends_id, points);
            recyclerView.setAdapter(profile_adapter);


        }
    }

    public Presenter_Profile() {
    }

    public void share_link(Context mContext,String id,String source,String type, String uid) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("boulderspot.page.link")
                .appendPath("Post")
                .appendPath("Source")
                .appendPath("UserID")
                .appendPath("Type")
                .appendQueryParameter("PostID", id)
                .appendQueryParameter("Source", source)
                .appendQueryParameter("Type", type)
                .appendQueryParameter("UserID", uid);;

        String myUrl = builder.build().toString();

        //String postid = pendingDynamicLinkData.getLink().getQueryParameter("Post");
        //String Source = pendingDynamicLinkData.getLink().getQueryParameter("Source");
        //String userID = pendingDynamicLinkData.getLink().getQueryParameter("UserID");
        //String type = pendingDynamicLinkData.getLink().getQueryParameter("Type");






        String query = "";
        try {
            query = URLEncoder.encode(String.format("&%1s=%2s", "Post", id), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://boulderspot.page.link/Post_invite" + query;






        Task<ShortDynamicLink> uri  = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(myUrl))
                .setDomainUriPrefix("https://boulderspot.page.link")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder()
                                .setMinimumVersion(11)
                                .build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle("Cooler Post auf uClimb")
                                .setDescription("uClimb ist ein Soziales Netzwerk für Kletter. Jemand versucht dir einen coolen Post zu schicken.")


                                //.setImageUrl(Uri.parse("https://onestickers.com/img/main-logo.png"))
                                .build())
                .buildShortDynamicLink()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"failure");


                    }
                })
                .addOnCompleteListener(task -> Log.d(TAG,"loading")).addOnFailureListener(e -> Log.d(TAG," cause you are a cool person: " + e)).addOnSuccessListener(new OnSuccessListener<ShortDynamicLink>() {
                    @Override
                    public void onSuccess(ShortDynamicLink shortDynamicLink) {
                        Log.d(TAG,"successs");
                        if (shortDynamicLink != null){
                            Log.d(TAG,"linkk" + shortDynamicLink.getShortLink());
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Jemand will dir einen coolen Kletter Post zeigen");
                            sendIntent.setType("text/plain");
                            sendIntent.putExtra("Post_ID",id);
                            sendIntent.putExtra(Intent.EXTRA_TEXT,"Tritt der Kletter Community bei und zeige uns deine coolen Momente beim Klettern. Downloade jetzt." + shortDynamicLink.getShortLink());
                            mContext.startActivity(sendIntent);


                        }

                    }
                });







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

                                StorageReference storageReference = mStorageRef.child("Sources_Users_Uploads").child(uid).child("PROFILE_PIC_200x200");
                                final android.os.Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                                DatabaseReference databaseReference = firebaseDatabase.getReference("");
                                                databaseReference.child("User").child(uid).child("IMG").setValue(uri.toString());
                                                Toast.makeText(mContext, "Changed Image", Toast.LENGTH_SHORT).show();



                                            }


                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(mContext, "Fehler: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();                                            }
                                        });
                                        //Do something after 100ms
                                    }
                                }, 2000);



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

