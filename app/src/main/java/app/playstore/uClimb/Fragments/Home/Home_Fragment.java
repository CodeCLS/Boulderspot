package app.playstore.uClimb.Fragments.Home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Scene;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Objects;

import app.playstore.uClimb.Adapters.Adapter_home;
import app.playstore.uClimb.Fragments.video_upload_fragment;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelFragments.current_user_presenter;
import app.playstore.uClimb.ViewModelFragments.home_posts_presenter;

public class Home_Fragment extends Fragment implements home_posts_presenter.display {
    private static final String TAG = "Home Fragment";
    private String name_fire;
    private String URL;
    private FirebaseAuth mAuth;
    final Bundle bundle = new Bundle();
    private ArrayList<String> post = new ArrayList<>();
    private ArrayList<String> IMG_URL = new ArrayList<>();
    private ArrayList<String> grade = new ArrayList<>();
    private ArrayList<String> place = new ArrayList<>();
    private ArrayList<String> seen_by = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> info = new ArrayList<>();

    private ArrayList<String> video_url = new ArrayList<>();
    private ArrayList<String> ID_User = new ArrayList<>();
    private ArrayList<String> likes = new ArrayList<>();

    //Adapter home is init
    //Firebase values are init
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");
    //Recycler view is established


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.main_page, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initreadDB(view);


        cleararrays();




    }

    private void initreadDB(View view) {
        SharedPreferences sharedPreferences_UID = getContext().getSharedPreferences("mAuth",Context.MODE_PRIVATE);




        SharedPreferences sharedPreferences = getContext().getSharedPreferences("mAuth" , Context.MODE_PRIVATE);
        String mAuth = sharedPreferences_UID.getString("mAuth_UID",null);
        Log.d(TAG,"mauth"+mAuth);

        home_posts_presenter home_posts_presenter = new home_posts_presenter(this);
        home_posts_presenter.setData(view,mAuth,getContext());

    }


    private void cleararrays() {
        IMG_URL.clear();
        name.clear();
        info.clear();
        video_url.clear();
        ID_User.clear();
        likes.clear();
        post.clear();
        grade.clear();
        place.clear();
    }




    private void fragmentTransactiontoUpload() {
        video_upload_fragment mFragment = new video_upload_fragment();
        FragmentManager fragmentManager = getFragmentManager();
        Scene mScene;

        assert fragmentManager != null;


        fragmentManager.beginTransaction().addToBackStack("video").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container_fragment, mFragment).commit();
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "my" + myRef);

    }


    @Override
    public void onResume() {
        super.onResume();


    }





    @Override
    public void allPostData(Bundle bundle) {


    }



}