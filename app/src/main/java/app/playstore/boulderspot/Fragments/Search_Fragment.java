package app.playstore.boulderspot.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;

import app.playstore.boulderspot.Adapters.Adapter_search;
import app.playstore.boulderspot.Models.CardsDataAdapter;
import app.playstore.boulderspot.Models.Event_Model;
import app.playstore.boulderspot.R;

public class Search_Fragment extends Fragment {
    private EditText search_edit;
    private ImageView search_img;
    private static final String TAG = "Search_Fragment";
    private ArrayList<String> title= new ArrayList<>();
    private static ArrayList<String> IMG_URL= new ArrayList<>();
    private static ArrayList<String> Date   = new ArrayList<>();
    private static ArrayList<String> Title_boulderhalle = new ArrayList<>();
    private static ArrayList<String> Location= new ArrayList<>();;
    private static ArrayList<String> costs  = new ArrayList<>();
    private static ArrayList<String> min_age= new ArrayList<>();
    private static ArrayList<String> Grade  = new ArrayList<>();
    private static ArrayList<String> event_name = new ArrayList<>();
    private static ArrayList<String> time   = new ArrayList<>();
    private static ArrayList<String> info= new ArrayList<>();

    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_page, container, false);




    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        Log.d(TAG,"similair:" + similarity("caleb","valeb"));
        Log.d(TAG,"similair:" + similarity("cales","cralse"));
       // setOnClick();

        initRec(view);
        mCardStack = (CardStack) view.findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_view_search);
        mCardStack.setStackMargin(20);
        mCardAdapter = new CardsDataAdapter(getContext(),0);
        mCardAdapter.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fharley-davidson-Be8TdJZPaBE-unsplash.jpg?alt=media&token=7e52932f-b73a-4574-abe8-46ae8eb67a08");
        mCardAdapter.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fharley-davidson-Be8TdJZPaBE-unsplash.jpg?alt=media&token=7e52932f-b73a-4574-abe8-46ae8eb67a08");
        mCardAdapter.add("https://vignette.wikia.nocookie.net/mario/images/a/a8/Link_Artwork.png/revision/latest?cb=20130614195537&path-prefix=de");
        mCardAdapter.add("https://vignette.wikia.nocookie.net/mario/images/a/a8/Link_Artwork.png/revision/latest?cb=20130614195537&path-prefix=de");
        mCardAdapter.add("https://vignette.wikia.nocookie.net/mario/images/a/a8/Link_Artwork.png/revision/latest?cb=20130614195537&path-prefix=de");

        mCardStack.setAdapter(mCardAdapter);


    }

    private void setOnClick() {
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_string =search_edit.getText().toString();



            }
        });
    }

    private void initViews(View view) {



    }

    private void initRec(@NonNull View view) {
        RecyclerView rec = view.findViewById(R.id.rec_course);
        Adapter_search adapter_event = new Adapter_search(IMG_URL,Date,Title_boulderhalle,Location,costs,min_age,
                Grade,event_name,time,info,getContext());
    }

    private void setVals(View view) {
        IMG_URL.add(Event_Model.getImgUrl());
        Date.add(Event_Model.getDate());
        Title_boulderhalle.add(Event_Model.getTitle_boulderhalle());
        Location.add(Event_Model.getLocation());
        costs.add(Event_Model.getCosts());
        min_age.add(Event_Model.getMin_age());
        Grade.add(Event_Model.getGrade());
        event_name.add(Event_Model.getEvent_name());
        time.add(Event_Model.getTime());
        info.add(Event_Model.getInfo());


    }
    double similarity(String a, String b) {
        if(a.length() == 0) return 1;
        int numberOfSimilarities = 0;
        for(int i = 0; i < a.length(); ++i) {
            if(a.charAt(i) == b.charAt(i)) {
                ++numberOfSimilarities;
            }
        }
        return (double) numberOfSimilarities / a.length();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
