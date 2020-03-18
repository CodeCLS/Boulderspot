package app.playstore.uClimb.Login_SignUp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.R;
import app.playstore.uClimb.ViewModelPresenters.register_presenter;

public class Boulderspot_Sign_Up extends AppCompatActivity {
    private static final String TAG = "Boulderspot_Sign_up";
    private EditText edit_email;
    private EditText edit_pwd;
    private EditText edit_user;
    private TextView log_in;
    private Button btn_sign_up;
    private FirebaseAuth mAuth;
    private Boolean edit_email_status_boolean= false;
    private Boolean edit_pwd_status_boolean= false;
    private Boolean edit_user_status_boolean= false;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    private int constant_length = 6;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        initViews();
        btn();
        textwatcher();




    }

    private void textwatcher() {
        user_listener();
        email_listener();
        pwd_listener();

    }

    private void user_listener() {
        edit_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().equals("")) {
                    edit_user_status_boolean = false;
                    Drawable background = btn_sign_up.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));

                } else {
                    if (edit_email_status_boolean && edit_pwd_status_boolean){

                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }
                    edit_user_status_boolean = true;


                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) {
                    edit_user_status_boolean = false;


                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.grey_pressed_btn));





                } else {

                    edit_user_status_boolean = true;
                    if (edit_pwd_status_boolean && edit_email_status_boolean){

                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                    }


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void email_listener() {
        edit_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().equals("")) {

                    edit_email_status_boolean = false;

                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.grey_pressed_btn));





                } else {
                    if (isEmailValid(s.toString().trim())){
                        edit_email_status_boolean = true;
                        if (edit_pwd_status_boolean && edit_user_status_boolean) {
                            Drawable background = btn_sign_up.getBackground();
                            background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                        }

                    }

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) {
                    edit_email_status_boolean = false;

                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.grey_pressed_btn));





                } else {
                    if (isEmailValid(s.toString().trim())){
                        edit_email_status_boolean = true;
                        if (edit_pwd_status_boolean && edit_user_status_boolean) {
                            Drawable background = btn_sign_up.getBackground();
                            background.setTint(getResources().getColor(R.color.blue_pressed_btn));

                        }

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void pwd_listener() {
        edit_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().equals("")) {
                    edit_pwd_status_boolean = false;
                    Drawable background = btn_sign_up.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));




                } else {
                    edit_pwd_status_boolean = true;

                    if (edit_user_status_boolean && edit_email_status_boolean){
                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));
                    }



                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) {
                    edit_pwd_status_boolean = false;
                    Drawable background = btn_sign_up.getBackground();
                    background.setTint(getResources().getColor(R.color.grey_pressed_btn));




                } else {
                    edit_pwd_status_boolean = true;

                    if (edit_user_status_boolean && edit_email_status_boolean){
                        Drawable background = btn_sign_up.getBackground();
                        background.setTint(getResources().getColor(R.color.blue_pressed_btn));
                    }



                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void btn() {
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkInput(edit_pwd, edit_user, edit_email)){
                    Log.d(TAG,"not all fields are full");
                    createSnack_empty();


                }
                else{
                    String email = edit_email.getText().toString();
                    if (isEmailValid(email)){
                        if (edit_pwd.getText().length() > constant_length && edit_user.getText().length() > constant_length) {
                            String pwd = edit_email.getText().toString();
                            String username = edit_user.getText().toString();
                            Intent intent = new Intent(Boulderspot_Sign_Up.this, extra_questions_register.class);
                            intent.putExtra("pwd" , pwd);
                            intent.putExtra("username" , username);
                            intent.putExtra("email" , email);

                            startActivity(intent);

}
                        else{
                            Snackbar  snackbar = Snackbar.make(Boulderspot_Sign_Up.this.getWindow().getDecorView(),"Please fill in a password or username longer than 6 characters",BaseTransientBottomBar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark));
                            snackbar.show();
                        }

                    }
                    else{

                    }

                }
            }
        });
    }

    private void createSnack_empty() {
        Snackbar snackbar = Snackbar.make(Boulderspot_Sign_Up.this.getWindow().getDecorView(),"Please enter all fields" , BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark));
        snackbar.show();
    }

    private void initViews() {
        btn_sign_up = findViewById(R.id.btn_register);
        edit_email = findViewById(R.id.edit_email_register);
        edit_user = findViewById(R.id.edit_user_register);
        edit_pwd = findViewById(R.id.edit_pwd_register);
    }
    private static boolean isEmailValid(String email) {

        Log.d(TAG,"androidisEmail" + android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());


        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    private Boolean checkInput(EditText pwd,EditText username, EditText email){
        Boolean boolean_pwd = pwd.getText().toString().isEmpty();
        Boolean boolean_username = username.getText().toString().isEmpty();
        Boolean boolean_email = email.getText().toString().isEmpty();

        Boolean all_inputs = false;
        if (!boolean_email && !boolean_pwd && !boolean_username){
            all_inputs = true;
        }


        return all_inputs;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Drawable background = btn_sign_up.getBackground();
        background.setTint(getResources().getColor(R.color.grey_pressed_btn));

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null){

        }
        else{
            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(Boulderspot_Sign_Up.this, MainActivity.class);
        startActivity(intent);
    }

}





      //  Intent signInIntent = gso.getSignInIntent();
      //  startActivityForResult(signInIntent, RC_SIGN_IN);






