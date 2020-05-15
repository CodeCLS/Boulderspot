package app.playstore.uClimb.Adapters.Home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Post.custom_post_page;
import app.playstore.uClimb.Fragments.Training_list_fragment;
import app.playstore.uClimb.Fragments.video_upload_fragment;
import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Home.Presenter_Home_Posts;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Adapter_Home extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private  ArrayList<String> array_uid;
    //private ArrayList<String> array_time;
    private ArrayList<String> array_name;
    private ArrayList<String> array_source_img;
    private ArrayList<String> array_source;
    private ArrayList<String> array_info;
    //private ArrayList<String> array_place;
    //private ArrayList<String> array_user_id;
    private ArrayList<String> array_post_id;
    private ArrayList<String> array_type;
    //private ArrayList<String> array_likes;
    private static final String TAG = "adapter_home";
    private Presenter_Home_Posts presenter_home_posts;
    private Presenter_Login login_presenter;






    private Context mContext;

    public Adapter_Home() {
    }

    @Override
    public int getItemViewType(final int position) {
        int i;

        Log.d(TAG, "position" + position);
        if (position == 0) {
            i = 0;
        } else {

            i = 1;
        }


        return i;
    }





    public Adapter_Home(ArrayList<String> array_name, ArrayList<String> array_source_img, ArrayList<String> array_source, ArrayList<String> array_info, ArrayList<String> array_post_id, ArrayList<String> array_type,ArrayList<String> array_uid) {
        //this.array_time = array_time;
        this.array_name = array_name;
        this.array_source_img = array_source_img;
        this.array_source = array_source;
        this.array_info = array_info;
        this.array_uid = array_uid;
        this.array_info = array_info;


        this.array_post_id = array_post_id;
        this.array_type = array_type;

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;

        mContext = viewGroup.getContext();
        this.presenter_home_posts = new Presenter_Home_Posts(mContext);//TODO might return null not sure
        this.login_presenter = new Presenter_Login();
        View view;
        if (i == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.training_rec_item, viewGroup, false);
            viewHolder = new ViewHolder_training(view);

        }
        if (i == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.main_custom_home, viewGroup, false);
            viewHolder = new ViewHolder(view);

        }
        assert viewHolder != null;
        return viewHolder;


    }


    @SuppressLint("Assert")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
         i = i-1;
        if (viewHolder.getItemViewType() == 0) {
            ViewHolder_training viewHolder_training = (ViewHolder_training) viewHolder;
            training_and_post_onClick(viewHolder_training);

        }
        if (viewHolder.getItemViewType() == 1) {

            assert viewHolder instanceof ViewHolder;
            ViewHolder viewHolder1 = (ViewHolder) viewHolder;
            int finalI = i;
            viewHolder1.custom_home_posts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    custom_post_page custom_post_page = new custom_post_page();
                    Bundle bundle = new Bundle();
                    Log.d(TAG,"user_id_home" + array_info);
                    bundle.putString("PostID",array_post_id.get(finalI));
                    bundle.putString("Type",array_type.get(finalI));
                    bundle.putString("Source",array_source.get(finalI));
                    bundle.putString("UserID",array_uid.get(finalI));


                    custom_post_page.setArguments(bundle);
                    FragmentActivity fragmentActivity;
                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,custom_post_page).addToBackStack("Home_Adapter").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction.commit();

                }
            });
            setWidgets(viewHolder1, i);


        }

    }

    @Override
    public int getItemCount() {
        return array_source.size()+1;
    }
   // private HttpProxyCacheServer proxy;
//
   // public  HttpProxyCacheServer getProxy(Context context) {
   //     return proxy == null ? (proxy = newProxy()) : proxy;
   // }

   // private HttpProxyCacheServer newProxy() {
   //     return new HttpProxyCacheServer(mContext);
   // }

    private void setWidgets(ViewHolder viewHolder, int i) {
        general_widget_work(viewHolder, i);

        if (array_type.get(i).equals("Video")){
            Presenter_Home_Posts.isLiked(array_post_id.get(i), login_presenter.getUID(mContext),viewHolder.like_btn);


            liked_onClick(i,viewHolder.like_btn);

            share_onClick(viewHolder.share_btn,i);
            Video_visibility(viewHolder);
            Uri uri = Uri.parse(array_source.get(i));
            video_work(viewHolder, uri);




        }
        else{
            Presenter_Home_Posts.isLiked(array_post_id.get(i), login_presenter.getUID(mContext),viewHolder.like_btn);

            liked_onClick(i,viewHolder.like_btn);
            share_onClick(viewHolder.share_btn,i);

            img_visibility(viewHolder);
            img_work(viewHolder, i);
            liked_onClick(i,viewHolder.like_btn);





        }






    }

    private void general_widget_work(ViewHolder viewHolder, int i) {
        viewHolder.video_view.setBackgroundResource(android.R.color.transparent);

        viewHolder.video_view.setDrawingCacheEnabled(true);
        viewHolder.name_txt.setText(array_name.get(i));

        viewHolder.info_txt.setText(array_info.get(i));
        Picasso.get().load(array_source_img.get(i)).fit().centerCrop().into(viewHolder.IMG_img_profile_pic);
    }

    private void img_work(ViewHolder viewHolder, int i) {
        Picasso.get().load(array_source.get(i)).fit().centerCrop().into(viewHolder.img_view, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void img_visibility(ViewHolder viewHolder) {
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.video_view.setVisibility(View.GONE);
        viewHolder.img_view.setVisibility(View.VISIBLE);
    }

    private void Video_visibility(ViewHolder viewHolder) {
        viewHolder.img_view.setVisibility(View.GONE);
        viewHolder.video_view.setVisibility(View.VISIBLE);
    }

    private void video_work(ViewHolder viewHolder, Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "uClimb"));
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(uri);
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(mContext).build();
        player.prepare(videoSource);
        viewHolder.video_view.setPlayer(player);
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
                viewHolder.progressBar.setVisibility(View.VISIBLE);


            }

            @Override
            public void onLoadCompleted(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                viewHolder.progressBar.setVisibility(View.GONE);

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




    public void isLikedAction(Boolean return_status, ImageView like_btn) {
        if (return_status){
            Log.d(TAG,"liekd123");
            like_btn.setImageResource(R.mipmap.like_active);
        }
        else{
            like_btn.setImageResource(R.mipmap.like_passive);



        }

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout abc;
        TextView name_txt;
        ImageView img_view;
        de.hdodenhof.circleimageview.CircleImageView IMG_img_profile_pic;
        TextView info_txt;
        SimpleExoPlayerView video_view;
        LinearLayout text_inside;
        ConstraintLayout home_custom;
        ImageView like_btn;
        ImageView share_btn;
        LinearLayout tools_btn_layout;
        EditText comment_edit;
        Button comment_btn;
        LinearLayout constraint_num1231q321;
        ProgressBar progressBar;
        Button custom_home_posts;



        ViewHolder(View view) {
            super(view);
            custom_home_posts = view.findViewById(R.id.btn_more_posts_home);
            constraint_num1231q321 = view.findViewById(R.id.constraing_num1231q321);
            comment_btn = view.findViewById(R.id.btn_comment_home_custom);
            comment_edit = view.findViewById(R.id.edit_comment_home_custom);
            abc = view.findViewById(R.id.abc);
            progressBar = view.findViewById(R.id.progress_custom_home);
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

    static class ViewHolder_training extends RecyclerView.ViewHolder {

        FrameLayout post_success_frame;
        FrameLayout frameLayout;


        ViewHolder_training(View view) {
            super(view);
            frameLayout = view.findViewById(R.id.rec_item_btn_view_training);
            post_success_frame = view.findViewById(R.id.rec_item_btn_view);


        }
    }


    //All methods















    private void share_onClick(ImageView share_btn,int i) {
      share_btn.setOnClickListener(v -> {
          presenter_home_posts.share_link(mContext,array_post_id.get(i));

      });

    }



                //.setLink(Uri.parse("post_link/"+array_post_id.get(position)))
                //.setDomainUriPrefix("https://boulderspot.page.link")



       // dynamicLinkUri = dynamicLink.getUri();



    private void liked_onClick(int i, ImageView like_btn) {
        like_btn.setOnClickListener(v -> {

            String id = login_presenter.getUID(mContext);
            presenter_home_posts.like(id,array_post_id.get(i),like_btn,mContext);



            });
    }


    private void training_and_post_onClick(@NonNull ViewHolder_training viewHolder) {
        viewHolder.frameLayout.setOnClickListener(v -> training_txt_action());
        viewHolder.post_success_frame.setOnClickListener(v -> {
            video_upload_fragment mFragment = new video_upload_fragment();


            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Fragment_add_post").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container_fragment,mFragment).commit();




        });
    }

    private void training_txt_action() {
        Training_list_fragment mFragment = new Training_list_fragment();

        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container_fragment, mFragment).commit();
    }












}








