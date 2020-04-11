package app.playstore.uClimb.Fragments.Post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

public class custom_post_page extends Fragment {
    private static final String TAG = "custom_post_page";
    private VideoView videoView;
    private ImageView img_post;


    private ImageView img_like;
    private ImageView img_share;

    private ImageView img_profile_pic;
    private TextView txt_name;
    private TextView info_txt;


    private String type;
    private String source;
    private String uid;
    private int video_state = 0;
    private String post_id = "";
    private Boolean liked = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_post_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImgandName();


        getcredentialsfragment();

        initViews(view);
        onClick();


        checkSource(type,view);

        //img_post.setOnLongClickListener(new View.OnLongClickListener() {
        //    @Override
        //    public boolean onLongClick(View v) {
        //        Toast.makeText(getContext(), "Liked", Toast.LENGTH_SHORT).show();
        //        return true;
//
        //    }
        //});
    }

    private void onClick() {
        img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liked = false;
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("");
                login_presenter login_presenter = new login_presenter();
                String id = login_presenter.getUID(getContext());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!liked) {
                            if (dataSnapshot.child("User").child(id).child("liked").child(post_id).exists()){
                                databaseReference.child("Posts").child(post_id).child("likes").child(id).removeValue();
                                databaseReference.child("User").child(id).child("liked").child(post_id).removeValue();
                                img_like.setImageDrawable(getResources().getDrawable(R.mipmap.like_btn_empty)); //TODO change Image
                                liked = true;
                                return;


                            }



                        }
                        if (liked){
                            if (!dataSnapshot.child("User").child(id).child("liked").child(post_id).exists()){
                            databaseReference.child("Posts").child(post_id).child("likes").child(id).setValue(id);
                            databaseReference.child("User").child(id).child("liked").child(post_id).setValue(post_id);
                            img_like.setImageDrawable(getResources().getDrawable(R.mipmap.like_btn_empty)); //TODO change Image
                            return;

                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });
    }

    private void setImgandName() {
        fetchData();

    }

    private void fetchData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"profiles_source" + uid);
                String profile_source = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("IMG").getValue()).toString();
                Picasso.get().load(profile_source).fit().into(img_profile_pic);
                String name = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Name").getValue().toString());
                String Info = Objects.requireNonNull(dataSnapshot.child("Posts").child(post_id).child("Info").getValue().toString());

                txt_name.setText(name);
                info_txt.setText(Info);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getcredentialsfragment() {

        Bundle arguments = getArguments();
        source= Objects.requireNonNull(arguments).getString("Source");
        type= Objects.requireNonNull(arguments).getString("Type");
        uid = Objects.requireNonNull(arguments).getString("UserID");
        Log.d(TAG,"uid_custom" + uid);
        post_id = Objects.requireNonNull(arguments).getString("PostID");

    }

    private void checkSource(String type,View view) {
        if (type.equals("video")){
            video_setup();
            video_player();


        }
        if (type.equals("img")){
            img_setup();




        }
    }

    private void img_setup() {
        img_post.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.GONE);
        Picasso.get().load(source).fit().centerCrop().into(img_post);

    }

    private void video_player() {
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video_state == 0){
                    videoView.start();
                    video_state = 1;
                }
                else{
                    videoView.pause();
                    video_state = 0;

                }
            }
        });

    }

    private void video_setup() {
        videoView.setVisibility(View.VISIBLE);
        img_post.setVisibility(View.GONE);
        videoView.setVideoPath(source);
        videoView.seekTo(2);
    }

    private void initViews(@NonNull View view) {
       img_post = view.findViewById(R.id.img_view_custom);
       videoView = view.findViewById(R.id.video_custom);
       img_like = view.findViewById(R.id.img_like_custom);
       img_share = view.findViewById(R.id.img_share_custom);
       txt_name = view.findViewById(R.id.txt_username_custom);
       img_profile_pic = view.findViewById(R.id.profile_img_custom);
       info_txt = view.findViewById(R.id.txt_info_custom);

    }


  // public abstract class DoubleClickListener implements View.OnClickListener {

  //     private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

  //     long lastClickTime = 0;

  //     @Override
  //     public void onClick(View v) {
  //         long clickTime = System.currentTimeMillis();
  //         if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
  //             onDoubleClick(v);
  //             lastClickTime = 0;
  //         } else {
  //             onSingleClick(v);
  //         }
  //         lastClickTime = clickTime;
  //     }

  //     public abstract void onSingleClick(View v);
  //     public abstract void onDoubleClick(View v);
  // }
}
