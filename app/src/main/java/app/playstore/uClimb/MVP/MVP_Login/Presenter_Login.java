package app.playstore.uClimb.MVP.MVP_Login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.ArrayList;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Statistics.Presenter_Statistics;

public class Presenter_Login {
    private static final String TAG = "login_presenter";
    private String mAuth;
    private String pwd_hash;
    private String username;
    private String email;
    private Model_Login login_model;
    private FirebaseAuth mAuth_fire;
    private GoogleSignInClient mGoogleSignInClient;

    public Presenter_Login() {
        this.login_model = new Model_Login();
        this.mAuth_fire = FirebaseAuth.getInstance();



    }
    public void FirebaseLink(Context mContext,Activity activity){
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(activity.getIntent())
                .addOnSuccessListener(activity, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            if (deepLink.equals("Post_Link")){

                            }
                        }
                        else{
                            return;
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

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("UID",Context.MODE_PRIVATE);
                                        SharedPreferences.Editor shared_edit = sharedPreferences.edit();
                                        shared_edit.putString("UID",user.getUid().trim().toString());
                                        shared_edit.apply();

                                        SharedPreferences sharedPreferences_stat = mContext.getSharedPreferences("UID_STAT",Context.MODE_PRIVATE);
                                        SharedPreferences.Editor shared_edit_stat = sharedPreferences_stat.edit();
                                        shared_edit_stat.putString("UID_STAT",dataSnapshot.child(user.getUid()).child("StatisticsID").getValue().toString());
                                        shared_edit_stat.apply();
                                        Intent intent = new Intent(mContext , MainActivity.class);
                                        mContext.startActivity(intent);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });



                            } else {

                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(mContext, "Authentication failed." + task.getException().getLocalizedMessage(),
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
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("UID_STAT" , Context.MODE_PRIVATE);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String stat_uid = dataSnapshot.child("User").child(getUID(mContext)).child("StatisticsID").getValue().toString();
                Log.d(TAG,"STAT24"+stat_uid);
                if (stat_uid.equals(sharedPreferences.getString("UID_STAT",null))){
                    Log.d(TAG,"STAT26"+stat_uid);
                }
                else{
                    Log.d(TAG,"STAT25"+stat_uid);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("UID_STAT",stat_uid);
                    editor.apply();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return sharedPreferences.getString("UID_STAT",null);
    }

    public String getUID(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("UID",Context.MODE_PRIVATE);
        Log.d(TAG,"UID"+sharedPreferences.getString("UID",null));
        return sharedPreferences.getString("UID",null);
    }

    public void getFriendsCompeting(Context mContext, View view) {
        ArrayList friends = new ArrayList();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"UID23"+getUID(mContext));

                for (DataSnapshot dataSnapshot1: dataSnapshot.child("User").child(getUID(mContext)).child("Friends").getChildren()){
                    friends.add(dataSnapshot1.getKey());
                    Log.d(TAG,"login_presenter"+friends);
                    Firebase_friends_callback.onCallback(friends,mContext,view);

                }
                Firebase_friends_callback.onCallback(friends,mContext,view);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public interface Firebase_friends_callback {
        static void onCallback(ArrayList value,Context mContext,View view) {
            Presenter_Statistics statistics_presenter = new Presenter_Statistics();
            statistics_presenter.getFireData(mContext,value,view);


        }
    }

}

