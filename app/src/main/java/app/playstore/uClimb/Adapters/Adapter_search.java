package app.playstore.uClimb.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.Post.custom_post_page;
import app.playstore.uClimb.R;

public class Adapter_search extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  ArrayList<String> URL= new ArrayList<>();
    private  ArrayList<String> Date   = new ArrayList<>();
    private  ArrayList<String> Location= new ArrayList<>();;
    private  ArrayList<String> info= new ArrayList<>();
    private  ArrayList<String> type_array= new ArrayList<>();

    public Context mContext;


    public Adapter_search(ArrayList<String> IMG_URL, ArrayList<String> date, ArrayList<String> location, ArrayList<String> info,ArrayList<String> type_array, Context mContext) {
        this.URL = IMG_URL;
        Date = date;
        Location = location;
        this.info = info;
        this.type_array = type_array;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =null;
        RecyclerView.ViewHolder viewHolder = null;
        Log.d("adapter_search_final" , "type_array" + type_array.get(i)+ "number..." + i);




        if (i == 0){
            Log.d("adapter_search" , " viewholder_video");
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_search_video,viewGroup,false);
            viewHolder= new ViewHolder_video(view);


        }
        if (i == 1){
            Log.d("adapter_search" , " viewholder_img");

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_search, viewGroup, false);
            viewHolder= new ViewHolder_img(view);


        }

        return viewHolder;
    }
    @Override
    public int getItemViewType(final int position) {
        int i = 0;
        if (type_array.get(position).equals("img")){
            Log.d("Adapter_home","POSITION0: "+position);
            i = 1;
        }
        if(type_array.get(position).equals("video")){
            Log.d("Adapter_home","POSITION1: "+position);

            i = 0;
        }
        return i;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.d("Adapter_search" , "viewtype" + viewHolder.getItemViewType() );


        //TODO create threads to fight against laggy pages

        if (viewHolder.getItemViewType()== 0) {
            ViewHolder_video viewHolder_video = (ViewHolder_video) viewHolder;
            viewHolder_video.videoView.setVideoPath(URL.get(i));
            viewHolder_video.videoView.seekTo(2);
            ((ViewHolder_video) viewHolder).videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transactiontopost(i);


                }
            });
        }
        if (viewHolder.getItemViewType() == 1){
            ViewHolder_img viewHolder_img = (ViewHolder_img) viewHolder;

            Picasso.get().load(URL.get(i)).into(viewHolder_img.img_item_rec_search_page);

            viewHolder_img.img_item_rec_search_page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transactiontopost(i);


                }
            });




        }

    }

    private void transactiontopost(int position) {
        custom_post_page mFragment = new custom_post_page();
        Bundle arguments = new Bundle();
        arguments.putString("Source", URL.get(position));
        arguments.putString("UserID", URL.get(position));
        arguments.putString("PostID", URL.get(position));
        arguments.putString("Type", type_array.get(position));






        FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
        mFragment.setArguments(arguments);
        fragmentManager.beginTransaction().addToBackStack("Fragment_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container_fragment, mFragment).commit();

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public int getItemCount() {
        return URL.size();
    }

    public class ViewHolder_img extends RecyclerView.ViewHolder {
        ImageView img_item_rec_search_page;



        public ViewHolder_img(View view) {
            super(view);
            img_item_rec_search_page = view.findViewById(R.id.imageview_item_search_page);
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

    public class ViewHolder_video extends RecyclerView.ViewHolder {
        VideoView videoView;



        public ViewHolder_video(View view) {
            super(view);
            videoView = view.findViewById(R.id.videoView_search_item);
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
