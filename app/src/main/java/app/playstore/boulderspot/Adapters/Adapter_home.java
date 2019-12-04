package app.playstore.boulderspot.Adapters;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.boulderspot.Fragments.Home_Fragment;
import app.playstore.boulderspot.R;

public class Adapter_home extends RecyclerView.Adapter<Adapter_home.ViewHolder> {

    private static final String TAG = "adapter_home";
    private ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> place   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private int clicked = 1;
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    private  ArrayList<String> likes = new ArrayList<>();


    public Context mContext;

    public Adapter_home(ArrayList<String> IMG_URL, ArrayList<String> grade, ArrayList<String> place,
                        ArrayList<String> name, ArrayList<String> info, ArrayList<String> maker,
                        ArrayList<String> video_url, ArrayList<String> ID_User,
                        ArrayList<String> ID_video, ArrayList<String> likes, Context mContext) {
        this.IMG_URL = IMG_URL;
        this.grade = grade;
        this.place = place;
        this.name = name;
        this.info = info;
        this.maker = maker;
        this.video_url = video_url;
        this.ID_User = ID_User;
        this.ID_video = ID_video;
        this.likes = likes;
        this.mContext = mContext;
        Log.d(TAG , "context:" + mContext);

    }


    @NonNull
    @Override
    public Adapter_home.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_home, viewGroup, false);
        Log.d(TAG , "context:" + this.mContext);
        mContext = viewGroup.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_home.ViewHolder viewHolder, int i) {

        Log.d(TAG , "context:" + this.mContext);
        viewHolder.grade_txt.setText(grade.get(i));
        viewHolder.info_txt.setText(info.get(i));
        Picasso.get().load(IMG_URL.get(i)).into(viewHolder.IMG_img_profile_pic);
        viewHolder.video_view.setVideoPath(video_url.get(i));
        viewHolder.video_view.seekTo(1);
        viewHolder.name_txt.setText(name.get(i));
       // viewHolder.full_screen.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //      //   enterFullScreen(viewHolder);
       //     }
       // });
        viewHolder.video_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == 1){
                    Log.d(TAG,"clicked_first");
                    viewHolder.video_view.start();
                    clicked = 0;
                }
                else{
                    viewHolder.video_view.pause();
                    clicked= 1;
                    Log.d(TAG,"clicked_second");


                }



            }
        });
        viewHolder.IMG_img_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

    @Override
    public int getItemCount() {
        return IMG_URL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView grade_txt;
        TextView name_txt;
        de.hdodenhof.circleimageview.CircleImageView IMG_img_profile_pic;
        TextView info_txt;
        VideoView video_view;
        TextView maker_txt;
      //  ImageView full_screen;
        TextView liked;




        public ViewHolder(View view) {
            super(view);
            liked = view.findViewById(R.id.like_txt);
            grade_txt = (TextView) view.findViewById(R.id.grade_txt);
            name_txt = (TextView) view.findViewById(R.id.username_txt);
            IMG_img_profile_pic =  view.findViewById(R.id.img_pro_custom_home_img);
            info_txt = (TextView) view.findViewById(R.id.info_txt);
            maker_txt = (TextView) view.findViewById(R.id.maker_txt);
            video_view = (VideoView) view.findViewById(R.id.video_custom_home);
          //  full_screen = (ImageView) view.findViewById(R.id.full_view_img_custom);









        }
    }
    private void enterFullScreen(Adapter_home.ViewHolder view){
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams params= view.video_view.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;

        view.video_view.setLayoutParams(params);

}}
