package app.playstore.uClimb.Adapters.Statistics;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.SolidFill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import app.playstore.uClimb.Fragments.List_Fragment;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;

public class Adapter_Statistics extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> training_sessions_time = new ArrayList<>();
    private ArrayList<String> training_sessions_types = new ArrayList<>();
    private ArrayList<String> boulders_times = new ArrayList<>();
    private ArrayList<String> boulders_grade = new ArrayList<>();
    private ArrayList<String> boulders_tries = new ArrayList<>();
    private ArrayList<Integer> competition_array_boulderpoints = new ArrayList<>();
    private ArrayList<String> competition_array_uid = new ArrayList<>();
    private HashMap<String,Integer> bouldermap = new HashMap<>();
    private HashMap<String,Integer> sessionmap = new LinkedHashMap<>();


    private ArrayList<String> competition_array_names = new ArrayList<>();

    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();

    private ArrayList<String> arrayList_sessions = new ArrayList<>();
    private ArrayList<String> arrayList_amount = new ArrayList<>();




    private ArrayList<String> training_sessions_notes = new ArrayList<>();

    private ArrayList<String> boulder_notes = new ArrayList<>();




    ArrayList<String> arrayList_boulder = new ArrayList<String>();
    ArrayList<String> arrayList_tries = new ArrayList<String>();
    private static final String TAG = "Adapter_statistic";

    private Pie pie_sessions;
    private List<DataEntry> data_sessions;

    private Pie pie_grade;

    private List<DataEntry> data_grade;

    private Cartesian bar_competition;
    private List<DataEntry> data_competition;
    private Context mContext;

    public Adapter_Statistics(ArrayList<String> boulder_grade,ArrayList<String> training_sessions_hang,ArrayList<String> training_sessions_pause,ArrayList<String> training_sessions_rest,ArrayList<String> training_sessions_sets,ArrayList<String> training_sessions_round,ArrayList<String> training_sessions_notes,HashMap<String, Integer> sessionmap,ArrayList<String> training_sessions_time, ArrayList<String> training_sessions_types, ArrayList<String> boulders_times, HashMap boulders_grade, ArrayList<String> boulders_tries, ArrayList<Integer> competition_array_boulderpoints, ArrayList<String> competition_array_uid, ArrayList<String> competition_array_names, ArrayList<String> boulder_notes ) {
        this.sessionmap = sessionmap;
        this.training_sessions_time = training_sessions_time;
        this.training_sessions_types = training_sessions_types;
        this.boulders_times = boulders_times;
        this.bouldermap = boulders_grade;
        this.boulders_tries = boulders_tries;
        this.competition_array_boulderpoints = competition_array_boulderpoints;
        this.competition_array_uid = competition_array_uid;
        this.competition_array_names = competition_array_names;
        this.sesssions_train_time = training_sessions_hang;
        this.sesssions_pause_time = training_sessions_pause;
        this.sesssions_rest_time = training_sessions_rest;
        this.sesssions_sets_time = training_sessions_sets;
        this.sesssions_rounds_time = training_sessions_round;
        this.training_sessions_notes = training_sessions_notes;
        this.boulders_grade = boulder_grade;

        this.boulder_notes = boulder_notes;
        Log.d(TAG,"trainingseession"+boulders_times + boulder_notes+boulders_times+boulders_tries);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        Toast.makeText(mContext, R.string.lädt, Toast.LENGTH_SHORT).show();

        View view;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.sessions,parent,false);
            viewHolder= new sessions_View_holder(view);


        }
        if (viewType == 1){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_grades_accomplished,parent,false);
            viewHolder= new Grades_View_holder(view);

        }
        if (viewType == 2){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_statistics_friends_competing_ui,parent,false);
            viewHolder= new competition_View_holder(view);


        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        this.pie_grade = AnyChart.pie();
        this.pie_sessions = AnyChart.pie();
        this.bar_competition = AnyChart.column();
        this.data_sessions = new ArrayList<>();
        this.data_grade = new ArrayList<>();
        this.data_competition = new ArrayList<>();

        if (holder.getItemViewType() == 0){
            Log.d(TAG,"sessionmap2: " + sessionmap + "  array_types: " + training_sessions_types);

            sessions_View_holder view_holder = (sessions_View_holder) holder;
            ArrayList<String> arrayList_key_set = new ArrayList<>();

            for (int i = 0;i<training_sessions_types.size();i++){
                Log.d(TAG,"key_set23" + sessionmap.keySet() + " keysetsize: " + training_sessions_types.size() + " " +arrayList_key_set + " position: " + i);
                if (!arrayList_key_set.contains(training_sessions_types.get(i))){
                    Integer integer = sessionmap.get(training_sessions_types.get(i));
                    arrayList_sessions.add(training_sessions_types.get(i));
                    arrayList_amount.add(integer + "");
                    arrayList_key_set.add(training_sessions_types.get(i));


                    data_sessions.add(new ValueDataEntry(training_sessions_types.get(i),integer));
                    Log.d(TAG,"2323" + arrayList_key_set);

                }



            }


            //Integer.valueOf(training_sessions_amount.get(position))
            //training_sessions_types.get(position)


            view_holder.button_sessions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"");
                    transaction_more(0);
                }
            });

                pie_sessions.data(data_sessions);




                view_holder.anyChartView.setChart(pie_sessions);




        }
        if (holder.getItemViewType() == 1){




            Grades_View_holder view_holder = (Grades_View_holder) holder;


            ArrayList<String> arrayList_key_set_2 = new ArrayList<>();

            for (int i = 0;i<boulders_grade.size();i++){
                Log.d(TAG,"key_set24" + bouldermap.keySet() + " keysetsize: " + boulders_grade.size() + " " +arrayList_key_set_2 + " position: " + i);
                if (!arrayList_key_set_2.contains(boulders_grade.get(i))){
                    Integer integer = bouldermap.get(boulders_grade.get(i));
                    arrayList_boulder.add(boulders_grade.get(i));
                    arrayList_tries.add(integer + "");
                    arrayList_key_set_2.add(boulders_grade.get(i));


                    data_grade.add(new ValueDataEntry(boulders_grade.get(i),integer));
                    Log.d(TAG,"2323" + arrayList_key_set_2);

                }



            }
                view_holder.button_boulder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        transaction_more(1);
                    }
                });





            pie_grade.data(data_grade);
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("#068799");
            arrayList.add("#AE6272");
            arrayList.add("#CE9BA9");
            arrayList.add("#B39DDB");
            arrayList.add("#E25353");
            arrayList.add("#EF9A9A");
            arrayList.add("#F7C83B");
            arrayList.add("#FFECB3");
            arrayList.add("#F7C83B");


            for (int i = 0;i<data_grade.size();i++){
                SolidFill solidFill = new SolidFill(arrayList.get(i),1);
                pie_grade.palette().itemAt(i,solidFill);


            }



            view_holder.anyChartView.setChart(pie_grade);











            //boulders_grade.get(position), Integer.valueOf(training_sessions_amount.get(position))




        }
        if (holder.getItemViewType() == 2){

            competition_View_holder view_holder = (competition_View_holder) holder;
            view_holder.button_friends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction_more(2);
                }
            });

            int points = 0;
            Log.d(TAG,"points_adapter" + competition_array_names);



            for (int i = 0; i< competition_array_names.size();i++){
                Log.d(TAG,"points_adapter2" + competition_array_uid);


                int competition_gradepoints_s= competition_array_boulderpoints.get(i);
                String competition_name = competition_array_names.get(i);
                data_competition.add(new ValueDataEntry(competition_name,competition_gradepoints_s));













            }
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Presenter_Login presenter_login = new Presenter_Login();
                    String uid = presenter_login.getUID(mContext);
                    String name = "Du";
                    Double points = Double.parseDouble(dataSnapshot.child("User").child(uid).child("Points").getValue().toString());

                    data_competition.add(new ValueDataEntry(name,points));


                    setStatisticsFriend(view_holder);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }

    private void setStatisticsFriend(competition_View_holder view_holder) {

        Column column = bar_competition.column(data_competition);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        bar_competition.animation(true);
        bar_competition.title("Comparison to your friends");

        bar_competition.yScale().minimum(0d);

        bar_competition.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        bar_competition.tooltip().positionMode(TooltipPositionMode.POINT);
        bar_competition.interactivity().hoverMode(HoverMode.BY_X);

        bar_competition.xAxis(0).title("Friends");
        bar_competition.yAxis(0).title("Points");


        bar_competition.data(data_competition);

        view_holder.anyChartView.setChart(bar_competition);
    }

    private void transaction_more(int i) {
        Log.d(TAG,"i23: " + i);
        if (i== 0){
            List_Fragment list_fragment = new List_Fragment(training_sessions_types,training_sessions_time,training_sessions_notes,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time);
            Bundle bundle = new Bundle();
            bundle.putString("Type","Workout");
            list_fragment.setArguments(bundle);
            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,list_fragment).addToBackStack("List_Adapter").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();


        }
        if (i == 1){
            List_Fragment list_fragment = new List_Fragment(boulders_times,boulders_grade,boulder_notes);
            Bundle bundle = new Bundle();
            Log.d(TAG,"boulder23" + arrayList_tries);
            bundle.putString("Type","Boulder");
            list_fragment.setArguments(bundle);
            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,list_fragment).addToBackStack("List_Adapter").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();


        }
        if (i ==2){
            List_Fragment list_fragment = new List_Fragment(competition_array_names,competition_array_boulderpoints);
            Bundle bundle = new Bundle();
            bundle.putString("Type","Friends");
            list_fragment.setArguments(bundle);
            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,list_fragment).addToBackStack("List_Adapter").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (position == 0) {
            i=0;

        }
        if (position == 1) {
            i=1;

        }
        if (position == 2) {
            i=2;

        }
        return i;
    }


    public class sessions_View_holder extends RecyclerView.ViewHolder {
        AnyChartView anyChartView;
        Button button_sessions;

        public sessions_View_holder(@NonNull View view) {
            super(view);
            anyChartView = view.findViewById(R.id.sessions_statistic);

            button_sessions = view.findViewById(R.id.sessions_btn_mehr);

        }
    }

    public class Grades_View_holder extends RecyclerView.ViewHolder {
        AnyChartView anyChartView;
        Button button_boulder;
        public Grades_View_holder(View view) {
            super(view);
            anyChartView = view.findViewById(R.id.grade_statistic);

            button_boulder = view.findViewById(R.id.boulder_btn_mehr);
        }
    }

    private class competition_View_holder extends RecyclerView.ViewHolder {
        AnyChartView anyChartView;
        Button button_friends;

        public competition_View_holder(View view) {
            super(view);
            anyChartView = view.findViewById(R.id.competition_statistic);

            button_friends = view.findViewById(R.id.friends_btn_mehr);
        }
    }
}
