package app.playstore.uClimb.Main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Community_Fragment;
import app.playstore.uClimb.Fragments.Profile.Profile_Fragment;
import app.playstore.uClimb.Notifaction.Base_Internet;
import app.playstore.uClimb.DB.FeedReaderDBHelper;
import app.playstore.uClimb.Fragments.Search.Search_Fragment;
import app.playstore.uClimb.Fragments.Home.Home_Fragment;
import app.playstore.uClimb.R;

public class MainActivity extends Base_Internet  {

    private static final String TAG = "Main";
    private static final String COLUMN_NAME_TITLE = "Caleb";
    private static final String COLUMN_NAME_SUBTITLE = "Info";
    private FirebaseAuth mAuth;
    private FloatingActionButton plus_img;
    private ArrayList IMG   = new ArrayList();
    private ArrayList source= new ArrayList();
    private ArrayList info_user  = new ArrayList();
    private ArrayList name_user  = new ArrayList();
    private ArrayList place_user = new ArrayList();
    private ArrayList<String> post = new ArrayList<>();
    private ArrayList<String> IMG_URL = new ArrayList<>();
    private ArrayList<String> grade = new ArrayList<>();
    private ArrayList<String> place = new ArrayList<>();
    private ArrayList<String> seen_by = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> info = new ArrayList<>();

    private ArrayList<String> video_url = new ArrayList<>();
    private ArrayList<String> ID_User = new ArrayList<>();
    private ArrayList<String> likes = new ArrayList<>();

    private ArrayList following = new ArrayList();

//    private SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);



    private FeedReaderDBHelper dbHelper = new FeedReaderDBHelper(MainActivity.this);





    private static final String CHANNEL_ID = "boulder";

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
       // if (savedInstanceState == null) {
       //     getSupportFragmentManager().beginTransaction()
       //             .add(R.id.container_fragment, new Home_Fragment()).commit();
       // }
// Gets the data repository in write mode();
        //            String mAuth = sharedPreferences.getString("mAuth", null);

        initAuth();
        initViews();
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
        bottomBar.setTabChangedListener((selectedTab, selectedIndex, oldIndex) -> {
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
                Community_Fragment mFragment_Community = new Community_Fragment();
                FragmentManager fragmentManager_community = getSupportFragmentManager();
                fragmentManager_community.beginTransaction()
                        .replace(R.id.container_fragment, mFragment_Community).commit();

            }

            if (selectedIndex == 3) {
                Profile_Fragment mFragment_profile = new Profile_Fragment();
                FragmentManager fragmentManager_profile = getSupportFragmentManager();
                fragmentManager_profile.beginTransaction()
                        .replace(R.id.container_fragment, mFragment_profile).commit();

            }

                 //           .replace(R.id.container_fragment, mFragment).commit();
                 //   setAlpha(1);
//
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



}