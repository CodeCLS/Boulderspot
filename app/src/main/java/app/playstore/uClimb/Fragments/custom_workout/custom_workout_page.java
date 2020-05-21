package app.playstore.uClimb.Fragments.custom_workout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.flarebit.flarebarlib.TabEventObject;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Friends_fragment;
import app.playstore.uClimb.Fragments.Home.Home_Fragment;
import app.playstore.uClimb.Fragments.Profile.Profile_Fragment;
import app.playstore.uClimb.Fragments.Search.Search_Fragment;
import app.playstore.uClimb.MVP.MVP_Workout.Presenter_Workout;
import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class custom_workout_page extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.private_custom_workout_page,container,false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);





    }

    private void initViews(View view) {

        Presenter_Workout presenter_workout1 = new Presenter_Workout();
        presenter_workout1.initRecWorkout(getContext(),view,"Workout");
        CircleImageView circleImageView = view.findViewById(R.id.upload_btn_custom_);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload(view,getContext());

            }
        });
        CircleImageView circleImageView2 = view.findViewById(R.id.user_upload_btn_custom_);
        circleImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile(view,getContext());

            }
        });

        //upload(view,getContext());

        FlareBar bottomBar = view.findViewById(R.id.flarebar_custom_workout);
        bottomBar.setBarBackgroundColor(Color.parseColor("#FFFFFF"));

        ArrayList<Flaretab> tabs = new ArrayList<>();
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.inboxb),"Training","#FFECB3"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.searchb),"Dehnen","#80DEEA"));
        tabs.add(new Flaretab(getResources().getDrawable(R.drawable.phoneb),"Aufwärmen","#B39DDB"));



        bottomBar.setTabList(tabs);
        bottomBar.attachTabs(getContext());
         bottomBar.setTabChangedListener((selectedTab, selectedIndex, oldIndex) -> {
             //tabIndex starts from 0 (zero). Example : 4 tabs = last Index - 3
             if (selectedIndex == 0) {
                 Presenter_Workout presenter_workout = new Presenter_Workout();
                 presenter_workout.initRecWorkout(getContext(),view,"Workout");



             }

             if (selectedIndex == 1) {
                 Presenter_Workout presenter_workout = new Presenter_Workout();
                 presenter_workout.initRecWorkout(getContext(),view,"Stretching");

             }

             if (selectedIndex == 2) {
                 Presenter_Workout presenter_workout = new Presenter_Workout();
                 presenter_workout.initRecWorkout(getContext(),view,"Warmup");

             }
            //if (selectedIndex == 3) {
            //    Fragment_Upload_Custom fragment_training_custom = new Fragment_Upload_Custom();
            //    FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
            //    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,fragment_training_custom).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            //    fragmentTransaction.commit();

            //}


             //           .replace(R.id.container_fragment, mFragment).commit();
             //   setAlpha(1);

         });





         //img_home = includeView.findViewById(R.id.img_home);
         //img_event = includeView.findViewById(R.id.img_event);
         //img_course = includeView.findViewById(R.id.img_training);
         //img_location = includeView.findViewById(R.id.img_location);

         //line_course = includeView.findViewById(R.id.line_img_training);
         //line_home = includeView.findViewById(R.id.line_img_home);
         //line_location = includeView.findViewById(R.id.line_img_location);
         //line_event = includeView.findViewById(R.id.line_img_event);




    }

    private void profile(View view, Context context) {
        Fragment_Profile_Custom fragment_training_custom = new Fragment_Profile_Custom();
        FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,fragment_training_custom).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

    }

    private void upload(View view, Context context) {
        Fragment_Upload_Custom fragment_training_custom = new Fragment_Upload_Custom();
        FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,fragment_training_custom).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


}
