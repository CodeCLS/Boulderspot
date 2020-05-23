package app.playstore.uClimb.Adapters.Search;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class Adapter_Spinner extends ArrayAdapter<String> {
    private static final String TAG = "Adapter_Spinner";

    List objects;




    public Adapter_Spinner(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.objects = objects;
        Log.d(TAG,"object23" + objects);
        UserList userList = (UserList) objects.get(0);
        Log.d(TAG,"Objects : 23" + userList.getSecondValue());



    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_spinner_search_item, parent, false);
            convertView = view;
        }
        Log.d(TAG,"templist: " + objects);

        TextView txt = convertView.findViewById(R.id.spinner_search_txt);
        //de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.image_round_spinner_search);
        txt.setText(getItem(position));


        return convertView;
    }


    }



