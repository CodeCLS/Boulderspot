package app.playstore.uClimb.MVP.MVP_Video_Upload;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;

public class Presenter_Video {
    private static final String TAG = "VIDEO_presenter";
    private String place_id;
    private String grade;
    private String impression;
    private String type;
    private String tries;
    private String place_name;
    private String info;

    public Presenter_Video(String place_id, String grade, String impression, String type, String tries, String place_name, String info) {
        this.place_id = place_id;
        this.grade = grade;
        this.impression = impression;
        this.type = type;
        this.tries = tries;
        this.place_name = place_name;
        this.info = info;
    }



    public void addSuccess( Context mContext, String random_hash, Boolean source_status, Boolean statistics_status, Uri Source) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        Presenter_Login login_presenter = new Presenter_Login();
        String statistics_id = login_presenter.getStatisticsID(mContext);
        String uid = login_presenter.getUID(mContext);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int points = Integer.parseInt(dataSnapshot.child("User").child(uid).child("Points").getValue().toString());
                if (source_status) {
                    writeData(databaseReference,uid,random_hash,currentDateandTime,statistics_id,statistics_status,Source,points);
                    databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");


                } else {
                    writeData(databaseReference,uid,random_hash,currentDateandTime,statistics_id,statistics_status,Source,points);
                    databaseReference.child("Posts").child(random_hash).child("type").setValue("Video");



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        // Upload succeeded

    }

    private void writeData(DatabaseReference databaseReference, String uid, String random_hash, String currentDateandTime, String statistics_id, Boolean statistics_status, Uri Source, int points) {
        checkStatistics(databaseReference,statistics_id,statistics_status,random_hash,currentDateandTime,Source,points,uid);
        databaseReference.child("User").child(uid).child("Posts").child(random_hash).setValue(random_hash);



        databaseReference.child("Posts").child(random_hash).child("Info").setValue(info);
        databaseReference.child("Posts").child(random_hash).child("Place").setValue(place_id);
        databaseReference.child("Posts").child(random_hash).child("Place_name").setValue(place_name);




        databaseReference.child("Posts").child(random_hash).child("Time").setValue(currentDateandTime);
        databaseReference.child("Posts").child(random_hash).child("User_ID").setValue(uid);
        databaseReference.child("Posts").child(random_hash).child("Source").setValue(Source.toString());
        databaseReference.child("Posts").child(random_hash).child("comments").setValue("comments");
        databaseReference.child("Posts").child(random_hash).child("likes").setValue("likes");
        databaseReference.child("Posts").child(random_hash).child("saved").setValue("saved");
        databaseReference.child("Posts").child(random_hash).child("shared").setValue("shared");


    }

    private void checkStatistics(DatabaseReference databaseReference, String statistics_id, Boolean statistics_status, String random_hash, String currentDateandTime, Uri Source, int points, String uid) {
        if (statistics_status) {
            databaseReference.child("User").child(uid).child("Points").setValue(points+points_system(grade));

            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).setValue(random_hash);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("Location").setValue(place_id);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("route_type").setValue(type);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("tries_num").setValue(tries);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("difficulty").setValue(grade);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("Info").setValue(info);

            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("difficulty_impression").setValue(impression);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("Source").setValue(Source.toString());

            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("Place_name").setValue(place_name);
            databaseReference.child("Statistics").child(statistics_id).child("Boulder_problem").child(random_hash).child("Time").setValue(currentDateandTime);
        }
    }
    private int points_system(String competition_grade_s){
        int points = 0;

        if (competition_grade_s.equals("V1") || competition_grade_s.equals("V2")) {
            points++;
        }
        if (competition_grade_s.equals("V3") || competition_grade_s.equals("V4")){
            points = points +2;
        }
        if (competition_grade_s.equals("V5") || competition_grade_s.equals("V6")){
            points = points +5;
        }
        if (competition_grade_s.equals("V7") || competition_grade_s.equals("V8")){
            points = points +8;
        }
        if (competition_grade_s.equals("V9") || competition_grade_s.equals("V10")){
            points = points +11;
        }
        if (competition_grade_s.equals("V11") || competition_grade_s.equals("V12")){
            points = points +14;
        }
        if (competition_grade_s.equals("V13") || competition_grade_s.equals("V14")){
            points = points +18;
        }
        if (competition_grade_s == "V15" || competition_grade_s == "V16"){
            points = points +20;
        }
        if (competition_grade_s == "V17"){
            points = points +25;
        }
        Log.d(TAG,"points_presenter" + points);
        return points;

    }
}

