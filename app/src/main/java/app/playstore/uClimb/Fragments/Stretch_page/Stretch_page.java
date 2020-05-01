package app.playstore.uClimb.Fragments.Stretch_page;

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

import app.playstore.uClimb.Adapters.Training.Adapter_Training_Stretching;
import app.playstore.uClimb.R;

public class Stretch_page extends Fragment {
    private ArrayList<String> source = new ArrayList<>();
    private ArrayList<String> text = new ArrayList<>();

    private Adapter_Training_Stretching adapter_stretch = new Adapter_Training_Stretching(source,text);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_stretch_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addData();

        initRec(view);
    }

    private void addData() {
        source.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FInsistentImpracticalBobolink-mobile.mp4?alt=media&token=f649fbee-ef5d-4046-a9bb-9985c101b9da");
        text.add("In this variation, you do the exact same as the first but you don’t lean as far forward and instead attempt to lift your palms off the ground. Some people are much more flexible in this plane than others, but everyone should be able to notice a stretch.");


        source.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FInsistentImpracticalBobolink-mobile.mp4?alt=media&token=f649fbee-ef5d-4046-a9bb-9985c101b9da");
        text.add("In this variation, you do the exact same as the first but you don’t lean as far forward and instead attempt to lift your palms off the ground. Some people are much more flexible in this plane than others, but everyone should be able to notice a stretch.");


        source.add("https://firebasestorage.googleapis.com/v0/b/boulderspot-42564.appspot.com/o/Folder%2FInsistentImpracticalBobolink-mobile.mp4?alt=media&token=f649fbee-ef5d-4046-a9bb-9985c101b9da");
        text.add("In this variation, you do the exact same as the first but you don’t lean as far forward and instead attempt to lift your palms off the ground. Some people are much more flexible in this plane than others, but everyone should be able to notice a stretch.");
    }

    private void initRec(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rec_stretch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter_stretch);
    }
}
