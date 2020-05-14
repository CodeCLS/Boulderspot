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


public class Adapter_Spinner extends ArrayAdapter<UserList> {
    private static final String TAG = "Adapter_Spinner";

    List<UserList> objects;



    public Adapter_Spinner(Context context, int resource, List<UserList> objects) {
        super(context, resource, objects);
        this.objects = objects;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_spinner_search_item, parent, false);
            convertView = view;
        }
        UserList userList = getItem(position);

        TextView txt = convertView.findViewById(R.id.spinner_search_txt);
        de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.image_round_spinner_search);
        txt.setText(userList.getSecondValue());
        Picasso.get().load(userList.getThirdValue().toString()).fit().into(circleImageView);

        return convertView;
    }



}
