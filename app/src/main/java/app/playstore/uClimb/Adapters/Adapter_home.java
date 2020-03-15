package app.playstore.uClimb.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import androidx.annotation.NonNull;
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
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Fragments.Training_list_fragment;
import app.playstore.uClimb.Fragments.video_upload_fragment;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelFragments.home_posts_presenter;

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
    private int weight_linear_text =1;



    private ArrayList<String> likes;
    private int clicked = 0;
    private String auth;
    private String UID;
    private String String_post;
    private Boolean exists;
    private FirebaseAuth mAuth;
    public static final int layout_one = 1;
    private double tools_weight = (float)3;
    private double i_anim = (float)1.5;
    private double weight_text_inside = (float)1;
    private double constraing_num1231q321_weight = (float)0.7;

    private double abc_weight = (float)1.2;
    public static final int layout_two = 2;



    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("Posts");

    private Context mContext;
    private boolean anim_boolean_status_source = false;
    private int grow_num_layout = 0;
    private float text_size_comment_txt = 13;
    private int height_anim2;
    private long long_time_anim_comment_duration = 500;
    private long long_time_anim_comment_delay = 0;
    private long long_time_anim_tools_duration = 500;
    private long long_time_anim_tools_delay = 0;
    private long long_time_anim_text_inside_duration = 500;
    private long long_time_anim_text_inside_delay = 0;
    private long long_time_anim_abc_duration = 500;
    private long long_time_anim_abc_delay = 0;
    private long long_time_anim_layout_duration = 500;
    private long long_time_anim_layout_delay = 250;
    private double weight_btn = 0.9;

    @Override
    public int getItemViewType(final int position) {
        return getItemViewtypeAction(position);

    }

    private int getItemViewtypeAction(int position) {
        int i = 0;


        if (position==0) {
            i = 0;
        }
        if (position==1) {

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
        return frontendonCreate(viewGroup, i);


    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        frontendonBind(viewHolder, i);
    }
    @Override
    public int getItemCount() {
        return array_source.size() + 1;
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
        RecyclerView comment_rec;

        //TextView liked;
        //  ImageView full_screen;


        ViewHolder(View view) {
            super(view);
            constraing_num1231q321 = view.findViewById(R.id.constraing_num1231q321);
            comment_btn = view.findViewById(R.id.btn_comment_home_custom);
            comment_edit = view.findViewById(R.id.edit_comment_home_custom);
            comment_rec = view.findViewById(R.id.rec_home_custom);
            abc = view.findViewById(R.id.abc);
            comments_txt = view.findViewById(R.id.comments_txt);
            tools_btn_layout = view.findViewById(R.id.tools_btn_layout_home);
            like_btn = view.findViewById(R.id.img_home_like);
            share_btn = view.findViewById(R.id.img_home_share);
            name_txt =  view.findViewById(R.id.txt_username_home);
            IMG_img_profile_pic = view.findViewById(R.id.profile_img_custom_home);
            info_txt =  view.findViewById(R.id.txt_info_custom_home);
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
            layout =  view.findViewById(R.id.rec_training_item_layout);
            start_training_txt = view.findViewById(R.id.txt_start_training);
            post_success_txt = view.findViewById(R.id.txt_post_success);






        }
    }










    //All methods






    private RecyclerView.ViewHolder frontendonCreate(@NonNull ViewGroup viewGroup, int i) {
        setInstances(viewGroup);

        View view;
        RecyclerView.ViewHolder viewHolder = null;


        if (i == 0) {
            viewHolder = setLayoutfor0(viewGroup);


        }
        if (i == 1) {

            viewHolder = setLayoutfor1(viewGroup);


        }
        return Objects.requireNonNull(viewHolder);
    }

    private void setInstances(@NonNull ViewGroup viewGroup) {
        mContext = viewGroup.getContext();
        mAuth = FirebaseAuth.getInstance();
    }

    private RecyclerView.ViewHolder setLayoutfor1(@NonNull ViewGroup viewGroup) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_home, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private RecyclerView.ViewHolder setLayoutfor0(@NonNull ViewGroup viewGroup) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.training_rec_item, viewGroup, false);
        viewHolder = new ViewHolder_training(view);
        return viewHolder;
    }



    private void frontendonBind(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        i = i-1; //important
        if (viewHolder.getItemViewType() == 0) {
            training_and_post_onClick((ViewHolder_training) viewHolder);

        }


        if (viewHolder.getItemViewType() == 1) {
            ViewHolder viewHolder_normal = (ViewHolder) viewHolder;
            home_posts_presenter home_posts_presenter = new home_posts_presenter(this);
            home_posts_presenter.initcomments(viewHolder_normal.comment_rec,mContext);
            observe_if_view_was_rendered(viewHolder_normal);




            shareonClick(viewHolder_normal);
            comment_animation_whole(viewHolder_normal);


            likedonCLick(i, viewHolder_normal);

            viewHolder_normal.home_custom.setOnClickListener(v -> {
                onCLick_text_inside_action(viewHolder_normal);
            });



            setWidgets(viewHolder_normal, i);
            pause_play_video(viewHolder_normal);

        }
    }

    private void comment_animation_whole(ViewHolder viewHolder_normal) {
        viewHolder_normal.comments_txt.setOnClickListener(v -> {
            viewHolder_normal.comment_rec.setVisibility(View.VISIBLE);
            viewHolder_normal.comment_edit.setVisibility(View.VISIBLE);
            viewHolder_normal.comment_btn.setVisibility(View.VISIBLE);
            viewHolder_normal.comments_txt.setVisibility(View.GONE);
            viewHolder_normal.tools_btn_layout.setVisibility(View.GONE);
            int k = 500;

            animate_height_change_home_custom_comment(viewHolder_normal, k);

            anim_comment(viewHolder_normal);


        });
    }

    private void anim_comment(ViewHolder viewHolder_normal) {

        ValueAnimator m1 = ValueAnimator.ofFloat((float)weight_text_inside, (float)weight_text_inside+(float)0.5); //fromWeight, toWeight
        weight_text_inside = weight_text_inside+0.5;
        m1.setDuration(long_time_anim_text_inside_duration);
        m1.setStartDelay(long_time_anim_text_inside_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.text_inside.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.text_inside.requestLayout();
        });
        m1.start();

        ValueAnimator m2 = ValueAnimator.ofFloat((float)tools_weight, (float)tools_weight+(float)0.1); //fromWeight, toWeight
        tools_weight = tools_weight+0.1;

        m2.setDuration(long_time_anim_tools_duration);
        m2.setStartDelay(long_time_anim_tools_delay);
        m2.setInterpolator(new LinearInterpolator());
        m2.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.tools_btn_layout.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.tools_btn_layout.requestLayout();
        });
        m2.start();

        ValueAnimator m3 = ValueAnimator.ofFloat((float)abc_weight, (float)abc_weight+(float)0.4); //fromWeight, toWeight
        abc_weight = abc_weight+0.4;

        m3.setDuration(long_time_anim_abc_duration);
        m3.setStartDelay(long_time_anim_abc_delay);
        m3.setInterpolator(new LinearInterpolator());
        m3.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.abc.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.abc.requestLayout();
        });
        m3.start();

        ValueAnimator m4 = ValueAnimator.ofFloat((float)constraing_num1231q321_weight, (float) ((float) constraing_num1231q321_weight+0.4)); //fromWeight, toWeight
        constraing_num1231q321_weight = constraing_num1231q321_weight+0.4;

        m4.setDuration(long_time_anim_text_inside_duration);
        m4.setStartDelay(long_time_anim_text_inside_delay);
        m4.setInterpolator(new LinearInterpolator());
        m4.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.constraing_num1231q321.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.constraing_num1231q321.requestLayout();
        });
        m4.start();
    }

    private void shareonClick(ViewHolder viewHolder_normal) {
        viewHolder_normal.share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_work();


            }
        });
    }

    private void share_work() {
        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                R.mipmap.home);
        String bitmapPath = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), icon, "uClimb", null);
        Uri bitmapUri = Uri.parse(bitmapPath);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
        intent.putExtra(Intent.EXTRA_TEXT, "Die perfekte App für Kletterer!\n" +
                "\n" +
                mContext.getString(R.string.uClimb_slogan));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Join uClimb");
        mContext.startActivity(Intent.createChooser(intent, "uClimb"));
    }

    private void likedonCLick(int i, ViewHolder viewHolder_normal) {
        int finalI = i;
        viewHolder_normal.like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_posts_presenter home_posts_presenter = new home_posts_presenter(Adapter_home.this);
                Boolean boolean_status =home_posts_presenter.liked(array_post_id.get(finalI),array_user_id.get(finalI));//TODO presenter like method
                if (boolean_status== false){
                    Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                else{
                    viewHolder_normal.like_btn.setImageDrawable(mContext.getDrawable(R.mipmap.boulderspot_logo));//TODO change to like_btn_full


                }

            }
        });
    }

    private void observe_if_view_was_rendered(ViewHolder viewHolder_normal) {
        ViewTreeObserver viewTreeObserver = viewHolder_normal.home_custom.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (viewHolder_normal.home_custom.getMeasuredWidth() > 0) {
                    viewHolder_normal.home_custom.getViewTreeObserver().removeOnPreDrawListener(this);
                    height_anim2 = viewHolder_normal.home_custom.getMeasuredHeight();

                }
                return true;
            }
        });
    }

    private void onCLick_text_inside_action(ViewHolder viewHolder_normal) {
        if (!anim_boolean_status){
            if (grow_num_layout <= 2) {
                animation_layout_and_text(viewHolder_normal);


            }


        }
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
    private void animate_height_change_home_custom_comment(ViewHolder viewHolder_normal,int k){
        ValueAnimator anim2 = ValueAnimator.ofInt(height_anim2, height_anim2+k);
        height_anim2 = height_anim2+k;
        anim2.setStartDelay(long_time_anim_layout_delay);

        anim2.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            LayoutParams layoutParams = viewHolder_normal.home_custom.getLayoutParams();
            layoutParams.height = val;
            viewHolder_normal.home_custom.setLayoutParams(layoutParams);
        });
        anim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                anim_boolean_status = false;
            }
        });
        anim2.setDuration(long_time_anim_layout_duration);
        anim2.start();

    }
    private void training_txt_action() {
        Training_list_fragment mFragment = new Training_list_fragment();

        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container_fragment, mFragment).commit();
    }

    private void animation_layout_and_text(ViewHolder viewHolder_normal) {



        if (!anim_boolean_status) {
            anim_boolean_status = true;
            grow_num_layout++;
            all_animations_for_custom_home(viewHolder_normal);


        }
    }

    private void all_animations_for_custom_home(ViewHolder viewHolder_normal) {
        animate_weight_change_text_inside_btn(viewHolder_normal);
        animate_height_change_home(viewHolder_normal);

        animate_weight_change_comment_txt(viewHolder_normal);

        animate_weight_change_tools_layout(viewHolder_normal);

        animate_weight_change_abc(viewHolder_normal);
    }

    private void animate_weight_change_text_inside_btn(ViewHolder viewHolder_normal) {
        ValueAnimator m1 = ValueAnimator.ofFloat((float)weight_text_inside, (float)weight_text_inside-(float)0.05); //fromWeight, toWeight
        weight_text_inside = weight_text_inside-0.05;
        m1.setDuration(long_time_anim_text_inside_duration);
        m1.setStartDelay(long_time_anim_text_inside_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.text_inside.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.text_inside.requestLayout();
        });
        m1.start();
        //btnanimation
        ValueAnimator m0 = ValueAnimator.ofFloat((float)weight_btn, (float)weight_btn+(float)0.04); //fromWeight, toWeight
        weight_btn = weight_btn+0.04;
        m0.setDuration(long_time_anim_text_inside_duration);
        m0.setStartDelay(long_time_anim_text_inside_delay);
        m0.setInterpolator(new LinearInterpolator());
        m0.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.comment_btn.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.comment_btn.requestLayout();
        });
        m0.start();
    }

    private void animate_height_change_home(ViewHolder viewHolder_normal) {
        ValueAnimator anim2 = ValueAnimator.ofInt(height_anim2, height_anim2+700);
        height_anim2 = height_anim2 +700;
        anim2.setStartDelay(long_time_anim_layout_delay);

        anim2.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            LayoutParams layoutParams = viewHolder_normal.home_custom.getLayoutParams();
            layoutParams.height = val;
            viewHolder_normal.home_custom.setLayoutParams(layoutParams);
        });
        anim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                anim_boolean_status = false;
            }
        });
        anim2.setDuration(long_time_anim_layout_duration);
        anim2.start();
    }

    private void animate_weight_change_comment_txt(ViewHolder viewHolder_normal) {
        ValueAnimator m1 = ValueAnimator.ofFloat((float)i_anim, (float)i_anim+(float)0.5); //fromWeight, toWeight
        i_anim = i_anim+0.5;
        m1.setDuration(long_time_anim_comment_duration);
        m1.setStartDelay(long_time_anim_comment_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.comments_txt.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.comments_txt.requestLayout();
        });
        m1.start();
    }

    private void animate_weight_change_tools_layout(ViewHolder viewHolder_normal) {
        //viewHolder_normal.comments_txt.setTextSize(text_size_comment_txt);
        ValueAnimator m1 = ValueAnimator.ofFloat((float)tools_weight, (float)tools_weight+(float)0.5); //fromWeight, toWeight
        tools_weight = tools_weight+0.5;
        m1.setDuration(long_time_anim_tools_duration);
        m1.setStartDelay(long_time_anim_tools_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.tools_btn_layout.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.tools_btn_layout.requestLayout();
        });
        m1.start();
    }

    private void animate_weight_change_abc(ViewHolder viewHolder_normal) {
        ValueAnimator m1 = ValueAnimator.ofFloat((float)abc_weight, (float)abc_weight+(float)0.04); //fromWeight, toWeight
        abc_weight = abc_weight+0.04;
        m1.setDuration(long_time_anim_abc_duration);
        m1.setStartDelay(long_time_anim_abc_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.abc.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.abc.requestLayout();
        });
        m1.start();
    }



    private void setWidgets(@NonNull ViewHolder viewHolder, int i) {
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




    private void pause_play_video(@NonNull final ViewHolder viewHolder) {
        viewHolder.video_view.setOnClickListener(v -> {

            if (clicked == 0) {
                viewHolder.video_view.start();
                clicked = 1;
            } else {
                viewHolder.video_view.pause();
                clicked = 0;


            }


        });
    }


}








