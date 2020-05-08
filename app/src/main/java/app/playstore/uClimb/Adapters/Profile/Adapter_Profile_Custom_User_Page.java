package app.playstore.uClimb.Adapters.Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import app.playstore.uClimb.Obsolete.Adapter_Profile_User_Uploads;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Custom_Profile.Presenter_Custom_Profile;

public class Adapter_Profile_Custom_User_Page extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CUstom_profile_adapter";
    private String uid;
    private String stat_uid;
    private String Age;
    private String Name;
    private String profile_img;
    private String Info;
    private String Subscription;
    private String grade;
    private String IMG;

    private String country;
    private ArrayList follower = new ArrayList();
    private ArrayList  following = new ArrayList();
    private String Height;
    private String account_type;
    private String time_created;
    private String Email;
    private ArrayList competition = new ArrayList();
    private String position_lat;
    private String position_long;
    private String position_last_updated;
    private Boolean position_status;

    private ArrayList<String> posts = new ArrayList<>();
    private ArrayList<String> source= new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<String> info = new ArrayList<>();
    private ArrayList<String> uid_inner = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> place = new ArrayList<>();

    private Adapter_Profile_User_Uploads inner_profile_adapter = new Adapter_Profile_User_Uploads(source,type,posts,info,uid_inner,time,place);

    private Context mContext;


    public Adapter_Profile_Custom_User_Page(String uid, String stat_uid, String age, String name, String profile_img, String info, String subscription, String grade, String country, ArrayList follower, ArrayList following, String height, String account_type, String time_created, ArrayList<String> posts) {
        this.uid = uid;
        Log.d(TAG,"uid3" + uid + "uid4 " +this.uid);
        this.stat_uid = stat_uid;
        Age = age;
        Name = name;
        this.profile_img = profile_img;
        Info = info;
        Subscription = subscription;
        this.grade = grade;
        this.country = country;
        this.follower = follower;
        this.following = following;
        Height = height;
        this.account_type = account_type;
        this.time_created = time_created;
        this.posts = posts;

    }

    public Adapter_Profile_Custom_User_Page() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        Log.d(TAG,"Context5" + mContext);
        View view;
        RecyclerView.ViewHolder viewHolder= null;


        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_custom_viewholder_custom_profile_1,parent,false);
            viewHolder = new standart_profile_view(view);

        }
        if (viewType == 1){

            view = LayoutInflater.from(mContext).inflate(R.layout.private_custom_viewholder_custom_profile_3,parent,false);
            viewHolder = new follow_profile_viewholder(view);



        }
        if (viewType == 2){
            Log.d(TAG,"post1" + posts);
            view = LayoutInflater.from(mContext).inflate(R.layout.public_custom_upload_user,parent,false);
            viewHolder = new uploads_viewholder_profile_custom(view);


        }

        assert viewHolder != null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        position = position-2;
        if (holder.getItemViewType() == 0){
            standart_profile_view standart_profile_view = (Adapter_Profile_Custom_User_Page.standart_profile_view) holder;

            Log.d(TAG,"Height:" + Height);
            standart_profile_view.Name.setText(Name);
            standart_profile_view.Grade.setText(grade);
            standart_profile_view.Country.setText(country);
            standart_profile_view.Info.setText(Info);
            standart_profile_view.Height.setText(Height);
            Picasso.get().load(profile_img).fit().into(standart_profile_view.img_profile);
            setFollowerSpinner(standart_profile_view.spinner_follower,standart_profile_view.spinner_following);



        }
        if (holder.getItemViewType() == 1){
            Presenter_Custom_Profile custom_profile_presenter = new Presenter_Custom_Profile();
            Log.d(TAG,"Context6" + mContext);



            follow_profile_viewholder follow_profile_viewholder = (Adapter_Profile_Custom_User_Page.follow_profile_viewholder) holder;
            custom_profile_presenter.isFollowing(uid,mContext,follow_profile_viewholder.button);




        }
        if (holder.getItemViewType() == 2){
            Log.d(TAG,"people21" + position + " " + posts.size());
            //if (position > posts.size()){
            //    Log.d(TAG,"people22");
            //    position = position-1;
            //}
            uploads_viewholder_profile_custom uploads_viewholder_profile_custom = (uploads_viewholder_profile_custom) holder;
            Presenter_Custom_Profile custom_profile_presenter = new Presenter_Custom_Profile();

            Presenter_Custom_Profile custom_profile = new Presenter_Custom_Profile();
            int holdertype = holder.getItemViewType();
            custom_profile.getSource(mContext,position,uploads_viewholder_profile_custom.img,uploads_viewholder_profile_custom.exoPlayer,uploads_viewholder_profile_custom.progress_custom_upload_profile,custom_profile,holdertype,posts);



        }


    }

    private void setFollowerSpinner(Spinner Follower,Spinner Following) {
        Presenter_Custom_Profile custom_profile = new Presenter_Custom_Profile();
        custom_profile.getUserData(mContext,Follower,Following);


    }



    public void addPosts(Context back_context,int position, ImageView img, SimpleExoPlayerView exoPlayer,ProgressBar progressBar, int holdertype, ArrayList<String> source, ArrayList type) {

            Log.d(TAG,"video21"+type.get(position));
            if (type.get(position).equals("Video")){
                Log.d(TAG,"source21" + source);
                Uri uri = Uri.parse(source.get(position));
                Log.d(TAG,"Context" + back_context);
                img.setVisibility(View.GONE);
                exoPlayer.setVisibility(View.VISIBLE);
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(back_context,
                        Util.getUserAgent(back_context, "uClimb"));
                MediaSource videoSource =
                        new ProgressiveMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(uri);
                SimpleExoPlayer player = new SimpleExoPlayer.Builder(back_context).build();
                player.prepare(videoSource);
                exoPlayer.setPlayer(player);
                Handler handler = new Handler();
                videoSource.addEventListener(handler, new MediaSourceEventListener() {
                    @Override
                    public void onMediaPeriodCreated(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {
                        player.seekTo(2);

                    }

                    @Override
                    public void onMediaPeriodReleased(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {

                    }

                    @Override
                    public void onLoadStarted(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                        progressBar.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onLoadCompleted(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onLoadCanceled(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {

                    }

                    @Override
                    public void onLoadError(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException error, boolean wasCanceled) {

                    }

                    @Override
                    public void onReadingStarted(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {

                    }

                    @Override
                    public void onUpstreamDiscarded(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {

                    }

                    @Override
                    public void onDownstreamFormatChanged(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {

                    }
                });

            }
            else{
                exoPlayer.setVisibility(View.GONE);
                img.setVisibility(View.VISIBLE);
                Picasso.get().load(source.get(position)).fit().into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG,"Error22");

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG,"Error21: " + e);

                    }
                });


            }








    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"item_count" + (2+ posts.size()));
        return 2 + posts.size();
    }

    public void setFollowing(boolean b,Button btn,Context mContext, String uid) {
        Log.d(TAG,"uid2: " + uid + "boolean " + b);
        if (b){
            Presenter_Custom_Profile custom_profile_presenter = new Presenter_Custom_Profile();
            Drawable background = btn.getBackground();
            background.setTint(mContext.getResources().getColor(R.color.cpb_green));
            btn.setText("Following");


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable background1 = btn.getBackground();
                    background1.setTint(mContext.getResources().getColor(R.color.blue_pressed_btn));
                    custom_profile_presenter.follow_new_user(mContext,uid,true,btn);



                }
            });




        }
        else{
            Presenter_Custom_Profile custom_profile_presenter = new Presenter_Custom_Profile();

            Drawable background = btn.getBackground();
            background.setTint(mContext.getResources().getColor(R.color.blue_pressed_btn));
            btn.setText("Follow");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    custom_profile_presenter.follow_new_user(mContext,uid,b,btn);
                    Drawable background1 = btn.getBackground();
                    background1.setTint(mContext.getResources().getColor(R.color.cpb_green));
                    Log.d(TAG,"hs32");

                }
            });






        }
    }

    public void setPosts() {
    }

    public static class standart_profile_view extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Grade;
        TextView Height;
        TextView Country;
        TextView Info;
        Spinner spinner_follower;
        Spinner spinner_following;
        de.hdodenhof.circleimageview.CircleImageView img_profile;
        TextView txt_follower;
        TextView txt_following;






        @SuppressLint("CutPasteId")
        public standart_profile_view(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name_txt_profile_custom);
            Grade = itemView.findViewById(R.id.grade_txt_profile_custom);
            Height = itemView.findViewById(R.id.height_txt_profile_custom);
            Country = itemView.findViewById(R.id.country_txt_profile_custom);
            Info = itemView.findViewById(R.id.info_txt_profile_custom);
            spinner_follower = itemView.findViewById(R.id.spinner_followers_profile_custom);
            spinner_following = itemView.findViewById(R.id.spinner_following_profile_custom);
            txt_follower = itemView.findViewById(R.id.number_followers_profile_custom);
            txt_following = itemView.findViewById(R.id.number_following_profile_custom);
            img_profile = itemView.findViewById(R.id.circle_profile_image_custom);


        }
    }

    public class uploads_viewholder_profile_custom extends RecyclerView.ViewHolder {
        SimpleExoPlayerView exoPlayer;
        ImageView img;
        ProgressBar progress_custom_upload_profile;

        public uploads_viewholder_profile_custom(View view) {
            super(view);
           img = view.findViewById(R.id.adapter_profile_custom_uploads_img);
           exoPlayer = view.findViewById(R.id.adapter_profile_custom_uploads_video);
           progress_custom_upload_profile = view.findViewById(R.id.progress_custom_upload_profile);

        }
    }

    private class follow_profile_viewholder extends RecyclerView.ViewHolder {
        Button button;
        public follow_profile_viewholder(View view) {
            super(view);
            button = view.findViewById(R.id.btn_follow_profile_custom);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        Log.d(TAG,"Position:" + position);
        if (position== 0){
            i = 0;
        }
        else{
            if (position == 1){
                i = 1;

            }
            else {
                i= 2;
            }


        }


        return i;
    }
}
