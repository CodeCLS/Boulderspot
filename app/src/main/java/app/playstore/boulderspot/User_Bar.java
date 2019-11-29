package app.playstore.boulderspot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class User_Bar extends AppCompatActivity {
    private String Username;
    private String follower;
    private String friends;
    private String grade;
    private ImageView follower_img;
    private ImageView friends_img;
    private ImageView notifications_img;
    private ImageView grade_img;
    private ImageView settings_img;
    private TextView Username_txt;
    private TextView follower_txt;
    private TextView friends_txt;
    private TextView grade_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_include);
        init_views();
        set_views();







    }

    private void set_views() {
        Username_txt.setText(Username);
        follower_txt.setText(follower);
        friends_txt.setText(friends);
        grade_txt.setText(grade);
        follower_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        friends_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        notifications_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        grade_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        settings_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void init_views() {
       // TextView Username_txt      = findViewById(R.id.txt_username);
       // TextView follower_txt      = findViewById(R.id.txt_follower);
       // TextView friends_txt       = findViewById(R.id.txt_friends);
       // TextView grade_txt         = findViewById(R.id.txt_grade);
       // ImageView follower_img     = findViewById(R.id.img_follower);
       // ImageView friends_img      = findViewById(R.id.img_friends);
       // ImageView notifications_img= findViewById(R.id.img_notifications);
       // ImageView grade_img        = findViewById(R.id.img_grade);
       // ImageView settings_img     = findViewById(R.id.img_settings);

    }
}
