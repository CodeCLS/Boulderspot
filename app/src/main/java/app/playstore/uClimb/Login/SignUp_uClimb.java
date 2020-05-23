package app.playstore.uClimb.Login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.playstore.uClimb.Main.MainActivity;
import app.playstore.uClimb.Notifaction.Base_Internet;
import app.playstore.uClimb.R;

public class SignUp_uClimb extends Base_Internet {
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
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_uClimb.this, Login_uClimb.class);
                startActivity(intent);

            }
        });




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
                    String email = edit_email.getText().toString().trim();
                    if (isEmailValid(email)){
                        if (edit_pwd.getText().toString().trim().length() > constant_length && edit_user.getText().toString().trim().length() > constant_length) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Boolean username_exists = false;
                                    for (DataSnapshot post: dataSnapshot.child("User").getChildren()){
                                        if (post.child("Name").getValue()== edit_user.getText().toString()){
                                            username_exists = true;

                                        }
                                    }
                                    if (username_exists){
                                        Toast.makeText(SignUp_uClimb.this, "Diesen Username gibt es schon", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        String pwd = edit_email.getText().toString();
                                        String username = edit_user.getText().toString();
                                        Intent intent = new Intent(SignUp_uClimb.this, Userdata_Questions_uClimb.class);
                                        intent.putExtra("pwd" , pwd);
                                        intent.putExtra("username" , username);
                                        intent.putExtra("email" , email);
                                        Log.d(TAG,"passwordfromlogingoogle" + pwd);

                                        startActivity(intent);
                                        finish();

                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


}
                        else{
                            Snackbar  snackbar = Snackbar.make(SignUp_uClimb.this.getWindow().getDecorView(),"Please fill in a password or username longer than 6 characters",BaseTransientBottomBar.LENGTH_SHORT);
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
        Snackbar snackbar = Snackbar.make(SignUp_uClimb.this.getWindow().getDecorView(),"Please enter all fields" , BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark));
        snackbar.show();
    }

    private void initViews() {
        log_in= findViewById(R.id.login_Register);
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
        Boolean boolean_pwd = pwd.getText().toString().trim().isEmpty();
        Boolean boolean_username = username.getText().toString().trim().isEmpty();
        Boolean boolean_email = email.getText().toString().trim().isEmpty();

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
        Intent intent = new Intent(SignUp_uClimb.this, MainActivity.class);
        startActivity(intent);
    }

}





      //  Intent signInIntent = gso.getSignInIntent();
      //  startActivityForResult(signInIntent, RC_SIGN_IN);






