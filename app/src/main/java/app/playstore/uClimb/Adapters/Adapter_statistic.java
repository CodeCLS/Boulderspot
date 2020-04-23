package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.playstore.uClimb.R;

public class Adapter_statistic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> training_sessions_time = new ArrayList<>();
    private ArrayList<String> training_sessions_types = new ArrayList<>();
    private ArrayList<String> training_sessions_amount = new ArrayList<>();
    private ArrayList<String> boulders_times = new ArrayList<>();
    private ArrayList<String> boulders_grade = new ArrayList<>();
    private ArrayList<String> boulders_tries = new ArrayList<>();
    private ArrayList<Integer> competition_array_boulderpoints = new ArrayList<>();
    private ArrayList<String> competition_array_uid = new ArrayList<>();
    private HashMap<String,Integer> bouldermap = new HashMap<>();

    private ArrayList<String> competition_array_names = new ArrayList<>();

    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();

    private ArrayList<String> training_sessions_notes = new ArrayList<>();

    private ArrayList<String> boulder_notes = new ArrayList<>();





    private static final String TAG = "Adapter_statistic";
    private Adapter_statistics_competition adapter_statistics_competition = new Adapter_statistics_competition(competition_array_names,competition_array_boulderpoints);
    private Adapter_statistics_boulder adapter_statistics_boulder = new Adapter_statistics_boulder(boulders_tries,boulders_times,boulders_grade,boulder_notes);
    private Adapter_statistics_sessions adapter_statistics_sessions = new Adapter_statistics_sessions(training_sessions_types,training_sessions_time,training_sessions_notes,sesssions_train_time,sesssions_pause_time,sesssions_rest_time,sesssions_sets_time,sesssions_rounds_time);

    private Pie pie_sessions;
    private List<DataEntry> data_sessions;

    private Pie pie_grade;
    private List<DataEntry> data_grade;

    private Cartesian bar_competition;
    private List<DataEntry> data_competition;
    private Context mContext;

    public Adapter_statistic(ArrayList<String> training_sessions_time, ArrayList<String> training_sessions_types, ArrayList<String> training_sessions_amount, ArrayList<String> boulders_times, HashMap boulders_grade, ArrayList<String> boulders_tries, ArrayList<Integer> competition_array_boulderpoints, ArrayList<String> competition_array_uid, ArrayList<String> competition_array_names, ArrayList<String> sesssions_train_time, ArrayList<String> sesssions_pause_time, ArrayList<String> sesssions_rest_time, ArrayList<String> sesssions_sets_time, ArrayList<String> sesssions_rounds_time, ArrayList<String> training_sessions_notes, ArrayList<String> boulder_notes ) {
        this.training_sessions_time = training_sessions_time;
        this.training_sessions_types = training_sessions_types;
        this.training_sessions_amount = training_sessions_amount;
        this.boulders_times = boulders_times;
        this.bouldermap = boulders_grade;
        this.boulders_tries = boulders_tries;
        this.competition_array_boulderpoints = competition_array_boulderpoints;
        this.competition_array_uid = competition_array_uid;
        this.competition_array_names = competition_array_names;
        this.sesssions_train_time = sesssions_train_time;
        this.sesssions_pause_time = sesssions_pause_time;
        this.sesssions_rest_time = sesssions_rest_time;
        this.sesssions_sets_time = sesssions_sets_time;
        this.sesssions_rounds_time = sesssions_rounds_time;
        this.training_sessions_notes = training_sessions_notes;
        this.adapter_statistics_competition = adapter_statistics_competition;
        this.adapter_statistics_boulder = adapter_statistics_boulder;
        this.adapter_statistics_sessions = adapter_statistics_sessions;
        this.boulder_notes = boulder_notes;
        Log.d(TAG,"trainingseession"+boulder_notes);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        View view=null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.sessions,parent,false);
            viewHolder= new sessions_View_holder(view);


        }
        if (viewType == 1){
            view = LayoutInflater.from(mContext).inflate(R.layout.grades_accomplished,parent,false);
            viewHolder= new Grades_View_holder(view);

        }
        if (viewType == 2){
            view = LayoutInflater.from(mContext).inflate(R.layout.competition_skala,parent,false);
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
            sessions_View_holder view_holder = (sessions_View_holder) holder;

            //Integer.valueOf(training_sessions_amount.get(position))
            //training_sessions_types.get(position)
            for(int i = 0;i<training_sessions_types.size();i++){
                String training_sessions_type_s= training_sessions_types.get(i);
                int training_sessions_amount_i = Integer.parseInt(training_sessions_amount.get(i));

                data_sessions.add(new ValueDataEntry(training_sessions_type_s, training_sessions_amount_i));


            }

                pie_sessions.data(data_sessions);

                view_holder.anyChartView.setChart(pie_sessions);




        }
        if (holder.getItemViewType() == 1){


            Grades_View_holder view_holder = (Grades_View_holder) holder;
            view_holder.rec.setLayoutManager(new LinearLayoutManager(mContext));
            Log.d(TAG,"Hallo123");

            view_holder.rec.setAdapter(adapter_statistics_boulder);
            for (int i = 0;i<16;i++){
                Log.d(TAG,"data3: "  + "bouldermap: " + bouldermap +"position" + i);

                Integer boulder = bouldermap.get("V"+i);
                if (boulder==null){
                    Log.d(TAG,"data2: " + boulder + "bouldermap: " + bouldermap +"position" + position);


                }
                else{
                    Log.d(TAG,"data1: " + boulder + "bouldermap: " + bouldermap +"position" + i);
                    String boulder_grade = "V"+i;
                    data_grade.add(new ValueDataEntry(boulder_grade,boulder));



                }



            }
            pie_grade.data(data_grade);


            view_holder.anyChartView.setChart(pie_grade);











            //boulders_grade.get(position), Integer.valueOf(training_sessions_amount.get(position))




        }
        if (holder.getItemViewType() == 2){

            competition_View_holder view_holder = (competition_View_holder) holder;
            view_holder.rec.setLayoutManager(new LinearLayoutManager(mContext));
            view_holder.rec.setAdapter(adapter_statistics_competition);
            int points = 0;
            Log.d(TAG,"points_adapter" + competition_array_uid);



            for (int i = 0; i< competition_array_uid.size();i++){
                Log.d(TAG,"points_adapter2" + competition_array_uid);


                int competition_gradepoints_s= competition_array_boulderpoints.get(i);
                String competition_name = competition_array_uid.get(i);
                data_competition.add(new ValueDataEntry(competition_name,competition_gradepoints_s));













            }

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
        RecyclerView rec;

        public sessions_View_holder(@NonNull View view) {
            super(view);
            anyChartView = view.findViewById(R.id.sessions_statistic);

            rec = view.findViewById(R.id.rec_sessions);

        }
    }

    public class Grades_View_holder extends RecyclerView.ViewHolder {
        AnyChartView anyChartView;
        RecyclerView rec;
        public Grades_View_holder(View view) {
            super(view);
            anyChartView = view.findViewById(R.id.grade_statistic);

            rec = view.findViewById(R.id.grade_statistic_rec);
        }
    }

    private class competition_View_holder extends RecyclerView.ViewHolder {
        AnyChartView anyChartView;
        RecyclerView rec;

        public competition_View_holder(View view) {git
            super(view);
            anyChartView = view.findViewById(R.id.competition_statistic);

            rec = view.findViewById(R.id.competition_statistic_rec);
        }
    }
}
