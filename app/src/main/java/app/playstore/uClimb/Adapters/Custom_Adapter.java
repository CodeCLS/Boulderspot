package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.MVP.MVP_Workout.Presenter_Workout;
import app.playstore.uClimb.R;

public class Custom_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String intent_type;
    private Context context;
    private ArrayList<String> intent_type_array = new ArrayList<>();
    private ArrayList<String>  post_id;
    private ArrayList<String>  u_id;
    private ArrayList<String>  info;
    private ArrayList<String>  Source_Video;
    private ArrayList<String>  IMG;
    private ArrayList<String>  Name;
    private ArrayList<String>  Likes;
    private ArrayList<String>  Time;


    public Custom_Adapter(ArrayList<String> post_id, ArrayList<String> u_id, ArrayList<String> info, ArrayList<String> source_Video, ArrayList<String> IMG, ArrayList<String> name, ArrayList<String> likes,String intent_type,ArrayList<String> Time) {
        this.post_id = post_id;
        this.u_id = u_id;
        this.info = info;
        Source_Video = source_Video;
        this.IMG = IMG;
        Name = name;
        Likes = likes;
        this.intent_type = intent_type;
        this.Time = Time;
    }
    public Custom_Adapter(ArrayList<String> post_id, ArrayList<String> u_id, ArrayList<String> info, ArrayList<String> source_Video, ArrayList<String> IMG, ArrayList<String> name, ArrayList<String> likes,ArrayList<String> intent_type,ArrayList<String> Time) {
        this.post_id = post_id;
        this.u_id = u_id;
        this.info = info;
        Source_Video = source_Video;
        this.IMG = IMG;
        Name = name;
        Likes = likes;
        this.intent_type_array = intent_type;
        this.Time = Time;

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();



        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_custom_home, parent, false);

        RecyclerView.ViewHolder  viewHolder= new Standart(view);
        if (viewType == 0){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.private_profile_page_user_multiple_layout_c, parent, false);

            viewHolder= new Standart_Profile(view);

        }

        return viewHolder;





    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            Standart_Profile standart = (Standart_Profile) holder;
            standart.exoPlayerView.setVisibility(View.VISIBLE);


            standart.IMG.setVisibility(View.GONE);


            Presenter_Workout presenter_workout = new Presenter_Workout();
            //presenter_workout.liked(post_id.get(position),standart.like,intent_type,context);

            String time = Time.get(position).toString();
            String convertedTime_1 = time.substring(0,4);
            String convertedTime_2 = time.substring(4,6);
            String convertedTime_3 =  time.substring(6,8);
            String convertedTime_4 =  time.substring(8,9);
            String convertedTime_5 =  time.substring(9,10);
            String final_time = convertedTime_3 + "." + convertedTime_2 + "." + convertedTime_1 + "-" +convertedTime_4 +":" + convertedTime_5;

            standart.time.setText(final_time);
            standart.likes.setText(Likes.get(position));
            //Picasso.get().load(IMG.get(position)).fit().centerCrop().into(standart.IMG);
             standart.delete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     presenter_workout.delete(post_id.get(position),intent_type_array.get(position),context);

                 }
             });
            standart.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            standart.place.setVisibility(View.GONE);
            standart.comments.setVisibility(View.GONE);
            standart.IMG.setVisibility(View.GONE);
            initPlayer(position,standart.exoPlayerView);

        }
        else{

            Viewwork(position,holder);

        }



    }


    private void Viewwork(int position,RecyclerView.ViewHolder holder) {
        Standart standart = (Standart) holder;

        standart.btn_more.setVisibility(View.GONE);


        Presenter_Workout presenter_workout = new Presenter_Workout();
        presenter_workout.liked(post_id.get(position),standart.likes_img,intent_type,context);
        standart.name.setText(Name.get(position));
        standart.info.setText(info.get(position));
        Picasso.get().load(IMG.get(position)).fit().centerCrop().into(standart.IMG);
        standart.likes_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter_workout.user_likes(post_id.get(position),standart.likes_img,intent_type,context);

            }
        });
        standart.share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initPlayer(position,standart.exoPlayerView);
    }

    private void initPlayer(int position,SimpleExoPlayerView exoPlayer) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "uClimb"));
// This is the MediaSource representing the media to be played.
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(Source_Video.get(position)));
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(context).build();
        player.prepare(videoSource);
        exoPlayer.setPlayer(player);
    }


    @Override
    public int getItemCount() {
        return post_id.size();
    }

    private class Standart extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView info;
        private SimpleExoPlayerView exoPlayerView;
        private de.hdodenhof.circleimageview.CircleImageView IMG;
        private ImageView likes_img;
        private ImageView share_img;
        Button btn_more;



        public Standart(View view) {
            super(view);
            name = view.findViewById(R.id.txt_username_home);
            info = view.findViewById(R.id.txt_info_custom_home);
            exoPlayerView = view.findViewById(R.id.video_custom_home);
            btn_more =   view.findViewById(R.id.btn_more_posts_home);
            IMG = view.findViewById(R.id.profile_img_custom_home);
            likes_img = view.findViewById(R.id.img_home_like);
            share_img = view.findViewById(R.id.img_home_share);
        }
    }

    private class Standart_Profile extends RecyclerView.ViewHolder {
        SimpleExoPlayerView exoPlayerView;
        TextView place;
        TextView comments;
        ImageView IMG;
        TextView likes;

        TextView time;
        Button delete;
        ImageView share;
        public Standart_Profile(View view) {
            super(view);
            place = view.findViewById(R.id.place_user_posts_custom);
            exoPlayerView = view.findViewById(R.id.exoplayer_user_profile_custom_post);
            comments = view.findViewById(R.id.comments_profile_custom_post);
            likes = view.findViewById(R.id.likes_profile_custom_post);
            time = view.findViewById(R.id.time_user_posts_custom);
            delete = view.findViewById(R.id.btn_delete_profile_custom_post);
            share = view.findViewById(R.id.share_profile_custom_post);
            IMG = view.findViewById(R.id.img_user_profile_custom_post);


        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = 1;
        if (!intent_type_array.isEmpty()){
            i  = 0;

        }
        return i;
    }
}
