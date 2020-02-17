package app.playstore.boulderspot.Main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.flarebit.flarebarlib.TabEventObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import app.playstore.boulderspot.Fragments.Community_Fragment;
import app.playstore.boulderspot.Notifaction.Base_Internet;
import app.playstore.boulderspot.DB.FeedReaderDBHelper;
import app.playstore.boulderspot.Fragments.Search.Search_Fragment;
import app.playstore.boulderspot.Fragments.Home.Home_Fragment;
import app.playstore.boulderspot.R;

public class MainActivity extends Base_Internet {

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

        final FlareBar bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setBarBackgroundColor(Color.parseColor("#FFFFFF"));
        ArrayList<Flaretab> tabs = new ArrayList<>();
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.inboxb),"Home","#FFECB3"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.searchb),"Search","#80DEEA"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.phoneb),"Community","#B39DDB"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.avatarb),"Training","#EF9A9A"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.settingsb),"Settings","#B2DFDB"));

        bottomBar.setTabList(tabs);
        bottomBar.attachTabs(this);
        bottomBar.setTabChangedListener(new TabEventObject.TabChangedListener() {
            @Override
            public void onTabChanged(LinearLayout selectedTab, int selectedIndex, int oldIndex) {
                //tabIndex starts from 0 (zero). Example : 4 tabs = last Index - 3
               if (selectedIndex == 0) {
                   Home_Fragment mFragment_Home= new Home_Fragment();
                   FragmentManager fragmentManager_search = getSupportFragmentManager();
                   fragmentManager_search.beginTransaction()
                           .replace(R.id.container_fragment, mFragment_Home).commit();

               }

                if (selectedIndex == 1) {
                    Search_Fragment mFragment_Search = new Search_Fragment();
                    FragmentManager fragmentManager_search = getSupportFragmentManager();
                    fragmentManager_search.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_Search).commit();

                }

                if (selectedIndex == 2) {
                    Community_Fragment mFragment_Search = new Community_Fragment();
                    FragmentManager fragmentManager_search = getSupportFragmentManager();
                    fragmentManager_search.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_Search).commit();

                }

                if (selectedIndex == 3) {
                    Search_Fragment mFragment_Search = new Search_Fragment();
                    FragmentManager fragmentManager_search = getSupportFragmentManager();
                    fragmentManager_search.beginTransaction()
                            .replace(R.id.container_fragment, mFragment_Search).commit();

                }

                     //           .replace(R.id.container_fragment, mFragment).commit();
                     //   setAlpha(1);
//
                }


        });




//
       //  img_home = includeView.findViewById(R.id.img_home);
       //  img_event = includeView.findViewById(R.id.img_event);
       //  img_course = includeView.findViewById(R.id.img_training);
       //  img_location = includeView.findViewById(R.id.img_location);
//
       //  line_course = includeView.findViewById(R.id.line_img_training);
       //  line_home = includeView.findViewById(R.id.line_img_home);
       //  line_location = includeView.findViewById(R.id.line_img_location);
       //  line_event = includeView.findViewById(R.id.line_img_event);




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
     //  img_course.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View v) {


     //          Home_Fragment mFragment = new Home_Fragment();
     //          FragmentManager fragmentManager = getSupportFragmentManager();
     //          fragmentManager.beginTransaction()
     //                  .replace(R.id.container_fragment, mFragment).commit();
     //          setAlpha(3);


     //      }
     //  });
     //  img_event.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View v) {
     //          Search_Fragment mFragment = new Search_Fragment();
     //          FragmentManager fragmentManager = getSupportFragmentManager();
     //          fragmentManager.beginTransaction()
     //                  .replace(R.id.container_fragment, mFragment).commit();
     //          setAlpha(2);

     //      }
     //  });
     //  img_location.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View v) {
     //          Home_Fragment mFragment = new Home_Fragment();
     //          FragmentManager fragmentManager = getSupportFragmentManager();
     //          fragmentManager.beginTransaction()
     //                  .replace(R.id.container_fragment, mFragment).commit();
     //          setAlpha(4);


     //      }
     //  });

     //  img_home.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View v) {
     //          Home_Fragment mFragment = new Home_Fragment();
     //          FragmentManager fragmentManager = getSupportFragmentManager();
     //          fragmentManager.beginTransaction()
     //                  .replace(R.id.container_fragment, mFragment).commit();
     //          setAlpha(1);

     //      }
     //  });
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