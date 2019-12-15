package app.playstore.boulderspot.Login_SignUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.playstore.boulderspot.Main.MainActivity;
import app.playstore.boulderspot.R;

public class Boulderspot_Sign_Up extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "Boulderspot_Sign_up";
    private GoogleApiClient mGoogleApiClient;
    private EditText edit_email;
    private EditText edit_pwd;
    private EditText edit_user;
    private TextView log_in;
    private GoogleSignInClient signInClient;
    private Button btn_sign_up;
    private ImageView google;
    private GoogleSignInClient msignin;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        initviews();
        sign_up();
        login_txt();
        initShared();
    }

    private void initShared() {
        SharedPreferences sharedPreferences_first = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor shared_edit_first = sharedPreferences_first.edit();
        shared_edit_first.putBoolean("First" , false);
        shared_edit_first.apply();
    }

    private void login_txt() {
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Boulderspot_Sign_Up.this, Boulderspot_Log_In.class);
                startActivity(intent);
                Boulderspot_Sign_Up.this.finish();

            }
        });
    }



      //  Intent signInIntent = gso.getSignInIntent();
      //  startActivityForResult(signInIntent, RC_SIGN_IN);






        // Initialize Facebook Login button




// ...


    private void sign_up() {
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_email.getText().toString().isEmpty() || edit_pwd.getText().toString().isEmpty()
                        || edit_user.getText().toString().isEmpty()){
                    Toast.makeText(Boulderspot_Sign_Up.this, "Fill in the fields", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (edit_user.getText().length() > 15){
                        Toast.makeText(Boulderspot_Sign_Up.this, "Name too long", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (isValidEmail(edit_email.getText().toString())){
                            task_new_user();
                        }
                        else {
                            Log.d(TAG,"EMail: " + edit_email.getText().toString() + "Valid? " + isValidEmail(edit_email.getText().toString()));
                            Toast.makeText(Boulderspot_Sign_Up.this, "Please enter a real email", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });






    }

    private void task_new_user() {
        final String email = edit_email.getText().toString();
        final String pwd = edit_pwd.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Boulderspot_Sign_Up.this, "Success!", Toast.LENGTH_SHORT).show();

                           // myRef.child(mAuth.getUid()).child("Email").setValue(email);
                            register_new_user_to_realtime();

                            //myRef.child(mAuth.getUid()).child("PWD").setValue(pwd);
                            intent_to_extra();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Boulderspot_Sign_Up.this, "Authentication failed. Try again later " + task.getException(),
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    private void register_new_user_to_realtime() {
        String nowAsISO = getTimeStamp_in_ISO8601();
        myRef.child(Objects.requireNonNull(mAuth.getUid())).setValue(mAuth.getUid());
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Name").setValue(edit_user.getText().toString());
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("User_type").setValue("person");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Bio").setValue("/");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Mitgliedschaft").setValue("Standart");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Boulder_most_centre").setValue("/");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Liked").setValue("Liked");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Follower").setValue("Follower");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Following").setValue("Following");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Posts").setValue("Posts");
        myRef.child(Objects.requireNonNull(mAuth.getUid())).child("Liked").setValue(nowAsISO);


        shared_mAuth_user_id();
    }

    private String getTimeStamp_in_ISO8601() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }

    private void shared_mAuth_user_id() {
        SharedPreferences sharedPreferences_UID = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor shared_edit_first = sharedPreferences_UID.edit();
        shared_edit_first.putString("mAuth_UID", mAuth.getUid());
        shared_edit_first.apply();
    }

    private void intent_to_extra() {
        Intent intent = new Intent(Boulderspot_Sign_Up.this, extra_questions_register.class);
        startActivity(intent);
        Boulderspot_Sign_Up.this.finish();
    }

    private void initviews() {
        google = findViewById(R.id.google_logo_log_in);
        edit_email = findViewById(R.id.edit_email_sign_up);
        edit_pwd = findViewById(R.id.edit_sign_up_pwd);
        edit_user = findViewById(R.id.edit_name_sign_up);
        log_in = findViewById(R.id.Log_in_txt_sign_up);
        btn_sign_up = findViewById(R.id.btn_sign_up);


    }
    @Override
    public void onStart() {
        super.onStart();
        //init AuthenticationInstance
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d(TAG,"currentuser:" + mAuth.getCurrentUser());


        if (currentUser != null){
            already_loggeed_in_action();


        }
        else{

        }



    }

    private void already_loggeed_in_action() {
        Log.d(TAG,"UID:"+mAuth.getUid() + "   " + mAuth.getCurrentUser());
        Intent intent = new Intent(Boulderspot_Sign_Up.this, MainActivity.class);
        startActivity(intent);
        Boulderspot_Sign_Up.this.finish();
    }


    public boolean isValidEmail(String email)
    {
        final String EMAIL_PATTERN =
                getString(R.string.email_patterm);
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int RC_SIGN_IN = 1;
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
// Signed in successfully
                GoogleSignInAccount acct = result.getSignInAccount();
                assert acct != null;
                //String id = acct.getId();
                //String name = acct.getDisplayName();
                String email = acct.getEmail();

                firebaseAuthWithGoogle(acct);


                Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();

            } else {


                Toast.makeText(getApplicationContext(), "Failed to authenticate user", Toast.LENGTH_SHORT).show();

            }

        }}
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

                                //FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent(Boulderspot_Sign_Up.this, extra_questions_register.class);
                                startActivity(intent);
                               // Boulderspot_Sign_Up.this.finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                             //   Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
