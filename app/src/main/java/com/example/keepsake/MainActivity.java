package com.example.keepsake;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SignIn.GoToSignUp, SignUp.GoToSignIn {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignIn signIn = new SignIn();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.signup_signin_container, signIn);
        fragmentTransaction.commit();
    }

    @Override
    public void signUpSwitch() {
        SignUp signUp= new SignUp();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.signup_signin_container, signUp);
        fragmentTransaction.commit();
    }

    @Override
    public void signInSwitch() {
        SignIn signIn = new SignIn();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.signup_signin_container, signIn);
        fragmentTransaction.commit();
    }
}
