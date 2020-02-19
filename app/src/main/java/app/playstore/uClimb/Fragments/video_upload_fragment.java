package app.playstore.uClimb.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import app.playstore.uClimb.R;

public class video_upload_fragment extends Fragment {
    private static final int REQUEST_TAKE_GALLERY_VIDEO = 222;
    private static final int RESULT_OK = -1;
    private static final String TAG = "Video_Upload";
    private TextView file_name;
    private StorageReference mStorageRef;
    private EditText info_edit;
    private EditText grade;
    private EditText name_of_location;
    private Button upload_btn;
    private Button import_btn;
    private View view_all;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"VIdeo");

        super.onViewCreated(view, savedInstanceState);
        view_all = view;
        Log.d(TAG,"VIdeo");
        initViews(view);
        onClick();
    }

    private void onClick() {
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        import_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Clicked");
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);


            }
        });
    }

    private void initViews(View view) {
        file_name = view.findViewById(R.id.txt_file_upload);
        info_edit = view.findViewById(R.id.info_edit);
        grade = view.findViewById(R.id.grade_edit);
        name_of_location = view.findViewById(R.id.edit_location);
        upload_btn = view.findViewById(R.id.btn_upload_video);
        import_btn = view.findViewById(R.id.btn_import_video);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.upload_video, container, false);





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,"META:" + resultCode);

        if (resultCode == RESULT_OK) {
            final Uri selectedVideoUri = data.getData();
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("video/mpeg")
                    .build();
            Log.d(TAG,"META:" + metadata);
            if (selectedVideoUri == null) {
                Log.e("selected video path", "null");
            } else {
                Log.v("selectedVideoPath", selectedVideoUri.toString());
                if (selectedVideoUri != null) {
                    file_name.setText(selectedVideoUri.toString());


                    upload_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            uploadBtn(selectedVideoUri);

                        }
                    });


                }
            }
        }
    }

    private void uploadBtn(Uri selectedVideoUri) {
        if (info_edit.getText().toString().equals("") || grade.getText().toString().equals("") || name_of_location.getText().toString().equals("")){
            Toast.makeText(getContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            if (info_edit.getText().toString().length() < 10){
                Toast.makeText(getContext(), "Please inform your fans more", Toast.LENGTH_SHORT).show();
            }
            else{
                final StorageReference photoRef = initStorage();
                uploadProgress(selectedVideoUri, photoRef);


            }
        }

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


}
