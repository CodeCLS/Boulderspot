package app.playstore.uClimb.Fragments.custom_workout;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import app.playstore.uClimb.MVP.MVP_Login.Presenter_Login;
import app.playstore.uClimb.R;

public class Fragment_Upload_Custom extends Fragment {
    private static final int REQUEST_TAKE_GALLERY_VIDEO = 312;
    private Button btn_upload;
    private Spinner Type_spinner;
    private EditText info_edit;
    private Button post_btn;
    private Uri selectedVideoPath;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.upload_custom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        ViewAction();
    }

    private void ViewAction() {
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);


            }
        });
        String[] types = { "Workout", "Warmup", "Stretching" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type_spinner.setAdapter(adapter);

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_btn.setClickable(false);
                btn_upload.setClickable(false);

                String info_s = info_edit.getText().toString();
                if (info_s.length() > 5 && info_s.length() < 60){
                    if (selectedVideoPath != null){
                        upload_Video();


                    }
                    else{
                        Toast.makeText(getContext(), R.string.video_waehlen, Toast.LENGTH_SHORT).show();
                        post_btn.setClickable(true);
                        btn_upload.setClickable(true);


                    }

                }
                else{
                    post_btn.setClickable(true);
                    btn_upload.setClickable(true);


                    Toast.makeText(getContext(), R.string.schreiben_mehr_5_weniger_60, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private StorageReference initStorage() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        Presenter_Login login_presenter = new Presenter_Login();
        String uid = login_presenter.getUID(getContext());
        // add File/URI
        StorageReference storageRefPath = storageRef.child("Sources_Users_Uploads").child(uid);
        String randomhashpath = random(10);
        return storageRefPath.child(randomhashpath);

    }

    private void upload_Video() {
        StorageReference storageReference = initStorage();
        storageReference.putFile(selectedVideoPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                setData(taskSnapshot,storageReference);

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Toast.makeText(getContext(), "Lädt: " + progress + "%", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), getString(R.string.fehler) + e.getLocalizedMessage() + getString(R.string.file_bigger), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setData(UploadTask.TaskSnapshot taskSnapshot,StorageReference storageReference) {
        Presenter_Login presenter_login = new Presenter_Login();
        String uid = presenter_login.getUID(getContext());
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                fire_work(uri, uid);


            }
        }).addOnFailureListener(e -> Toast.makeText(getContext(), R.string.schief_gelaufen, Toast.LENGTH_SHORT).show());




    }

    private void fire_work(Uri uri, String uid) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String random = random(20);
        databaseReference.child("Custom_Uploads").child(Type_spinner.getSelectedItem().toString()).child(random).child("User_ID").setValue(uid);
        databaseReference.child("Custom_Uploads").child(Type_spinner.getSelectedItem().toString()).child(random).child("Info").setValue(info_edit.getText().toString());
        databaseReference.child("Custom_Uploads").child(Type_spinner.getSelectedItem().toString()).child(random).child("Source").setValue(uri.toString());
        databaseReference.child("Custom_Uploads").child(Type_spinner.getSelectedItem().toString()).child(random).child("Time").setValue(getTime());



        databaseReference.child("User").child(uid).child("Custom_Uploads").child(random).child("User_ID").setValue(uid);
        databaseReference.child("User").child(uid).child("Custom_Uploads").child(random).child("Info").setValue(info_edit.getText().toString());
        databaseReference.child("User").child(uid).child("Custom_Uploads").child(random).child("Source").setValue(uri.toString());
        databaseReference.child("User").child(uid).child("Custom_Uploads").child(random).child("Type").setValue(Type_spinner.getSelectedItem().toString());
        databaseReference.child("User").child("Custom_Uploads").child(random).child("Time").setValue(getTime());




        Toast.makeText(getContext(), R.string.super_session_hoch, Toast.LENGTH_SHORT).show();
        fragmenttransaction();
    }

    private void fragmenttransaction() {
        custom_workout_page fragment_training_custom = new custom_workout_page();
        FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container_fragment,fragment_training_custom).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());


        return currentDateandTime;
    }

    private void initViews(View view) {
        btn_upload = view.findViewById(R.id.upload_btn_custom);
        Type_spinner = view.findViewById(R.id.type_spinner_custom);
        info_edit = view.findViewById(R.id.info_edit_custom);
        post_btn = view.findViewById(R.id.post_btn_custom);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
            final Uri selectedVideoUri = data.getData();
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("video/*")
                    .build();
            if (selectedVideoUri == null) {
                Log.d("selected image path", "null");
            } else {
                Log.d("selectedVideoPath", selectedVideoUri.toString());
                if (selectedVideoUri != null) {
                    selectedVideoPath = selectedVideoUri;

                }
            }
        }
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
