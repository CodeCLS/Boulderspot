package app.playstore.uClimb.Adapters.Friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Adapter_Location_Friends_List extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> friend_id = new ArrayList<>();
    private ArrayList<String> friend_location_status = new ArrayList<>();
    private ArrayList<String> friend_location_time = new ArrayList<>();
    private ArrayList<String> friend_name = new ArrayList<>();
    private ArrayList<String> friend_url_img = new ArrayList<>();
    private Context mContext;

    public Adapter_Location_Friends_List(ArrayList<String> friend_id, ArrayList<String> friend_location_status, ArrayList<String> friend_location_time, ArrayList<String> friend_name, ArrayList<String> friend_url_img) {
        this.friend_id = friend_id;
        this.friend_location_status = friend_location_status;
        this.friend_location_time = friend_location_time;
        this.friend_name = friend_name;
        this.friend_url_img = friend_url_img;
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        return i;
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

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            Standart_viewholder standart_viewholder = (Standart_viewholder) holder;
            Picasso.get().load(friend_url_img.get(position)).into(standart_viewholder.img);
            standart_viewholder.name.setText(friend_name.get(position));

            if (friend_location_status.get(position).equals("Online")){
                standart_viewholder.status_img.setBackgroundColor(mContext.getResources().getColor(R.color.cpb_green));


            }
            if (friend_location_status.get(position).equals("Offline"))
                {
                standart_viewholder.status_img.setBackgroundColor(mContext.getResources().getColor(R.color.cpb_red));


            }


        }

    }

    @Override
    public int getItemCount() {
        return friend_name.size();
    }

    private class Standart_viewholder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView img;
        TextView name;
        ConstraintLayout constraintLayout;
        de.hdodenhof.circleimageview.CircleImageView status_img;
        public Standart_viewholder(View view) {
            super(view);
            img = view.findViewById(R.id.img_location_item);
            name = view.findViewById(R.id.location_item_name);
            status_img = view.findViewById(R.id.status_img_location_item);
            constraintLayout = view.findViewById(R.id.constraint_location_parent_1264);


        }
    }
}
