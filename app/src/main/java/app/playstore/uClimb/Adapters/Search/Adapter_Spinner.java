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
import java.util.List;
import java.util.logging.Logger;

import app.playstore.uClimb.R;


public class Adapter_Spinner extends ArrayAdapter {
    private static final String TAG = "Adapter_Spinner";
    private ArrayList source;
    private ArrayList name;
    private Context context;

    public Adapter_Spinner(Context context, int resource, List<String> objects, ArrayList source, ArrayList name, Context context1) {
        super(context, resource, objects);
        Log.d(TAG,"Name234" + name);
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
        Log.d(TAG,"324233" + name);
        TextView txt = convertView.findViewById(R.id.spinner_search_txt);
        de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.image_round_spinner_search);
        txt.setText(name.get(position).toString());
        Picasso.get().load(source.get(position).toString()).fit().into(circleImageView);

        return convertView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        Log.d(TAG,"3242" + name);
        return name.get(position).toString();
    }@Override
    public int getCount() {
        Log.d(TAG,"324233" + source);
        return source.size();
    }@Override
    public long getItemId(int position) {
        Log.d(TAG,"3242");
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if(constraint != null) {
                    Log.d(TAG,"constraint23"+ constraint);
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = constraint;
                    filterResults.count = constraint.length();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0) {
                    clear();
                    add(results.values);
                    notifyDataSetChanged();
                }


            }
        };
        return filter;
    }
}
