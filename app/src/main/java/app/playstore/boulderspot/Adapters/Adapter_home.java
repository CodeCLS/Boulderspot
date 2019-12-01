package app.playstore.boulderspot.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

import app.playstore.boulderspot.R;

public class Adapter_home extends RecyclerView.Adapter<Adapter_home.ViewHolder> {

    private  ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    private  ArrayList<String> likes = new ArrayList<>();

    public Context mContext;

    public Adapter_home(ArrayList<String> IMG_URL, ArrayList<String> grade, ArrayList<String> name, ArrayList<String> info, ArrayList<String> maker, ArrayList<String> video_url, ArrayList<String> ID_User, ArrayList<String> ID_video, Context mContext) {
        this.IMG_URL = IMG_URL;
        this.grade = grade;
        this.name = name;
        this.info = info;
        this.maker = maker;
        this.video_url = video_url;
        this.ID_User = ID_User;
        this.ID_video = ID_video;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Adapter_home.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_home.ViewHolder viewHolder, int i) {



        viewHolder.video_view.setVideoPath("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Epic%20Bouldering%20Motivation.mp4?alt=media&token=056f6fd9-32d0-4e08-b7d7-a8856a2f451c");
        viewHolder.video_view.start();
        viewHolder.grade_txt.setText("5V");
        viewHolder.name_txt.setText("CodeCLS");
        viewHolder.info_txt.setText("Hard Boulder with many crimps. Be careful very slippery");
        viewHolder.maker_txt.setText("HardMaker92");

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




        public ViewHolder(View view) {
            super(view);
            grade_txt = (TextView) view.findViewById(R.id.grade_txt);
            name_txt = (TextView) view.findViewById(R.id.username_txt);
            IMG_img_profile_pic =  view.findViewById(R.id.img_pro_custom_home_img);
            info_txt = (TextView) view.findViewById(R.id.info_txt);
            maker_txt = (TextView) view.findViewById(R.id.maker_txt);
            video_view = (VideoView) view.findViewById(R.id.video_custom_home);







        }
    }
}
