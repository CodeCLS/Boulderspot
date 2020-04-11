package app.playstore.uClimb.Login_SignUp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

public class Boulderspot_Log_In extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "Login";
    private static final int RC_SIGN_IN = 1;
    public static boolean status;
    private EditText edit_pwd;
    private EditText edit_email;
    private Button btn_login;
    private FirebaseAuth mAuth;
    private TextView sign_up_txt;
    private ImageView img_google;
    private boolean edit_pwd_boolean_status = false;
    private boolean edit_email_boolean_status = false;
    private int num_min_email = 6;
    private int num_min_pwd = 6;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);
        initViews();
        Log.d(TAG,"hello");
        edittextlistener();
        login();
        txt_signup();
        google_sign_up();



    }

    private void edittextlistener() {
        Log.d(TAG,"hello1");

        edit_email_text_watcher();
        edit_pwd_text_watcher();





    }
    private void txt_signup(){
        sign_up_txt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Boulderspot_Log_In.this,Boulderspot_Sign_Up.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Boulderspot_Log_In.this).toBundle());
                finish();


            }
        });
    }
    private void google_sign_up(){
        Log.d(TAG,"pressed_google");

        img_google.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"pressed_google");

                login_presenter login_presenter = new login_presenter();
                GoogleSignInClient mGoogleSignInClient = login_presenter.login_google(Boulderspot_Log_In.this);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });


    }
    private void edit_pwd_text_watcher() {
        edit_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() < num_min_pwd){
                    edit_pwd_action_0();
                }
                else{
                    edit_pwd_action_longer();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() < num_min_pwd){
                    edit_pwd_action_0();
                }
                else{
                    edit_pwd_action_longer();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void edit_pwd_action_longer() {
        edit_pwd_boolean_status = true;
        if (edit_email_boolean_status){
            Drawable background = btn_login.getBackground();
            background.setTint(getResources().getColor(R.color.blue_pressed_btn));




        }
        else{
            Drawable background = btn_login.getBackground();
            background.setTint(getResources().getColor(R.color.grey_pressed_btn));



        }
    }

    private void edit_pwd_action_0() {
        edit_pwd_boolean_status = false;
            Drawable background = btn_login.getBackground();
            background.setTint(getResources().getColor(R.color.grey_pressed_btn));


    }

    private void edit_email_text_watcher() {
        edit_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isEmailValid(s.toString().trim())){
                    edit_email_action_longer();

                }
                else{
                    edit_email_action_0();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isEmailValid(s.toString().trim())){
                    edit_email_action_longer();
                    Log.d(TAG,"actionpoint2A");

                }
                else{
                    Log.d(TAG,"actionpoint1A");
                    edit_email_action_0();


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void edit_email_action_longer() {
        Log.d(TAG,"actionpoint3B");
        edit_email_boolean_status = true;
        if (edit_pwd_boolean_status){
            Drawable background = btn_login.getBackground();
            background.setTint(getResources().getColor(R.color.blue_pressed_btn));




        }
        else{
            Drawable background = btn_login.getBackground();
            background.setTint(getResources().getColor(R.color.grey_pressed_btn));



        }
    }

    private void edit_email_action_0() {
        edit_email_boolean_status = false;
        Drawable background = btn_login.getBackground();
        background.setTint(getResources().getColor(R.color.grey_pressed_btn));

    }

    private void login() {
        btn_login.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                String email =edit_email.getText().toString();
                String pwd = edit_pwd.getText().toString();
                Log.d(TAG,"androidisEmail2" + edit_email_boolean_status);



                if (edit_email_boolean_status && edit_pwd_boolean_status){
                    login_presenter login_presenter = new login_presenter();
                    login_presenter.login(Boulderspot_Log_In.this,email,pwd);




                }
                else{
                    createsnackbar();
                }


            }
        });

    }

    private void createsnackbar() {
        Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"Please fill all the fields",Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark));
        snackbar.show();
    }

    private void initViews() {
        edit_pwd = findViewById(R.id.edit_pwd_user);
        edit_email = findViewById(R.id.edit_email_name);
        btn_login = findViewById(R.id.btn_log_in_log_in);
        sign_up_txt = findViewById(R.id.sign_up_txt_login);
        img_google = findViewById(R.id.google_logo_log_in);

    }
    public static boolean isEmailValid(String email) {

        Log.d(TAG,"androidisEmail" + android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());


        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser == null){
            Log.d(TAG,"currentsuer" + currentUser);
            return;
        }
        else{
            Log.d(TAG,"updateUI" + currentUser);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("User");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(Objects.requireNonNull(mAuth.getUid())).exists()){
                        Log.d(TAG,"mauthexists");

                        Intent intent = new Intent(Boulderspot_Log_In.this, MainActivity.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Boulderspot_Log_In.this).toBundle());
                        finish();

                    }
                    else{
                        updateUInew(currentUser);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();

                GoogleSignInAccount account = task.getResult(ApiException.class);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                firebaseAuthWithGoogle(account);

                //firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseUserMetadata metadata = mAuth.getCurrentUser().getMetadata();



                                Log.d(TAG,"mAuthgoogle" + mAuth.getUid());

                                // Write a message to the database
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("");

                                myRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.child(Objects.requireNonNull(mAuth.getUid())).exists()){
                                            Log.d(TAG,"notfirst");

                                            updateUI(user);

                                        }
                                        else{
                                            updateUInew(user);

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                // This is an existing user, show them a welcome back screen.
                                                     //updateUI(user);
                            Log.d(TAG,"users123:"+user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(Boulderspot_Log_In.this.getWindow().getDecorView(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUInew(FirebaseUser user) {
        Log.d(TAG,"Logingooglenew");
        Intent intent = new Intent(Boulderspot_Log_In.this,extra_questions_register.class);
        intent.putExtra("pwd" , "wqn9YzHxf4odtGt");
        Log.d(TAG,"Logingooglenew" + user.getDisplayName());
        Log.d(TAG,"Logingooglenew" + user.getEmail());


        intent.putExtra("username" , user.getDisplayName());
        intent.putExtra("email", user.getEmail());
        startActivity(intent);
    }
}
