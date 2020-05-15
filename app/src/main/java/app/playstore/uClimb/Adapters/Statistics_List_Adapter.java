package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Statistics_List_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Statistics_list";
    private String intent_type;
    private Context context;

    private ArrayList training_boulder_tries = new ArrayList<String>();
    private ArrayList training_boulder_time = new ArrayList<String>();
    private ArrayList training_boulder_grade = new ArrayList<String>();
    private ArrayList training_boulder_notes = new ArrayList<String>();

    private ArrayList competition_name = new ArrayList<String>();
    private ArrayList competition_Accomplishment = new ArrayList<String>();

    private ArrayList training_session_title = new ArrayList<String>();
    private ArrayList training_session_time = new ArrayList<String>();

    private ArrayList training_session_notes = new ArrayList<String>();
    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();

    public Statistics_List_Adapter(ArrayList training_boulder_tries, ArrayList training_boulder_time, ArrayList training_boulder_grade, ArrayList training_boulder_notes,String intent_type) {
        this.training_boulder_tries = training_boulder_tries;
        this.training_boulder_time = training_boulder_time;
        this.training_boulder_grade = training_boulder_grade;
        this.training_boulder_notes = training_boulder_notes;
        this.intent_type = intent_type;
    }

    public Statistics_List_Adapter(ArrayList competition_name, ArrayList competition_Accomplishment,String intent_type) {
        this.competition_name = competition_name;
        this.competition_Accomplishment = competition_Accomplishment;
        this.intent_type = intent_type;

    }

    public Statistics_List_Adapter(ArrayList training_session_title, ArrayList training_session_time, ArrayList training_session_notes, ArrayList<String> sesssions_train_time, ArrayList<String> sesssions_pause_time, ArrayList<String> sesssions_rest_time, ArrayList<String> sesssions_sets_time, ArrayList<String> sesssions_rounds_time,String intent_type) {
        this.training_session_title = training_session_title;
        this.training_session_time = training_session_time;
        this.training_session_notes = training_session_notes;
        this.sesssions_train_time = sesssions_train_time;
        this.sesssions_pause_time = sesssions_pause_time;
        this.sesssions_rest_time = sesssions_rest_time;
        this.sesssions_sets_time = sesssions_sets_time;
        this.sesssions_rounds_time = sesssions_rounds_time;
        this.intent_type = intent_type;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        Log.d(TAG,"holder354"+viewType);

        if (viewType == 1){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.private_statistics_friends_competing_ui, parent, false);
            viewHolder = new Friends_ViewHolder(view);


        }
        if (viewType == 2){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.private_statistics_friends_competing_ui, parent, false);
            viewHolder = new Boulder_ViewHolder(view);

        }
        if (viewType == 3){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.private_statistics_friends_competing_ui, parent, false);
            viewHolder = new Workout_ViewHolder(view);

        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG,"holder354"+holder.getItemViewType());
        if (holder.getItemViewType() == 1){
            Log.d(TAG,"2323" + competition_Accomplishment);
            Friends_ViewHolder friends_viewHolder = (Friends_ViewHolder) holder;

            friends_viewHolder.accomplishment__txt.setText(competition_Accomplishment.get(position).toString());
            friends_viewHolder.name_txt.setText(competition_name.get(position).toString());



        }
        if (holder.getItemViewType() == 2){
            Log.d(TAG,"2323" + training_boulder_time);

            Boulder_ViewHolder friends_viewHolder = (Boulder_ViewHolder) holder;
            friends_viewHolder.grade_boulder_txt.setText(training_boulder_grade.get(position).toString());
            friends_viewHolder.tries_boulder_txt.setText(training_boulder_tries.get(position).toString());
            friends_viewHolder.time_boulder_txt.setText(training_boulder_time.get(position).toString());
            friends_viewHolder.info_boulder__txt.setText(training_boulder_notes.get(position).toString());




        }
        if (holder.getItemViewType() == 3){
            Workout_ViewHolder friends_viewHolder = (Workout_ViewHolder) holder;
            Log.d(TAG,"2323" + sesssions_train_time);

            friends_viewHolder.info_txt.setText(training_session_notes.get(position).toString());
            friends_viewHolder.hang_txt.setText(sesssions_train_time.get(position).toString());
            friends_viewHolder.pause_txt.setText(sesssions_pause_time.get(position).toString());
            friends_viewHolder.rest_txt.setText(sesssions_rest_time.get(position).toString());
            friends_viewHolder.sets_txt.setText(sesssions_sets_time.get(position).toString());
            friends_viewHolder.rounds_txt.setText(sesssions_rounds_time.get(position).toString());
            friends_viewHolder.type_session_txt.setText(training_session_title.get(position).toString());
            friends_viewHolder.time_txt.setText(training_session_time.get(position).toString());





        }


    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (intent_type.equals("Friends")){

            count = competition_name.size();
            Log.d(TAG,"count23Friends"+count);


        }
        if (intent_type.equals("Boulder")){
            count = training_boulder_grade.size();
            Log.d(TAG,"count23Boulder"+count);


        }
        if (intent_type.equals("Workout")){
            count = sesssions_pause_time.size();
            Log.d(TAG,"count23Workout"+count);


        }
        Log.d(TAG,"count23" + count);
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (intent_type.equals("Friends")){
            i =1;

        }
        if (intent_type.equals("Boulder")){
            i = 2;

        }
        if (intent_type.equals("Workout")){
            i = 3;

        }
        return i;
    }

    private class Friends_ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_txt;
        private TextView accomplishment__txt;
        public Friends_ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.name_txt_competition);
            accomplishment__txt = itemView.findViewById(R.id.accomplished_txt_competition);

        }
    }

    private class Boulder_ViewHolder extends RecyclerView.ViewHolder {
        private TextView tries_boulder_txt;
        private TextView info_boulder__txt;
        private TextView time_boulder_txt;
        private TextView grade_boulder_txt;
        public Boulder_ViewHolder(View view) {
            super(view);
            info_boulder__txt = view.findViewById(R.id.txt_info_boulder);
            tries_boulder_txt = view.findViewById(R.id.txt_boulder_tries);
            time_boulder_txt = view.findViewById(R.id.txt_time_boulder);
            grade_boulder_txt = view.findViewById(R.id.txt_grade_boulder);
        }
    }

    private class Workout_ViewHolder extends RecyclerView.ViewHolder {
        private TextView type_session_txt;
        private TextView info_txt;
        private TextView time_txt;
        private TextView hang_txt;
        private TextView pause_txt;
        private TextView rest_txt;
        private TextView sets_txt;
        private TextView rounds_txt;


        //TODO change
        public Workout_ViewHolder(View view) {
            super(view);
            type_session_txt = view.findViewById(R.id.txt_session_type);
            info_txt = view.findViewById(R.id.txt_info_session);
            time_txt = view.findViewById(R.id.txt_time_session);
            hang_txt = view.findViewById(R.id.txt_train_time_sessions);
            pause_txt = view.findViewById(R.id.txt_pause_sessions);
            rest_txt = view.findViewById(R.id.txt_rest_sessions);
            sets_txt = view.findViewById(R.id.txt_sets_sessions);
            rounds_txt = view.findViewById(R.id.txt_rounds_sessions);


        }
    }
}
