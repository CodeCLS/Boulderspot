package app.playstore.uClimb.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.jarvanmo.exoplayerview.media.SimpleMediaSource;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

import app.playstore.uClimb.Fragments.Training_list_fragment;
import app.playstore.uClimb.Fragments.video_upload_fragment;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.home_posts_presenter;

public class Adapter_home extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements home_posts_presenter.display {
    private ArrayList<String> array_time = new ArrayList();
    private ArrayList<String> array_name = new ArrayList();
    private ArrayList<String> array_source_img = new ArrayList();
    private ArrayList<String> array_source = new ArrayList();
    private ArrayList<String> array_info = new ArrayList();
    private ArrayList<String> array_place = new ArrayList();
    private ArrayList<String> array_user_id = new ArrayList();
    private ArrayList<String> array_post_id = new ArrayList();
    private ArrayList<String> array_type = new ArrayList();
    private ArrayList<String> array_likes = new ArrayList();
    private static final String TAG = "adapter_home";
    private boolean anim_boolean_status = false;
    private int weight_linear_name = 1;
    private float weight_comment = (int) 1.5;
    private int weight_linear_text = 1;


    private ArrayList< String> likes;
    private int clicked = 0;
    private String auth;
    private String UID;
    private String String_post;
    private Boolean exists;
    private FirebaseAuth mAuth;
    public static final int layout_one = 1;
    private double tools_weight = (float) 3;
    private double i_anim = (float) 1.5;
    private double weight_text_inside = (float) 1;
    private double constraing_num1231q321_weight = (float) 0.7;

    private double abc_weight = (float) 1.2;
    public static final int layout_two = 2;


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("Posts");

    private Context mContext;

    @Override
    public int getItemViewType(final int position) {
        int i = 0;

        Log.d(TAG, "position" + position);
        if (position == 0) {
            i = 0;
        } else {

            i = 1;
        }


        return i;
    }


    public Adapter_home(ArrayList<String> array_time, ArrayList<String> array_name, ArrayList<String> array_source_img, ArrayList<String> array_source, ArrayList<String> array_info, ArrayList<String> array_place, ArrayList<String> array_user_id, ArrayList<String> array_post_id, ArrayList<String> array_type, ArrayList<String> array_likes) {
        this.array_time = array_time;
        this.array_name = array_name;
        this.array_source_img = array_source_img;
        this.array_source = array_source;
        this.array_info = array_info;
        this.array_place = array_place;
        this.array_user_id = array_user_id;
        this.array_post_id = array_post_id;
        this.array_type = array_type;
        this.array_likes = array_likes;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        mContext = viewGroup.getContext();
        mAuth = FirebaseAuth.getInstance();
        View view;
        if (i == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.training_rec_item, viewGroup, false);
            viewHolder = new ViewHolder_training(view);

        }
        if (i == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_home, viewGroup, false);
            viewHolder = new ViewHolder(view);

        }
        return viewHolder;


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
         i = i-1;
        if (viewHolder.getItemViewType() == 0) {
            ViewHolder_training viewHolder_training = (ViewHolder_training) viewHolder;
            training_and_post_onClick(viewHolder_training);

        }
        if (viewHolder.getItemViewType() == 1) {

            ViewHolder viewHolder1 = (ViewHolder) viewHolder;
            setWidgets(viewHolder1, i);


        }

    }

    @Override
    public int getItemCount() {
        return array_source.size()+1;
    }
    private HttpProxyCacheServer proxy;

    public  HttpProxyCacheServer getProxy(Context context) {
        return proxy == null ? (proxy = newProxy()) : proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(mContext);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWidgets(ViewHolder viewHolder, int i) {
        viewHolder.video_view.setBackgroundResource(android.R.color.transparent);
         viewHolder.video_view.setDrawingCacheEnabled(true);
        viewHolder.info_txt.setText(array_info.get(i));
        Picasso.get().load(array_source_img.get(i)).fit().centerCrop().into(viewHolder.IMG_img_profile_pic);

        if (array_type.get(i).equals("Video")){
            viewHolder.img_view.setVisibility(View.GONE);
            viewHolder.video_view.setVisibility(View.VISIBLE);
            Log.d(TAG,"array_source"+array_source.get(i));
           String s = array_source.get(i);
           viewHolder.video_view.setVideoPath(s);
           viewHolder.video_view.seekTo(2);

            Log.d(TAG,"videosource: " + s);
            viewHolder.video_view.start();


        }
        else{
            viewHolder.video_view.setVisibility(View.GONE);
            viewHolder.img_view.setVisibility(View.VISIBLE);
            Picasso.get().load(array_source.get(i)).fit().centerCrop().into(viewHolder.img_view);




        }
        //HttpProxyCacheServer proxy = getProxy(mContext);
        //proxy.registerCacheListener((CacheListener) mContext, array_source.get(i));
        //String proxyUrl = proxy.getProxyUrl(array_source.get(i));





        viewHolder.name_txt.setText(array_name.get(i));

        pause_play_video(viewHolder,i);


    }

    @Override
    public void allPostData(Bundle bundle) {

    }






    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout abc;
        //TextView grade_txt;
        TextView name_txt;
        ImageView img_view;
        de.hdodenhof.circleimageview.CircleImageView IMG_img_profile_pic;
        TextView info_txt;
        VideoView video_view;
        LinearLayout text_inside;
        ConstraintLayout home_custom;
        ImageView like_btn;
        ImageView share_btn;
        LinearLayout tools_btn_layout;
        TextView comments_txt;
        EditText comment_edit;
        Button comment_btn;
        LinearLayout constraing_num1231q321;
        //RecyclerView comment_rec;


        //TextView liked;
        //  ImageView full_screen;


        ViewHolder(View view) {
            super(view);
            constraing_num1231q321 = view.findViewById(R.id.constraing_num1231q321);
            comment_btn = view.findViewById(R.id.btn_comment_home_custom);
            comment_edit = view.findViewById(R.id.edit_comment_home_custom);
            //comment_rec = view.findViewById(R.id.rec_home_custom);
            abc = view.findViewById(R.id.abc);
            comments_txt = view.findViewById(R.id.comments_txt);
            tools_btn_layout = view.findViewById(R.id.tools_btn_layout_home);
            like_btn = view.findViewById(R.id.img_home_like);
            share_btn = view.findViewById(R.id.img_home_share);
            name_txt = view.findViewById(R.id.txt_username_home);
            IMG_img_profile_pic = view.findViewById(R.id.profile_img_custom_home);
            info_txt = view.findViewById(R.id.txt_info_custom_home);
            video_view = view.findViewById(R.id.video_custom_home);
            img_view = view.findViewById(R.id.img_view_custom_home);
            text_inside = view.findViewById(R.id.text_inside_home);
            home_custom = view.findViewById(R.id.custom_home_posts);


        }
    }

    static class ViewHolder_training extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView start_training_txt;
        TextView post_success_txt;


        ViewHolder_training(View view) {
            super(view);
            layout = view.findViewById(R.id.rec_training_item_layout);
            start_training_txt = view.findViewById(R.id.txt_start_training);
            post_success_txt = view.findViewById(R.id.txt_post_success);


        }
    }


    //All methods















    private void shareonClick(ViewHolder viewHolder_normal) {
        viewHolder_normal.share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_work();


            }
        });
    }

    private void share_work() {


    }

    private void likedonCLick(int i, ViewHolder viewHolder_normal) {
        int finalI = i;
        viewHolder_normal.like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_posts_presenter home_posts_presenter = new home_posts_presenter(Adapter_home.this,mContext);
                Boolean boolean_status =home_posts_presenter.liked(array_post_id.get(finalI));//TODO presenter like method
                if (boolean_status== false){
                    Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                else{
                    viewHolder_normal.like_btn.setImageDrawable(mContext.getDrawable(R.mipmap.boulderspot_logo));//TODO change to like_btn_full


                }

            }
        });
    }





    private void training_and_post_onClick(@NonNull ViewHolder_training viewHolder) {
        viewHolder.start_training_txt.setOnClickListener(v -> {
            training_txt_action();

        });
        viewHolder.post_success_txt.setOnClickListener(v -> {
            video_upload_fragment mFragment = new video_upload_fragment();


            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Fragment_add_post").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container_fragment,mFragment).commit();




        });
    }

    private void training_txt_action() {
        Training_list_fragment mFragment = new Training_list_fragment();

        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container_fragment, mFragment).commit();
    }









    private void pause_play_video(@NonNull final ViewHolder viewHolder,int i) {
        viewHolder.video_view.setOnClickListener(v -> {

            if (clicked == 0) {
                SimpleMediaSource mediaSource = new SimpleMediaSource(array_source.get(i));//uri also supported
                viewHolder.video_view.start();
                clicked = 1;
            } else {
                viewHolder.video_view.pause();
                clicked = 0;


            }


        });
    }


}








