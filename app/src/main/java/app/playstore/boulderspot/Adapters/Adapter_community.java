package app.playstore.boulderspot.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.boulderspot.R;

public class Adapter_community extends RecyclerView.Adapter<Adapter_community.ViewHolder> {
    private ArrayList<String> array_title = new ArrayList<>();
    private ArrayList<String> array_img = new ArrayList<>();
    private ArrayList<String> array_text_description = new ArrayList<>();
    private Context mContext;


    public Adapter_community(ArrayList<String> array_title, ArrayList<String> array_img, ArrayList<String> array_text_description, Context mContext) {
        this.array_title = array_title;
        this.array_img = array_img;
        this.array_text_description = array_text_description;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Adapter_community.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_page_custom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_community.ViewHolder holder, final int position) {
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return array_img.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView array_title_textView;
        ImageView array_img_Imageview;
        TextView array_text_description;
        ConstraintLayout layout;




        public ViewHolder(View view) {
            super(view);
            array_img_Imageview = view.findViewById(R.id.custom_img_community);
            array_title_textView = view.findViewById(R.id.title_custom_community);
            array_text_description = view.findViewById(R.id.text_custom_community);
            layout = view.findViewById(R.id.layout_custom_community);

        }
    }
}
