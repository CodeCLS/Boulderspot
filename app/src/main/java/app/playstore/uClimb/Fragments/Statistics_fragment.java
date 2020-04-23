package app.playstore.uClimb.Fragments;

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
import app.playstore.uClimb.ViewModelPresenters.statistics_presenter;

public class Statistics_fragment extends Fragment {
    private static final String TAG = "statistics_fragment";
    private FrameLayout stat;
    private LinearLayout profile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"statistics");
        return LayoutInflater.from(getContext()).inflate(R.layout.statistics_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statistics_presenter statistics_presenter = new statistics_presenter(view);
        statistics_presenter.setData(view,getContext());
        initviews(view);
        nav();



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
