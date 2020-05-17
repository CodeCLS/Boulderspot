package app.playstore.uClimb.Adapters.Friends;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Friends extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Adapter_Friends";
    //Main ArrayLists for Data
    private ArrayList<String> url_img;
    private ArrayList<String> user_txt;
    private ArrayList<String> friend_invite_id;
    private ArrayList<String> friend_invite_name;
    private ArrayList<String> friend_invite_source;


    private ArrayList<String> u_id;

    private Context mContext;


    public Adapter_Friends(ArrayList<String> url_img, ArrayList<String> user_txt, ArrayList<String> friend_invite_id, ArrayList<String> friend_invite_name, ArrayList<String> friend_invite_source, ArrayList<String> u_id) {
        this.url_img = url_img;
        this.user_txt = user_txt;
        this.friend_invite_id = friend_invite_id;
        this.friend_invite_name = friend_invite_name;
        this.friend_invite_source = friend_invite_source;
        this.u_id = u_id;
    }

    @Override
    public int getItemViewType(int position) {
        int i;
        Log.d(TAG,"friend_invite: " + friend_invite_id  + " position: " + position);
        if (friend_invite_id.size() > position){
            i = 1;

        }
        else{
            i = 0;
        }

        return i;
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
        if (viewType == 1){
            view = LayoutInflater.from(mContext).inflate(R.layout.private_friends_item_invite,parent,false);
            viewHolder = new ViewHolder_item_friend_invite(view);



        }

        assert viewHolder != null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == 0){
            ViewHolder_item_friend viewHolder_item_friend = (ViewHolder_item_friend) holder;
            position = position-friend_invite_id.size();


            setViews(position, viewHolder_item_friend);

            //transfer to user_page
            username_onClick(position, viewHolder_item_friend);


        }
        if (holder.getItemViewType() == 1){
            ViewHolder_item_friend_invite viewHolder_item_friend_invite = (ViewHolder_item_friend_invite) holder;
            Log.d(TAG,"friend_invite_name " + friend_invite_name+ "position: " + position);
            viewHolder_item_friend_invite.txt_name.setText(friend_invite_name.get(position));
            Picasso.get().load(friend_invite_source.get(position)).fit().centerCrop().into(viewHolder_item_friend_invite.circleImageView);
            int finalPosition = position;
            viewHolder_item_friend_invite.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Presenter_Login presenter_login = new Presenter_Login();
                    String id = presenter_login.getUID(mContext);
                    Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("User").child(id).child("Invites_Friends").child(friend_invite_id.get(finalPosition)).removeValue();
                    remove_Invite(finalPosition);

                }
            });
            int finalPosition1 = position;
            viewHolder_item_friend_invite.btn_accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Accepted", Toast.LENGTH_SHORT).show();
                    Presenter_Login presenter_login = new Presenter_Login();
                    String id = presenter_login.getUID(mContext);
                    Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("User").child(id).child("Invites_Friends").child(friend_invite_id.get(finalPosition1)).removeValue();
                    databaseReference.child("User").child(id).child("Friends").child(friend_invite_id.get(finalPosition1)).setValue(friend_invite_id.get(finalPosition1));
                    databaseReference.child("User").child(friend_invite_id.get(finalPosition1)).child("Friends").child(id).setValue(id);
                    remove_Invite(finalPosition);



                }
            });



        }


    }

    private void remove_Invite(int finalPosition) {
        friend_invite_id.remove(finalPosition);
        friend_invite_name.remove(finalPosition);
        friend_invite_source.remove(finalPosition);
        notifyDataSetChanged();
    }

    private void setViews(int position, ViewHolder_item_friend viewHolder_item_friend) {
        Log.d(TAG,"user_txt24:" + user_txt + " position: " + position);

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
        return u_id.size() + friend_invite_id.size();
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

    private class ViewHolder_item_friend_invite extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView txt_name;
        Button btn_accept;
        Button btn_delete;

        public ViewHolder_item_friend_invite(View view) {
            super(view);
            circleImageView = (CircleImageView) view.findViewById(R.id.img_item_friend_invite);
            txt_name = view.findViewById(R.id.txt_name_item_friend_invite);
            btn_accept = view.findViewById(R.id.btn_accept_invite);
            btn_delete = view.findViewById(R.id.btn_delete_invite);

        }
    }
}
