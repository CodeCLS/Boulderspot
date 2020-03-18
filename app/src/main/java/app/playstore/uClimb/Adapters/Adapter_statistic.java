package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Adapter_statistic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList training_session_title;
    private ArrayList training_session_time;
    private ArrayList training_session_duration;
    private ArrayList training_session_notes;
    private Context mContext;






    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        if (holder.getItemViewType() == 0){
            sessions_View_holder view_holder = (sessions_View_holder) holder;



        }
        if (holder.getItemViewType() == 1){
            Grades_View_holder view_holder = (Grades_View_holder) holder;

        }
        if (holder.getItemViewType() == 2){
            competition_View_holder view_holder = (competition_View_holder) holder;

        }

    }

    @Override
    public int getItemCount() {
        return training_session_duration.size();
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
        public sessions_View_holder(@NonNull View view) {
            super(view);

        }
    }

    public class Grades_View_holder extends RecyclerView.ViewHolder {
        public Grades_View_holder(View view) {
            super(view);
        }
    }

    private class competition_View_holder extends RecyclerView.ViewHolder {
        public competition_View_holder(View view) {
            super(view);
        }
    }
}
