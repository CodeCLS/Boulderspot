package app.playstore.uClimb.Adapters.Search;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import app.playstore.uClimb.R;


public class Adapter_Spinner extends ArrayAdapter {
    private static final String TAG = "Adapter_Spinner";
    private ArrayList source;
    private ArrayList name;
    private Context context;

    public Adapter_Spinner(@NonNull Context context, int resource, @NonNull Object[] objects, ArrayList source, ArrayList name, Context context1) {
        super(context, resource, objects);
        this.source = source;
        this.name = name;
        this.context = context1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_spinner_search_item, parent, false);
            convertView = view;
        }
        Log.d(TAG,"3242");
        TextView txt = convertView.findViewById(R.id.spinner_search_txt);
        de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.image_round_spinner_search);
        txt.setText(name.get(position).toString());
        Picasso.get().load(source.get(position).toString()).fit().into(circleImageView);

        return convertView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        Log.d(TAG,"3242");
        return name.get(position).toString();
    }@Override
    public int getCount() {
        Log.d(TAG,"3242");
        return source.size();
    }@Override
    public long getItemId(int position) {
        Log.d(TAG,"3242");
        return position;
    }


}
