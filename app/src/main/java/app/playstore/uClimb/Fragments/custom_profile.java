package app.playstore.uClimb.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Custom_Profile.Presenter_Custom_Profile;

public class custom_profile extends Fragment {
    private static final String TAG = "customprofile";
    private Context mContext;
    private String uid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mContext = Objects.requireNonNull(container).getContext();
        return LayoutInflater.from(mContext).inflate(R.layout.main_custom_profile_page_ui,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        uid = Objects.requireNonNull(bundle).getString("uid");

        Presenter_Custom_Profile custom_profile_presenter = new Presenter_Custom_Profile(uid);
        custom_profile_presenter.init(mContext,view);


    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
