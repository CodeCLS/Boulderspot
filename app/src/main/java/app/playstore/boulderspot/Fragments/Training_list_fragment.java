package app.playstore.boulderspot.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.Adapters.training_list_adapter;
import app.playstore.boulderspot.R;

public class Training_list_fragment extends Fragment {
    private ArrayList<String> array_txt = new ArrayList<>();
    private ArrayList<String> array_img_link = new ArrayList<>();

    private training_list_adapter training_adapter = new training_list_adapter(array_img_link,array_txt ,getContext());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.training_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setarrayitems();

        initRec(view);
    }

    private void setarrayitems() {
        array_img_link.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F02-001-002-1.png?alt=media&token=6b027f8c-67fa-4581-a020-b14c3a2701dc");
        array_txt.add("Hangboard");
        array_img_link.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fkl-dehn-ubungen-fur-kletterer-richtig-dehnen-teaserbild-n-jpg--bigMobileWideOdc-3394e8b-1565918.jpg?alt=media&token=04e73511-a15d-4e7a-add3-e6cfc22f0f57");
        array_txt.add("Dehnen");
        array_img_link.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F2018_01_Aufwaermen-Klettern-Sophie-Arnold-Beitragsbild.jpg?alt=media&token=387c02b5-916c-4e8b-b1d3-ae373c026568");
        array_txt.add("Aufwärmen");
        array_img_link.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F2019-Boulderwelt-Regensburg-Event-Bouldern-Klettern-Bouldergame-Catch-Ya-Match-25.jpg?alt=media&token=3dfa8e9a-47f4-40d9-a777-f6287d397685");
        array_txt.add("Custom");
    }

    private void initRec(View view) {
        RecyclerView rec = view.findViewById(R.id.rec_training_list);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setAdapter(training_adapter);

    }
}
