package app.playstore.uClimb.Fragments.Profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import app.playstore.uClimb.Fragments.Statistics_fragment;
import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.profile_presenter;

public class Profile_Fragment extends Fragment {
    private static final int CHANGE_PROFILE_PIC_CODE = 210;
    private FrameLayout stat;
    private  RecyclerView recyclerView;
    private LinearLayout profile_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "Hey", Toast.LENGTH_SHORT).show();
        initViews(view);
        nav();
        setRecyclerview(view);





    }


    private void setRecyclerview(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        profile_presenter profile_presenter = new profile_presenter(getContext());
        profile_presenter.setRecyclerview(getContext(),view);
    }


    private void nav() {
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Fragmennt transaction
                Statistics_fragment mFragment = new Statistics_fragment();

                FragmentManager fragmentManager = ((AppCompatActivity) Objects.requireNonNull(getContext())).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_stat").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment,mFragment).commit();
            }
        });
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Profile_Fragment.this).attach(Profile_Fragment.this).commit();

            }
        });
    }


    private void initViews(View view) {
         recyclerView = view.findViewById(R.id.profile_rec);
         stat = view.findViewById(R.id.frame_profile_statistics);
         profile_img = view.findViewById(R.id.frame_profile_up);



    }

}
