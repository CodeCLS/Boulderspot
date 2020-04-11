package app.playstore.uClimb.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.custom_profile_presenter;

public class Custom_profile_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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


    private inner_profile_adapter inner_profile_adapter = new inner_profile_adapter(source,type,posts,info,uid_inner,time,place);

    private Context mContext;

    public Custom_profile_Adapter(String uid, String stat_uid, String age, String name, String profile_img, String info, String subscription, String grade, String country, ArrayList follower, ArrayList following, String height, String account_type, String time_created, ArrayList<String> posts) {
        this.uid = uid;
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewHolder= null;


        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_viewholder_custom_profile_1,parent,false);
            viewHolder = new standart_profile_view(view);

        }
        if (viewType == 1){

            view = LayoutInflater.from(mContext).inflate(R.layout.custom_viewholder_custom_profile_3,parent,false);
            viewHolder = new follow_profile_viewholder(view);



        }
        if (viewType == 2){
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_viewholder_custom_profile_2,parent,false);
            viewHolder = new uploads_viewholder_profile_custom(view);


        }

        assert viewHolder != null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            standart_profile_view standart_profile_view = (Custom_profile_Adapter.standart_profile_view) holder;

            Log.d(TAG,"Height:" + Height);
            standart_profile_view.Name.setText(Name);
            standart_profile_view.Grade.setText(grade);
            standart_profile_view.Country.setText(country);
            standart_profile_view.Info.setText(Info);
            standart_profile_view.Height.setText(Height);
            Picasso.get().load(profile_img).fit().into(standart_profile_view.img_profile);



        }
        if (holder.getItemViewType() == 1){
            follow_profile_viewholder follow_profile_viewholder = (Custom_profile_Adapter.follow_profile_viewholder) holder;
            Drawable background = follow_profile_viewholder.button.getBackground();
            background.setTint(mContext.getResources().getColor(R.color.blue_pressed_btn));

            follow_profile_viewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    custom_profile_presenter custom_profile_presenter = new custom_profile_presenter();
                    custom_profile_presenter.follow_new_user(mContext,uid);
                }
            });


        }
        if (holder.getItemViewType() == 2){

            uploads_viewholder_profile_custom profile_custom = (uploads_viewholder_profile_custom) holder;
            int numb_columns = 2;
            profile_custom.recyclerView.setLayoutManager(new GridLayoutManager(mContext,numb_columns));
            profile_custom.recyclerView.setAdapter(inner_profile_adapter);


        }


    }

    @Override
    public int getItemCount() {
        return 3;
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

    private class uploads_viewholder_profile_custom extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public uploads_viewholder_profile_custom(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.rec_viewholder_profile_custom_2_12);
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
        if (position == 0){
            i = 0;

        }
        if (position == 1){
            i = 1;

        }
        if (position == 2){
            i = 2;

        }
        return i;
    }
}
