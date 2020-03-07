package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class adapter_stretch extends RecyclerView.Adapter<adapter_stretch.ViewHolder1> {
    private ArrayList<String> source = new ArrayList<>();
    private ArrayList<String> text = new ArrayList<>();
    private int video_state = 0;
    private Context mContext;

    public adapter_stretch(ArrayList<String> source, ArrayList<String> text) {
        this.source = source;
        this.text = text;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_stretch,parent,false);
        ViewHolder1 viewHolder = new ViewHolder1(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder1 holder, int position) {
            Log.d("stretch","viewholder"+holder.videoView);
           holder.videoView.setVideoPath(source.get(position));
           holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
               @Override
               public void onPrepared(MediaPlayer mp) {
                   holder.videoView.seekTo(4);


               }
           });

           holder.videoView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {

                   Toast.makeText(mContext, "Long pressed", Toast.LENGTH_SHORT).show();
                   return true;
               }
           });
          // holder.videoView.setZOrderOnTop(false);
       // SurfaceHolder surfaceHolder = holder.videoView.getHolder();
       //         surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
            holder.txt.setText(text.get(position));
            holder.videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (video_state == 0 ){
                        holder.videoView.start();
                        video_state = 1;

                    }
                    else{
                        holder.videoView.pause();
                        video_state= 0;

                    }
                }
            });





    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView txt;
        VideoView videoView;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.stretch_txt);
            videoView = itemView.findViewById(R.id.stretch_video);

        }
    }
}
