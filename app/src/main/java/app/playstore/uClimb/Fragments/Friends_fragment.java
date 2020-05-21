package app.playstore.uClimb.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Friends.Presenter_Friends;

public class Friends_fragment extends Fragment {
    private ArrayList<String> array_img = new ArrayList<>();
    private ArrayList<String> array_title = new ArrayList<>();
    private ArrayList<String> array_description = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.main_friends_page, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRec(view);
        initbar(view);
    }

    private void initbar(View view) {
        Presenter_Friends friends_presenter = new Presenter_Friends(view,getContext());
        friends_presenter.initViews();

    }

    private void setRec(@NonNull View view) {
        Presenter_Friends friends_presenter = new Presenter_Friends(view,getContext());
        friends_presenter.setData();


    }







    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
