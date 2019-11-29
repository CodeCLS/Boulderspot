package app.playstore.boulderspot;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Event extends Fragment {
    private static final String TAG = "Event";
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_page, container, false);




    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i = 0;i< Event_Model.getSize();i++){
            Fire_Event.getEvent_attributes(i);
            setVals(view);

        }
        RecyclerView rec = view.findViewById(R.id.rec_event);
        Adapter_event adapter_event = new Adapter_event(IMG_URL,Date,Title_boulderhalle,Location,costs,min_age,
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
