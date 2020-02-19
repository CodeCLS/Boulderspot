package app.playstore.uClimb.Fragments.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.playstore.uClimb.R;

public class Fragment_chat_list extends Fragment {

  //  private app.playstore.boulderspot.Adapters.Adapter_community Adapter_community = new Adapter_community(array_title,array_img,array_description, getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.chat_list, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //RecyclerView rec;

        //rec = view.findViewById(R.id.rec_home);

        //rec.setAdapter(Adapter_community);

        //rec.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
