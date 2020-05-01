package app.playstore.uClimb.Adapters.Friends;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.R;

public class Adapter_Friends extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Main ArrayLists for Data
    private ArrayList<String> url_img;
    private ArrayList<String> user_txt;
    private ArrayList<String> u_id;

    private Context mContext;

    public Adapter_Friends(ArrayList<String> url_img, ArrayList<String> user_txt, ArrayList<String> u_id) {
        this.url_img = url_img;
        this.user_txt = user_txt;
        this.u_id = u_id;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.private_friends_item,parent,false);
            viewHolder = new ViewHolder_item_friend(view);



        }

        assert viewHolder != null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == 0){
            ViewHolder_item_friend viewHolder_item_friend = (ViewHolder_item_friend) holder;

            setViews(position, viewHolder_item_friend);

            //transfer to user
            username_onClick(position, viewHolder_item_friend);


        }


    }

    private void setViews(int position, ViewHolder_item_friend viewHolder_item_friend) {
        viewHolder_item_friend.username_txt.setText(user_txt.get(position));

        Picasso.get().load(url_img.get(position)).into(viewHolder_item_friend.img);
    }

    private void username_onClick(int position, ViewHolder_item_friend viewHolder_item_friend) {
        viewHolder_item_friend.username_txt.setOnClickListener(v -> {
            custom_profile custom_profile = new custom_profile();
            Bundle bundle = new Bundle();
            bundle.putString("uid", u_id.get(position));
            custom_profile.setArguments(bundle);


            FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container_fragment,custom_profile).commit();

        });
    }

    @Override
    public int getItemCount() {
        return u_id.size();
    }



    private static class ViewHolder_item_friend extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView img;
        TextView username_txt;
        private ViewHolder_item_friend(View view) {
            super(view);
            img = view.findViewById(R.id.img_item_friend);
            username_txt = view.findViewById(R.id.txt_name_item_friend);

        }
    }
}
