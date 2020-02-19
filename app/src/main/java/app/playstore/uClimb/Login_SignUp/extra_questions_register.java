package app.playstore.uClimb.Login_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;

public class extra_questions_register extends AppCompatActivity {
    private static final String TAG = "extra";
    private EditText edit_grade;
    private EditText edit_height;
    private EditText edit_city;
    private EditText edit_age;
    private Button btn_continue;
    private TextView terms;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_questions_layout);
        initviews();
        onClick();

    }

    private void onClick() {
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_fields()){
                    Toast_fill();
                }
                else{
                    setVals();
                    MainIntent();


                }
            }
        });
    }

    private boolean check_fields() {
        return edit_age.getText().toString().isEmpty() ||edit_city.getText().toString().isEmpty()
        || edit_height.getText().toString().isEmpty() || edit_grade.getText().toString().isEmpty();
    }

    private void Toast_fill() {
        Toast.makeText(extra_questions_register.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
    }

    private void setVals() {
        myRef.child(mAuth.getUid()).child("Grade").setValue(edit_grade.getText().toString());
        myRef.child(mAuth.getUid()).child("Groeße").setValue(edit_height.getText().toString());
        myRef.child(mAuth.getUid()).child("City").setValue(edit_city.getText().toString());
        myRef.child(mAuth.getUid()).child("Alter").setValue(edit_age.getText().toString());
    }

    private void MainIntent() {
        Intent intent = new Intent(extra_questions_register.this, MainActivity.class);
        startActivity(intent);
    }

    private void initviews() {
        edit_age = findViewById(R.id.edit_age_extra);
        edit_height = findViewById(R.id.edit_height_extra);
        edit_grade = findViewById(R.id.edit_grade_extra);
        edit_city = findViewById(R.id.edit_city_extra);
        btn_continue = findViewById(R.id.btn_continue);



    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"war heir");
        mAuth = FirebaseAuth.getInstance();
    }
}
