package app.playstore.boulderspot.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

import app.playstore.boulderspot.R;

public class training_list_adapter extends RecyclerView.Adapter<training_list_adapter.ViewHolder> {
    private ArrayList<String> img_array = new ArrayList<>();

    private ArrayList<String> txt_array = new ArrayList<>();
    private Context mContext;

    public training_list_adapter(ArrayList<String> img_array, ArrayList<String> txt_array, Context mContext) {
        this.img_array = img_array;
        this.txt_array = txt_array;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public training_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(layoutInflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull training_list_adapter.ViewHolder holder, int position) {
        holder.txt.setText(txt_array.get(position));
        Picasso.get().load(img_array.get(position)).fit().centerCrop().into(holder.img);



    }

    @Override
    public int getItemCount() {
        return img_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        TextView txt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_item_training);
            txt = itemView.findViewById(R.id.txt_item_list_training);
        }
    }
}
