package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

import app.playstore.uClimb.R;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class Adapter_statistics_boulder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Adapter_boulder";
    private ArrayList training_boulder_tries;
    private ArrayList training_boulder_time;
    private ArrayList training_boulder_grade;
    private ArrayList training_boulder_notes;
    private Context mContext;

    public Adapter_statistics_boulder(ArrayList training_boulder_tries, ArrayList training_boulder_time, ArrayList training_boulder_grade, ArrayList training_boulder_notes) {
        Log.d(TAG,"adapter_notes"+this.training_boulder_grade + training_boulder_tries+training_boulder_time+training_boulder_grade+training_boulder_notes);
        this.training_boulder_tries = training_boulder_tries;
        this.training_boulder_time = training_boulder_time;
        this.training_boulder_grade = training_boulder_grade;
        this.training_boulder_notes = training_boulder_notes;


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"123123");
        mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType==0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.boulder_item,parent,false);
            viewHolder = new standart_boulder_view(view);
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            Log.d(TAG,"128472");
            standart_boulder_view standart_boulder_view = (standart_boulder_view) holder;
            standart_boulder_view.accomplishment_boulder_txt.setText(training_boulder_tries.get(position).toString());
            standart_boulder_view.info_boulder__txt.setText(training_boulder_notes.get(position).toString());
            standart_boulder_view.time_boulder_txt.setText(training_boulder_time.get(position).toString());
            standart_boulder_view.grade_boulder_txt.setText(training_boulder_grade.get(position).toString());

        }

    }

    @Override
    public int getItemCount() {

        return training_boulder_grade.size();
    }
    public static class standart_boulder_view extends RecyclerView.ViewHolder{
        private TextView accomplishment_boulder_txt;
        private TextView info_boulder__txt;
        private TextView time_boulder_txt;
        private TextView grade_boulder_txt;

        public standart_boulder_view(@NonNull View itemView) {
            super(itemView);
            accomplishment_boulder_txt = itemView.findViewById(R.id.txt_boulder_accomplishment);
            info_boulder__txt = itemView.findViewById(R.id.txt_info_boulder);
            time_boulder_txt = itemView.findViewById(R.id.txt_time_boulder);
            grade_boulder_txt = itemView.findViewById(R.id.txt_grade_boulder);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
