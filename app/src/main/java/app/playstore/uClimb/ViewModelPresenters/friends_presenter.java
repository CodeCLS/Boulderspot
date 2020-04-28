package app.playstore.uClimb.ViewModelPresenters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Adapter_community;
import app.playstore.uClimb.Adapters.Friends_Adapter;
import app.playstore.uClimb.Fragments.Friends_fragment;
import app.playstore.uClimb.Fragments.Location_fragment;
import app.playstore.uClimb.Fragments.Profile.Profile_Fragment;
import app.playstore.uClimb.Fragments.Statistics_fragment;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class friends_presenter {
    private ArrayList<String> friends_id = new ArrayList<>();
    private ArrayList<String> friends_name = new ArrayList<>();
    private ArrayList<String> friends_img_url = new ArrayList<>();

    private View view;
    private Context mContext;
    friends_model friends_model;
    private Friends_Adapter friends_adapter = new Friends_Adapter(friends_img_url,friends_name,friends_id);

    public friends_presenter(View view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        this.friends_model  = new friends_model();

    }
    public void setData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearArray();
                login_presenter login_presenter = new login_presenter();
                String id = login_presenter.getUID(mContext);
                Log.d(TAG,"uid"+id);
                Log.d(TAG,"friends"+dataSnapshot.child("User").child(id).child("Friends").getChildren());
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").child(id).child("Friends").getChildren()){
                    friends_id.add(postSnapshot.getKey());
                    String name = dataSnapshot.child("User").child(postSnapshot.getKey()).child("Name").getValue().toString();
                    friends_name.add(name);
                    String url = dataSnapshot.child("User").child(postSnapshot.getKey()).child("IMG").getValue().toString();
                    friends_img_url.add(url);

                }
                initRec();

            }

            private void clearArray() {
                friends_id.clear();
                friends_name.clear();
                friends_img_url.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initRec() {
        RecyclerView rec;

        rec = view.findViewById(R.id.friends_rec);
        rec.setAdapter(friends_adapter);

        rec.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public void initViews() {
        LinearLayout friends_linear = view.findViewById(R.id.friends_linear);
        LinearLayout location_linear = view.findViewById(R.id.location_linear);


        location_linear.setOnClickListener(v -> {
            Location_fragment location_fragment = new Location_fragment();
            FragmentManager fragmentManager = ((AppCompatActivity) Objects.requireNonNull(mContext)).getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Fragment_location").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container_fragment,location_fragment).commit();



        });

        friends_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friends_fragment friends_fragment = new Friends_fragment();
                FragmentManager fragmentManager = ((AppCompatActivity) Objects.requireNonNull(mContext)).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_friends").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment,friends_fragment).commit();


            }
        });
    }
}
