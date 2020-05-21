package app.playstore.uClimb.Main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentTransaction;

import com.danikula.videocache.CacheListener;
import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.io.File;
import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Friends_fragment;
import app.playstore.uClimb.Fragments.Post.custom_post_page;
import app.playstore.uClimb.Fragments.Settings_Fragment;
import app.playstore.uClimb.Fragments.Statistics_fragment;
import app.playstore.uClimb.MVP.MVP_Home.Presenter_Home_Posts;
import app.playstore.uClimb.Notifaction.Base_Internet;
import app.playstore.uClimb.Fragments.Search.Search_Fragment;
import app.playstore.uClimb.Fragments.Home.Home_Fragment;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Profile_User.Presenter_Profile;

public class MainActivity extends Base_Internet implements CacheListener {

    private static final String TAG = "Main";
    private static final String COLUMN_NAME_TITLE = "Caleb";
    private static final String COLUMN_NAME_SUBTITLE = "Info";
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
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
    private FusedLocationProviderClient fusedLocationClient;


    private ArrayList<String> video_url = new ArrayList<>();
    private ArrayList<String> ID_User = new ArrayList<>();
    private ArrayList<String> likes = new ArrayList<>();

    private ArrayList following = new ArrayList();

//    private SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);








    private static final String CHANNEL_ID = "boulder";
    private int CHANGE_PROFILE_PIC_CODE = 210;

    public void img(ProgressBar progressBar) {
        this.progressBar = progressBar;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 210);
    }

    public void BetaMessage(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean first_time = sharedPreferences.getBoolean("First",true);
        Log.d(TAG,"First" + first_time);

        if (first_time){
            Dialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Willkommen zu uClimb")
                    .setMessage(getResources().getString(R.string.welcome_short_info) )

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            dialog.dismiss();
                            showbetadialog();

                        }
                    })

                    .show();

            SharedPreferences.Editor shared_ed = sharedPreferences.edit();
            shared_ed.putBoolean("First",false);
            shared_ed.apply();

        }
        else{
            return;
        }



        }

    private void showbetadialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.beta_message_title)
                .setMessage(getResources().getString(R.string.beta_message) )

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        dialog.dismiss();


                    }
                })

                .show();

    }
    public void FirebaseLink(Context mContext, Activity activity){
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(activity.getIntent())
                .addOnSuccessListener(activity, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            if (pendingDynamicLinkData.getLink().getQueryParameter("Post") != null){
                                deepLink = pendingDynamicLinkData.getLink();
                                String id = pendingDynamicLinkData.getLink().getQueryParameter("Posts");
                                if (id!= null){
                                    custom_post_page custom_post_page = new custom_post_page();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("uid" , id);
                                    custom_post_page.setArguments(bundle);
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,custom_post_page).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    fragmentTransaction.commit();

                                }



                            }


                        }



                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "getDynamicLink:onFailure", e);
                    }
                });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_PROFILE_PIC_CODE) {
            if (data != null&& resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            Presenter_Profile profile_presenter = new Presenter_Profile();
            profile_presenter.here_is_image(imageUri,this,progressBar);

        } }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.private_main_ui);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BetaMessage();
        FirebaseLink(this,this);



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
        tabs.add(new Flaretab(getResources().getDrawable(R.mipmap.house_ui_icon),this.getString(R.string.Home),"#FFECB3"));
        tabs.add(new Flaretab(getResources().getDrawable(R.mipmap.search_ui_icon_a),this.getString(R.string.Suche),"#80DEEA"));
        tabs.add(new Flaretab(getResources().getDrawable(R.mipmap.communityicon),this.getString(R.string.Community),"#B39DDB"));
        tabs.add(new Flaretab(getResources().getDrawable(R.mipmap.user_icon_blue),this.getString(R.string.Profil),"#EF9A9A"));
        tabs.add(new Flaretab(getResources().getDrawable(R.mipmap.settingsicon),this.getString(R.string.Einstellungen),"#B2DFDB"));

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
                Friends_fragment mFragment_Community = new Friends_fragment();
                FragmentManager fragmentManager_community = getSupportFragmentManager();
                fragmentManager_community.beginTransaction()
                        .replace(R.id.container_fragment, mFragment_Community).commit();

            }

            if (selectedIndex == 3) {
                Statistics_fragment mFragment_profile = new Statistics_fragment();
                FragmentManager fragmentManager_profile = getSupportFragmentManager();
                fragmentManager_profile.beginTransaction()
                        .replace(R.id.container_fragment, mFragment_profile).commit();

            }
            if (selectedIndex == 4) {
                Settings_Fragment mFragment_profile = new Settings_Fragment();
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
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
       //     CharSequence name = "boulder";
       //     String description = "spot";
       //     int importance = NotificationManager.IMPORTANCE_DEFAULT;
       //     NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
       //     channel.setDescription(description);
       //     // Register the channel with the system; you can't change the importance
       //     // or other notification behaviors after this
       //     NotificationManager notificationManager = getSystemService(NotificationManager.class);
       //     notificationManager.createNotificationChannel(channel);
       // }
//
    }


    @Override
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {

    }

    public void share_post() {
        //Presenter_Home_Posts presenter_home_posts = new Presenter_Home_Posts(this);
        //Uri link = presenter_home_posts.share_link(this,);
        //Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_TEXT, link.toString());
        //startActivity(Intent.createChooser(intent, "Sharing uClimb Post"));
    }
}