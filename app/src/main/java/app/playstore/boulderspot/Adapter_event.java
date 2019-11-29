package app.playstore.boulderspot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_event extends RecyclerView.Adapter<Adapter_event.ViewHolder> {

    private  ArrayList<String> IMG_URL= new ArrayList<>();
    private  ArrayList<String> Date   = new ArrayList<>();
    private  ArrayList<String> Title_boulderhalle = new ArrayList<>();
    private  ArrayList<String> Location= new ArrayList<>();;
    private  ArrayList<String> costs  = new ArrayList<>();
    private  ArrayList<String> min_age= new ArrayList<>();
    private  ArrayList<String> Grade  = new ArrayList<>();
    private  ArrayList<String> event_name = new ArrayList<>();
    private  ArrayList<String> time   = new ArrayList<>();
    private  ArrayList<String> info= new ArrayList<>();
    public Context mContext;

    public Adapter_event(ArrayList<String> IMG_URL, ArrayList<String> date, ArrayList<String> title_boulderhalle,
                         ArrayList<String> location, ArrayList<String> costs, ArrayList<String> min_age, ArrayList<String> grade,
                         ArrayList<String> event_name, ArrayList<String> time, ArrayList<String> info, Context mContext) {
        this.IMG_URL = IMG_URL;
        Date = date;
        Title_boulderhalle = title_boulderhalle;
        Location = location;
        this.costs = costs;
        this.min_age = min_age;
        Grade = grade;
        this.event_name = event_name;
        this.time = time;
        this.info = info;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Adapter_event.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_custom, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_event.ViewHolder viewHolder, int i) {
        viewHolder.date_txt.setText(Date.get(i));
        Picasso.get().load(IMG_URL.get(i)).into(viewHolder.IMG_img);

    }

    @Override
    public int getItemCount() {
        return IMG_URL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title_boulderhalle_txt;
        TextView date_txt;
        ImageView IMG_img;
        TextView Location_txt;
        TextView costs_txt;
        TextView min_age_txt;
        TextView Grade_txt;
        TextView event_name_txt;
        TextView time_txt;



        public ViewHolder(View view) {
            super(view);
        //   date_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   Title_boulderhalle_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   Location_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   costs_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   min_age_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   Grade_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   event_name_txt = (TextView) view.findViewById(R.id.event_custom_txt);
        //   time_txt = (TextView) view.findViewById(R.id.event_custom_txt);





        }
    }
}
