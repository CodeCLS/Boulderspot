package app.playstore.uClimb.Fragments.Search;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
        RecyclerView rec = view.findViewById(R.id.rec_search_main);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setAdapter(new Adapter_search());



    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
