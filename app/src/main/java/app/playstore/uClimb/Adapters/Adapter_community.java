package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Location_fragment;
import app.playstore.uClimb.Fragments.Post.custom_post_page;
import app.playstore.uClimb.Fragments.Training_list_fragment;
import app.playstore.uClimb.R;

public class Adapter_community extends RecyclerView.Adapter<Adapter_community.ViewHolder> {
    private ArrayList<String> array_title;
    private ArrayList<String> array_img;
    private Context mContext;


    public Adapter_community(ArrayList<String> array_title, ArrayList<String> array_img, Context mContext) {
        this.array_title = array_title;
        this.array_img = array_img;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Adapter_community.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_page_custom, parent, false);
        mContext = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_community.ViewHolder holder, final int position) {
        Picasso.get().load(array_img.get(position)).fit().centerCrop().into(holder.array_img_Imageview);
        holder.array_title_textView.setText(array_title.get(position));
        holder.array_img_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (array_title.get(position) == "Locations"){
                    Location_fragment mFragment = new Location_fragment();
                    Log.d("adapter_home" , "context_home2" + mContext);

                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, mFragment).commit();


                }











            }
        });
    }

    @Override
    public int getItemCount() {
        return array_img.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView array_title_textView;
        ImageView array_img_Imageview;
        ConstraintLayout layout;




        ViewHolder(View view) {
            super(view);
            array_img_Imageview = view.findViewById(R.id.custom_img_community);
            array_title_textView = view.findViewById(R.id.title_custom_community);
            layout = view.findViewById(R.id.layout_custom_community);

        }
    }
}
