package com.example.keepsake;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignIn signIn = new SignIn();
        SignUp signUp= new SignUp();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.signup_container, signIn);
        fragmentTransaction.add(R.id.signin_container, signUp);
        fragmentTransaction.commit();
    }
}
