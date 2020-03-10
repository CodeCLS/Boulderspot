package app.playstore.uClimb.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Adapter_home_comment extends RecyclerView.Adapter<Adapter_home_comment.ViewHolder> {
    private static final String TAG = "comment_home";
    private ArrayList<String> array_name = new ArrayList();
    private ArrayList<String> array_comment_id = new ArrayList();
    private ArrayList<String> array_comment = new ArrayList();
    private ArrayList<String> array_User_ID = new ArrayList();
    private ArrayList<String> array_time = new ArrayList();

    private Context mContext;

    public Adapter_home_comment(ArrayList<String> array_name, ArrayList<String> array_comment_id, ArrayList<String> array_comment, ArrayList<String> array_User_ID, ArrayList<String> array_time) {
        this.array_name = array_name;
        this.array_comment_id = array_comment_id;
        this.array_comment = array_comment;
        this.array_User_ID = array_User_ID;
        this.array_time = array_time;
    }

    @NonNull
    @Override
    public Adapter_home_comment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mContext = parent.getContext();

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter_home_comment.ViewHolder holder, int position) {
        Log.d(TAG,"array_name" + array_name);
        Log.d(TAG,"array_commment" + array_comment);
        Log.d(TAG,"array_id" + array_comment_id);
        Log.d(TAG,"array_time" + array_time);

        holder.comment_txt.setText(array_name.get(position)+ ": " + array_comment.get(position));


    }

    @Override
    public int getItemCount() {
        return array_comment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_txt = itemView.findViewById(R.id.txt_comment);
        }
    }
}
