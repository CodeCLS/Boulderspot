package app.playstore.uClimb.Fragments.Post;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
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
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

import app.playstore.uClimb.Fragments.Fragment_Comments;
import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Custom_Post.Presenter_Custom_Post;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class custom_post_page extends Fragment {
    private static final String TAG = "main_custom_post_page";
    private SimpleExoPlayerView videoView;
    private ImageView img_post;
    private SimpleExoPlayer player;



    private ImageView img_like;
    private ImageView img_share;

    private ImageView img_profile_pic;
    private TextView txt_name;
    private TextView info_txt;
    private TextView location_txt;


    private String type;
    private String source;
    private String uid;
    private int video_state = 0;
    private String post_id = "";
    private Boolean liked = false;

    private TextView comment_txt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.main_custom_post_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImgandName();




        getcredentialsfragment();

        initViews(view);
        img_share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                share_link(getContext(),post_id,source,type,uid);

            }
        });
        ProfileOnClick();

        txtComment();
        //onClick();


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

    private void ProfileOnClick() {
        img_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_transaction();
            }
        });
    }
    public void share_link(Context mContext, String id, String source, String type, String uid) {

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

    private void user_transaction() {
        custom_profile custom_profile = new custom_profile();
        Bundle bundle = new Bundle();
        bundle.putString("uid",uid);
        custom_profile.setArguments(bundle);
        FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,custom_profile).addToBackStack("Custom_Post").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    private void txtComment() {
        comment_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Comments fragment_comments = new Fragment_Comments();
                Bundle bundle = new Bundle();
                bundle.putString("PostID",post_id);
                fragment_comments.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,fragment_comments).addToBackStack("Comments_Post").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();

            }
        });
    }

    //private void onClick() {
    //    img_like.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
    //            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //            DatabaseReference databaseReference = firebaseDatabase.getReference("");
    //            Presenter_Login login_presenter = new Presenter_Login();
    //            String id = login_presenter.getUID(getContext());
    //            databaseReference.addValueEventListener(new ValueEventListener() {
    //                @Override
    //                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    //                    if (!liked) {
    //                        if (dataSnapshot.child("User").child(id).child("liked").child(post_id).exists()){
    //                            databaseReference.child("Posts").child(post_id).child("likes").child(id).removeValue();
    //                            databaseReference.child("User").child(id).child("liked").child(post_id).removeValue();
    //                            img_like.setImageDrawable(getResources().getDrawable(R.mipmap.icon_user)); //TODO change Image
    //                            liked = true;
    //                            return;
//
//
    //                        }
//
//
//
    //                    }
    //                    if (liked){
    //                        if (!dataSnapshot.child("User").child(id).child("liked").child(post_id).exists()){
    //                        databaseReference.child("Posts").child(post_id).child("likes").child(id).setValue(id);
    //                        databaseReference.child("User").child(id).child("liked").child(post_id).setValue(post_id);
    //                        img_like.setImageDrawable(getResources().getDrawable(R.mipmap.icon_user)); //TODO change Image
    //                        return;
//
    //                        }
//
    //                    }
//
//
    //                }
//
    //                @Override
    //                public void onCancelled(@NonNull DatabaseError databaseError) {
//
    //                }
    //            });
//
//
//
//
    //        }
    //    });
    //}

    private void setImgandName() {
        fetchData();

    }

    private void fetchData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.d(TAG,"profiles_source" + uid);
                //if (!dataSnapshot.child("User").child(uid).child("IMG").exists() ||!dataSnapshot.child("Posts").child(post_id).child("Info").exists()||!dataSnapshot.child("Posts").child(post_id).child("Place_name").exists()){
                //    return;
                //}
                String profile_source = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("IMG").getValue()).toString();
                Picasso.get().load(profile_source).fit().into(img_profile_pic);
                String name = Objects.requireNonNull(dataSnapshot.child("User").child(uid).child("Name").getValue().toString());
                String Info = Objects.requireNonNull(dataSnapshot.child("Posts").child(post_id).child("Info").getValue().toString());
                String Location = Objects.requireNonNull(dataSnapshot.child("Posts").child(post_id).child("Place_name").getValue().toString());

                txt_name.setText(name);
                info_txt.setText(Info);
                location_txt.setText(Location);


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
        like_setup();
        like_onClick();
        if (type.equals("Video")){
            video_setup();
            video_player();


        }
        if (type.equals("IMG")){
            img_setup();




        }
    }

    private void like_onClick() {
        img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter_Custom_Post custom_posts_presenter = new Presenter_Custom_Post();
                custom_posts_presenter.setupLike(getContext(),post_id,img_like);

            }
        });


    }

    private void img_setup() {
        img_post.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.GONE);
        Picasso.get().load(source).fit().centerCrop().into(img_post);

    }
    private void like_setup(){
        Presenter_Custom_Post custom_posts_presenter = new Presenter_Custom_Post();
        custom_posts_presenter.initLike(getContext(),img_like,post_id);
    }

    private void video_player() {

                    DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                            Util.getUserAgent(getContext(), "uClimb"));
// This is the MediaSource representing the media to be played.
                    MediaSource videoSource =
                            new ProgressiveMediaSource.Factory(dataSourceFactory)
                                    .createMediaSource(Uri.parse(source));
                    player = new SimpleExoPlayer.Builder(getContext()).build();
                    player.prepare(videoSource);
                    videoView.setPlayer(player);
                    video_state = 1;




    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
        }

    }

    private void video_setup() {
        videoView.setVisibility(View.VISIBLE);
        img_post.setVisibility(View.GONE);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "uClimb"));
// This is the MediaSource representing the media to be played.
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(source));
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(getContext()).build();
        player.prepare(videoSource);
        videoView.setPlayer(player);
    }

    private void initViews(@NonNull View view) {
       img_post = view.findViewById(R.id.img_view_custom);
       videoView = view.findViewById(R.id.video_custom);
       img_like = view.findViewById(R.id.img_like_custom);
       img_share = view.findViewById(R.id.img_share_custom);
       txt_name = view.findViewById(R.id.txt_username_custom);
       img_profile_pic = view.findViewById(R.id.profile_img_custom);
       info_txt = view.findViewById(R.id.txt_info_custom);
       comment_txt = view.findViewById(R.id.txt_comments_custom);
       location_txt = view.findViewById(R.id.txt_location_custom);

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
