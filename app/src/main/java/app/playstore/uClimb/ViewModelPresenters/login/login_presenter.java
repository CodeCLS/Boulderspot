package app.playstore.uClimb.ViewModelPresenters.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;

public class login_presenter {
    private static final String TAG = "login_presenter";
    private String mAuth;
    private String pwd_hash;
    private String username;
    private String email;
    private login_model login_model;
    private FirebaseAuth mAuth_fire;
    private GoogleSignInClient mGoogleSignInClient;

    public login_presenter() {
        this.login_model = new login_model();
        this.mAuth_fire = FirebaseAuth.getInstance();



    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void login(Context mContext , String email, String pwd_hash) {
        FirebaseUser currentUser = mAuth_fire.getCurrentUser();
        Log.d(TAG,"hellloo");
       // Log.d(TAG,"currentuser" + currentUser.getDisplayName());
       // if (currentUser == null){
            mAuth_fire.signInWithEmailAndPassword(email, pwd_hash)
                    .addOnCompleteListener(mContext.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                                Log.d(TAG,"isNew" + isNew);
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(mContext, "New User", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth_fire.getCurrentUser();
                                Intent intent = new Intent(mContext , MainActivity.class);
                                mContext.startActivity(intent);

                            } else {
                                Toast.makeText(mContext, "No newsog User", Toast.LENGTH_SHORT).show();

                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(mContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });



       // }
      //  else{
      //      Intent intent = new Intent(mContext , MainActivity.class);
      //      mContext.startActivity(intent);
//
//
      //  }


    }


    public GoogleSignInClient login_google(Context mContext) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mContext.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(mContext, gso);
        Log.d(TAG,"GoogleSIgnInCLient" + mGoogleSignInClient);
        return mGoogleSignInClient;



    }

    public String getStatisticsID(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("statuid" , Context.MODE_PRIVATE);


        return sharedPreferences.getString("statuid",null);
    }

    public String getUID(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("uid",Context.MODE_PRIVATE);
        return sharedPreferences.getString("uid",null);
    }

    public ArrayList getFriendsCompeting(Context mContext) {
        ArrayList friends = new ArrayList();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.child("Statistic").child(getStatisticsID(mContext)).child("CompetingFriends").getChildren()){
                    friends.add(dataSnapshot1.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return friends;
    }
}
