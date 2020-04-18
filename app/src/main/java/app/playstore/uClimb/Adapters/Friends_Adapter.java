package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class Friends_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> url_img = new ArrayList<>();
    private ArrayList<String> user_txt = new ArrayList<>();
    private ArrayList<String> u_id = new ArrayList<>();


    Context mContext;

    public Friends_Adapter(ArrayList<String> url_img, ArrayList<String> user_txt, ArrayList<String> u_id) {
        this.url_img = url_img;
        this.user_txt = user_txt;
        this.u_id = u_id;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.friends_item,parent,false);
            viewHolder = new ViewHolder_item_friend(view);



        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            ViewHolder_item_friend viewHolder_item_friend = (ViewHolder_item_friend) holder;
            viewHolder_item_friend.username_txt.setText(user_txt.get(position));
            Picasso.get().load(url_img.get(position)).into(viewHolder_item_friend.img);
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


    }

    @Override
    public int getItemCount() {
        return u_id.size();
    }

    private class ViewHolder_bar extends RecyclerView.ViewHolder {
        LinearLayout friends_linear;
        LinearLayout location_linear;
        public ViewHolder_bar(View view) {
            super(view);

        }
    }

    private class ViewHolder_item_friend extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView img;
        TextView username_txt;
        public ViewHolder_item_friend(View view) {
            super(view);
            img = view.findViewById(R.id.img_item_friend);
            username_txt = view.findViewById(R.id.txt_name_item_friend);

        }
    }
}
