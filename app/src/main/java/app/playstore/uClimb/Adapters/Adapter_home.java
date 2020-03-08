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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Training_list_fragment;
import app.playstore.uClimb.R;

public class Adapter_home extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    private double l = (float)3;
    private double i_anim = (float)1.5;
    private double q = (float)1;

    private double w = (float)1.2;
    public static final int layout_two = 2;



    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("Posts");

    private Context mContext;
    private boolean anim_boolean_status_source = false;
    private int grow_num_layout = 0;
    private float text_size_comment_txt = 13;
    private int height_anim2;
    private long long_time_anim_comment_duration = 1000;
    private long long_time_anim_comment_delay = 0;
    private long long_time_anim_tools_duration = 1000;
    private long long_time_anim_tools_delay = 0;
    private long long_time_anim_text_inside_duration = 1000;
    private long long_time_anim_text_inside_delay = 0;
    private long long_time_anim_abc_duration = 1000;
    private long long_time_anim_abc_delay = 0;
    private long long_time_anim_layout_duration = 1000;
    private long long_time_anim_layout_delay = 500;

    @Override
    public int getItemViewType(final int position) {
        int i = 0;


        if (position==0) {
            Log.d("Adapter_home", "POSITION0: " + position);
            i = 0;
        }
        if (position==1) {
            Log.d("Adapter_home", "POSITION1: " + position);

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
        mContext = viewGroup.getContext();
        mContext = viewGroup.getContext();
        mAuth = FirebaseAuth.getInstance();

        View view = null;
        RecyclerView.ViewHolder viewHolder = null;


        if (i == 0) {
            Log.d("adapter_search", " viewholder_video");
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.training_rec_item, viewGroup, false);
            viewHolder = new ViewHolder_training(view);


        }
        if (i == 1) {
            Log.d("adapter_search", " viewholder_img");

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_home, viewGroup, false);
            viewHolder = new ViewHolder(view);


        }
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        i = i-1;
        Log.d("adapter_home", "context_home" + mContext);
        if (viewHolder.getItemViewType() == 0) {
            Adapter_home.ViewHolder_training viewHolder_training = (ViewHolder_training) viewHolder;
            viewHolder_training.start_training_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Training_list_fragment mFragment = new Training_list_fragment();
                    Log.d("adapter_home", "context_home2" + mContext);

                    FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, mFragment).commit();

                }
            });
            viewHolder_training.post_success_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                }
            });

        }


        if (viewHolder.getItemViewType() == 1) {
            Adapter_home.ViewHolder viewHolder_normal = (ViewHolder) viewHolder;
            ViewTreeObserver viewTreeObserver = viewHolder_normal.home_custom.getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    if (viewHolder_normal.home_custom.getMeasuredWidth() > 0) {
                        viewHolder_normal.home_custom.getViewTreeObserver().removeOnPreDrawListener(this);
                        height_anim2 = viewHolder_normal.home_custom.getMeasuredHeight();

                        //Do something with width and height here!
                    }
                    return true; // Continue with the draw pass, as not to stop it
                }
            });
            Log.d(TAG,"anim2first" + height_anim2 + "measure" + viewHolder_normal.home_custom.getY());

            viewHolder_normal.video_view.setOnClickListener(v -> {
                if (!anim_boolean_status_source){
                    if (grow_num_layout<=5) {
                        grow_num_layout++;

                }


                }

            });



            viewHolder_normal.text_inside.setOnClickListener(v -> {
                //viewHolder_normal.text_inside.animate().scaleY(2);
            });
            height_anim2 = viewHolder_normal.home_custom.getMeasuredHeight();

            viewHolder_normal.text_inside.setOnClickListener(v -> {
                if (!anim_boolean_status){
                    if (grow_num_layout <= 10) {
                        animation_layout_and_text(viewHolder_normal);


                    }


                }
            });


            //liking_event(viewHolder_normal, i);
            //viewHolder_normal.liked.setText(likes.get(i));
            setWidgets(viewHolder_normal, i);
            pause_play_video(viewHolder_normal);

        }
    }

    private void animation_layout_and_text(ViewHolder viewHolder_normal) {



        if (!anim_boolean_status) {
            anim_boolean_status = true;
            grow_num_layout++;
            all_animations_for_custom_home(viewHolder_normal);


        }
    }

    private void all_animations_for_custom_home(ViewHolder viewHolder_normal) {
        animate_weight_change_text_inside(viewHolder_normal);
        animate_height_change_home(viewHolder_normal);
        //viewHolder_normal.abc.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, (float) w));

        //text_size_comment_txt = text_size_comment_txt - 10;
        animate_weight_change_comment_txt(viewHolder_normal);

        animate_weight_change_tools_layout(viewHolder_normal);
        //viewHolder_normal.tools_btn_layout.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
        //LayoutParams.MATCH_PARENT, (float) i));

        animate_weight_change_abc(viewHolder_normal);
    }

    private void animate_weight_change_text_inside(ViewHolder viewHolder_normal) {
        ValueAnimator m1 = ValueAnimator.ofFloat((float)q, (float)q-(float)0.05); //fromWeight, toWeight
        q = q-0.05;
        m1.setDuration(long_time_anim_text_inside_duration);
        m1.setStartDelay(long_time_anim_text_inside_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.text_inside.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.text_inside.requestLayout();
        });
        m1.start();
    }

    private void animate_height_change_home(ViewHolder viewHolder_normal) {
        Log.d(TAG,"anim2" + height_anim2);
        ValueAnimator anim2 = ValueAnimator.ofInt(height_anim2, height_anim2+500);
        height_anim2 = height_anim2 +500;
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
        ValueAnimator m1 = ValueAnimator.ofFloat((float)l, (float)l+(float)0.5); //fromWeight, toWeight
        l = l+0.5;
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
        ValueAnimator m1 = ValueAnimator.ofFloat((float)w, (float)w+(float)0.04); //fromWeight, toWeight
        w = w+0.04;
        m1.setDuration(long_time_anim_abc_duration);
        m1.setStartDelay(long_time_anim_abc_delay);
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(animation -> {
            ((LinearLayout.LayoutParams) viewHolder_normal.abc.getLayoutParams()).weight = (float) animation.getAnimatedValue();
            viewHolder_normal.abc.requestLayout();
        });
        m1.start();
    }

    // Read from the database


    //
    //
    //
    //
    //
    //


    private void setWidgets(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.info_txt.setText(array_info.get(i));
        Picasso.get().load(array_source_img.get(i)).fit().centerCrop().into(viewHolder.IMG_img_profile_pic);
        viewHolder.video_view.setVideoPath(array_source.get(i));
        viewHolder.video_view.seekTo(2);
        viewHolder.name_txt.setText(array_name.get(i));
    }




    private void pause_play_video(@NonNull final ViewHolder viewHolder) {
        viewHolder.video_view.setOnClickListener(v -> {

            if (clicked == 0) {
                Log.d(TAG, "clicked_first");
                viewHolder.video_view.start();
                clicked = 1;
            } else {
                viewHolder.video_view.pause();
                clicked = 0;
                Log.d(TAG, "clicked_second");


            }


        });
    }

    @Override
    public int getItemCount() {
        return array_source.size() + 1;
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

        //TextView liked;
        //  ImageView full_screen;


        ViewHolder(View view) {
            super(view);
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
}








