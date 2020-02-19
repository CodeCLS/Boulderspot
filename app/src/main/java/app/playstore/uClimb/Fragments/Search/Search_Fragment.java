package app.playstore.uClimb.Fragments.Search;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


import java.util.ArrayList;

import app.playstore.uClimb.Adapters.Adapter_search;
import app.playstore.uClimb.R;

public class Search_Fragment extends Fragment {
    private EditText search_edit;
    private ImageView search_img;
    private static final String TAG = "Search_Fragment";
    private static ArrayList<String> IMG_URL= new ArrayList<>();
    private static ArrayList<String> Date   = new ArrayList<>();
    private static ArrayList<String> Info = new ArrayList<>();
    private static ArrayList<String> Location= new ArrayList<>();
    private static ArrayList<String> type_array= new ArrayList<>();





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_page, container, false);




    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsexceptRec(view);
        IMG_URL.clear();
        Date.clear();
        Location.clear();
        Info.clear();
        type_array.clear();
        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FNameYoWantToAdd?alt=media&token=00ab2c0b-6397-4e9a-8c04-c22b3da1e496");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("video");
        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FNameYoWantToAdd?alt=media&token=00ab2c0b-6397-4e9a-8c04-c22b3da1e496");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("video");
        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FNameYoWantToAdd?alt=media&token=00ab2c0b-6397-4e9a-8c04-c22b3da1e496");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("video");
        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FNameYoWantToAdd?alt=media&token=00ab2c0b-6397-4e9a-8c04-c22b3da1e496");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("video");

       IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F141047_00_2x.jpg?alt=media&token=1e726061-5bf6-4738-9dd8-ebb9d531513d");
       Date.add("ada");
       Location.add("asdasd");
       Info.add("asdasd");
       type_array.add("img");

       IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F141047_00_2x.jpg?alt=media&token=1e726061-5bf6-4738-9dd8-ebb9d531513d");
       Date.add("ada");
       Location.add("asdasd");
       Info.add("asdasd");
       type_array.add("img");

       IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F141047_00_2x.jpg?alt=media&token=1e726061-5bf6-4738-9dd8-ebb9d531513d");
       Date.add("ada");
       Location.add("asdasd");
       Info.add("asdasd");
       type_array.add("img");

        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F141047_00_2x.jpg?alt=media&token=1e726061-5bf6-4738-9dd8-ebb9d531513d");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("img");



        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F141047_00_2x.jpg?alt=media&token=1e726061-5bf6-4738-9dd8-ebb9d531513d");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("img");





        IMG_URL.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F141047_00_2x.jpg?alt=media&token=1e726061-5bf6-4738-9dd8-ebb9d531513d");
        Date.add("ada");
        Location.add("asdasd");
        Info.add("asdasd");
        type_array.add("img");











        initRec(view);




    }

    private void setOnClick() {
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_string =search_edit.getText().toString();



            }
        });
    }

    private void initViewsexceptRec(View view) {





    }

    private void initRec(@NonNull View view) {
        RecyclerView rec = view.findViewById(R.id.rec_search_page);
        int numbercolumns = 2;
        rec.setLayoutManager(new GridLayoutManager(getContext(),numbercolumns));


        rec.setAdapter(new Adapter_search(IMG_URL,Date,Location,Info,type_array,getContext()));



    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
