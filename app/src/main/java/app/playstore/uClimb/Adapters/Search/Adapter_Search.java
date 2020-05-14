package app.playstore.uClimb.Adapters.Search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.R;
import app.playstore.uClimb.MVP.MVP_Search.Presenter_Search;

public class Adapter_Search extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "Adapter_search";
    private Context mContext;
    private ArrayList peoples = new ArrayList();
    private ArrayList people_id = new ArrayList();
    private ArrayList peoples_img = new ArrayList<String>();
    private List<UserList> peoples_list = new ArrayList<>();

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
            getPeopleImg(holder);


        }
        if (holder.getItemViewType() ==1){
            Recyclerview_viewholder viewHolderrec = (Recyclerview_viewholder) holder;

            Presenter_Search search_presenter = new Presenter_Search();
            search_presenter.setRec(holder.itemView,mContext);

        }

    }

    private void holder_zero(RecyclerView.ViewHolder holder) {
        ViewHolderSearch viewHolderSearch = (ViewHolderSearch) holder;
        Log.d(TAG,"peoples234"+peoples_list.get(1).getSecondValue());
        Adapter_Spinner adapter = new Adapter_Spinner(mContext,R.layout.public_spinner_search_item,peoples_list);
        viewHolderSearch.editText.setAdapter(adapter);
        Log.d(TAG,"array_people2" + adapter);
        viewHolderSearch.editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                custom_profile custom_profile = new custom_profile();
                Bundle bundle = new Bundle();
                bundle.putString("uid",people_id.get(position).toString());
                custom_profile.setArguments(bundle);

                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment,custom_profile).commit();
            }
        });

        viewHolderSearch.Enter.setOnClickListener(v -> {


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

    private void getPeopleImg(RecyclerView.ViewHolder holder) {

        peoples_img.clear();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").getChildren()){
                    peoples_img.add(postSnapshot.child("IMG").getValue().toString());


                }
                getPeople(holder);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getPeople(RecyclerView.ViewHolder holder) {


        people_id.clear();
        peoples.clear();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").getChildren()){
                    peoples_list.add(new UserList(postSnapshot.getKey().toString(),postSnapshot.child("Name").getValue().toString(),postSnapshot.child("IMG").getValue().toString()));
                    people_id.add(postSnapshot.getKey().toString());
                    peoples.add(postSnapshot.child("Name").getValue().toString());


                }
                Log.d(TAG,"array_people" + peoples);

                holder_zero(holder);

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
