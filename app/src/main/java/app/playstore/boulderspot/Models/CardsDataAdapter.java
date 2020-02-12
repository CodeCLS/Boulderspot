package app.playstore.boulderspot.Models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import app.playstore.boulderspot.R;

public class CardsDataAdapter extends ArrayAdapter<String> {

    public CardsDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        //supply the layout for your card
        ImageView v = (ImageView) (contentView.findViewById(R.id.content_card_img));
        Picasso.get().load(getItem(position)).into(v);

        return contentView;
    }

}
