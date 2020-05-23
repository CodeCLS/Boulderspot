package app.playstore.uClimb.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;

public class Settings_Fragment extends Fragment {
    private static final String TAG = "Settings_Fragment";
    private SharedPreferences sharedPreferences;
    private Boolean shared;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        sharedPreferences = Objects.requireNonNull(container.getContext()).getSharedPreferences("Switch_Location",Context.MODE_PRIVATE);

        return inflater.inflate(R.layout.main_settings_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Switch switch_location = init(view);
        initButton(view);

        switch_box(switch_location);




    }

    private void initButton(View view) {
        Button feedback_btn = view.findViewById(R.id.feedback_btn);
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",getString(R.string.email_boulderspot), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback));
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.email_schicken)));

            }
        });
    }

    private Switch init(@NonNull View view) {
        shared = sharedPreferences.getBoolean("Switch_Public",false);
        return view.findViewById(R.id.switch_location);
    }

    private void switch_box(Switch switch_location) {
        switch_location.setChecked(shared);
        switch_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Presenter_Login presenter_login = new Presenter_Login();
                String uid = presenter_login.getUID(getContext());


                if (isChecked){
                    switch_location.setChecked(true);
                    SharedPreferences.Editor shared_edit = sharedPreferences.edit();
                    shared_edit.putBoolean("Switch_Public",true);
                    shared_edit.apply();
                    Log.d(TAG,"switch_state" + sharedPreferences.getBoolean("Switch_Location",true));
                    shared = true;

                    databaseReference.child("Public_Users").child(uid).setValue(uid);



                }
                if (!isChecked){
                    switch_location.setChecked(false);
                    SharedPreferences.Editor shared_edit = sharedPreferences.edit();
                    shared_edit.putBoolean("Switch_Public",false);
                    shared_edit.apply();
                    shared_edit.commit();
                    shared = false;
                    databaseReference.child("Public_Users").child(uid).removeValue();



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
