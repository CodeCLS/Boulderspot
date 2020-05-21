package app.playstore.uClimb.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.uClimb.Adapters.Training.Adapter_Training_List;
import app.playstore.uClimb.R;

public class Training_list_fragment extends Fragment {
    private ArrayList<String> array_txt = new ArrayList<>();
    private ArrayList<String> array_img_link = new ArrayList<>();


    private Adapter_Training_List training_adapter = new Adapter_Training_List(array_img_link,array_txt ,getContext());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.main_training_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setarrayitems();

        initRec(view);
    }

    private void setarrayitems() {
        array_img_link.clear();
        array_txt.clear();

        array_img_link.add(getContext().getResources().getString(R.string.link_hangboard_img));
        array_txt.add("Training");
        array_img_link.add(getContext().getResources().getString(R.string.stretching_img_link));
        array_txt.add("Dehnen");
        array_img_link.add(getContext().getString(R.string.warmup_link_img));
        array_txt.add("Aufwärmen");
        array_img_link.add(getContext().getString(R.string.custom_link_img));
        array_txt.add("Custom");
    }

    private void initRec(View view) {
        Log.d("main_training_list" , "context:" + training_adapter + "context_real:" + getContext());
        RecyclerView rec = view.findViewById(R.id.rec_training_list);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setAdapter(training_adapter);

    }
}
