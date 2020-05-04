package app.playstore.uClimb.Adapters.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Spinner extends ArrayAdapter {
    private ArrayList source;
    private ArrayList name;
    private Context mContext;

    public Adapter_Spinner(@NonNull Context context, int resource, ArrayList source, ArrayList name, Context mContext) {
        super(context, resource);
        this.source = source;
        this.name = name;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
