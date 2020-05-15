package app.playstore.uClimb.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import app.playstore.uClimb.Fragments.Profile.Profile_Fragment;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Statistics.Presenter_Statistics;
import dagger.internal.Beta;

public class Statistics_fragment extends Fragment {
    private static final String TAG = "statistics_fragment";
    private FrameLayout stat;
    private LinearLayout profile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        Log.d(TAG,"statistics");
        return LayoutInflater.from(getContext()).inflate(R.layout.main_statistics_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Presenter_Statistics statistics_presenter = new Presenter_Statistics(view);
        statistics_presenter.setData(view,getContext());
        initviews(view);
        nav();
        BetaMessage();



    }
    public void BetaMessage(){
        SharedPreferences sharedPreferences = ((AppCompatActivity)getContext()).getPreferences(Context.MODE_PRIVATE);
        Boolean first_time = sharedPreferences.getBoolean("First_Statistics",true);
        Log.d(TAG,"First_Statistics" + first_time);

        if (first_time){
            Dialog dialog = new AlertDialog.Builder(getContext())
                    .setTitle(R.string.Statistiken)
                    .setMessage(getResources().getString(R.string.statistiken_message) )

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            dialog.dismiss();

                        }
                    })

                    .show();

            SharedPreferences.Editor shared_ed = sharedPreferences.edit();
            shared_ed.putBoolean("First_Statistics",false);
            shared_ed.apply();

        }
        else{
            return;
        }



    }


    private void initviews(View view) {
        stat = view.findViewById(R.id.frame_profile_statistics);
        profile = view.findViewById(R.id.frame_profile_up);


    }

    private void nav() {
        stat.setOnClickListener(v -> {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(Statistics_fragment.this).attach(Statistics_fragment.this).commit();
            //TODO Fragmennt transaction
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile_Fragment mFragment = new Profile_Fragment();

                FragmentManager fragmentManager = ((AppCompatActivity) Objects.requireNonNull(getContext())).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment,mFragment).commit();




            }
        });
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }
}
