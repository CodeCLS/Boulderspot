package app.playstore.uClimb.Adapters.Search;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Spinner extends ArrayAdapter {
    private static final String TAG = "Adapter_Spinner";
    private ArrayList source;
    private ArrayList name;

    public Adapter_Spinner(@NonNull Context context, int resource, ArrayList source, ArrayList name) {
        super(context, resource);
        this.source = source;
        this.name = name;
    }

    @Override
    public int getCount() {
        //Log.d(TAG,"log_size" + source.size());
        return source.size();
    }




    @Override
    public Object getItem(int position) {
        return source.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG,"3234234323");
        View listitem = convertView;
        Context mContext = parent.getContext();
        if (listitem == null){
            listitem = LayoutInflater.from(mContext).inflate(R.layout.public_spinner_search_item,parent,false);


        }
        de.hdodenhof.circleimageview.CircleImageView imageView = listitem.findViewById(R.id.image_round_spinner_search);
        Picasso.get().load(source.get(position).toString()).fit().into(imageView);
        TextView tx = listitem.findViewById(R.id.spinner_search_txt);
        tx.setText(name.get(position).toString());


        return listitem;
    }
}
