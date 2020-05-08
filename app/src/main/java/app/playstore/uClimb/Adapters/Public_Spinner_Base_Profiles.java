package app.playstore.uClimb.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.R;

public class Public_Spinner_Base_Profiles extends BaseAdapter {
    private ArrayList source = new ArrayList<String>();
    private ArrayList name = new ArrayList<String>();
    private ArrayList uid = new ArrayList<String>();

    public Public_Spinner_Base_Profiles(ArrayList source, ArrayList name, ArrayList uid) {
        this.source = source;
        this.name = name;
        this.uid = uid;
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Object getItem(int position) {
        return name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_spinner_search_item, parent, false);
            convertView = view;
        }
        de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.image_round_spinner_search);
        TextView textView = convertView.findViewById(R.id.spinner_search_txt);
        Picasso.get().load(source.get(position).toString()).fit().into(circleImageView);
        textView.setText(name.get(position).toString());
        return  convertView;
    }
}
