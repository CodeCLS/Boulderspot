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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.playstore.boulderspot.Main.MainActivity;
import app.playstore.boulderspot.R;

public class Boulderspot_Log_In extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "Login";
    public static boolean status;
    private GoogleApiClient mGoogleApiClient;

    private Button btn_log_in;
    private TextView txt_sign_up;
    private TextView terms_conditions_txt;
    private ImageView img_google_log_in;
    private ImageView img_facebook_sign_in;
    private EditText editText_name_log_in;
    private int RC_SIGN_IN = 1;
    private EditText editText_pwd_log_in;
    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Login");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);
        initGoogle();

        init_views();
        listener();
        txt_sign_up_listener();
        google_sign_up();


    }

    private void initGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void txt_sign_up_listener() {
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Boulderspot_Log_In.this,Boulderspot_Sign_Up.class );
                startActivity(intent);
            }
        });
    }

    private void listener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    btn_log_in.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (editText_name_log_in.getText().toString().equals("")|| editText_pwd_log_in.getText().toString().equals("")){
                                Toast.makeText(Boulderspot_Log_In.this, "Fill all fields", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                mAuth.signInWithEmailAndPassword(editText_name_log_in.getText().toString(),editText_pwd_log_in.getText().toString())
                                        .addOnCompleteListener(Boulderspot_Log_In.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    // Sign in success, update UI with the signed-in user's information
                                                    Log.d(TAG, "signInWithEmail:success");
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    Intent intent = new Intent(Boulderspot_Log_In.this , MainActivity.class);
                                                    startActivity(intent);
                                                    Boulderspot_Log_In.this.finish();

                                                } else {
                                                    // If sign in fails, display a message to the user.
                                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                                    Toast.makeText(Boulderspot_Log_In.this, "Authentication failed. Error: " + task.getException().getLocalizedMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                }

                                                // ...
                                            }
                                        });


                            }
                            Log.d(TAG,"Data_of_post:" + postSnapshot.child("User").getValue());










                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG,"status_of_boolean" + status);



    }













    private void init_views() {
        btn_log_in = findViewById(R.id.btn_sign_up);
        txt_sign_up = findViewById(R.id.Log_in_txt_sign_up);
        terms_conditions_txt = findViewById(R.id.terms_conditions_txt_sign_up);
        editText_name_log_in = findViewById(R.id.edit_name_sign_up);
        editText_pwd_log_in = findViewById(R.id.edit_sign_up_pwd);


    }
    @Override
    public void onStart() {
        super.onStart();
        //init Authentication
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG,"currentuser:" + mAuth.getCurrentUser());

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            return;
        }
        else{
            Log.d(TAG,"UID:"+mAuth.getUid() + "   " + mAuth.getCurrentUser());
            Intent intent = new Intent(Boulderspot_Log_In.this, MainActivity.class);
            startActivity(intent);
            Boulderspot_Log_In.this.finish();

        }
    }
    private void google_sign_up() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
// Signed in successfully
                GoogleSignInAccount acct = result.getSignInAccount();
                String id = acct.getId();
                String name = acct.getDisplayName();
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

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(Boulderspot_Log_In.this, MainActivity.class);
                            startActivity(intent);
                            Boulderspot_Log_In.this.finish();

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
