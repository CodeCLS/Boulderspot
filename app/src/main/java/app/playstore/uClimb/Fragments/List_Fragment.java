package app.playstore.uClimb.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.uClimb.Adapters.Statistics_List_Adapter;
import app.playstore.uClimb.R;

public class List_Fragment extends Fragment {
    private static final String TAG = "List_Fragment";
    private ArrayList training_boulder_time = new ArrayList<String>();
    private ArrayList training_boulder_grade= new ArrayList<String>();
    private ArrayList training_boulder_notes= new ArrayList<String>();

    private ArrayList competition_name= new ArrayList<String>();
    private ArrayList competition_Accomplishment= new ArrayList<String>();

    private ArrayList training_session_title= new ArrayList<String>();
    private ArrayList training_session_time= new ArrayList<String>();

    private ArrayList training_session_notes;
    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();

    //private Statistics_List_Adapter statistics_list_adapter_boulder = new Statistics_List_Adapter(training_boulder_tries,training_boulder_time,training_boulder_grade,training_boulder_notes,"Boulder");
    //private Statistics_List_Adapter statistics_list_adapter_sessions = new Statistics_List_Adapter(training_session_title,training_session_time,training_session_notes,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time,"Workout");
    //private Statistics_List_Adapter statistics_list_adapter_friends = new Statistics_List_Adapter(competition_name,competition_Accomplishment,"Friends");


    public List_Fragment( ArrayList training_boulder_time, ArrayList training_boulder_grade, ArrayList training_boulder_notes) {
        this.training_boulder_time = training_boulder_time;
        this.training_boulder_grade = training_boulder_grade;
        this.training_boulder_notes = training_boulder_notes;
    }

    public List_Fragment(ArrayList competition_name, ArrayList competition_Accomplishment) {
        this.competition_name = competition_name;
        this.competition_Accomplishment = competition_Accomplishment;
    }

    public List_Fragment(ArrayList training_session_title, ArrayList training_session_time, ArrayList training_session_notes, ArrayList<String> sesssions_train_time, ArrayList<String> sesssions_pause_time, ArrayList<String> sesssions_rest_time, ArrayList<String> sesssions_sets_time, ArrayList<String> sesssions_rounds_time) {
        this.training_session_title = training_session_title;
        this.training_session_time = training_session_time;
        this.training_session_notes = training_session_notes;
        this.sesssions_train_time = sesssions_train_time;
        this.sesssions_pause_time = sesssions_pause_time;
        this.sesssions_rest_time = sesssions_rest_time;
        this.sesssions_sets_time = sesssions_sets_time;
        this.sesssions_rounds_time = sesssions_rounds_time;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_view,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        assert bundle != null;
        String type = bundle.getString("Type");
        RecyclerView recyclerView = view.findViewById(R.id.rec_list_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG,"training_boulder_tries" + training_boulder_grade);


        if (type.equals("Friends")){
            Log.d(TAG,"123456");
            Statistics_List_Adapter statistics_list_adapter_friends = new Statistics_List_Adapter(competition_name,competition_Accomplishment,"Friends");

            recyclerView.setAdapter(statistics_list_adapter_friends);


        }
        if (type.equals("Workout")){
            Log.d(TAG,"1234567" + training_session_time);
            Statistics_List_Adapter statistics_list_adapter_sessions = new Statistics_List_Adapter(training_session_title,training_session_time,training_session_notes,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time,"Workout");

            recyclerView.setAdapter(statistics_list_adapter_sessions);


        }
        if (type.equals("Boulder")){
            Log.d(TAG,"12345678");
            Statistics_List_Adapter statistics_list_adapter_boulder = new Statistics_List_Adapter(training_boulder_time,training_boulder_grade,training_boulder_notes,"Boulder");

            recyclerView.setAdapter(statistics_list_adapter_boulder);


        }
    }
}
