package app.playstore.uClimb.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.search_presenter.Search_presenter;

public class Adapter_search extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Adapter_search";
    Context mContext;
    ArrayList peoples = new ArrayList();
    ArrayList people_id = new ArrayList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == 0){
            view= LayoutInflater.from(mContext).inflate(R.layout.search_bar,parent,false);
            viewHolder = new ViewHolderSearch(view);



        }
        if (viewType == 1){
            view= LayoutInflater.from(mContext).inflate(R.layout.search_custom_recyclerview,parent,false);
            viewHolder = new Recyclerview_viewholder(view);

        }
        assert viewHolder != null;
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()== 0){
            getPeople();

            ViewHolderSearch viewHolderSearch = (ViewHolderSearch) holder;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                    android.R.layout.simple_dropdown_item_1line, peoples);
            viewHolderSearch.editText.setAdapter(adapter);
            viewHolderSearch.Enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String input = viewHolderSearch.editText.getText().toString();
                    Log.d(TAG,"array_people" + peoples);
                    if (peoples.contains(input)){
                        custom_profile custom_profile = new custom_profile();
                        int index =peoples.indexOf(input);
                        Bundle bundle = new Bundle();
                        bundle.putString("uid",people_id.get(index).toString());
                        custom_profile.setArguments(bundle);
                        FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                        fragmentManager.beginTransaction().addToBackStack("Fragment_custom_post").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.container_fragment, custom_profile).commit();




                    }
                    else{
                        Toast.makeText(mContext, "Diesen User gibt es nicht", Toast.LENGTH_SHORT).show();
                    }

                }
            });
          //  viewHolderSearch.Enter.setOnClickListener(v -> {
          //      String input =((ViewHolderSearch) holder).editText.getText().toString();
//
//
          //      //TODO enter onclick
//x
//
//
          //  });


        }
        if (holder.getItemViewType() ==1){
            Recyclerview_viewholder viewHolderrec = (Recyclerview_viewholder) holder;

            Search_presenter search_presenter = new Search_presenter();
            search_presenter.setRec(holder.itemView,mContext);

        }

    }

    private void getPeople() {


        people_id.clear();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").getChildren()){
                    people_id.add(postSnapshot.getKey().toString());
                    peoples.add(postSnapshot.child("Name").getValue().toString());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (position == 0){
            i = 0;
        }
        if (position == 1){
            i = 1;

        }
        return i;

    }

    public static class ViewHolderSearch extends RecyclerView.ViewHolder{
        Button Enter;
        AutoCompleteTextView editText;


        public ViewHolderSearch(@NonNull View itemView) {
            super(itemView);
            Enter = itemView.findViewById(R.id.btn_search_search);
            editText = itemView.findViewById(R.id.edit_search_nav);

        }
    }
    public class Recyclerview_viewholder extends RecyclerView.ViewHolder{
        RecyclerView recyclerview;


        public Recyclerview_viewholder(@NonNull View itemView) {
            super(itemView);
            recyclerview = itemView.findViewById(R.id.rec_search_page);

        }
    }
}
