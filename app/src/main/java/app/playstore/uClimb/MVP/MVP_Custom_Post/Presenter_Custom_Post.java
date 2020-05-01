package app.playstore.uClimb.MVP.MVP_Custom_Post;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Presenter_Custom_Post {

    public Presenter_Custom_Post() {
    }

    public void setupLike(Context mContext, String post_id, ImageView like){
        Presenter_Login login_presenter = new Presenter_Login();
        String uid = login_presenter.getUID(mContext);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Posts").child(post_id).child("likes").child(uid).exists()){
                    databaseReference.child("Posts").child(post_id).child("likes").child(uid).removeValue();
                    like.setImageResource(R.mipmap.like_passive);


                }
                else{
                    databaseReference.child("Posts").child(post_id).child("likes").child(uid).setValue(uid);
                    like.setImageResource(R.mipmap.like_active);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void initLike(Context mContext,ImageView like,String post_id){
        Presenter_Login login_presenter = new Presenter_Login();
        String uid = login_presenter.getUID(mContext);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Posts").child(post_id).child("likes").child(uid).exists()){
                    like.setImageResource(R.mipmap.like_passive);


                }
                else{
                    like.setImageResource(R.mipmap.like_active);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
