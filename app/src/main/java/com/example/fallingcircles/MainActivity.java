package com.example.fallingcircles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View wshapes = new WhiteCircleView(this);
        //View bshapes = new BlackCircleView(this);
        setContentView(R.layout.activity_main);
    }
}
