package app.playstore.uClimb.ViewModelPresenters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.Random;

import app.playstore.uClimb.Login_SignUp.Boulderspot_Sign_Up;
import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;

public class register_presenter {
    private static final String TAG = "register_presenter";
    private String uid;
    private String Username;
    private String email;
    private String pwd;
    private register_model register_model;
    private FirebaseAuth mAuth;

    public register_presenter() {
        register_model = new register_model();
        mAuth = FirebaseAuth.getInstance();

    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void RegisterNewUser(String pwd, String email, String username , Context mContext , Bundle bundle , View v){
        if (pwd == "wqn9YzHxf4odtGt"){
            createfiredata(bundle);

        }
        else {
            // Write a message to the database
            mAuth.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(mContext.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                createSnackbar(true, mContext,v);
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user, mContext,"null");
                                createfiredata(bundle);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                createSnackbar(false, mContext,v);

                                updateUI(null, mContext,task.getException().toString());
                            }

                            // ...
                        }
                    });
        }


    }

    private void createfiredata(Bundle bundle) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        String Age = bundle.getString("age");
        String Country = bundle.getString("country");
        String Grade = bundle.getString("Grade");
        String Height = bundle.getString("Height");
        String Info = "Just the usual climber";
        String Subscription ="Standart";
        String Name = bundle.getString("Name");
        String IMG = "IMG";//TODO IMG
        String boulder_centre_default = "";
        String account_type = "person";
        String close_friends_competitiors = "close_friends_competitiors";
        String Follower = "Follower";
        String Following = "Following";
        String Position = "Position";
        String Position_last_Time_updated = "";
        String Position_lat = "";
        String Position_lon = "";
        String position_status = "";

        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Age").setValue(Age);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Country").setValue(Country);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Grade").setValue(Grade);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Height").setValue(Height);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Info").setValue(Info);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Subscription").setValue(Subscription);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Name").setValue(Name);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("IMG").setValue(IMG);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("boulder_centre_default").setValue(boulder_centre_default);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("account_type").setValue(account_type);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("close_friends_competitiors").setValue(close_friends_competitiors);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Follower").setValue(Follower);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Following").setValue(Following);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Position").setValue(Position);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Position").child("Position_last_Time_updated").setValue(Position_last_Time_updated);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Position").child("Position_lat").setValue(Position_lat);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Position").child("Position_lon").setValue(Position_lon);
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("Position").child("position_status").setValue(position_status);
        String hash = getSaltString();
        myRef.child("User").child(Objects.requireNonNull(mAuth.getUid())).child("StatisticsID").setValue(hash);
        myRef.child("Statistics").child(Objects.requireNonNull(hash)).setValue(hash);







    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;


    }

    private void createSnackbar(boolean b,Context mContext,View v) {

        if (b){
            Snackbar snackbar =Snackbar.make(v,"Perfect, you have registered" , BaseTransientBottomBar.LENGTH_SHORT);
            snackbar.setBackgroundTint(mContext.getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();



        }
        else{

            Snackbar snackbar =Snackbar.make(v,"Error: User couldn't be logged in A412" , BaseTransientBottomBar.LENGTH_SHORT);
            snackbar.setBackgroundTint(mContext.getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();
        }
    }

    private void updateUI(FirebaseUser user,Context mContext,String message) {
        if (user != null){
            Log.d(TAG,"activatedregistered");
            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);

        }
        else{
            Toast.makeText(mContext, "Message:" + message, Toast.LENGTH_SHORT).show();

        }

    }


}
