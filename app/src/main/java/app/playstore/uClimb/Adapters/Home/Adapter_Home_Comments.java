package app.playstore.uClimb.Adapters.Home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Adapter_Home_Comments extends RecyclerView.Adapter<Adapter_Home_Comments.ViewHolder> {
    private static final String TAG = "comment_home";
    private ArrayList<String> array_name;
    private ArrayList<String> array_comment_id;
    private ArrayList<String> array_comment;
    //private ArrayList<String> array_User_ID = new ArrayList();
    private ArrayList<String> array_time;
    private ArrayList<String> array_uid;


    //public Adapter_Home_Comments(ArrayList<String> array_name, ArrayList<String> array_comment_id, ArrayList<String> array_comment, ArrayList<String> array_User_ID, ArrayList<String> array_time) {
    //    this.array_name = array_name;
    //    this.array_comment_id = array_comment_id;
    //    this.array_comment = array_comment;
    //    this.array_User_ID = array_User_ID;
    //    this.array_time = array_time;
    //}
    public Adapter_Home_Comments(ArrayList<String> array_name, ArrayList<String> array_comment_id, ArrayList<String> array_comment, ArrayList<String> array_time,ArrayList<String> array_uid) {
        this.array_name = array_name;
        this.array_comment_id = array_comment_id;
        this.array_comment = array_comment;
        //this.array_User_ID = array_User_ID;
        this.array_time = array_time;
        this.array_uid = array_uid;

    }

    @NonNull
    @Override
    public Adapter_Home_Comments.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.private_post_comment_item, parent, false);
        //Context mContext = parent.getContext();

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter_Home_Comments.ViewHolder holder, int position) {
        Log.d(TAG,"array_name" + array_name);
        Log.d(TAG,"array_commment" + array_comment);
        Log.d(TAG,"array_id" + array_comment_id);
        Log.d(TAG,"array_time" + array_time);
        holder.comment_name.setText(array_name.get(position));

        holder.comment_txt.setText(array_comment.get(position));


    }

    @Override
    public int getItemCount() {
        return array_comment.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment_name;
        TextView comment_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_txt = itemView.findViewById(R.id.txt_comment);
            comment_name = itemView.findViewById(R.id.txt_comment_name);
        }
    }
}
