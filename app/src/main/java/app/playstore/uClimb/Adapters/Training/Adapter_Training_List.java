package app.playstore.uClimb.Adapters.Training;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Training.Hangboard_page;
import app.playstore.uClimb.Fragments.Stretch_page.Stretch_page;
import app.playstore.uClimb.Fragments.custom_workout.custom_workout_page;
import app.playstore.uClimb.R;

public class Adapter_Training_List extends RecyclerView.Adapter<Adapter_Training_List.ViewHolder> {
    private static final String TAG = "Adapter_training";
    private ArrayList<String> img_array = new ArrayList<>();

    private ArrayList<String> txt_array = new ArrayList<>();
    private Context mContext;

    public Adapter_Training_List(ArrayList<String> img_array, ArrayList<String> txt_array, Context mContext) {
        this.img_array = img_array;
        this.txt_array = txt_array;
        this.mContext = mContext;
        Log.d("mContext1" ,"context:" + mContext);

    }

    @NonNull
    @Override
    public Adapter_Training_List.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.private_training_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(layoutInflater);
        Log.d("mContext" ,"context:" + mContext);
        mContext = parent.getContext();


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Training_List.ViewHolder holder, final int position) {
        holder.txt.setText(txt_array.get(position));
        Log.d(TAG,"img_position");
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
                    Toast.makeText(mContext, "Kommt bald : )", Toast.LENGTH_SHORT).show();
                   //Stretch_page mFragment = new Stretch_page();
                   //FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                   //fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                   //        .replace(R.id.container_fragment, mFragment).commit();

                }
                if (position==2){
                    Toast.makeText(mContext, "Kommt bald : )", Toast.LENGTH_SHORT).show();

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
