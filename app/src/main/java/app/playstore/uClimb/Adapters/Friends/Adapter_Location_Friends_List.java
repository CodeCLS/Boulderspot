package app.playstore.uClimb.Adapters.Friends;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import app.playstore.uClimb.R;

public class Adapter_Location_Friends_List extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //private ArrayList<String> friend_id = new ArrayList<>();
    private ArrayList<String> friend_location_status;
    //private ArrayList<String> friend_location_time = new ArrayList<>();
    private ArrayList<String> friend_name;
    private ArrayList<String> friend_url_img;
    private ArrayList<String> friend_time;

    private Context mContext;

    public Adapter_Location_Friends_List(ArrayList<String> friend_location_status, ArrayList<String> friend_name, ArrayList<String> friend_url_img, ArrayList<String> friend_time) {
        this.friend_location_status = friend_location_status;
        this.friend_name = friend_name;
        this.friend_url_img = friend_url_img;
        this.friend_time = friend_time;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_item_location,parent,false);
            viewHolder = new Standart_viewholder(view);

        }

        assert viewHolder != null;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            Standart_viewholder standart_viewholder = (Standart_viewholder) holder;
            try {
                setViews(position, standart_viewholder);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //standart_viewholder.time.setText();


        }

    }

    private void setViews(int position, Standart_viewholder standart_viewholder) throws ParseException {
        Picasso.get().load(friend_url_img.get(position)).into(standart_viewholder.img);
        standart_viewholder.name.setText(friend_name.get(position));

        if (compareTime(getTime(),friend_time.get(position))<5){
            standart_viewholder.status_img.setImageResource(R.color.cpb_green);


        }
        if (compareTime(getTime(),friend_time.get(position))>5)
            {
            standart_viewholder.status_img.setImageResource((R.color.cpb_red));


        }
    }
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
    private int compareTime(String time , String time_now) throws ParseException {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

        Date date1 = simpleDateFormat.parse(time);
        Date date2 = simpleDateFormat.parse(time_now);

        long difference = date2.getTime() - date1.getTime();
        int days = (int) (difference / (1000*60*60*24));
        int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
        int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        hours = (hours < 0 ? -hours : hours);
        Log.d("======= Hours"," :: "+hours);
        return hours;
    }

    @Override
    public int getItemCount() {
        return friend_name.size();
    }

    private static class Standart_viewholder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView img;
        TextView name;
        TextView time;
        ConstraintLayout constraintLayout;
        de.hdodenhof.circleimageview.CircleImageView status_img;
        Standart_viewholder(View view) {
            super(view);
            img = view.findViewById(R.id.img_location_item);
            name = view.findViewById(R.id.location_item_name);
            status_img = view.findViewById(R.id.status_img_location_item);
            constraintLayout = view.findViewById(R.id.constraint_location_parent_1264);
            time = view.findViewById(R.id.time_location);


        }
    }
}
