package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class inner_home_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    private Context mContext;

    public inner_home_adapter(ArrayList<String> array_time, ArrayList<String> array_name, ArrayList<String> array_source_img, ArrayList<String> array_source, ArrayList<String> array_info, ArrayList<String> array_place, ArrayList<String> array_user_id, ArrayList<String> array_post_id, ArrayList<String> array_type) {
        this.array_time = array_time;
        this.array_name = array_name;
        this.array_source_img = array_source_img;
        this.array_source = array_source;
        this.array_info = array_info;
        this.array_place = array_place;
        this.array_user_id = array_user_id;
        this.array_post_id = array_post_id;
        this.array_type = array_type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_home,parent,false);
            viewHolder = new ViewHolder(view);


        }
        if (viewType == 1){

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            ViewHolder viewHolder = (ViewHolder) holder;
            setWidgets(viewHolder,position);

        }
        if (holder.getItemViewType() ==1){

        }

    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (true){
            i = 0;
        }
        return i;
    }

    @Override
    public int getItemCount() {
        return array_info.size();
    }
    private void setWidgets(ViewHolder viewHolder, int i) {
        viewHolder.info_txt.setText(array_info.get(i));
        Picasso.get().load(array_source_img.get(i)).fit().centerCrop().into(viewHolder.IMG_img_profile_pic);
        viewHolder.video_view.setVideoPath(array_source.get(i));
        viewHolder.video_view.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
        viewHolder.video_view.seekTo(2);
        viewHolder.name_txt.setText(array_name.get(i));
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
}
