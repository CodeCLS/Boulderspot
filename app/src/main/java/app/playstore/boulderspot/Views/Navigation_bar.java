package app.playstore.boulderspot.Views;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import app.playstore.boulderspot.R;

public class Navigation_bar extends AppCompatActivity {
    private ImageView home;
    private ImageView events;
    private ImageView review;
    private ImageView location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav);


        setOnclick();


    }

    private void setOnclick() {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_line();

            }
        });

    }

    private void reveal_line() {

    }
}
