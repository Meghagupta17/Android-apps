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
    int life;
    int score;
    AnimationArea animationArea;
    AnimationInterface animationInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationArea = new AnimationArea(this);

        tvScore = findViewById(R.id.tvScore);
        tvLives = findViewById(R.id.tvLives);
        btnPause = findViewById(R.id.btnPause);
        btnStart = findViewById(R.id.btnStart);
        startBtnState = 'N';
        pauseBtnState = 'P';
        btnPause.setText("PAUSE");
        btnPause.setEnabled(false);
        btnStart.setText("START");
        score = 0;
        life = 3;
        tvScore.setText("Score: "+ score);
        tvLives.setText("Lives: "+ life);

        //animationInterface = new AnimationInterface() {

        //};

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pauseBtnState=='P'){
                    btnPause.setText("RESUME");
                    pauseBtnState = 'R';
                    //To pause animation
                    animationArea.pauseAnimation();
                }else {
                   pauseBtnState='P';
                   btnPause.setText("PAUSE");
                   //To Resume the animation
                    animationArea.startAnimation();
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startBtnState=='N') {
                    btnStart.setText("END");
                    startBtnState = 'S';
                    //To Start the animation
                    animationArea.startAnimation();
                    btnPause.setEnabled(true);

                } else if (startBtnState == 'S') {
                    btnStart.setText("NEW");
                    startBtnState = 'E';
                    //To End the new animation
                    animationArea.pauseAnimation();
                    btnPause.setEnabled(false);

                } else {
                    startBtnState='N';
                    btnStart.setText("START");
                    //Balls should vanished and score =0 , lives = 3
                    animationArea.newGame();
                    score = 0;
                    life = 3;
                }
            }
        });

    }
}
