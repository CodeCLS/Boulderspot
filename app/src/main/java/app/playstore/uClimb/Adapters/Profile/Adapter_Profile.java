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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.playstore.uClimb.Adapters.Public_Spinner_Base_Profiles;
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
    private ArrayList<String> following_final = new ArrayList<String>();
    private ArrayList<String> follower_final = new ArrayList<String>();
    private ArrayList<String> following_name = new ArrayList<String>();
    private ArrayList<String> follower_name = new ArrayList<String>();
    private ArrayList<String> following_img = new ArrayList<String>();
    private ArrayList<String> follower_img = new ArrayList<String>();

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

    @SuppressLint({"SetTextI18n", "Assert"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == 0){
            viewHolder_0_code((standart_profile) holder);


        }
        if (holder.getItemViewType() == 1 ){
            assert holder instanceof profile_a;
            viewholder_1_code((profile_a) holder);


        }
        if (holder.getItemViewType() == 2 ){
            assert holder instanceof profile_b;
            viewholder_2_code((profile_b) holder);


        }
        if (holder.getItemViewType() == 3){
            assert holder instanceof standart_profile_c;
            standart_profile_c standart_profile_c = (standart_profile_c) holder;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   //for (DataSnapshot postSnapshot : dataSnapshot.child("User").child(uid).child("Posts").getChildren()){
                   //    //post_id.add(postSnapshot.getValue().toString());
                   //}
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
                animate_height_data_change();
                holder.country.setVisibility(View.VISIBLE);
                holder.age.setVisibility(View.VISIBLE);
                holder.height.setVisibility(View.VISIBLE);
                holder.info.setVisibility(View.VISIBLE);
                holder.name.setVisibility(View.VISIBLE);
                holder.email.setVisibility(View.VISIBLE);
                holder.recyclerview_holder.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.input_friends.setVisibility(View.VISIBLE);

                holder.btn_change_data.setOnClickListener(v1 -> {

                    FirebaseDatabase auth = FirebaseDatabase.getInstance();
                    DatabaseReference reference = auth.getReference("");
                    Presenter_Login login_presenter = new Presenter_Login();
                    String uid = login_presenter.getUID(mContext);
                    holder.btn_change_data.setOnClickListener(v11 -> {
                        //Locale[] locales = Locale.getAvailableLocales();
                        //for (Locale locale : locales) {
                        //    //String country = locale.getDisplayCountry();
                        //    //if (country.trim().length()>0 && !countries.contains(country)) {
                        //        //countries.add(country);
                        //    //}
                        //}

                        //System.out.println( "# countries found: " + countries.size());
                        if (!holder.age.getText().toString().isEmpty() && Integer.parseInt(holder.age.getText().toString()) < 100){
                            reference.child("User").child(uid).child("Age").setValue(holder.age.getText().toString());


                        }
                        if (Integer.parseInt(holder.age.getText().toString()) > 100){
                            Toast.makeText(mContext, "Alter zu groß", Toast.LENGTH_SHORT).show();
                        }

                        if (!holder.height.getText().toString().isEmpty() && Integer.parseInt(holder.height.getText().toString())>250){
                            reference.child("User").child(uid).child("Height").child(holder.height.getText().toString());


                        }if (Integer.parseInt(holder.height.getText().toString())>250){
                            Toast.makeText(mContext, "Echte Größe bitte eingeben", Toast.LENGTH_SHORT).show();
                        }

                        if (!holder.info.getText().toString().isEmpty() && holder.info.getText().toString().length() < 300){
                            reference.child("User").child(uid).child("Info").child(holder.info.getText().toString());


                        }if (holder.info.getText().toString().length() > 300){
                            Toast.makeText(mContext, "Text zu lang", Toast.LENGTH_SHORT).show();

                        }
                        //if (!holder.country.getText().toString().isEmpty() && countries.contains(holder.country.getText())){
                        //    reference.child("User").child(uid).child("Country").child(holder.country.getText().toString());
//
//
                        //}
                        //if (!countries.contains(holder.country.getText().toString())){
                        //    Toast.makeText(mContext, "Das Land gibt es nicht", Toast.LENGTH_SHORT).show();
//
//
                        //}
                        //else{
                        //    return;
                        //}
                        if (!holder.name.getText().toString().isEmpty()&& holder.name.getText().toString().length() < 15){
                            reference.child("User").child(uid).child("Name").child(holder.name.getText().toString());


                        }
                        if (holder.name.getText().toString().length() <15){
                            Toast.makeText(mContext, "Name zu lang", Toast.LENGTH_SHORT).show();

                        }





                        if (!holder.email.getText().toString().isEmpty() && isEmailValid(holder.email.getText().toString())){
                            reference.child("User").child(uid).child("Email").child(holder.email.getText().toString());
                            change_email_auth();



                        }
                        if (!isEmailValid(holder.email.getText().toString())){
                            Toast.makeText(mContext, "Echte Email bitte eingeben", Toast.LENGTH_SHORT).show();



                        }







                    });

                });

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

    private void change_email_auth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234"); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        assert user != null;
        user.reauthenticate(credential)
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "User re-authenticated.");
                    //Now change your email address \\
                    //----------------Code for Changing Email Address----------\\
                    FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                    assert user1 != null;
                    user1.updateEmail("user@example.com")
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Log.d(TAG, "User email address updated.");
                                }
                            });
                    //----------------------------------------------------------\\
                });
    }

    private void set_spinner_friends(standart_profile standart_profile) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> dataAdapter_friends = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, friends);

        //TODO here

// Specify the layout to use when the list of choices appears
        dataAdapter_friends.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        standart_profile.Friends.setAdapter(dataAdapter_friends);
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
