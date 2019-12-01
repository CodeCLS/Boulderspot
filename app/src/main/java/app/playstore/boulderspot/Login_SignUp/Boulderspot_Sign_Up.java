package app.playstore.boulderspot.Login_SignUp;

import android.content.Intent;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.playstore.boulderspot.Main.MainActivity;
import app.playstore.boulderspot.R;

public class Boulderspot_Sign_Up extends AppCompatActivity {
    private static final String TAG = "Boulderspot_Sign_up";
    private static final int RC_SIGN_IN = 2;
    private EditText edit_email;
    private EditText edit_pwd;
    private EditText edit_user;
    private TextView log_in;
    private Button btn_sign_up;
    private ImageView facebook;
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
        google_sign_up();
        login_txt();
    }

    private void login_txt() {
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Boulderspot_Sign_Up.this, Boulderspot_Log_In.class);
                startActivity(intent);
            }
        });
    }

    private void google_sign_up() {
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                msignin = GoogleSignIn.getClient(Boulderspot_Sign_Up.this, gso);

                Intent intent = msignin.getSignInIntent();
                startActivity(intent);

            }
        });

      //  Intent signInIntent = gso.getSignInIntent();
      //  startActivityForResult(signInIntent, RC_SIGN_IN);






        // Initialize Facebook Login button




// ...
    }

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
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG,"userData: " + user + " I want: " + mAuth.getUid());
                            Toast.makeText(Boulderspot_Sign_Up.this, "Success!", Toast.LENGTH_SHORT).show();
                            myRef.child(mAuth.getUid()).child("Email").setValue(email);
                            myRef.child(mAuth.getUid()).child("Username").setValue(edit_user.getText().toString());
                            myRef.child(mAuth.getUid()).child("PWD").setValue(pwd);
                            Intent intent = new Intent(Boulderspot_Sign_Up.this, extra_questions_register.class);
                            startActivity(intent);


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

    private void initviews() {
        facebook = findViewById(R.id.facebook_logo_sign_up);
        google = findViewById(R.id.google_logo_sign_up);
        edit_email = findViewById(R.id.edit_email_sign_up);
        edit_pwd = findViewById(R.id.edit_sign_up_pwd);
        edit_user = findViewById(R.id.edit_name_sign_up);
        log_in = findViewById(R.id.Log_in_txt_sign_up);
        btn_sign_up = findViewById(R.id.btn_sign_up);


    }
    @Override
    public void onStart() {
        super.onStart();
        //init Authentication
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            return;
        }
        else{
            Log.d(TAG,"UID:"+mAuth.getUid() + "   " + mAuth.getCurrentUser());
            Intent intent = new Intent(Boulderspot_Sign_Up.this, MainActivity.class);
            startActivity(intent);
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
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
                            Intent intent = new Intent(Boulderspot_Sign_Up.this, extra_questions_register.class);
                            startActivity(intent);
                         //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        //    Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
