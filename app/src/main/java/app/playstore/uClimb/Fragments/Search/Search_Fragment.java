package app.playstore.uClimb.Fragments.Search;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import app.playstore.uClimb.Adapters.Search.Adapter_Spinner;
import app.playstore.uClimb.Adapters.Search.Adapter_Spinner;
import app.playstore.uClimb.Adapters.Search.UserList;
import app.playstore.uClimb.Fragments.custom_profile;
import app.playstore.uClimb.MVP.MVP_Search.Presenter_Search;
import app.playstore.uClimb.R;

public class Search_Fragment extends Fragment {

    private static final String TAG = "Search_Fragment";
    private static ArrayList<String> IMG_URL= new ArrayList<>();
    private static ArrayList<String> Date   = new ArrayList<>();
    private static ArrayList<String> Info = new ArrayList<>();
    private static ArrayList<String> Location= new ArrayList<>();
    private static ArrayList<String> type_array= new ArrayList<>();

    private ArrayList peoples = new ArrayList();
    private ArrayList people_id = new ArrayList();
    private ArrayList peoples_img = new ArrayList<String>();
    private List<UserList> peoples_list = new ArrayList<>();

    private AutoCompleteTextView autoCompleteTextView;
    private ImageView Enter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.main_search_page, container, false);





    }



    private void holder_zero() {
        Log.d(TAG,"peoples_list " + peoples_list + "  " + peoples_img);
        Context context = getContext();
        if (context != null) {
            ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, peoples);
            autoCompleteTextView.setAdapter(adapter);


            //autoCompleteTextView.setThreshold(1);

            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    custom_profile custom_profile = new custom_profile();
                    Bundle bundle = new Bundle();
                    String txt_o = adapter.getItem(position).toString();
                    int uid = peoples.indexOf(txt_o);
                    Log.d(TAG,"uidindex" + adapter.getItem(position));
                    bundle.putString("uid", people_id.get(uid).toString());
                    custom_profile.setArguments(bundle);

                    FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack("Fragment_custom_profile").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.container_fragment, custom_profile).commit();
                }
            });
        }

        Enter.setOnClickListener(v -> {


            String input = autoCompleteTextView.getText().toString().trim();
            if (peoples.contains(input)){
                custom_profile custom_profile = new custom_profile();
                int index =peoples.indexOf(input);
                Bundle bundle = new Bundle();
                bundle.putString("uid",people_id.get(index).toString());
                custom_profile.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("Fragment_custom_post").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container_fragment, custom_profile).commit();




            }
            else{
                Toast.makeText(getContext(), "Diesen User gibt es nicht", Toast.LENGTH_SHORT).show();
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

    private void getPeople() {


        people_id.clear();
        peoples.clear();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").getChildren() ){
                    if (postSnapshot.child("Name").exists() && postSnapshot.exists() &&postSnapshot.child("IMG").exists()) {
                        peoples_list.add(new UserList(postSnapshot.getKey().toString(), postSnapshot.child("Name").getValue().toString(), postSnapshot.child("IMG").getValue().toString()));
                        people_id.add(postSnapshot.getKey().toString());
                        peoples.add(postSnapshot.child("Name").getValue().toString());
                    }


                }
                Log.d(TAG,"array_people" + peoples);

                holder_zero();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getPeopleImg() {

        peoples_img.clear();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("User").getChildren()){
                    if (postSnapshot.child("IMG").exists()) {
                        peoples_img.add(postSnapshot.child("IMG").getValue().toString());
                    }


                }
                getPeople();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        getPeopleImg();










        initRec(view);




    }

    private void initViews(View view) {
        autoCompleteTextView = view.findViewById(R.id.edit_search_nav);
        Enter = view.findViewById(R.id.img_search_search);
    }


    private void initRec(@NonNull View view) {
        RecyclerView rec = view.findViewById(R.id.rec_search_main);

        Presenter_Search search_presenter = new Presenter_Search();
        search_presenter.setRec(rec,getContext());




    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}
