package app.playstore.boulderspot.Fragments;

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

import app.playstore.boulderspot.Adapters.Adapter_community;
import app.playstore.boulderspot.Adapters.Adapter_home;
import app.playstore.boulderspot.R;

public class Community_Fragment extends Fragment {
    private ArrayList<String> array_img = new ArrayList<>();
    private ArrayList<String> array_title = new ArrayList<>();
    private ArrayList<String> array_description = new ArrayList<>();

    private Adapter_community Adapter_community = new Adapter_community(array_title,array_img,array_description, getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.community_page, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();

        RecyclerView rec;

        rec = view.findViewById(R.id.rec_home);
        rec.setAdapter(Adapter_community);

        rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setAdapter() {
        array_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fmelody-jacob-hb8v-SZm7VY-unsplash.jpg?alt=media&token=d9daa0e9-a575-4c6a-8143-e19acc2b1649");
        array_title.add("Chat");
        array_description.add("Ein Ort an dem Boulderer und Kletterer miteinander kommunizieren können");

        array_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fmelody-jacob-hb8v-SZm7VY-unsplash.jpg?alt=media&token=d9daa0e9-a575-4c6a-8143-e19acc2b1649");
        array_title.add("Training");
        array_description.add("Ein Ort an dem Boulderer und Kletterer neue Wege finden sich auzuwärmen und fitter zu werden");

        array_img.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2Fmelody-jacob-hb8v-SZm7VY-unsplash.jpg?alt=media&token=d9daa0e9-a575-4c6a-8143-e19acc2b1649");
        array_title.add("Freunde");
        array_description.add("Ein Ort an dem Boulderer und Kletterer sehen können ob Freunde in einer Boulderhalle sind ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
