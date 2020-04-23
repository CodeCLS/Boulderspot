package app.playstore.uClimb.ViewModelPresenters.video;

import android.content.Context;
import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;

public class video_presenter {
    private static final String TAG = "VIDEO_presenter";
    private String place_id;
    private String grade;
    private String impression;
    private String type;
    private String tries;
    private String place_name;
    private String info;

    public video_presenter(String place_id, String grade, String impression, String type, String tries, String place_name, String info) {
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
        login_presenter login_presenter = new login_presenter();
        String statistics_id = login_presenter.getStatisticsID(mContext);
        String uid = login_presenter.getUID(mContext);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("");
        if (source_status) {
            writeData(databaseReference,uid,random_hash,currentDateandTime,statistics_id,statistics_status,Source);
            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");


        } else {
            writeData(databaseReference,uid,random_hash,currentDateandTime,statistics_id,statistics_status,Source);
            databaseReference.child("Posts").child(random_hash).child("type").setValue("Video");



        }





        // Upload succeeded

    }

    private void writeData(DatabaseReference databaseReference, String uid, String random_hash, String currentDateandTime, String statistics_id, Boolean statistics_status, Uri Source) {
        checkStatistics(databaseReference,statistics_id,statistics_status,random_hash,currentDateandTime,Source);
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

    private void checkStatistics(DatabaseReference databaseReference, String statistics_id, Boolean statistics_status, String random_hash, String currentDateandTime, Uri Source) {
        if (statistics_status) {
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
}

