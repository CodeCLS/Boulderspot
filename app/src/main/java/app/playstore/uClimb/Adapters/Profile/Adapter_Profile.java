package app.playstore.uClimb.Adapters.Profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
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

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private ArrayList friends;
    //private ArrayList following_final;
    //private ArrayList follower_final;

    //private String account_type;
    //private String time_created;
    //private String position_lat;
    //private String position_long;
    //private String position_last_updated;
    //private Boolean position_status;
    //private ArrayList<String> uploads_post_id = new ArrayList<>();
    //private String IMG;

    private String Email;
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
    public Adapter_Profile(String uid, String age, String name, String profile_img, String info, String country, ArrayList follower, ArrayList following, String height, ArrayList friends, String email) {
        this.uid = uid;
        //this.stat_uid = stat_uid;
        Age = age;
        Name = name;
        this.profile_img = profile_img;
        Info = info;
        //Subscription = subscription;
        //this.grade = grade;
        this.country = country;
        this.follower = follower;
        this.following = following;
        Height = height;
        this.friends= friends;
        //this.account_type = account_type;
        //this.time_created = time_created;
        //this.position_lat = position_lat;
        //this.position_long = position_long;
        //this.position_last_updated = position_last_updated;
        //this.position_status = position_status;
        this.Email = email;

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
            viewholder = new standart_profile(view);
//
        }
        return Objects.requireNonNull(viewholder);



    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == 0){
            viewHolder_0_code((standart_profile) holder);


        }
        if (holder.getItemViewType() == 1 ){
            viewholder_1_code((profile_a) holder);


        }
        if (holder.getItemViewType() == 2 ){
            viewholder_2_code((profile_b) holder);


        }
        if (holder.getItemViewType() == 3){
            standart_profile_c standart_profile_c = (standart_profile_c) holder;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.child("User").child(uid).child("Posts").getChildren()){
                        //post_id.add(postSnapshot.getValue().toString());
                    }
                   //for (int i = 0;i<post_id.size();i++){
                   //    //String id = post_id.get(i).toString();
                   //    //source.add(dataSnapshot.child("Posts").child(id).child("Source").getValue().toString());
                   //    //type.add(dataSnapshot.child("Posts").child(id).child("type").getValue().toString());
                   //    //info.add(dataSnapshot.child("Posts").child(id).child("info").getValue().toString());
                   //    //u_id.add(uid);
                   //    //time.add(dataSnapshot.child("Posts").child(id).child("time").getValue().toString());
                   //    //place.add(dataSnapshot.child("Posts").child(id).child("place").getValue().toString());



                   //}
                    standart_profile_c.rec.setLayoutManager(new GridLayoutManager(mContext,numbercolumns));
                    //standart_profile_c.rec.setAdapter(custom_profile_adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }




        }

    private void viewHolder_0_code(@NonNull standart_profile holder) {
        standart_profile standart_profile = holder;
        standart_profile.Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter_Profile profile_presenter = new Presenter_Profile(mContext);
                profile_presenter.setProfilePic(mContext,standart_profile.progressBar);

            }
        });
        Picasso.get().load(profile_img).into(standart_profile.Imageview);
        Log.d(TAG,"profile_img4" + profile_img);
        standart_profile.Email.setText(Email);
        standart_profile.Name.setText(Name);
        standart_profile.age.setText(Age+ " years");
        standart_profile.Country.setText(country);
        standart_profile.uid.setText(uid);
        standart_profile.info.setText(Info);
        standart_profile.Height.setText(Height);
        set_spinner_following(standart_profile);
        set_spinner_follower(standart_profile);
        set_spinner_friends(standart_profile);
        standart_profile.Following_number.setText(following.size() + " Following");
        standart_profile.Follower_number.setText(follower.size() + " Follower");
// Create an ArrayAdapter using the string array and a default spinner layout
    }

    private void viewholder_1_code(@NonNull profile_a holder) {
        Log.d(TAG,"clicked_animate");

        profile_a standart_profile_a = holder;
        standart_profile_a.btn_change_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bool_clicked = true;
                Log.d(TAG,"clicked_animate");

                animate_pwd_change_height();
                standart_profile_a.change_edit_profile.setVisibility(View.VISIBLE);
                standart_profile_a.email.setVisibility(View.VISIBLE);
                standart_profile_a.old_pwd.setVisibility(View.VISIBLE);
                standart_profile_a.btn_change_pwd.setText("Change PWD");
                standart_profile_a.btn_change_pwd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bool_clicked){


                            String pwd_new = standart_profile_a.btn_change_pwd.getText().toString();
                            String pwd_old = standart_profile_a.old_pwd.getText().toString();
                            String email = standart_profile_a.email.getText().toString();
                            if (standart_profile_a.change_edit_profile.getText().toString().length() < 6 || standart_profile_a.email.getText().toString().isEmpty() || standart_profile_a.old_pwd.getText().toString().length() < 6) {
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
                                                            ValueAnimator anim2 = ValueAnimator.ofInt(standart_profile_a.constraintLayout.getHeight(), standart_profile_a.constraintLayout.getHeight()-500);
                                                            anim2.setStartDelay(200);

                                                            anim2.addUpdateListener(valueAnimator -> {
                                                                int val = (Integer) valueAnimator.getAnimatedValue();
                                                                ViewGroup.LayoutParams layoutParams = standart_profile_a.constraintLayout.getLayoutParams();
                                                                layoutParams.height = val;
                                                                standart_profile_a.constraintLayout.setLayoutParams(layoutParams);
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
                ValueAnimator anim2 = ValueAnimator.ofInt(standart_profile_a.constraintLayout.getHeight(), standart_profile_a.constraintLayout.getHeight()+500);
                anim2.setStartDelay(200);

                anim2.addUpdateListener(valueAnimator -> {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = standart_profile_a.constraintLayout.getLayoutParams();
                    layoutParams.height = val;
                    standart_profile_a.constraintLayout.setLayoutParams(layoutParams);
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
        profile_b standart_profile_b = holder;
        standart_profile_b.btn_change_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate_height_data_change();
                standart_profile_b.country.setVisibility(View.VISIBLE);
                standart_profile_b.age.setVisibility(View.VISIBLE);
                standart_profile_b.height.setVisibility(View.VISIBLE);
                standart_profile_b.info.setVisibility(View.VISIBLE);
                standart_profile_b.name.setVisibility(View.VISIBLE);
                standart_profile_b.email.setVisibility(View.VISIBLE);
                standart_profile_b.recyclerview_holder.setVisibility(View.VISIBLE);
                standart_profile_b.recyclerView.setVisibility(View.VISIBLE);
                standart_profile_b.input_friends.setVisibility(View.VISIBLE);

                standart_profile_b.btn_change_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseDatabase auth = FirebaseDatabase.getInstance();
                        DatabaseReference reference = auth.getReference("");
                        Presenter_Login login_presenter = new Presenter_Login();
                        String uid = login_presenter.getUID(mContext);
                        standart_profile_b.btn_change_data.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Locale[] locales = Locale.getAvailableLocales();
                                for (Locale locale : locales) {
                                    String country = locale.getDisplayCountry();
                                    if (country.trim().length()>0 && !countries.contains(country)) {
                                        countries.add(country);
                                    }
                                }

                                System.out.println( "# countries found: " + countries.size());
                                if (!standart_profile_b.age.getText().toString().isEmpty() && Integer.parseInt(standart_profile_b.age.getText().toString()) < 100){
                                    reference.child("User").child(uid).child("Age").setValue(standart_profile_b.age.getText().toString());


                                }
                                if (Integer.parseInt(standart_profile_b.age.getText().toString()) > 100){
                                    Toast.makeText(mContext, "Alter zu groß", Toast.LENGTH_SHORT).show();
                                }
                                else{


                                }
                                if (!standart_profile_b.height.getText().toString().isEmpty() && Integer.parseInt(standart_profile_b.height.getText().toString())>250){
                                    reference.child("User").child(uid).child("Height").child(standart_profile_b.height.getText().toString());


                                }if (Integer.parseInt(standart_profile_b.height.getText().toString())>250){
                                    Toast.makeText(mContext, "Echte Größe bitte eingeben", Toast.LENGTH_SHORT).show();
                                }
                                else{

                                }
                                if (!standart_profile_b.info.getText().toString().isEmpty() && standart_profile_b.info.getText().toString().length() < 300){
                                    reference.child("User").child(uid).child("Info").child(standart_profile_b.info.getText().toString());


                                }if (standart_profile_b.info.getText().toString().length() > 300){
                                    Toast.makeText(mContext, "Text zu lang", Toast.LENGTH_SHORT).show();

                                }
                                if (!standart_profile_b.country.getText().toString().isEmpty() && countries.contains(standart_profile_b.country.getText())){
                                    reference.child("User").child(uid).child("Country").child(standart_profile_b.country.getText().toString());


                                }if (!countries.contains(standart_profile_b.country.getText().toString())){
                                    Toast.makeText(mContext, "Das Land gibt es nicht", Toast.LENGTH_SHORT).show();


                                }
                                else{
                                    return;
                                }
                                if (!standart_profile_b.name.getText().toString().isEmpty()&&standart_profile_b.name.getText().toString().length() < 15){
                                    reference.child("User").child(uid).child("Name").child(standart_profile_b.name.getText().toString());


                                }
                                if (standart_profile_b.name.getText().toString().length() <15){
                                    Toast.makeText(mContext, "Name zu lang", Toast.LENGTH_SHORT).show();

                                }





                                if (!standart_profile_b.email.getText().toString().isEmpty() && isEmailValid(standart_profile_b.email.getText().toString())){
                                    reference.child("User").child(uid).child("Email").child(standart_profile_b.email.getText().toString());
                                    change_email_auth();



                                }
                                if (!isEmailValid(standart_profile_b.email.getText().toString())){
                                    Toast.makeText(mContext, "Echte Email bitte eingeben", Toast.LENGTH_SHORT).show();



                                }







                            }
                        });

                    }
                });

            }

            private void animate_height_data_change() {
                ValueAnimator anim2 = ValueAnimator.ofInt(standart_profile_b.constraintLayout.getHeight(), standart_profile_b.constraintLayout.getHeight()+1000);
                anim2.setStartDelay(200);

                anim2.addUpdateListener(valueAnimator -> {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = standart_profile_b.constraintLayout.getLayoutParams();
                    layoutParams.height = val;
                    standart_profile_b.constraintLayout.setLayoutParams(layoutParams);
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
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toString);
        return matcher.matches();
    }

    private void change_email_auth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234"); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User re-authenticated.");
                        //Now change your email address \\
                        //----------------Code for Changing Email Address----------\\
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updateEmail("user@example.com")
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User email address updated.");
                                        }
                                    }
                                });
                        //----------------------------------------------------------\\
                    }
                });
    }

    private void set_spinner_friends(standart_profile standart_profile) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dataAdapter_friends = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, friends);

// Specify the layout to use when the list of choices appears
        dataAdapter_friends.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        standart_profile.Friends.setAdapter(dataAdapter_friends);
    }

    private void set_spinner_follower(standart_profile standart_profile) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dataAdapter_follower = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, follower);


// Specify the layout to use when the list of choices appears
        dataAdapter_follower.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        standart_profile.Follower.setAdapter(dataAdapter_follower);

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
                        String name = dataSnapshot.child("User").child(id).child("Name").getValue().toString();
                        follower_final.add(name);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        standart_profile.Follower.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"clicked_153324");
                String uid = follower.get(position).toString();
                custom_profile custom_profile = new custom_profile();
                Bundle bundle = new Bundle();
                bundle.putString("uid",uid);
                custom_profile.setArguments(bundle);

                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment,custom_profile).commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG,"clicked_124233");

            }
        });




    }

    private void set_spinner_following(standart_profile standart_profile) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i< following.size();i++){
                    String id = following.get(i).toString();
                    if (!dataSnapshot.child("User").child(id).child("Name").exists()){
                        following_final.add("/");

                    }
                    else{
                        String name = dataSnapshot.child("User").child(id).child("Name").getValue().toString();
                        following_final.add(name);


                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"clicked_2124233");


            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dataAdapter_following = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, following_final);

// Specify the layout to use when the list of choices appears
        dataAdapter_following.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        standart_profile.Following.setAdapter(dataAdapter_following);
        standart_profile.Following.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"clicked_15334");
                String uid = following.get(position).toString();
                custom_profile custom_profile = new custom_profile();
                Bundle bundle = new Bundle();
                bundle.putString("uid",uid);
                custom_profile.setArguments(bundle);

                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment,custom_profile).commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
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
        AutoCompleteTextView input_friends;
        RecyclerView recyclerView;
        LinearLayout recyclerview_holder;
        ConstraintLayout constraintLayout;
        public profile_b(@NonNull View itemView) {
            super(itemView);

            age = itemView.findViewById(R.id.age_edit_profile);
            name = itemView.findViewById(R.id.Name_edit_profile);
            email = itemView.findViewById(R.id.Email_edit_profile);
            height = itemView.findViewById(R.id.height_edit_profile);
            country = itemView.findViewById(R.id.country_edit_profile);
            info = itemView.findViewById(R.id.profile_edit_info);
            recyclerview_holder = itemView.findViewById(R.id.linear_rec_holder_profile);
            constraintLayout = itemView.findViewById(R.id.standart_profile_page_layout_b);
            recyclerView = itemView.findViewById(R.id.rec_friends_profile_edit);
            input_friends = itemView.findViewById(R.id.friends_input_profile);



            btn_change_data = itemView.findViewById(R.id.btn_change_data);

        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = -2;
        if (position == 0){
            i=0;


        }
        if (position==1){
            i=1;


        }
        if (position == 2){
            i=2;


        }
        if (position == 3){
            i=3;


        }
        return i;
    }


    private class standart_profile_c extends RecyclerView.ViewHolder {
        public RecyclerView rec;

        public standart_profile_c(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.rec_profile_images_and_videos_uploads);
        }
    }


}
