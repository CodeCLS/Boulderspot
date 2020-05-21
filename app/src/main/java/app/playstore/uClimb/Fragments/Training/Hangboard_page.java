package app.playstore.uClimb.Fragments.Training;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import app.playstore.uClimb.Fragments.Training.Log_Training_Fragment;
import app.playstore.uClimb.R;

public class Hangboard_page extends Fragment {
    private static final String TAG = "Hangboard";
    private EditText hang_time_edit;
    private EditText pause_time_edit;
    private EditText rest_time_edit;
    private EditText sets_edit;
    private EditText rounds_edit;
    private ImageButton audio_btn;
    private Button start_btn;

    private TextView hang_time_txt;
    private TextView pause_time_txt;
    private TextView rest_time_txt;
    private TextView sets_txt;
    private TextView rounds_txt;

    private TextView txt_main;

    private int hang_starter =0;
    private int sets_num =0;
    private int btn_status = 0;

    private boolean audio_status = false;

    private MediaPlayer mediaPlayer;
    private CountDownTimer hang_timer;
    private CountDownTimer pause_timer;
    private CountDownTimer rest_timer;
    private CountDownTimer start_timer;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.main_hangboard_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(getContext(),R.raw.training_sound);
        initViews(view);
        onClick(view);



    }



    private void onClick(View view) {
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_status == 0) {
                    Log.d(TAG,"halli");
                    String hang_time_string = hang_time_edit.getText().toString();
                    String pause_time_string = pause_time_edit.getText().toString();
                    String rest_time_string = rest_time_edit.getText().toString();
                    String sets_string = sets_edit.getText().toString();
                    String rounds_string = rounds_edit.getText().toString();

                    if (hang_time_edit.getText().length() < 999 && pause_time_edit.getText().length() < 999 && rest_time_edit.getText().length() < 999 && sets_edit.getText().length() < 999
                            && rounds_edit.getText().length() < 999 && !hang_time_edit.getText().toString().isEmpty() && !pause_time_edit.getText().toString().isEmpty()
                            && !rest_time_edit.getText().toString().isEmpty() && !sets_edit.getText().toString().isEmpty() && !rounds_edit.getText().toString().isEmpty()) {

                        setVisibilityEdit(false);
                        setTXTsize(false);


                        startGame(hang_time_string, pause_time_string, rest_time_string, sets_string, rounds_string);


                    }
                }
                if (btn_status==1){
                    start_btn.setText("Start");
                    Log.d(TAG,"halli2");
                    setVisibilityEdit(true);
                    setTXTsize(true);
                    setEditEmpty();
                    if (hang_timer != null){
                        hang_timer.cancel();


                    }
                    if (pause_timer != null){
                        pause_timer.cancel();


                    }
                    if (rest_timer != null){
                        rest_timer.cancel();


                    }
                    start_timer.cancel();
                    btn_status =0;
                    audio_status = false;
                    sets_num = 0;
                    hang_starter = 0;
                    hang_time_txt.setText("Hang Time");
                    pause_time_txt.setText("Pause Time");
                    rest_time_txt.setText("Rest Time");
                    sets_txt.setText("Sets");
                    rounds_txt.setText("Rounds");
                    mediaPlayer.stop();
                    Context context = getContext();
                    mediaPlayer = mediaPlayer.create(context,R.raw.training_sound);





                }
                if (btn_status == 2){
                    //TODO Log workout Dialog
                }
            }
        });
        audio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio_status == true){
                    audio_status = false;
                    audio_btn.setImageResource(R.drawable.ic_volume_mute_white_36dp);


                }
                else{
                    audio_status = true;
                    audio_btn.setImageResource(R.drawable.ic_volume_up_white_36dp);


                }

            }
        });


    }


    private void setEditEmpty() {
        hang_time_edit.setText("");
        pause_time_edit.setText("");
        rest_time_edit.setText("");
        sets_edit.setText("");
        rounds_edit.setText("");
    }

    private void startGame(String hang_time_string, String pause_time_string, String rest_time_string, String sets_string, String rounds_string) {
        countdown_start(hang_time_string,pause_time_string,rest_time_string,sets_string,rounds_string);





    }

    private void countdown_start(String hang_time_string, String pause_time_string, String rest_time_string, String sets_string, String rounds_string) {
        txt_main.setVisibility(View.VISIBLE);
        start_timer = new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                txt_main.setText(""+millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }


            public void onFinish() {
                txt_main.setVisibility(View.GONE);

                Work_Procedure(sets_string, hang_time_string, pause_time_string, rest_time_string, rounds_string);
                btn_status = 1;
                start_btn.setText("Restart");

            }

        }.start();
    }

    private void Work_Procedure(String sets_string, String hang_time_string, String pause_time_string, String rest_time_string, String rounds_string) {
        Log.d(TAG,"rounds_string" + rounds_string);
        if (hang_starter != Integer.parseInt(rounds_string)){
            sets_num =0;
            SetsProcedure(sets_string, hang_time_string, pause_time_string, rest_time_string, rounds_string);
            rest_time_txt.setText("0");




        }
        else{

            //TODO fix sound when different timer and fix sound between some timers
            Toast.makeText(getContext(), "Finished", Toast.LENGTH_SHORT).show();
            Log_Training_Fragment log_training_fragment = new Log_Training_Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("Hang_Time",hang_time_string);
            bundle.putString("Pause_Time",pause_time_string);
            bundle.putString("Rest_Time",pause_time_string);
            bundle.putString("Sets",pause_time_string);
            bundle.putString("Rounds",pause_time_string);

            log_training_fragment.setArguments(bundle);
            FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack("Log_training").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container_fragment,log_training_fragment);
            fragmentTransaction.commit();
            //final Handler handler = new Handler();
           //btn_status = 2;
           //handler.postDelayed(new Runnable() {
           //    @Override
           //    public void run() {
           //        btn_status = 1;
           //        //Do something after 100ms
           //    }
           //}, 100);



        }











        //Toast.makeText(getContext(), "Finished", Toast.LENGTH_SHORT).show();

    }

    private void SetsProcedure(String sets_string, String hang_time_string, String pause_time_string, String rest_time_string, String rounds_string) {

        if (sets_num != Integer.parseInt(sets_string)){
            startHang(hang_time_string,pause_time_string,rest_time_string,sets_string,rounds_string);
            if (audio_status){
                mediaPlayer.start();

            }

            sets_num++;
            sets_txt.setText(sets_num+"");
            pause_time_txt.setText("0");





        }
        else{
            if (audio_status){
                mediaPlayer.start();

            }
            startRest(hang_time_string,pause_time_string,rest_time_string,sets_string,rounds_string);
            hang_starter++;
            rounds_txt.setText(hang_starter+"");
        }


    }

    private void startRest(String hang_time_string, String pause_time_string, String rest_time_string, String sets_string, String rounds_string) {
        if (audio_status){
            mediaPlayer.start();
        }
        rest_timer = new CountDownTimer(Integer.parseInt(rest_time_string)*1000, 1000) {


            public void onTick(long millisUntilFinished) {
                restTick(millisUntilFinished);
                //here you can have your logic to set text to edittext
                //Integer.parseInt(hang_time_string)*10,
            }

            public void onFinish() {
                Work_Procedure(sets_string,hang_time_string,pause_time_string,rest_time_string,rounds_string);
                if (audio_status){
                    mediaPlayer.start();
                    Context context = getContext();
                    mediaPlayer = mediaPlayer.create(context,R.raw.training_sound);
                }
                mediaPlayer = mediaPlayer.create(getContext(),R.raw.training_sound);



                //SetsProcedure(hang_time_string,pause_time_string,rest_time_string,sets_string,rounds_string);
            }

        }.start();

    }

    @SuppressLint("SetTextI18n")
    private void restTick(long millisUntilFinished) {
        rest_time_txt.setText("" + Math.round(millisUntilFinished * 0.001f));


    }
    private void hangTick(long millisUntilFinished) {
        hang_time_txt.setText("" + Math.round(millisUntilFinished * 0.001f));


    }
    private void pauseTick(long millisUntilFinished) {
        pause_time_txt.setText("" + Math.round(millisUntilFinished * 0.001f));


    }


    private void startHang(String hang_time_string, String pause_time_string, String rest_time_string, String sets_string, String rounds_string) {
        if (audio_status){
            mediaPlayer.start();
        }

       hang_timer = new CountDownTimer(Integer.parseInt(hang_time_string)*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                hangTick(millisUntilFinished);
                //here you can have your logic to set text to edittext
                //Integer.parseInt(hang_time_string)*10,
            }

            public void onFinish() {
                if (audio_status){
                    mediaPlayer.stop();
                    Context context = getContext();
                    mediaPlayer = mediaPlayer.create(context,R.raw.training_sound);
                }
                mediaPlayer = mediaPlayer.create(getContext(),R.raw.training_sound);


                startPause(hang_time_string,pause_time_string,rest_time_string,sets_string,rounds_string);
                hang_time_txt.setText("0");
            }

        }.start();


    }

    private void startPause(String hang_time_string, String pause_time_string, String rest_time_string, String sets_string, String rounds_string) {
        if (audio_status){
            mediaPlayer.start();
        }
        pause_timer =new CountDownTimer(Integer.parseInt(pause_time_string)*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                pauseTick(millisUntilFinished);
                //here you can have your logic to set text to edittext
                //Integer.parseInt(hang_time_string)*10,
            }

            public void onFinish() {
                if (audio_status){
                    mediaPlayer.stop();
                    Context context = getContext();
                    mediaPlayer = mediaPlayer.create(context,R.raw.training_sound);
                }
                mediaPlayer = mediaPlayer.create(getContext(),R.raw.training_sound);

                //startHang(hang_time_string,pause_time_string,rest_time_string,sets_string,rounds_string);
                SetsProcedure(sets_string,hang_time_string,pause_time_string,rest_time_string,rounds_string);

            }

        }.start();

    }

    private void setTXTsize(boolean b) {
        if (b){
            hang_time_txt.setTextSize(15f);
            pause_time_txt.setTextSize(15f);
            rest_time_txt.setTextSize(15f);
            sets_txt.setTextSize(15f);
            rounds_txt.setTextSize(15f);

        }
        else{
            hang_time_txt.setTextSize(30f);
            pause_time_txt.setTextSize(30f);
            rest_time_txt.setTextSize(30f);
            sets_txt.setTextSize(30f);
            rounds_txt.setTextSize(30f);

        }

    }

    private void setVisibilityEdit(boolean b) {

        if (b){

            hang_time_edit.setVisibility(View.VISIBLE);
            pause_time_edit.setVisibility(View.VISIBLE);
            rest_time_edit.setVisibility(View.VISIBLE);
            sets_edit.setVisibility(View.VISIBLE);
            rounds_edit.setVisibility(View.VISIBLE);

        }
        else{
            hang_time_edit.setVisibility(View.GONE);
            pause_time_edit.setVisibility(View.GONE);
            rest_time_edit.setVisibility(View.GONE);
            sets_edit.setVisibility(View.GONE);
            rounds_edit.setVisibility(View.GONE);

        }

    }

    private void initViews(View view) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        hang_time_edit = view.findViewById(R.id.hang_time_edit);
        pause_time_edit = view.findViewById(R.id.pause_time_edit);
        rest_time_edit = view.findViewById(R.id.rest_time_edit);
        sets_edit = view.findViewById(R.id.sets_edit);
        rounds_edit = view.findViewById(R.id.rounds_edit);
        start_btn = view.findViewById(R.id.start_btn_hangboard);
        audio_btn = view.findViewById(R.id.audio_btn_hangboard);

        hang_time_txt = view.findViewById(R.id.hang_time_txt);
        pause_time_txt = view.findViewById(R.id.pause_time_txt);
        rest_time_txt = view.findViewById(R.id.rest_time_txt);
        sets_txt = view.findViewById(R.id.sets_txt);
        rounds_txt = view.findViewById(R.id.rounds_txt);
        setBackgroundButtons();

        txt_main = view.findViewById(R.id.txt_hangboard_big_main);


    }

    private void setBackgroundButtons() {
        Drawable drawable = start_btn.getBackground();
        drawable.setTint(getResources().getColor(R.color.blue_pressed_btn));
        Drawable drawable1 = audio_btn.getBackground();
        drawable1.setTint(getResources().getColor(R.color.blue_pressed_btn));
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.stop();
        if (hang_timer != null){
            hang_timer.cancel();


        }
        if (pause_timer != null){
            pause_timer.cancel();


        }
        if (rest_timer != null){
            rest_timer.cancel();


        }

        getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();

    }
}


