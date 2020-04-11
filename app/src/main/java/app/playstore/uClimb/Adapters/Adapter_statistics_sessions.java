package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Adapter_statistics_sessions extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList training_session_title;
    private ArrayList training_session_time;

    private ArrayList training_session_notes;
    private ArrayList<String> sesssions_train_time = new ArrayList<>();
    private ArrayList<String> sesssions_pause_time = new ArrayList<>();
    private ArrayList<String> sesssions_rest_time = new ArrayList<>();
    private ArrayList<String> sesssions_sets_time = new ArrayList<>();
    private ArrayList<String> sesssions_rounds_time = new ArrayList<>();
    private Context mContext;

    public Adapter_statistics_sessions(ArrayList training_session_title, ArrayList training_session_time, ArrayList training_session_notes, ArrayList<String> sesssions_train_time, ArrayList<String> sesssions_pause_time, ArrayList<String> sesssions_rest_time, ArrayList<String> sesssions_sets_time, ArrayList<String> sesssions_rounds_time) {
        this.training_session_title = training_session_title;
        this.training_session_time = training_session_time;
        this.training_session_notes = training_session_notes;
        this.sesssions_train_time = sesssions_train_time;
        this.sesssions_pause_time = sesssions_pause_time;
        this.sesssions_rest_time = sesssions_rest_time;
        this.sesssions_sets_time = sesssions_sets_time;
        this.sesssions_rounds_time = sesssions_rounds_time;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = null;
        if (viewType==0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.sessions_item, parent);
        }
        return new standart_sessions_view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            standart_sessions_view standart_sessions_view = (Adapter_statistics_sessions.standart_sessions_view) holder;
            standart_sessions_view.info_txt.setText(training_session_notes.get(position).toString());
            standart_sessions_view.time_txt.setText(training_session_time.get(position).toString());
            standart_sessions_view.type_session_txt.setText(training_session_title.get(position).toString());

        }

    }

    @Override
    public int getItemCount() {
        return sesssions_pause_time.size();
    }
    public static class standart_sessions_view extends RecyclerView.ViewHolder{
        private TextView type_session_txt;
        private TextView info_txt;
        private TextView time_txt;
        private TextView duration_txt;

        public standart_sessions_view(@NonNull View itemView) {
            super(itemView);
            type_session_txt = itemView.findViewById(R.id.txt_session_type);
            info_txt = itemView.findViewById(R.id.txt_info_session);
            time_txt = itemView.findViewById(R.id.txt_time_session);
            duration_txt = itemView.findViewById(R.id.txt_duration_session);

        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;

        return i;
    }
}
