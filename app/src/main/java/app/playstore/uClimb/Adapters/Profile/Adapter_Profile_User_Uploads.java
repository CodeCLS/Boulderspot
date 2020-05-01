package app.playstore.uClimb.Adapters.Profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Post.custom_post_page;
import app.playstore.uClimb.R;

public class Adapter_Profile_User_Uploads extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "inner_profile_adapter";
    private ArrayList<String> source = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<String> post_id = new ArrayList<>();

    private ArrayList<String> info = new ArrayList<>();
    private ArrayList<String> u_id = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> place = new ArrayList<>();
    private ArrayList<String> comments = new ArrayList<>();
    private ArrayList<String> likes = new ArrayList<>();
    private ArrayList<String> saved = new ArrayList<>();
    private ArrayList<String> shared = new ArrayList<>();

    private Context mContext;
    private boolean status_video = false;

    public Adapter_Profile_User_Uploads(ArrayList<String> source, ArrayList<String> type, ArrayList<String> post_id, ArrayList<String> info, ArrayList<String> u_id, ArrayList<String> time, ArrayList<String> place) {
        this.source = source;
        this.type = type;
        this.post_id = post_id;
        this.info = info;
        this.u_id = u_id;
        this.time = time;
        this.place = place;
        Log.d(TAG,"source" + source);
        Log.d(TAG,"type" + type);
        Log.d(TAG,"info" + info);

        Log.d(TAG,"post" + post_id);

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view ;
        RecyclerView.ViewHolder viewholder= null;


        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.main_video_profile_uploads,parent,false);
            viewholder = new standart_holder_video(view);

        }
        if (viewType== 1){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_img_profile_uploads,parent,false);
            viewholder = new standart_holder_img(view);

        }
        if (viewType==2) {

        }
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            standart_holder_video  standart_holder_video = (Adapter_Profile_User_Uploads.standart_holder_video) holder;
            standart_holder_video.videoView.setVideoPath(source.get(position));
            standart_holder_video.videoView.seekTo(10);
            standart_holder_video.videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    custom_post_page custom_post_page = new custom_post_page();
                    Bundle arguments = new Bundle();
                    arguments.putString("Source",source.get(position));
                    arguments.putString("Type",type.get(position));
                    arguments.putString("PostID",post_id.get(position));
                    arguments.putString("UserID",u_id.get(position));
                    custom_post_page.setArguments(arguments);

                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_custom_post_page").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, custom_post_page).commit();

                }
            });










        }
        if (holder.getItemViewType() == 1){
            standart_holder_img  standart_holder_img = (standart_holder_img) holder;
            Picasso.get().load(source.get(position)).fit().into(standart_holder_img.img);
            standart_holder_img.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    custom_post_page custom_post_page = new custom_post_page();
                    Bundle arguments = new Bundle();
                    arguments.putString("Source",source.get(position));
                    arguments.putString("Type",type.get(position));
                    arguments.putString("PostID",post_id.get(position));
                    arguments.putString("UserID",u_id.get(position));
                    custom_post_page.setArguments(arguments);

                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_custom_post_page").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, custom_post_page).commit();

                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return post_id.size();
    }
    public class standart_holder_video extends RecyclerView.ViewHolder{
        VideoView videoView;

        public standart_holder_video(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_custom_profile_2);
        }
    }
    public class standart_holder_img extends RecyclerView.ViewHolder{
        ImageView img;

        public standart_holder_img(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_profile_uploads_custom_1);

        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (type.get(position).equals("IMG")){
            i = 1;
        }
        if (type.get(position).equals("Video")){
            i = 0;
        }
        return i;
    }
}
