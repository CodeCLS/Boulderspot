package app.playstore.uClimb.Fragments.Post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import app.playstore.uClimb.R;

public class custom_post_page extends Fragment {
    private VideoView videoView;
    private ImageView img_post;
    private ImageView like_img;


    String type;
    String source;
    private int video_state = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_post_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getcredentialsfragment();

        initViews(view);


        checkSource(type,view);

        img_post.setOnClickListener(new DoubleClickListener() {

            @Override
            public void onSingleClick(View v) {
                //do nothing

            }

            @Override
            public void onDoubleClick(View v) {
                Toast.makeText(getContext(), "Liked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getcredentialsfragment() {
        Bundle arguments = getArguments();
        source= arguments.getString("Source");
        type= arguments.getString("Type");

    }

    private void checkSource(String type,View view) {
        if (type == "video"){
            video_setup();
            video_player();


        }
        if (type == "img"){
            img_post.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            Picasso.get().load(source).fit().centerCrop().into(img_post);




        }
    }

    private void video_player() {
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video_state == 0){
                    videoView.start();
                    video_state = 1;
                }
                else{
                    videoView.pause();
                    video_state = 0;

                }
            }
        });

    }

    private void video_setup() {
        videoView.setVisibility(View.VISIBLE);
        img_post.setVisibility(View.GONE);
        videoView.setVideoPath(source);
        videoView.seekTo(2);
    }

    private void initViews(@NonNull View view) {
        img_post = view.findViewById(R.id.img_post_custom);
        videoView = view.findViewById(R.id.video_post_custom);
    }


    public abstract class DoubleClickListener implements View.OnClickListener {

        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

        long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
                onDoubleClick(v);
                lastClickTime = 0;
            } else {
                onSingleClick(v);
            }
            lastClickTime = clickTime;
        }

        public abstract void onSingleClick(View v);
        public abstract void onDoubleClick(View v);
    }
}
