package app.playstore.uClimb.Fragments;

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

import java.util.ArrayList;

import app.playstore.uClimb.Adapters.Adapter_community;
import app.playstore.uClimb.R;

public class Community_Fragment extends Fragment {
    private ArrayList<String> array_img = new ArrayList<>();
    private ArrayList<String> array_title = new ArrayList<>();
    private ArrayList<String> array_description = new ArrayList<>();

    private Adapter_community Adapter_community = new Adapter_community(array_title,array_img, getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.community_page, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();

        setRec(view);
    }

    private void setRec(@NonNull View view) {
        RecyclerView rec;

        rec = view.findViewById(R.id.rec_community);
        rec.setAdapter(Adapter_community);

        rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setAdapter() {
        cleararray();
        addData();

    }

    private void addData() {
        array_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F2019-Boulderwelt-Regensburg-Event-Bouldern-Klettern-Bouldergame-Catch-Ya-Match-25.jpg?alt=media&token=3dfa8e9a-47f4-40d9-a777-f6287d397685");
        array_title.add("Locations");
        array_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F2019-Boulderwelt-Regensburg-Event-Bouldern-Klettern-Bouldergame-Catch-Ya-Match-25.jpg?alt=media&token=3dfa8e9a-47f4-40d9-a777-f6287d397685");
        array_title.add("Freunde");
        array_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2F2019-Boulderwelt-Regensburg-Event-Bouldern-Klettern-Bouldergame-Catch-Ya-Match-25.jpg?alt=media&token=3dfa8e9a-47f4-40d9-a777-f6287d397685");
        array_title.add("Statistik");
    }

    private void cleararray() {
        array_description.clear();
        array_title.clear();
        array_img.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
