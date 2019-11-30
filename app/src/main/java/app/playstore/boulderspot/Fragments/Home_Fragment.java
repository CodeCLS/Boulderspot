package app.playstore.boulderspot.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.R;

public class Home_Fragment extends Fragment {

    private ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> grade   = new ArrayList<>();
    private  ArrayList<String> name = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();;
    private  ArrayList<String> maker  = new ArrayList<>();
    private  ArrayList<String> video_url= new ArrayList<>();
    private  ArrayList<String> ID_User  = new ArrayList<>();
    private  ArrayList<String> ID_video = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rec = view.findViewById(R.id.rec_home);
        Adapter_home adapter_event = new Adapter_home(IMG_URL,grade,name,info,maker,video_url,
                ID_User,ID_video,getContext());
        IMG_URL.add("url");
        grade.add("sd");
        name.add("as");
        info.add("as");
        maker.add("sda");
        video_url.add("sda");
        ID_User.add("sda");
        ID_video.add("sad");
        IMG_URL.add("url");
        grade.add("sd");
        name.add("as");
        info.add("as");
        maker.add("sda");
        video_url.add("sda");
        ID_User.add("sda");
        ID_video.add("sad");
        IMG_URL.add("url");
        grade.add("sd");
        name.add("as");
        info.add("as");
        maker.add("sda");
        video_url.add("sda");
        ID_User.add("sda");
        ID_video.add("sad");
        rec.setAdapter(adapter_event);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));



    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
