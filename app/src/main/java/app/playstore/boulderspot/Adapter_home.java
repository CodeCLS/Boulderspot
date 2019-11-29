package app.playstore.boulderspot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_home extends RecyclerView.Adapter<Adapter_home.ViewHolder> {

    private  ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();

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



    }

    @Override
    public int getItemCount() {
        return IMG_URL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView grade_txt;
        TextView name_txt;
        com.mikhaellopez.circularimageview.CircularImageView IMG_img_profile_pic;
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
