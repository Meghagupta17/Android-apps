package com.example.whiteballgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvScore;
    TextView tvLives;
    Button btnStart;
    Button btnPause;
    char startBtnState;
    char pauseBtnState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tvScore);
        tvLives = findViewById(R.id.tvLives);
        btnPause = findViewById(R.id.btnPause);
        btnStart = findViewById(R.id.btnStart);
        startBtnState = 'S';
        pauseBtnState = 'P';

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pauseBtnState=='P'){
                    btnPause.setText("RESUME");
                    pauseBtnState = 'R';
                    //To pause animation
                }else {
                   pauseBtnState='P';
                   //To Resume the animation
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startBtnState=='S') {
                    btnStart.setText("END");
                    startBtnState = 'E';
                    //To End the animation

                } else if (startBtnState == 'E') {
                    btnStart.setText("NEW");
                    startBtnState = 'N';
                    //To start new animation

                } else {
                    startBtnState='S';
                    //To Start the animation
                }
            }
        });

    }
}
