package app.playstore.uClimb.Login_SignUp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;

public class extra_questions_register extends AppCompatActivity {
    private static final String TAG = "extra";
    private AutoCompleteTextView edit_grade;
    private AutoCompleteTextView edit_height;
    private AutoCompleteTextView edit_city;
    private AutoCompleteTextView edit_age;
    private Button btn_continue;
    private TextView terms;

    private Boolean edit_age_status = false;
    private Boolean edit_city_status = false;
    private Boolean edit_height_status = false;
    private Boolean edit_grade_status = false;


    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_questions);

        initviews();
        onClick();
        listeners();

    }

    private void listeners() {
        edit_age_listener();
        edit_city_listener();
        edit_height_listener();
        edit_grade_listener();
    }

    private void edit_grade_listener() {
        edit_grade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    edit_grade_status = true;
                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }




                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    edit_grade_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    edit_grade_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void edit_height_listener() {
        edit_height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    edit_height_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    edit_height_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    edit_height_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    edit_height_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void edit_city_listener() {
        edit_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    edit_city_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    edit_city_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    edit_city_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    edit_city_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void edit_age_listener() {
        edit_age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0){
                    edit_age_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                }
                else{
                    edit_age_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0){
                    edit_age_status = false;

                    Drawable background = btn_continue.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                }
                else{
                    edit_age_status = true;

                    if (status_edit()){
                        Drawable background = btn_continue.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean status_edit() {
        Boolean bool = edit_age_status && edit_city_status&& edit_height_status&&edit_grade_status;
        Log.d(TAG,"abool"+bool);

        return edit_age_status && edit_city_status&& edit_height_status&&edit_grade_status;
    }
    public ArrayList getCities(){

        ArrayList<String> list=new ArrayList<String>();

        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);


            System.out.println("Country Name = " + obj.getDisplayCountry());

            list.add(obj.getDisplayCountry());

        }
        return list;
    }

    private void onClick() {
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCities().contains(edit_city.getText().toString())&& getGrade().contains(edit_grade.getText().toString())) {
                    if (check_fields()) {
                        Snackbar snackbar = Snackbar.make(extra_questions_register.this.getWindow().getDecorView(), "Please fill all the fields", BaseTransientBottomBar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark));
                    } else {
                        Intent intent = new Intent(extra_questions_register.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else{
                    Snackbar snackbar = Snackbar.make(extra_questions_register.this.getWindow().getDecorView(),"Please enter a correct country or grade", BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getCities());
        ArrayAdapter<String> adapter_grade = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getGrade());
        edit_city.setAdapter(adapter);
        edit_grade.setAdapter(adapter_grade);




    }

    private ArrayList getGrade() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0;i<17;i++){
            arrayList.add("V" + i);
            Log.d(TAG,"grade2:" + "V"+i);



        }
        Log.d(TAG,"grade:" + arrayList);
        return  arrayList;

    }


    @Override
    protected void onStart() {
        super.onStart();


        if (check_fields() == true){
            Drawable background = btn_continue.getBackground();
            background.setTint(getResources().getColor(R.color.grey_pressed_btn));


        }
        Log.d(TAG,"war heir");
        mAuth = FirebaseAuth.getInstance();
        updateUI(mAuth.getCurrentUser());
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null){
            Log.d(TAG,"currentsuer" + currentUser);
            return;
        }
    }
}
