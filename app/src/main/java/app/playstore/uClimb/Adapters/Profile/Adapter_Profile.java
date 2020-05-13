package app.playstore.uClimb.Adapters.Profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.playstore.uClimb.Adapters.Public_Spinner_Base_Profiles;
import app.playstore.uClimb.Fragments.Profile.Profile_Fragment;
import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.MVP.MVP_Profile_User.Presenter_Profile;

public class Adapter_Profile extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Profile_Adapter";
    private String uid;
    //private String stat_uid;
    private String Age;
    private String Name;
    private String profile_img;
    private String Info;
    //private String Subscription;
    //private String grade;
    private String country;
    private ArrayList follower;
    private ArrayList  following;
    private String Height;

    private ArrayList<String> arrayList_Source = new ArrayList<>();
    private ArrayList<String> arrayList_type = new ArrayList<>();
    private ArrayList<String> arrayList_info = new ArrayList<>();
    private ArrayList<String> arrayList_place = new ArrayList<>();
    private ArrayList<String> arrayList_uid = new ArrayList<>();
    private ArrayList<String> arrayList_post_id = new ArrayList<>();
    private ArrayList<String> arrayList_time = new ArrayList<>();
    private ArrayList<String> arrayList_likes = new ArrayList<>();
    private ArrayList<String> arrayList_comments= new ArrayList<>();

    //private String account_type;
    //private String time_created;
    //private String position_lat;
    //private String position_long;
    //private String position_last_updated;
    //private Boolean position_status;
    //private ArrayList<String> uploads_post_id = new ArrayList<>();
    //private String IMG;

    private String Email;

    private ArrayList friends;
    private ArrayList<String> following_final = new ArrayList<String>();
    private ArrayList<String> follower_final = new ArrayList<String>();
    private ArrayList<String> following_name = new ArrayList<String>();
    private ArrayList<String> follower_name = new ArrayList<String>();
    private ArrayList<String> following_img = new ArrayList<String>();
    private ArrayList<String> follower_img = new ArrayList<String>();

    private Context mContext;

    private boolean bool_clicked = false;
    //private ArrayList<String> countries;


    //private ArrayList<String> source;
    //private ArrayList<String> type;
    //private ArrayList<String> post_id;
    //private ArrayList<String> info;
    //private ArrayList<String> u_id;
    //private ArrayList<String> time;
    //private ArrayList<String> place;
    //TODO uplaod data is null


    //private Adapter_Profile_User_Uploads custom_profile_adapter = new Adapter_Profile_User_Uploads(source,type,post_id,info,u_id,time,place);
    private int numbercolumns = 2;
    private ArrayList friends_final = new ArrayList<String>();
    private ArrayList friends_img = new ArrayList<String>();
    private ArrayList friends_name = new ArrayList<String>();
    private int btn_status = 0;

    //public Adapter_Profile() {
    //}

   // public Adapter_Profile(String uid, String stat_uid, String age, String name, String profile_img, String info, String subscription, String grade, String country, ArrayList follower, ArrayList following, String height, ArrayList friends, String account_type, String time_created, String position_lat, String position_long, String position_last_updated, Boolean position_status, String email) {
   //     this.uid = uid;
   //     this.stat_uid = stat_uid;
   //     Age = age;
   //     Name = name;
   //     this.profile_img = profile_img;
   //     Info = info;
   //     Subscription = subscription;
   //     this.grade = grade;
   //     this.country = country;
   //     this.follower = follower;
   //     this.following = following;
   //     Height = height;
   //     this.friends= friends;
   //     this.account_type = account_type;
   //     this.time_created = time_created;
   //     this.position_lat = position_lat;
   //     this.position_long = position_long;
   //     this.position_last_updated = position_last_updated;
   //     this.position_status = position_status;
   //     this.Email = email;
//
   // }


    public Adapter_Profile(String uid, String age, String name, String profile_img, String info, String country, ArrayList follower, ArrayList following, String height, ArrayList<String> arrayList_Source, ArrayList<String> arrayList_type, ArrayList<String> arrayList_info, ArrayList<String> arrayList_place, ArrayList<String> arrayList_uid, ArrayList<String> arrayList_post_id, ArrayList<String> arrayList_time, ArrayList<String> arrayList_likes, ArrayList<String> arrayList_comments, String email, ArrayList friends) {
        this.uid = uid;
        Age = age;
        Name = name;
        this.profile_img = profile_img;
        Info = info;
        this.country = country;
        this.follower = follower;
        this.following = following;
        Height = height;
        this.arrayList_Source = arrayList_Source;
        this.arrayList_type = arrayList_type;
        this.arrayList_info = arrayList_info;
        this.arrayList_place = arrayList_place;
        this.arrayList_uid = arrayList_uid;
        this.arrayList_post_id = arrayList_post_id;
        this.arrayList_time = arrayList_time;
        this.arrayList_likes = arrayList_likes;
        this.arrayList_comments = arrayList_comments;
        Email = email;
        this.friends = friends;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewholder = null;

        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.profile_page_user_standart_layout,parent,false);
            viewholder = new standart_profile(view);

        }
        if (viewType == 1){
            view = LayoutInflater.from(mContext).inflate(R.layout.profile_page_user_multiple_layout_a,parent,false);
            viewholder = new profile_a(view);

        }
        if (viewType == 2){
            view = LayoutInflater.from(mContext).inflate(R.layout.profile_page_user_multiple_layout_b,parent,false);
            viewholder = new profile_b(view);

        }
        if (viewType == 3){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_profile_page_user_multiple_layout_c,parent,false);
            viewholder = new standart_profile_c(view);
//
        }
        return Objects.requireNonNull(viewholder);



    }

    @SuppressLint({"SetTextI18n", "Assert"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,  int position) {
        position = position-3;

        if (holder.getItemViewType() == 0) {
            Log.d(TAG,"Position1111" + position);
            viewHolder_0_code((standart_profile) holder);


        }
        if (holder.getItemViewType() == 1) {
            Log.d(TAG,"Position2222" + position);

            viewholder_1_code((profile_a) holder);


        }
        if (holder.getItemViewType() == 2) {
            Log.d(TAG,"Position333" + position);

            viewholder_2_code((profile_b) holder);


        }
        if (holder.getItemViewType() == 3) {
            Log.d(TAG,"Position444" + position);



            standart_profile_c standart_profile_c = (standart_profile_c) holder;


            setWidgetspost(position, standart_profile_c);


        }


        }

    @SuppressLint("SetTextI18n")
    private void setWidgetspost(int position, standart_profile_c standart_profile_c) {
        int finalPosition = position;
        standart_profile_c.delete_post.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d(TAG,"data_deleted");
                    delete_Post(finalPosition);

                    return true;
                }

                private void delete_Post(int i) {
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();

                    databaseReference1.child("Posts").child(arrayList_post_id.get(i).toString()).removeValue();
                    databaseReference1.child("User").child(uid).child("Posts").child(arrayList_post_id.get(i).toString()).removeValue();
                    arrayList_post_id.remove(i);
                    arrayList_comments.remove(i);
                    arrayList_info.remove(i);
                    arrayList_likes.remove(i);
                    arrayList_place.remove(i);
                    arrayList_Source.remove(i);
                    arrayList_time.remove(i);
                    arrayList_type.remove(i);
                    arrayList_uid.remove(i);

                    notifyDataSetChanged();

                }
            });
        Log.d(TAG,"comments21" + arrayList_comments.size() + "position: " + position);
        standart_profile_c.comments.setText(arrayList_comments.get(position) + " comments");
        standart_profile_c.likes.setText(arrayList_likes.get(position) + " likes");
        standart_profile_c.time.setText(arrayList_time.get(position).toString());
        standart_profile_c.location.setText(arrayList_place.get(position).toString());
        Log.d(TAG,"type234" +arrayList_type.get(position));
        if (arrayList_type.get(position).equals("IMG")){
            standart_profile_c.exoPlayerView.setVisibility(View.GONE);
            standart_profile_c.imageView.setVisibility(View.VISIBLE);
            Log.d(TAG,"img234" + arrayList_Source.get(position));
            Picasso.get().load(arrayList_Source.get(position)).fit().centerCrop().into(standart_profile_c.imageView);

        }
        if (arrayList_type.get(position).toString().equals("Video")){
            Log.d(TAG,"video234");
            standart_profile_c.exoPlayerView.setVisibility(View.VISIBLE);
            standart_profile_c.imageView.setVisibility(View.INVISIBLE);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                    Util.getUserAgent(mContext, "uClimb"));
            MediaSource videoSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(Uri.parse(arrayList_Source.get(position).toString()));
            SimpleExoPlayer player = new SimpleExoPlayer.Builder(mContext).build();
            player.prepare(videoSource);
            standart_profile_c.exoPlayerView.setPlayer(player);
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
                    standart_profile_c.progressBar.setVisibility(View.VISIBLE);


                }

                @Override
                public void onLoadCompleted(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                    standart_profile_c.progressBar.setVisibility(View.GONE);

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
        Log.d(TAG,"nothing234");
    }


    @SuppressLint("SetTextI18n")
    private void viewHolder_0_code(@NonNull standart_profile holder) {
        holder.Imageview.setOnClickListener(v -> {
            Presenter_Profile profile_presenter = new Presenter_Profile(mContext);
            profile_presenter.setProfilePic(mContext, holder.progressBar);

        });
        Picasso.get().load(profile_img).into(holder.Imageview);
        Log.d(TAG,"profile_img4" + profile_img);
        holder.Email.setText(Email);
        holder.Name.setText(Name);
        holder.age.setText(Age + " years");
        holder.Country.setText(country);
        holder.uid.setText(uid);
        holder.info.setText(Info);
        holder.Height.setText(Height);
        set_spinner_following(holder);
        set_spinner_follower(holder);
        set_spinner_friends(holder);
        holder.Following_number.setText(following.size() + " Following");
        holder.Follower_number.setText(follower.size() + " Follower");
        holder.Friends_number.setText(friends.size()+" Friends");

// Create an ArrayAdapter using the string array and a default spinner layout
    }

    private void viewholder_1_code(@NonNull profile_a holder) {
        Log.d(TAG,"clicked_animate");

        holder.btn_change_pwd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                bool_clicked = true;
                Log.d(TAG,"clicked_animate");

                animate_pwd_change_height();
                holder.change_edit_profile.setVisibility(View.VISIBLE);
                holder.email.setVisibility(View.VISIBLE);
                holder.old_pwd.setVisibility(View.VISIBLE);
                holder.btn_change_pwd.setText("Change PWD");
                holder.btn_change_pwd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bool_clicked){


                            String pwd_new = holder.btn_change_pwd.getText().toString();
                            String pwd_old = holder.old_pwd.getText().toString();
                            String email = holder.email.getText().toString();
                            if (holder.change_edit_profile.getText().toString().length() < 6 || holder.email.getText().toString().isEmpty() || holder.old_pwd.getText().toString().length() < 6) {
                                Toast.makeText(mContext, "Bitte geben sie ein längeres Passwort ein", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(mContext, "Loading", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
                                AuthCredential credential = EmailAuthProvider
                                        .getCredential(email, pwd_old);

// Prompt the user to re-provide their sign-in credentials
                                Objects.requireNonNull(user).reauthenticate(credential)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    user.updatePassword(pwd_new).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Log.d(TAG, "Password updated");
                                                                Toast.makeText(mContext, "Passwort geupdatet", Toast.LENGTH_SHORT).show();
                                                                animate_pwd_layout_height_back();
                                                            } else {
                                                                Log.d(TAG, "Error password not updated");
                                                                Toast.makeText(mContext, "Passwort wurde nicht geupdatet", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        private void animate_pwd_layout_height_back() {
                                                            ValueAnimator anim2 = ValueAnimator.ofInt(holder.constraintLayout.getHeight(), holder.constraintLayout.getHeight()-500);
                                                            anim2.setStartDelay(200);

                                                            anim2.addUpdateListener(valueAnimator -> {
                                                                int val = (Integer) valueAnimator.getAnimatedValue();
                                                                ViewGroup.LayoutParams layoutParams = holder.constraintLayout.getLayoutParams();
                                                                layoutParams.height = val;
                                                                holder.constraintLayout.setLayoutParams(layoutParams);
                                                            });
                                                            anim2.addListener(new AnimatorListenerAdapter() {
                                                                @Override
                                                                public void onAnimationEnd(Animator animation) {
                                                                    super.onAnimationEnd(animation);
                                                                }
                                                            });
                                                            anim2.setDuration(500);
                                                            anim2.start();
                                                        }
                                                    });
                                                } else {
                                                    Log.d(TAG, "Error auth failed");
                                                    Toast.makeText(mContext, "Falsche Anmeldedaten", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }

                        }
                    }
                });


            }

            private void animate_pwd_change_height() {
                ValueAnimator anim2 = ValueAnimator.ofInt(holder.constraintLayout.getHeight(), holder.constraintLayout.getHeight()+500);
                anim2.setStartDelay(200);

                anim2.addUpdateListener(valueAnimator -> {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = holder.constraintLayout.getLayoutParams();
                    layoutParams.height = val;
                    holder.constraintLayout.setLayoutParams(layoutParams);
                });
                anim2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                anim2.setDuration(500);
                anim2.start();
            }
        });
    }

    private void viewholder_2_code(@NonNull profile_b holder) {

        holder.btn_change_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_status ==0) {

                    Log.d(TAG,"Adapter23");

                    animate_height_data_change();
                    holder.country.setVisibility(View.VISIBLE);
                    holder.age.setVisibility(View.VISIBLE);
                    holder.height.setVisibility(View.VISIBLE);
                    holder.info.setVisibility(View.VISIBLE);
                    holder.country.setVisibility(View.VISIBLE);
                    holder.name.setVisibility(View.VISIBLE);
                    holder.email.setVisibility(View.VISIBLE);
                    holder.height.setVisibility(View.VISIBLE);
                    holder.pwd_edit.setVisibility(View.VISIBLE);
                    holder.name.setText(Name);
                    holder.email.setText(Email);
                    holder.info.setText(Info);
                    holder.age.setText(Age);
                    holder.country.setText(country);
                    holder.height.setText(Height);
                    btn_status = 1;
                    holder.btn_change_data.setText("Press again to change data");
                }

                else{
                    Log.d(TAG,"Adapter24");





                    Presenter_Login login_presenter = new Presenter_Login();
                    String name =holder.name.getText().toString();
                    String email = holder.email.getText().toString();
                    String info =holder.info.getText().toString();
                    String age = holder.age.getText().toString();
                    String country = holder.country.getText().toString();
                    String height = holder.height.getText().toString();
                    String pwd = holder.pwd_edit.getText().toString();
                    if (name.length() < 10 && name.length()>1){
                        if (isEmailValid(email)){
                            if (info.length() < 40 && info.length() > 1){
                                if (Integer.parseInt(age) < 2030&& Integer.parseInt(age) > 1950){
                                    if (getCities().contains(country)){
                                        if (Integer.parseInt(height)<250 && Integer.parseInt(height) > 130){
                                            if (!pwd.isEmpty()) {
                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("");
                                                String uid = login_presenter.getUID(mContext);
                                                Log.d(TAG,"14523423");



                                                reference.child("User").child(uid).child("Name").setValue(name);
                                                reference.child("User").child(uid).child("Info").setValue(info);
                                                reference.child("User").child(uid).child("Age").setValue(age);
                                                reference.child("User").child(uid).child("Country").setValue(country);
                                                reference.child("User").child(uid).child("Height").setValue(height);
                                                Toast.makeText(mContext, "Information geändert", Toast.LENGTH_SHORT).show();
                                                change_email_auth(email, pwd);

                                                ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                                                        new Profile_Fragment()).commit();
                                            }
                                            else{
                                                Toast.makeText(mContext, "Passwort eingeben", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                        else{
                                            Toast.makeText(mContext, "Falsche Größe", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else{
                                        Toast.makeText(mContext, "Kein existierendes Land", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(mContext, "Falsches Geburtsdatum", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {
                                Toast.makeText(mContext, "Bio soll nicht länger als 40 und kleiner als 1 eine Zeichensetzung sein", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(mContext, "Ungültige Email", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(mContext, "Überprüfe deinen Namen", Toast.LENGTH_SHORT).show();
                    }








                }

            }

            private void animate_height_data_change() {
                ValueAnimator anim2 = ValueAnimator.ofInt(holder.constraintLayout.getHeight(), holder.constraintLayout.getHeight()+1000);
                anim2.setStartDelay(200);

                anim2.addUpdateListener(valueAnimator -> {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = holder.constraintLayout.getLayoutParams();
                    layoutParams.height = val;
                    holder.constraintLayout.setLayoutParams(layoutParams);
                });
                anim2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                anim2.setDuration(500);
                anim2.start();
            }
        });
    }

    private boolean isEmailValid(String toString) {
        String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toString);
        return matcher.matches();
    }

    private void change_email_auth(String email, String pwd) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            Toast.makeText(mContext, "Try again later", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG,"usercurrent"+user);
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential(Email, pwd); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(mContext, "Email wurde geändert", Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"success231");
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                            databaseReference.child("User").child(uid).child("Email").setValue(email);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mContext, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
    public ArrayList getCities(){

        ArrayList<String> list=new ArrayList<String>();

        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);


            System.out.println("Country Name = " + obj.getDisplayCountry());

            list.add(obj.getDisplayCountry());

        }
        return list;
    }

    private void set_spinner_friends(standart_profile standart_profile) {
        // Create an ArrayAdapter using the string array and a default spinner layout


// Specify the layout to use when the list of choices appears
// Apply the adapter to the spinner
        Log.d(TAG,"Friends23" + friends);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < friends.size(); i++) {
                    String id = follower.get(i).toString();
                    if (!dataSnapshot.child("User").child(id).child("Name").exists()) {
                        friends_final.add("/");

                    } else {
                        if (i==0){
                            Log.d(TAG,"friends_null" + friends);
                            friends_final.add("/");
                            friends_img.add("/");
                            friends_name.add("/");

                        }



                        String id_user = Objects.requireNonNull(dataSnapshot.child("User").child(id).getKey());
                        String name = Objects.requireNonNull(dataSnapshot.child("User").child(id).child("Name").getValue()).toString();
                        String img = Objects.requireNonNull(dataSnapshot.child("User").child(id).child("IMG").getValue()).toString();

                        friends_final.add(id_user);
                        friends_img.add(img);
                        friends_name.add(name);




                    }


                }
                if (follower_final.isEmpty()){
                    follower_final.add("/");
                    follower_img.add("/");
                    follower_img.add("/");
                }
                standart_profile.Friends.setAdapter(new Public_Spinner_Base_Profiles(friends_img,friends_name,friends_final));
                standart_profile.Friends.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (friends_final.get(position).equals("/")){
                            return;
                        }
                        else{
                            Log.d(TAG,"clicked_21" +"------- "+ id +" ----- " + parent.getItemAtPosition(0));
                            String uid = friends.get(position-1).toString();
                            custom_profile custom_profile = new custom_profile();
                            Bundle bundle = new Bundle();
                            bundle.putString("uid",uid);
                            custom_profile.setArguments(bundle);
                            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.container_fragment,custom_profile).commit();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void set_spinner_follower(standart_profile standart_profile) {
        // Create an ArrayAdapter using the string array and a default spinner layout


// Specify the layout to use when the list of choices appears
// Apply the adapter to the spinner

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < follower.size(); i++) {
                    String id = follower.get(i).toString();
                    if (!dataSnapshot.child("User").child(id).child("Name").exists()) {
                        follower_final.add("/");

                    } else {
                        if (i==0){
                            follower_final.add("/");
                            follower_img.add("/");
                            follower_name.add("/");

                        }



                            String id_user = Objects.requireNonNull(dataSnapshot.child("User").child(id).getKey());
                            String name = Objects.requireNonNull(dataSnapshot.child("User").child(id).child("Name").getValue()).toString();
                            String img = Objects.requireNonNull(dataSnapshot.child("User").child(id).child("IMG").getValue()).toString();

                            follower_final.add(id_user);
                            follower_img.add(img);
                            follower_name.add(name);




                    }


                }
                if (follower_final.isEmpty()){
                    follower_final.add("/");
                    follower_img.add("/");
                    follower_img.add("/");
                }
                standart_profile.Follower.setAdapter(new Public_Spinner_Base_Profiles(follower_img,follower_name,follower_final));
                standart_profile.Follower.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (follower_final.get(position).equals("/")){
                            return;
                        }
                        else{
                            Log.d(TAG,"clicked_21" +"------- "+ id +" ----- " + parent.getItemAtPosition(0));
                            String uid = follower.get(position-1).toString();
                            custom_profile custom_profile = new custom_profile();
                            Bundle bundle = new Bundle();
                            bundle.putString("uid",uid);
                            custom_profile.setArguments(bundle);
                            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.container_fragment,custom_profile).commit();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    private void set_spinner_following(standart_profile standart_profile) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < following.size(); i++) {
                    String id = following.get(i).toString();
                    if (!dataSnapshot.child("User").child(id).child("Name").exists()) {
                        follower_final.add("/");

                    }
                    else {
                        if (i==0){
                            following_final.add("/");
                            following_img.add("/");
                            following_name.add("/");

                        }
                        String id_user = Objects.requireNonNull(dataSnapshot.child("User").child(id).getKey());
                        String name = Objects.requireNonNull(dataSnapshot.child("User").child(id).child("Name").getValue()).toString();
                        String img = Objects.requireNonNull(dataSnapshot.child("User").child(id).child("IMG").getValue()).toString();

                        following_final.add(id_user);
                        following_img.add(img);
                        following_name.add(name);



                    }


                }
                if (following_final.isEmpty()){
                    follower_final.add("/");
                    following_img.add("/");
                    following_name.add("/");
                }
                standart_profile.Following.setAdapter(new Public_Spinner_Base_Profiles(following_img,following_name,following_final));
                standart_profile.Following.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG,"clicked_15334" + " -----" + id + " -------" + parent);
                        if (following_final.get(position).toString().equals("/")){
                            return;

                        }
                        else{
                            String uid = following.get(position-1).toString();
                            custom_profile custom_profile = new custom_profile();
                            Bundle bundle = new Bundle();
                            bundle.putString("uid",uid);
                            custom_profile.setArguments(bundle);
                            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .replace(R.id.container_fragment,custom_profile).commit();

                        }
                      //String uid = following.get(position).toString();
                      //custom_profile custom_profile = new custom_profile();
                      //Bundle bundle = new Bundle();
                      //bundle.putString("uid",uid);
                      //custom_profile.setArguments(bundle);

                      //FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                      //fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                      //        .replace(R.id.container_fragment,custom_profile).commit();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"clicked_2124233");


            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout


// Specify the layout to use when the list of choices appears
// Apply the adapter to the spinner


    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"itemcount: " + (3+arrayList_post_id.size()));
        return 3 + arrayList_post_id.size();
    }
    public static class standart_profile extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView Imageview;
        TextView Name;
        TextView Email;
        TextView age;
        TextView Country;
        TextView uid;
        TextView info;
        TextView Height;
        Spinner Follower;
        Spinner Following;
        Spinner Friends;
        TextView Follower_number;
        TextView Following_number;
        ProgressBar progressBar;
        TextView Friends_number;


        public standart_profile(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_upload_profile_pic);
            uid = itemView.findViewById(R.id.uid_txt_profile);
            Imageview = itemView.findViewById(R.id.circle_profile_image);
            Name = itemView.findViewById(R.id.name_txt_profile);
            Email = itemView.findViewById(R.id.email_txt_profile);
            age = itemView.findViewById(R.id.age_txt_profile);
            Country = itemView.findViewById(R.id.country_txt_profile);
            info = itemView.findViewById(R.id.info_txt_profile);
            Height = itemView.findViewById(R.id.height_txt_profile);
            Follower = itemView.findViewById(R.id.spinner_followers_profile);
            Following = itemView.findViewById(R.id.spinner_following_profile);
            Friends= itemView.findViewById(R.id.spinner_friends_profile);
            Follower_number = itemView.findViewById(R.id.number_followers_profile);
            Following_number = itemView.findViewById(R.id.number_following_profile);
            Friends_number = itemView.findViewById(R.id.friends_txt_profile);


            Log.d(TAG,"uid" + uid);
        }

    }
    public class profile_a extends RecyclerView.ViewHolder {
        Button btn_change_pwd;
        EditText change_edit_profile;
        ConstraintLayout constraintLayout;
        EditText email;
        EditText old_pwd;
        public profile_a(@NonNull View itemView) {
            super(itemView);
            old_pwd = itemView.findViewById(R.id.pwd_old_profile);
            email = itemView.findViewById(R.id.email_profile_new);
            btn_change_pwd = itemView.findViewById(R.id.btn_change_pwd);
            change_edit_profile = itemView.findViewById(R.id.pwd_edit_change_profile);
            constraintLayout = itemView.findViewById(R.id.constraint_profile_a);

        }
    }
    public class profile_b extends RecyclerView.ViewHolder {

        Button btn_change_data;
        EditText age;
        EditText name;
        EditText email;
        EditText height;
        AutoCompleteTextView country;
        EditText info;
        ConstraintLayout constraintLayout;
        EditText pwd_edit;
        public profile_b(@NonNull View itemView) {
            super(itemView);

            age = itemView.findViewById(R.id.age_edit_profile);
            pwd_edit = itemView.findViewById(R.id.pwd_edit_profile);
            name = itemView.findViewById(R.id.Name_edit_profile);
            email = itemView.findViewById(R.id.Email_edit_profile);
            height = itemView.findViewById(R.id.height_edit_profile);
            country = itemView.findViewById(R.id.country_edit_profile);
            info = itemView.findViewById(R.id.profile_edit_info);
            constraintLayout = itemView.findViewById(R.id.standart_profile_page_layout_b);



            btn_change_data = itemView.findViewById(R.id.btn_change_data);

        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = -2;
        Log.d(TAG,"position23"+position);
        if (position == 0){

            Log.d(TAG,"final0"+position);
            i=0;


        }
        if (position == 1){
            i=1;
            Log.d(TAG,"final1"+position);



        }
        if (position == 2){
            Log.d(TAG,"final2"+position);




            i=2;


        }
        if (position >= 3){
            Log.d(TAG,"final3"+position);


            i=3;


        }
        return i;
    }


    private class standart_profile_c extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        private SimpleExoPlayerView exoPlayerView;
        private ImageView imageView;
        private Button delete_post;
        private ImageView share;
        private TextView likes;
        private TextView comments;
        private TextView location;
        private TextView time;
        public standart_profile_c(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_user_profile_custom_post);
            likes =  itemView.findViewById(R.id.likes_profile_custom_post);
            comments = itemView.findViewById(R.id.comments_profile_custom_post);
            exoPlayerView = itemView.findViewById(R.id.exoplayer_user_profile_custom_post);
            delete_post = itemView.findViewById(R.id.btn_delete_profile_custom_post);
            share = itemView.findViewById(R.id.share_profile_custom_post);
            time = itemView.findViewById(R.id.time_user_posts_custom);
            progressBar = itemView.findViewById(R.id.progress_custom_post_profile);
            location = itemView.findViewById(R.id.place_user_posts_custom);
        }
    }



}
