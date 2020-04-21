package app.playstore.uClimb.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.login.login_presenter;
import app.playstore.uClimb.ViewModelPresenters.upload_presenter;

public class video_upload_fragment extends Fragment {
    private static final int REQUEST_TAKE_GALLERY_VIDEO = 222;
    private static final int RESULT_OK = -1;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;

    private static final String TAG = "Video_Upload";
    private static final int RESULT_CANCELED = 5;
    private static final int IMG_REQUEST_CODE = 8;
    private TextView file_name;
    private StorageReference mStorageRef;
    private EditText info_edit;
    private Spinner spinner_grade;
    private Spinner spinner_impression_grade;
    private Spinner spinner_tries;
    private Spinner spinner_route_type;
    private Button name_of_location;
    private Button upload_btn;
    private Boolean selected_video = false;
    private Boolean selected_img = true;
    private Boolean selected_source= true;
    private Button import_btn;

    private View view_all;
    private Spinner spinner_source_type;
    private String selected_video_source;
    private String selected_IMG_source = "sdiufnospiufosnfu";
    private String place_id="kjsdf";
    private String place_Name = "Hallo";
    private CheckBox checkbox_statistics;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_all = view;
        initViews(view);
        listener(view);



        onClick();
    }

    private void listener(View view){
        name_of_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int AUTOCOMPLETE_REQUEST_CODE = 1;

// Set the fields to specify which types of place data to
// return after the user has made a selection.
                String apiKey = getResources().getString(R.string.googleapikeay);

                if (!Places.isInitialized()) {
                    Places.initialize(Objects.requireNonNull(getContext()), apiKey);
                }

// Create a new Places client instance.
                PlacesClient placesClient = Places.createClient(Objects.requireNonNull(getContext()));


                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

// Start the autocomplete intent.

                /**
                 * Initialize Places. For simplicity, the API key is hard-coded. In a production
                 * environment we recommend using a secure mechanism to manage API keys.
                 */
                // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
// and once again when the user makes a selection (for example when calling fetchPlace()).
                AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

// Create a RectangularBounds object.
                RectangularBounds bounds = RectangularBounds.newInstance(
                        new LatLng(-33.880490, 151.184363),
                        new LatLng(-33.858754, 151.229596));
// Use the builder to create a FindAutocompletePredictionsRequest.
                FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
// Call either setLocationBias() OR setLocationRestriction().
                        //.setLocationBias(bounds)
                        .setQuery("Bouldering")
                        //.setLocationRestriction(bounds)
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .setSessionToken(token)
                        .build();

                placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
                    for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                        Log.i(TAG, prediction.getPlaceId());
                        Log.i(TAG, prediction.getPrimaryText(null).toString());
                    }
                }).addOnFailureListener((exception) -> {
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Log.e(TAG, "Place not found: " + apiException.getStatusCode());
                    }
                });






                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(getContext());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);




            }
        });
        //String info_text = info_edit.getText().toString();
        //if (info_text.isEmpty() || info_text.length() < 15){
        //    Snackbar snackbar =Snackbar.make(view,"Please enter a text longer than 15 characters", BaseTransientBottomBar.LENGTH_SHORT);
        //    snackbar.setBackgroundTint(getContext().getResources().getColor(R.color.colorPrimaryDark));
        //    snackbar.show();
//
        //}
        //else{



       // }

    }

    private void onClick() {
    //  upload_btn.setOnClickListener(new View.OnClickListener() {
    //      @Override
    //      public void onClick(View v) {
    //          Bundle bundle = new Bundle();

    //          upload_presenter upload_presenter = new upload_presenter();
    //          upload_presenter.addData(view_all,getContext(),bundle);


    //      }
    //  });

      import_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (spinner_source_type.getSelectedItem().toString().equals("Video")) {
                  Log.d(TAG, "Clicked");
                  Intent intent = new Intent();
                  intent.setType("video/*");
                  intent.setAction(Intent.ACTION_GET_CONTENT);
                  startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);


              }
              if (spinner_source_type.getSelectedItem().toString().equals("Image")){
                  Log.d(TAG, "Clicked");
                  Intent intent = new Intent();
                  intent.setType("image/*");
                  intent.setAction(Intent.ACTION_GET_CONTENT);
                  startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQUEST_CODE);

              }
          }

      });
    }

    private void initViews(View view) {
        info_edit = view.findViewById(R.id.upload_info_edit);
        checkbox_statistics = view.findViewById(R.id.checkbox_statistic);
        name_of_location = view.findViewById(R.id.upload_location);
        upload_btn = view.findViewById(R.id.submit_upload_source);
        import_btn = view.findViewById(R.id.upload_source_btn);
        spinner_route_type = view.findViewById(R.id.spinner_route_type_upload);
        spinner_grade = view.findViewById(R.id.spinner_grade_upload);
        spinner_impression_grade = view.findViewById(R.id.spinner_impression_grade_upload);
        spinner_tries = view.findViewById(R.id.spinner_tries_upload);
        spinner_source_type = view.findViewById(R.id.spinner_choose_type_source);
        Drawable drawable = import_btn.getBackground();
        Drawable drawable1 = name_of_location.getBackground();
        drawable1.setTint(getResources().getColor(R.color.blue_pressed_btn));

        drawable.setTint(getResources().getColor(R.color.blue_pressed_btn));
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info_edit.getText().toString().length() > 10 &&selected_source&& place_id != null ){
                    if (!checkbox_statistics.isSelected()){
                        if (selected_img){
                            String random_hash =random(20);
                            login_presenter login_presenter = new login_presenter();
                            String uid = login_presenter.getUID(getContext());
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference("");
                            databaseReference.child("User").child(uid).child("Posts").child(random_hash).setValue(random_hash);
                            databaseReference.child("Posts").child(random_hash).child("Info").setValue(info_edit.getText().toString());
                            databaseReference.child("Posts").child(random_hash).child("Place").setValue(place_id);
                            databaseReference.child("Posts").child(random_hash).child("Place_name").setValue(place_Name);
                            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");


                            databaseReference.child("Posts").child(random_hash).child("Source").setValue(selected_IMG_source);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            databaseReference.child("Posts").child(random_hash).child("Time").setValue(currentDateandTime);
                            databaseReference.child("Posts").child(random_hash).child("User_ID").setValue(uid);
                            databaseReference.child("Posts").child(random_hash).child("comments").setValue("comments");
                            databaseReference.child("Posts").child(random_hash).child("likes").setValue("likes");
                            databaseReference.child("Posts").child(random_hash).child("saved").setValue("saved");
                            databaseReference.child("Posts").child(random_hash).child("shared").setValue("shared");
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).setValue(random_hash);
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).child("Location").setValue(place_id);
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).child("route_type").setValue(spinner_route_type.getSelectedItem().toString());
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).child("tries_num").setValue(spinner_tries.getSelectedItem().toString());
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).child("difficulty").setValue(spinner_grade.getSelectedItem().toString());
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).child("difficulty_impression").setValue(spinner_impression_grade.getSelectedItem().toString());
                            databaseReference.child("Statistics").child("Statistics_ID").child("Boulder_problem").child(random_hash).child("Place_name").setValue(place_Name);








                            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");





                        }
                        if (selected_video){
                            String random_hash =random(20);
                            login_presenter login_presenter = new login_presenter();
                            String uid = login_presenter.getUID(getContext());
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference("");
                            databaseReference.child("User").child(uid).child("Posts").child(random_hash).setValue(random_hash);
                            databaseReference.child("Posts").child(random_hash).child("Info").setValue(info_edit.getText().toString());
                            databaseReference.child("Posts").child(random_hash).child("Place").setValue(place_id);
                            databaseReference.child("Posts").child(random_hash).child("Place_name").setValue(place_Name);
                            databaseReference.child("Posts").child(random_hash).child("type").setValue("Video");


                            databaseReference.child("Posts").child(random_hash).child("Source").setValue(selected_IMG_source);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            databaseReference.child("Posts").child(random_hash).child("Time").setValue(currentDateandTime);
                            databaseReference.child("Posts").child(random_hash).child("User_ID").setValue(uid);
                            databaseReference.child("Posts").child(random_hash).child("comments").setValue("comments");
                            databaseReference.child("Posts").child(random_hash).child("likes").setValue("likes");
                            databaseReference.child("Posts").child(random_hash).child("saved").setValue("saved");
                            databaseReference.child("Posts").child(random_hash).child("shared").setValue("shared");
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).setValue(random_hash);
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).child("Location").setValue(place_id);
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).child("route_type").setValue(spinner_route_type.getSelectedItem().toString());
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).child("tries_num").setValue(spinner_tries.getSelectedItem().toString());
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).child("difficulty").setValue(spinner_grade.getSelectedItem().toString());
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).child("difficulty_impression").setValue(spinner_impression_grade.getSelectedItem().toString());
                            databaseReference.child("Statistics_ID").child("boulder_problem").child(random_hash).child("Place_name").setValue(place_Name);








                            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");







                        }
                        if (selected_video){
                            Toast.makeText(getContext(), "Selected video", Toast.LENGTH_SHORT).show();

                        }


                    }
                    else{
                        if (selected_img){
                            String random_hash =random(20);
                            login_presenter login_presenter = new login_presenter();
                            String uid = login_presenter.getUID(getContext());
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference("");
                            databaseReference.child("User").child(uid).child("Posts").child(random_hash).setValue(random_hash);
                            databaseReference.child("Posts").child(random_hash).child("Info").setValue(info_edit.getText().toString());
                            databaseReference.child("Posts").child(random_hash).child("Place").setValue(place_id);
                            databaseReference.child("Posts").child(random_hash).child("Place_name").setValue(place_Name);
                            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");


                            databaseReference.child("Posts").child(random_hash).child("Source").setValue(selected_IMG_source);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            databaseReference.child("Posts").child(random_hash).child("Time").setValue(currentDateandTime);
                            databaseReference.child("Posts").child(random_hash).child("User_ID").setValue(uid);
                            databaseReference.child("Posts").child(random_hash).child("comments").setValue("comments");
                            databaseReference.child("Posts").child(random_hash).child("likes").setValue("likes");
                            databaseReference.child("Posts").child(random_hash).child("saved").setValue("saved");
                            databaseReference.child("Posts").child(random_hash).child("shared").setValue("shared");






                            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");





                        }
                        if (selected_video){
                            String random_hash =random(20);
                            login_presenter login_presenter = new login_presenter();
                            String uid = login_presenter.getUID(getContext());
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference("");
                            databaseReference.child("User").child(uid).child("Posts").child(random_hash).setValue(random_hash);
                            databaseReference.child("Posts").child(random_hash).child("Info").setValue(info_edit.getText().toString());
                            databaseReference.child("Posts").child(random_hash).child("Place").setValue(place_id);
                            databaseReference.child("Posts").child(random_hash).child("Place_name").setValue(place_Name);
                            databaseReference.child("Posts").child(random_hash).child("type").setValue("Video");


                            databaseReference.child("Posts").child(random_hash).child("Source").setValue(selected_IMG_source);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            databaseReference.child("Posts").child(random_hash).child("Time").setValue(currentDateandTime);
                            databaseReference.child("Posts").child(random_hash).child("User_ID").setValue(uid);
                            databaseReference.child("Posts").child(random_hash).child("comments").setValue("comments");
                            databaseReference.child("Posts").child(random_hash).child("likes").setValue("likes");
                            databaseReference.child("Posts").child(random_hash).child("saved").setValue("saved");
                            databaseReference.child("Posts").child(random_hash).child("shared").setValue("shared");

                            databaseReference.child("Posts").child(random_hash).child("type").setValue("IMG");







                        }

                    }


                    
                    //databaseReference

                }
                else{
                    Toast.makeText(getContext(), "You either havent written more than 10 characters, didn't enter a location or entered a video/image. Please resolve", Toast.LENGTH_LONG).show();
                }
            }
        });


        String[] types = { "Bouldering", "Lead climbing", "Top rope", "Trad climbing", "Free solo" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_route_type.setAdapter(adapter);

        String[] type_source = { "Video" , "Image" };
        ArrayAdapter<String> adapter_source = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, type_source);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_source_type.setAdapter(adapter_source);

        ArrayList grades = new ArrayList();
        for (int i = 0; i <17;i++){
            grades.add("V" + i);
        }
        ArrayAdapter<String> adapter_grades = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, grades);
        adapter_grades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_grade.setAdapter(adapter_grades);
        spinner_impression_grade.setAdapter(adapter_grades);

        String[] tries = { "Bouldering", "Lead climbing", "Top rope", "Trad climbing", "Free solo" };
        ArrayAdapter<String> adapter_tries = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tries);
        adapter_tries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tries.setAdapter(adapter);






    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.upload_source, container, false);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,"META:" + resultCode);

        if (resultCode == REQUEST_TAKE_GALLERY_VIDEO) {
            final Uri selectedVideoUri = data.getData();
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("video/*")
                    .build();
            Log.d(TAG,"META:" + metadata);
            if (selectedVideoUri == null) {
                Log.e("selected video path", "null");
            } else {
                Log.v("selectedVideoPath", selectedVideoUri.toString());
                if (selectedVideoUri != null) {

                    //file_name.setText(selectedVideoUri.toString());


                    selected_source = true;
                    selected_video = true;
                    selected_video_source = selectedVideoUri.toString();


                }
            }
        }
        if (requestCode == IMG_REQUEST_CODE){
            final Uri selectedIMGUri = data.getData();
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("image/*")
                    .build();
            Log.d(TAG,"META:" + metadata);
            if (selectedIMGUri == null) {
                Log.d("selected image path", "null");
            } else {
                Log.d("selectedVideoPath", selectedIMGUri.toString());
                if (selectedIMGUri != null) {


                    selected_source = true;
                    selected_img = true;
                    selected_IMG_source = selectedIMGUri.toString();


                }
            }

        }

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.d(TAG, "Place: " + place.getName() + ", " + place.getId());
                place_id = place.getId();
                place_Name = place.getName();
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.d(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void uploadBtn(Uri selectedVideoUri) {
        //if (info_edit.getText().toString().equals("") || grade.getText().toString().equals("") || name_of_location.getText().toString().equals("")){
        //    Toast.makeText(getContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        //}
        //else{
        //    if (info_edit.getText().toString().length() < 10){
        //        Toast.makeText(getContext(), "Please inform your fans more", Toast.LENGTH_SHORT).show();
        //    }
        //    else{
        //        final StorageReference photoRef = initStorage();
        //        uploadProgress(selectedVideoUri, photoRef);
//
//
        //    }
        //}
//
    }

    private StorageReference initStorage() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        // add File/URI
        return storageRef.child("FolderToCreate").child("NameYoWantToAdd");
    }

    private void uploadProgress(Uri selectedVideoUri, final StorageReference photoRef) {
        photoRef.putFile(selectedVideoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Log.d(TAG,"StorageReference" + photoRef);

                        // Upload succeeded
                        Toast.makeText(getContext(), "Upload Success...", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Upload failed
                        Toast.makeText(getContext(), "Upload failed...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(
                new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        //calculating progress percentage
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        //displaying percentage in progress dialog
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onStart() {
        super.onStart();


    }
    @Override
    public void onResume(){
        super.onResume();


    }
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String random(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }




}
