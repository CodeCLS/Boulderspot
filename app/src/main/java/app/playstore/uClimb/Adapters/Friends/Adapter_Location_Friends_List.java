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
    private static final String TAG = "Adapter_Location";
    //private ArrayList<String> friend_id = new ArrayList<>();
    private ArrayList<String> friend_location_status;
    //private ArrayList<String> friend_location_time = new ArrayList<>();
    private ArrayList<String> friend_name;
    private ArrayList<String> friend_url_img;
    private ArrayList<String> friend_time;
    long hours_five = 18000000;
    private long date2_2;
    private long date1_1;
    private Date date1;
    private Date date2;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        date1 = sdf.parse(getTime());
        date1_1 = date1.getTime() ;

        date2 = sdf.parse(friend_time.get(position));
        date2_2 = date2.getTime()+ hours_five;

        Picasso.get().load(friend_url_img.get(position)).into(standart_viewholder.img);
        standart_viewholder.name.setText(friend_name.get(position));

        String time = friend_time.get(position).toString();
        Log.d(TAG,"time23: " + time);
        String convertedTime_1 = time.substring(0,4);
        String convertedTime_2 = time.substring(4,6);
        String convertedTime_3 =  time.substring(6,8);
        String convertedTime_4 =  time.substring(9,11);
        String convertedTime_5 =  time.substring(11,13);
        Log.d(TAG,"convertedtime4: " + convertedTime_4);
        Log.d(TAG,"convertedtime5: " + convertedTime_5);

        String final_time = convertedTime_3 + "." + convertedTime_2 + "." + convertedTime_1 + "-" +convertedTime_4 +":" + convertedTime_5;
        standart_viewholder.time.setText(final_time);

        if (date2_2>date1_1){
            standart_viewholder.status_img.setImageResource(R.color.cpb_green);


        }
        if (date2_2<date1_1)
            {
            standart_viewholder.status_img.setImageResource((R.color.cpb_red));


        }
    }
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
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
