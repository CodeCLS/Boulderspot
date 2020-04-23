package app.playstore.uClimb.Fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCanceledListener;
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
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import app.playstore.uClimb.ViewModelPresenters.video.video_presenter;

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
    private String place_id;
    private String place_Name;
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
                        Log.d(TAG,"video_prediction"+prediction.getPlaceId());
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
                  Log.d(TAG, "Clicked1");
                  Intent intent = new Intent();
                  intent.setType("video/*");
                  intent.setAction(Intent.ACTION_GET_CONTENT);
                  startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);


              }
              if (spinner_source_type.getSelectedItem().toString().equals("Image")){
                  Log.d(TAG, "Clicked2");
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
                datawork();

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
        ArrayAdapter<String> adapter_grades1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, grades);
        adapter_grades1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_grade.setAdapter(adapter_grades1);

        ArrayAdapter<String> adapter_grades = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, grades);
        adapter_grades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_grade.setAdapter(adapter_grades);
        spinner_impression_grade.setAdapter(adapter_grades);

        String[] tries = { "1","2","3","4","5","6","7","8","9","10","11+" };
        ArrayAdapter<String> adapter_tries = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tries);
        adapter_tries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tries.setAdapter(adapter_tries);







    }

    private void datawork() {
        if (info_edit.getText().toString().length() > 10 &&selected_source&& place_id != null ){
            if (!checkbox_statistics.isSelected()){
                if (selected_img){
                    uploadProgress(selected_IMG_source,initStorage(),true,true);

                }
                if (selected_video){
                    uploadProgress(selected_video_source,initStorage(),false,true);






                }



            }
            else{
                if (selected_img){
                    uploadProgress(selected_IMG_source,initStorage(),true,false);



                }
                if (selected_video){
                    uploadProgress(selected_video_source,initStorage(),false,false);




                }

            }



            //databaseReference

        }
        else{
            Toast.makeText(getContext(), "You either havent written more than 10 characters, didn't enter a location or entered a video/image. Please resolve", Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.upload_source, container, false);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,"META:" + requestCode + "OK? " + resultCode);

        if (requestCode == REQUEST_TAKE_GALLERY_VIDEO&& resultCode == resultCode) {
            final Uri selectedVideoUri = data.getData();
            Log.d(TAG,"metadata" + selectedVideoUri);
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("video/*")
                    .build();
            Log.d(TAG,"META:" + metadata);
            if (selectedVideoUri == null) {
                Log.d("selected video path", "null");
            } else {
                Log.d("selectedVideoPath", selectedVideoUri.toString());
                if (selectedVideoUri != null) {
                    Log.d(TAG,"selected1");


                    //file_name.setText(selectedVideoUri.toString());


                    selected_source = true;
                    selected_video = true;
                    selected_video_source = selectedVideoUri.toString();
                    selected_img = false;



                }
            }
        }

        if (requestCode == IMG_REQUEST_CODE&& resultCode == resultCode){
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
                    Log.d(TAG,"selected1");


                    selected_source = true;
                    selected_img = true;
                    selected_IMG_source = selectedIMGUri.toString();
                    selected_video= false;


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



    private StorageReference initStorage() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        login_presenter login_presenter = new login_presenter();
        String uid = login_presenter.getUID(getContext());
        // add File/URI
        return storageRef.child("Sources_Users_Uploads").child(uid).child("_#"+random(10));
    }

    private void uploadProgress(String selectedUri, final StorageReference ref, boolean source_status, boolean statistics_status) {
        Log.d(TAG,"selectedURI1"+selectedUri);
        ref.putFile(Uri.parse(selectedUri))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d(TAG,"Success"+uri.equals(uri + ""));
                                video_presenter video_presenter = new video_presenter(place_id,spinner_grade.getSelectedItem().toString(),spinner_impression_grade.getSelectedItem().toString(),spinner_route_type.getSelectedItem().toString(),spinner_tries.getSelectedItem().toString(),place_Name,info_edit.getText().toString());
                                video_presenter.addSuccess(getContext(),random(10),source_status,statistics_status,uri);


                            }
                        });

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
