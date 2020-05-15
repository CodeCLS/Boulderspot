package app.playstore.uClimb.MVP.MVP_Training;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import app.playstore.uClimb.Fragments.Training.Hangboard_page;
import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Presenter_Training {

    private static final String TAG = "Presenter_Training";
    private ArrayList workouts = new ArrayList<String>();
    private HashMap<String,Integer> workouts_final = new HashMap<>();

    String Hang_Time;
    String Pause_Time;
    String Rest_Time;
    String Sets;
    String Rounds;

    private int btn_add_status = 1;

    private String id;
    private String statistics_id;
    private Presenter_Login presenter_login;

    public Presenter_Training(String hang_Time, String pause_Time, String rest_Time, String sets, String rounds,Context context) {
        Hang_Time = hang_Time;
        Pause_Time = pause_Time;
        Rest_Time = rest_Time;
        Sets = sets;
        Rounds = rounds;
        presenter_login = new Presenter_Login();
        statistics_id = presenter_login.getStatisticsID(context);
    }

    public void getSpinnerData(Context context, Spinner spinner) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("Statistics").child(statistics_id).child("Workouts").getChildren()) {

                    String workout = postSnapshot.child("Name").getValue().toString();
                    workouts.add(workout);






                }
                for (int i = 0; i< workouts.size();i++){
                    if (workouts_final.containsKey(workouts.get(i))){
                        Integer integer = Integer.parseInt(workouts_final.get(workouts.get(i)).toString());
                        integer++;
                        workouts_final.put(workouts.get(i).toString(),integer);

                    }
                    else{

                        workouts_final.put(workouts.get(i).toString(),1);

                    }
                }
                setSpinner(context, spinner);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setSpinner(Context context,Spinner spinner_workouts) {
        ArrayList<String> workouts = new ArrayList<String>();
        for (String key: workouts_final.keySet()){
            workouts.add(key);
        }


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, workouts);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_workouts.setAdapter(adapter);


    }


    //private View view;








    public void onClickBtn(Context context, String Hang_Time, String Pause_Time, String Rest_Time, String Sets, String Rounds, Button btn_cancel, Button btn_log, CircleImageView add_workout_btn, Spinner spinner, CheckBox checkbox_public_workout, EditText edit_add_workout_log, EditText info){


    getSpinnerData(context,spinner);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_add_status ==0){
                    if (info.getText().toString().length() >1 && info.getText().toString().length() < 60){
                        if (edit_add_workout_log.getText().toString().length() >1 && edit_add_workout_log.getText().toString().length() < 15){
                            //)

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Presenter_Login presenter_login = new Presenter_Login();
                                    String uid = presenter_login.getUID(context);
                                    int points = Integer.parseInt(dataSnapshot.child("User").child(uid).child("Points").getValue().toString());

                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Statistics").child(statistics_id).child("Workouts").child(getRandomString(10));
                                    databaseReference.child("Name").setValue(edit_add_workout_log.getText().toString());
                                    databaseReference.child("Info").setValue(info.getText().toString());
                                    databaseReference1.child("User").child(uid).child("points").setValue(points+1);

                                    databaseReference.child("Hang_Time").setValue(Hang_Time);
                                    databaseReference.child("Pause_Time").setValue(Pause_Time);
                                    databaseReference.child("Rest_Time").setValue(Rest_Time);
                                    databaseReference.child("Sets").setValue(Sets);
                                    databaseReference.child("Rounds").setValue(Rounds);
                                    databaseReference.child("Time").setValue(currentDateandTime);
                                    if (checkbox_public_workout.isChecked()){
                                        databaseReference.child("Public").setValue(true);


                                    }
                                    else{
                                        databaseReference.child("Public").setValue(true);

                                    }

                                    transaction((AppCompatActivity)context);

                                    Toast.makeText(context, "Protokolliert", Toast.LENGTH_SHORT).show();


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }
                        else{
                            Toast.makeText(context, "Zu kurzer oder langer Workout Name", Toast.LENGTH_SHORT).show();


                        }

                    }
                    else{
                        Toast.makeText(context, R.string.Buchstaben_Zeichen, Toast.LENGTH_SHORT).show();

                    }


                }
                else{

                        if (info.getText().toString().length() >1 && info.getText().toString().length() < 60) {
                            if (spinner.getSelectedItem() != null && spinner.getSelectedItem() != ""){

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                                String currentDateandTime = sdf.format(new Date());


                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Statistics").child(statistics_id).child("Workouts").child(getRandomString(10));
                                databaseReference.child("Name").setValue(spinner.getSelectedItem());
                                databaseReference.child("Info").setValue(info.getText().toString());

                                databaseReference.child("Hang_Time").setValue(Hang_Time);
                                databaseReference.child("Pause_Time").setValue(Pause_Time);
                                databaseReference.child("Rest_Time").setValue(Rest_Time);
                                databaseReference.child("Sets").setValue(Sets);
                                databaseReference.child("Rounds").setValue(Rounds);
                                databaseReference.child("Time").setValue(currentDateandTime);
                                if (checkbox_public_workout.isChecked()){
                                    databaseReference.child("Public").setValue(true);


                                }
                                else{
                                    databaseReference.child("Public").setValue(true);

                                }

                                transaction((AppCompatActivity)context);

                                Toast.makeText(context, "Logged", Toast.LENGTH_SHORT).show();



                            }
                            else{
                                String s = context.getString(R.string.trainingseinheitertellen);
                                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

                            }

                        }
                        else{
                            Toast.makeText(context, R.string.Buchstaben_Zeichen, Toast.LENGTH_SHORT).show();


                        }




                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction((AppCompatActivity) context);
            }
        });
        add_workout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_add_status ==1){
                    spinner.setVisibility(View.GONE);
                    edit_add_workout_log.setVisibility(View.VISIBLE);
                    btn_add_status = 0;


                }
                else{
                    spinner.setVisibility(View.VISIBLE);
                    edit_add_workout_log.setVisibility(View.GONE);
                    btn_add_status = 1;
                    //TODO add


                }


            }
        });


    }

    private void transaction(AppCompatActivity context) {
        Hangboard_page hangboard_page = new Hangboard_page();

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container_fragment,hangboard_page);
        fragmentTransaction.commit();
    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
