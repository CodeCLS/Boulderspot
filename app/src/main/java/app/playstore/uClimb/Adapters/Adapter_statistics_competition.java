package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Adapter_statistics_competition extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList competition_name;
    private ArrayList competition_Accomplishment;
    private Context mContext;

    public Adapter_statistics_competition(ArrayList competition_name, ArrayList competition_Accomplishment) {
        this.competition_name = competition_name;
        this.competition_Accomplishment = competition_Accomplishment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = null;
        if (viewType==0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.competition_skala_item, parent);
        }
        return new standart_competition_view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            standart_competition_view standart_competition_view = (standart_competition_view) holder;
            standart_competition_view.name_txt.setText(competition_name.get(position).toString());
            standart_competition_view.accomplishment__txt.setText(competition_Accomplishment.get(position).toString());




        }

    }

    @Override
    public int getItemCount() {
        return competition_name.size();
    }
    public static class standart_competition_view extends RecyclerView.ViewHolder{
        private TextView name_txt;
        private TextView accomplishment__txt;
        private TextView updated_last_txt;

        public standart_competition_view(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.name_txt_competition);
            accomplishment__txt = itemView.findViewById(R.id.accomplished_txt_competition);
            updated_last_txt = itemView.findViewById(R.id.updated_last_txt_competition);


        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
