package app.playstore.uClimb.MVP.MVP_Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import app.playstore.uClimb.Adapters.Home.Adapter_Home;
import app.playstore.uClimb.Adapters.Home.Adapter_Home_Comments;
import app.playstore.uClimb.MVP.MVP_Invite.InviteContent;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Presenter_Home_Posts {
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
    //Adapter_Home_Comments adapter_home_comment = new Adapter_Home_Comments(array_name_comment,array_comment_id,array_comment, array_time_comment);
    Adapter_Home adapter_home = new Adapter_Home(array_name,array_source_img,array_source,array_info, array_post_id,array_type,array_user_id);
    private String mAuth;




    private Model_Home_Posts model_home;
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

    public Presenter_Home_Posts(Context mContext) {
        this.model_home = new Model_Home_Posts();
        Presenter_Login login_presenter = new Presenter_Login();
        this.mAuth = login_presenter.getUID(mContext);
        Log.d(TAG,"mAuth3" + this.mAuth);
        //TODO mAUth null

    }
    public void setData(View view, Context mContext){
        clearArrayLists();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        Presenter_Login presenter_login = new Presenter_Login();
        String uid = presenter_login.getUID(mContext);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(uid).child("Following").getChildren()){
                    String posts_new = null;
                    long posts_new_time = 1;


                    for (DataSnapshot postSnapshot2 : dataSnapshot.child("User").child(postSnapshot.getValue().toString()).child("Posts").getChildren()){
                        if (dataSnapshot.child("Posts").child(postSnapshot2.getValue().toString()).child("Time").exists()) {
                            String Time = dataSnapshot.child("Posts").child(postSnapshot2.getValue().toString()).child("Time").getValue().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            Date date = null;
                            try {
                                date = sdf.parse(Time);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long millis = date.getTime();
                            if (posts_new_time < millis) {
                                posts_new = postSnapshot2.getKey();
                                posts_new_time = millis;
                            }


                        }
                    }
                    posts.add(posts_new);




                }
                Collections.shuffle(posts);
                Log.d(TAG,"Posts32" + posts);

                for (int i = 0; i < posts.size();i++){
                    if (posts.get(i) != null) {
                        if (dataSnapshot.child("Posts").child(posts.get(i)).exists()) {

                            Log.d(TAG, "Posts23" + posts + "data: " + dataSnapshot);
                            DataSnapshot dataSnapshot1 = dataSnapshot.child("Posts").child(posts.get(i));
                            addDatato_arrays(dataSnapshot, dataSnapshot1);
                        }
                    }

                }
                initRec(view,mContext);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRec(View view, Context context) {
        Log.d(TAG,"initRec");


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

            if (view.getRootView() != null){
                Snackbar snackbar = Snackbar.make(view.getRootView(), R.string.folgen_leute_global, Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(context.getResources().getColor(R.color.colorPrimaryDark));
                snackbar.show();

            }


        }

    }





    private void addDatato_arrays(@NonNull DataSnapshot dataSnapshot, DataSnapshot postSnapshot) {


        if (!dataSnapshot.child("User").child(postSnapshot.child("User_ID").getValue().toString()).child("Name").exists() && !postSnapshot.child("Time").exists()
        && !dataSnapshot.child("User").child(postSnapshot.child("User_ID").getValue().toString()).child("IMG").exists() && !postSnapshot.child("Source").exists() && !postSnapshot.child("Info").exists()
        && !postSnapshot.child("Place").exists() && !postSnapshot.child("User_ID").exists() &&!postSnapshot.exists() &&  !postSnapshot.child("type").exists() &&  !postSnapshot.child("likes").exists()){
            return;

        }
        else{

            String name = dataSnapshot.child("User").child(postSnapshot.child("User_ID").getValue().toString()).child("Name").getValue().toString();
            String time =postSnapshot.child("Time").getValue().toString();

            String profile_img = dataSnapshot.child("User").child(postSnapshot.child("User_ID").getValue().toString()).child("IMG").getValue().toString();
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
            Log.d(TAG,"likes"+likes);

        }



    }

    private Bundle createBundle() {
        Model_Home_Posts home_posts_model = new Model_Home_Posts(array_time,array_name,array_source_img,array_source,array_info,array_place,array_user_id,array_post_id,array_type,array_likes);
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
    //public void initcomments(RecyclerView rec, Context mContext){
//
//
    //    getcommentdata(mContext,rec,array_name, array_time, array_comment_id, array_comment, array_User_ID);
    //    rec.setAdapter(adapter_home_comment);
    //    rec.setLayoutManager(new LinearLayoutManager(mContext));
//
    //}





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
    public boolean liked(String id){
        // Write a message to the database
        DatabaseReference myRef = initfireliked();
        // Read from the database
        setstatusliked(id, myRef);


        return status;


    }

    private void setstatusliked(String id, DatabaseReference myRef) {
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
            myRef_posts.child("Posts").child(postID).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public static void isLiked(String s, String d,ImageView like_btn) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Posts234: " + dataSnapshot.child("Posts").child(s).child("likes").getValue() + " d:" + d);
                Boolean return_status = false;
                if (dataSnapshot.child("Posts").child(s).child("likes").child(d).exists()){
                    return_status = true;

                }
                Adapter_Home adapter_home = new Adapter_Home();

                adapter_home.isLikedAction(return_status,like_btn);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void like(String id, String s, ImageView like_btn,Context mContext) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        //databaseReference.child("Posts").child(s).child("likes").child(id).setValue(id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Posts").child(s).child("likes").child(id).exists()){
                    databaseReference.child("Posts").child(s).child("likes").child(id).removeValue();
                    like_btn.setImageResource(R.mipmap.like_passive);
                    isLiked(s,id,like_btn);


                }
                else{
                    databaseReference.child("Posts").child(s).child("likes").child(id).setValue(id);
                    Toast.makeText(mContext, "Liked", Toast.LENGTH_SHORT).show();

                    isLiked(s,id,like_btn);


                    like_btn.setImageResource(R.mipmap.like_active);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void share_link(Context mContext,String id) {
        String query = "";
        try {
            query = URLEncoder.encode(String.format("&%1s=%2s", "Post", id), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://boulderspot.page.link/Post_invite" + query;




        Task<ShortDynamicLink> uri  = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(url))
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


    // [START ddl_generate_content_link]






}
