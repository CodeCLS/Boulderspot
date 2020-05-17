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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        Log.d(TAG,"templist: " + objects);

        UserList userList = getItem(position);
        TextView txt = convertView.findViewById(R.id.spinner_search_txt);
        de.hdodenhof.circleimageview.CircleImageView circleImageView = convertView.findViewById(R.id.image_round_spinner_search);
        txt.setText((CharSequence) userList.getSecondValue());
        Picasso.get().load(userList.getThirdValue().toString()).fit().into(circleImageView);

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<String> tempList=new ArrayList<String>();
                //constraint is the result from text you want to filter against.
                //objects is your data set you will filter from
                if(constraint != null && objects!=null) {
                    int length=objects.size();
                    int i=0;
                    while(i<length){
                        String item=objects.get(i).getSecondValue();
                        //do whatever you wanna do here
                        //adding result set output array

                        tempList.add(item);

                        i++;
                    }
                    //following two lines is very important
                    //as publish result can only take FilterResults objects
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                //objects = (ArrayList<UserList>) results.values;
                notifyDataSetChanged();
                clear();
                notifyDataSetInvalidated();
            }
        };
        return myFilter;
    }



    @Override
    public int getPosition(@Nullable UserList item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
