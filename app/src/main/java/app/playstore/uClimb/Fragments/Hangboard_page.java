package app.playstore.uClimb.Fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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

import app.playstore.uClimb.R;

public class Hangboard_page extends Fragment {
    private EditText hang_time_edit;
    private EditText pause_time_edit;
    private TextView hang_time_txt;
    private TextView pause_time_txt;

    private EditText rest_time_edit;
    private EditText number_sets_edit;
    private TextView rest_time_txt;
    private TextView number_sets_txt;
    private EditText number_rounds_edit;
    private TextView number_rounds_txt;
    private Button btn_start;
    private Button btn_sound;


    Handler wait_start_delay_handler;

    private int numb_rounds_counting = 0;
    private int numb_sets_counting = 0;

    private boolean sound = false;


    private boolean sound_state= false;




    private int hang_time;
    private int pause_time;
    private int rest_time;
    private int numb_rounds;
    private int number_sets;
    private MediaPlayer mp ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_hangboard_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        mp = MediaPlayer.create(getContext(), R.raw.training_sound);


        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sound_state){
                    sound_state = true;

                }
                else{
                    sound_state = false;



                }

            }
        });

        btn_start(1);
        btn_sound_action();


    }

    private void btn_sound_action() {

    }

    private void btn_start(final int numb_status) {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numb_status==1) {


                    button_1_action();
                }
                if (numb_status==2){
                    //Log workout
                    //TODO workout log
                    Toast.makeText(getContext(), "Workout logged", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    private void button_1_action() {
        if (hang_time_edit.getText().toString().isEmpty() || pause_time_edit.getText().toString().isEmpty() || rest_time_edit.getText().toString().isEmpty()
                || number_sets_edit.getText().toString().isEmpty() || number_rounds_edit.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please enter numbers", Toast.LENGTH_SHORT).show();


        } else {

            try {
                hang_time = Integer.parseInt(hang_time_edit.getText().toString());
                pause_time = Integer.parseInt(pause_time_edit.getText().toString());
                rest_time = Integer.parseInt(rest_time_edit.getText().toString());
                number_sets = Integer.parseInt(number_sets_edit.getText().toString());
                numb_rounds = Integer.parseInt(number_rounds_edit.getText().toString());
                new CountDownTimer(3000, 1000) {

                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisUntilFinished) {
                        btn_start.setText("" + millisUntilFinished / 1000);

                    }

                    @SuppressLint("SetTextI18n")
                    public void onFinish() {
                        booldoSound();


                        btn_start.setText("GO!");



                        hang_time_text();
                    }
                }.start();
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Wrong input", Toast.LENGTH_SHORT).show();

            }


        }
    }

    private void booldoSound() {
        if (sound_state){
            mp.start();


        }
        else{


        }
    }

    private void hang_time_text() {
        new CountDownTimer(hang_time*1000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                hang_time_edit.setText("Hang: " + millisUntilFinished / 1000);
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                hang_time_edit.setText("Break");
                booldoSound();

                pause_time_text();
            }
        }.start();
    }

    private void pause_time_text() {

        new CountDownTimer(pause_time*1000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                pause_time_edit.setText("Break: " + millisUntilFinished / 1000+ " sec");
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                booldoSound();

                numb_rounds_counting = numb_rounds_counting +1;
                number_rounds_edit.setText(""+numb_rounds_counting);
                number_sets_edit.setText(""+numb_sets_counting);
                Log.d("training" , "numb_counting: " + numb_rounds_counting + "edit: " + numb_rounds);
                testing_time();

            }
        }.start();
    }

    private void testing_time() {
        if (numb_rounds_counting == numb_rounds){
            if (numb_sets_counting == number_sets){
                setTextnull();
                handler();
                booldoSound();



            }
            else{
                rest_time_text();
                booldoSound();



            }
        }
        else{
            pause_time_edit.setText("Hang");
            booldoSound();

            hang_time_text();

        }
    }

    private void handler() {
        wait_start_delay_handler = new Handler();
        btn_start.setText("Log workout? 5sec");
        btn_start(2);

        wait_start_delay_handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_start.setText("Start");
                btn_start(1);

            }
        },5000);
    }

    private void setTextnull() {
        rest_time_edit.setText("");
        pause_time_edit.setText("");
        number_sets_edit.setText("");
        number_rounds_edit.setText("");
        hang_time_edit.setText("");
    }

    private void rest_time_text() {
        new CountDownTimer(rest_time*1000,1000){

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                rest_time_edit.setText("Break: " + millisUntilFinished/ 1000 +" sec");


            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                if (numb_sets_counting == number_sets){
                    btn_start.setText("Perfect! Workout finished");
                    booldoSound();

                }
                else{
                    rest_time_edit.setText("Hang!");
                    numb_rounds_counting = 0;
                    number_rounds_edit.setText(""+numb_rounds_counting);
                    numb_sets_counting = numb_sets_counting +1;
                    number_sets_edit.setText(""+numb_sets_counting);
                    booldoSound();


                    hang_time_text();
                }


            }
        }.start();

    }

    private void findViews(@NonNull View view) {
       // hang_time_edit = view.findViewById(R.id.hang_time_edit);
       // pause_time_edit = view.findViewById(R.id.pause_time_edit);
       // hang_time_txt = view.findViewById(R.id.hang_time_txt);
       // pause_time_txt = view.findViewById(R.id.pause_time_txt);
//
       // rest_time_edit = view.findViewById(R.id.rest_time_edit);
       // number_sets_edit = view.findViewById(R.id.number_sets_edit);
       // rest_time_txt = view.findViewById(R.id.rest_time_txt);
       // number_sets_txt = view.findViewById(R.id.number_sets_txt);
//
       // number_rounds_edit = view.findViewById(R.id.number_rounds_edit);
       // number_rounds_txt = view.findViewById(R.id.number_rounds_txt);
//
       // btn_start = view.findViewById(R.id.btn_start_train_hangboard);
       // btn_sound = view.findViewById(R.id.btn_sound_hang);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
