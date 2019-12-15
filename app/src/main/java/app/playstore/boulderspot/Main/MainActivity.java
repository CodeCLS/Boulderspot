package app.playstore.boulderspot.Main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import app.playstore.boulderspot.DB.FeedReaderDBHelper;
import app.playstore.boulderspot.Fragments.Search_Fragment;
import app.playstore.boulderspot.Fragments.Home_Fragment;
import app.playstore.boulderspot.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    private static final String COLUMN_NAME_TITLE = "Caleb";
    private static final String COLUMN_NAME_SUBTITLE = "Info";
    private FirebaseAuth mAuth;
    private FloatingActionButton plus_img;
    private View includeView;
    private ImageView img_home  ;
    private ImageView img_event ;
    private ImageView img_course ;
    private ImageView img_location ;
    private View line_home;
    private View line_event;
    private View line_course;
    private View line_location;
    private FeedReaderDBHelper dbHelper = new FeedReaderDBHelper(MainActivity.this);





    private static final String CHANNEL_ID = "boulder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
       // if (savedInstanceState == null) {
       //     getSupportFragmentManager().beginTransaction()
       //             .add(R.id.container_fragment, new Home_Fragment()).commit();
       // }
// Gets the data repository in write mode();



        initAuth();
        initViews();
        setOnclick();
        setHomeFragment();
        sendNotification();

        // Gets an instance of the NotificationManager service//

    }




// Create a new map of values, where column names are the keys



    private void sendNotification() {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.boulderspot_logo)
                .setContentTitle("Test")
                .setContentText("test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(101, builder.build());
    }

    private void setHomeFragment() {
        Home_Fragment mFragment = new Home_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, mFragment).commit();
    }

    private void initAuth() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {




         includeView = findViewById(R.id.include_nav_home);


         img_home = includeView.findViewById(R.id.img_home);
         img_event = includeView.findViewById(R.id.img_event);
         img_course = includeView.findViewById(R.id.img_training);
         img_location = includeView.findViewById(R.id.img_location);

         line_course = includeView.findViewById(R.id.line_img_training);
         line_home = includeView.findViewById(R.id.line_img_home);
         line_location = includeView.findViewById(R.id.line_img_location);
         line_event = includeView.findViewById(R.id.line_img_event);




    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "boulder";
            String description = "spot";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void setOnclick() {
        img_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home_Fragment mFragment = new Home_Fragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, mFragment).commit();
                setAlpha(3);


            }
        });
        img_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_Fragment mFragment = new Search_Fragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, mFragment).commit();
                setAlpha(2);

            }
        });
        img_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home_Fragment mFragment = new Home_Fragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, mFragment).commit();
                setAlpha(4);


            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home_Fragment mFragment = new Home_Fragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, mFragment).commit();
                setAlpha(1);

            }
        });
    }

    private void reveal_line(int i) {
        switch(i){
            case 1:
                line_location.setVisibility(View.INVISIBLE);
                line_event.setVisibility(View.INVISIBLE);
                line_course.setVisibility(View.INVISIBLE);
                line_home.setVisibility(View.VISIBLE);
            case 2:
                line_location.setVisibility(View.INVISIBLE);
                line_event.setVisibility(View.VISIBLE);
                line_course.setVisibility(View.INVISIBLE);
                line_home.setVisibility(View.INVISIBLE);
            case 3:
                line_location.setVisibility(View.INVISIBLE);
                line_event.setVisibility(View.INVISIBLE);
                line_course.setVisibility(View.VISIBLE);
                line_home.setVisibility(View.INVISIBLE);
            case 4:
                line_location.setVisibility(View.VISIBLE);
                line_event.setVisibility(View.INVISIBLE);
                line_course.setVisibility(View.INVISIBLE);
                line_home.setVisibility(View.INVISIBLE);


        }

    }
    private void setAlpha(int i){
        switch(i){
            case 1:
                Log.d(TAG,"case: " + i);
                img_home.setAlpha(1f);
                img_event.setAlpha(1/2f);
                img_course.setAlpha(1/2f);
                img_location.setAlpha(1/2f);

            case 2:
                Log.d(TAG,"case: " + i);


                img_home.setAlpha(1/2f);
                img_event.setAlpha(1f);
                img_course.setAlpha(0.5f);
                img_location.setAlpha(0.5f);
            case 3:
                Log.d(TAG,"case: " + i);

                img_home.setAlpha(0.5f);
                img_event.setAlpha(0.5f);
                img_course.setAlpha(1.0f);
                img_location.setAlpha(0.5f);
            case 4:
                Log.d(TAG,"case: " + i);

                img_home.setAlpha(0.5f);
                img_event.setAlpha(0.5f);
                img_course.setAlpha(0.5f);
                img_location.setAlpha(1.0f);


        }


    }


}