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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Hangboard_page;
import app.playstore.uClimb.Fragments.Stretch_page.Stretch_page;
import app.playstore.uClimb.Fragments.custom_workout.custom_workout_page;
import app.playstore.uClimb.R;

public class training_list_adapter extends RecyclerView.Adapter<training_list_adapter.ViewHolder> {
    private ArrayList<String> img_array = new ArrayList<>();

    private ArrayList<String> txt_array = new ArrayList<>();
    private Context mContext;

    public training_list_adapter(ArrayList<String> img_array, ArrayList<String> txt_array, Context mContext) {
        this.img_array = img_array;
        this.txt_array = txt_array;
        this.mContext = mContext;
        Log.d("mContext1" ,"context:" + mContext);

    }

    @NonNull
    @Override
    public training_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(layoutInflater);
        Log.d("mContext" ,"context:" + mContext);
        mContext = parent.getContext();


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull training_list_adapter.ViewHolder holder, final int position) {
        holder.txt.setText(txt_array.get(position));
        Picasso.get().load(img_array.get(position)).fit().centerCrop().into(holder.img);
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    Log.d("mContex3t" ,"context:" + mContext);


                    Hangboard_page mFragment = new Hangboard_page();
                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, mFragment).commit();

                }
                if (position==1){
                    Stretch_page mFragment = new Stretch_page();
                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, mFragment).commit();

                }
                if (position==2){

                }
                if (position==3){
                    custom_workout_page mFragment = new custom_workout_page();
                    FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, mFragment).commit();

                }

            }
        });



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
