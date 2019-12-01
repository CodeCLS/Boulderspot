package app.playstore.boulderspot.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.playstore.boulderspot.Models.Event_Model;

public class Fire_Event extends AppCompatActivity {
    private static ArrayList<String> IMG_URL= new ArrayList<>();
    private static ArrayList<String> Date   = new ArrayList<>();
    private static ArrayList<String> Title_boulderhalle = new ArrayList<>();
    private static ArrayList<String> Location= new ArrayList<>();
    private static ArrayList<String> costs  = new ArrayList<>();
    private static ArrayList<String> min_age= new ArrayList<>();
    private static ArrayList<String> Grade  = new ArrayList<>();
    private static ArrayList<String> event_name = new ArrayList<>();
    private static ArrayList<String> time   = new ArrayList<>();
    private static ArrayList<String> info   = new ArrayList<>();
    private static final String TAG = "Fire_Event";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("indoor_bouldering");
    private static FirebaseDatabase database_list = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef_list = database_list.getReference("indoor_bouldering");


    public Fire_Event() {
        getSize();







    }
    public static void getEvent_attributes(int i){
        getVal(i);

    }

    private void getSize() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event_Model.setSize(Integer.parseInt(dataSnapshot.getChildren().toString()));

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private static boolean getVal(final int i) {

        // Read from the database
        myRef_list.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if (postSnapshot.child("IMG_URL").exists()&&postSnapshot.child("IMG_URL").exists()&&postSnapshot.child("IMG_URL").exists()
                            &&postSnapshot.child("IMG_URL").exists()&&postSnapshot.child("IMG_URL").exists()
                            &&postSnapshot.child("IMG_URL").exists()&&postSnapshot.child("IMG_URL").exists()){
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("Date").getValue().toString());
                        IMG_URL.add(postSnapshot.child("Title_boulderhalle").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());
                        IMG_URL.add(postSnapshot.child("IMG_URL").getValue().toString());


                    }
                    else{
                        return;
                    }



                }
                Event_Model.setImgUrl(IMG_URL.get(i));
                Event_Model.setDate(Date.get(i));
                Event_Model.setTitle_boulderhalle(Title_boulderhalle.get(i));
                Event_Model.setLocation(Location.get(i));
                Event_Model.setCosts(costs.get(i));
                Event_Model.setMin_age(min_age.get(i));
                Event_Model.setGrade(Grade.get(i));
                Event_Model.setEvent_name(event_name.get(i));
                Event_Model.setTime(time.get(i));
                Event_Model.setGrade(Grade.get(i));
                Event_Model.setInfo(info.get(i));










            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        return false;
    }
}
