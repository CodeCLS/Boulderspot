package app.playstore.uClimb.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Objects;

import app.playstore.uClimb.R;

public class Settings_Fragment extends Fragment {
    private static final String TAG = "Settings_Fragment";
    private SharedPreferences sharedPreferences;
    private Boolean shared;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = Objects.requireNonNull(container.getContext()).getSharedPreferences("Switch_Location",Context.MODE_PRIVATE);

        return inflater.inflate(R.layout.settings_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shared = sharedPreferences.getBoolean("Switch_Location",false);
        Switch switch_location = view.findViewById(R.id.switch_location);
        Log.d(TAG,"switch_state" + sharedPreferences.getBoolean("Switch_Location",false));

        switch_location.setChecked(shared);
        switch_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    switch_location.setChecked(true);
                    SharedPreferences.Editor shared_edit = sharedPreferences.edit();
                    shared_edit.putBoolean("Switch_Location",true);
                    shared_edit.apply();
                    Log.d(TAG,"switch_state" + sharedPreferences.getBoolean("Switch_Location",true));
                    shared = true;



                }
                if (!isChecked){
                    switch_location.setChecked(false);
                    SharedPreferences.Editor shared_edit = sharedPreferences.edit();
                    shared_edit.putBoolean("Switch_Location",false);
                    shared_edit.apply();
                    shared_edit.commit();
                    shared = false;

                }
                // do something, the isChecked will be
                // true if the switch is in the On position
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
