package com.example.keepsake;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;


public class SignUp extends Fragment implements View.OnClickListener {

    private static final String SignUp = "signUp";
    //private EditText username;
    private EditText email;
    private EditText password;
    private Button btnSignup;
    private TextView signinText;
    private static final String TAG = "myApp";

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        FirebaseApp.initializeApp(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        //username = view.findViewById(R.id.username);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        btnSignup =(Button)view.findViewById(R.id.btnSignup);
        signinText = (TextView) view.findViewById(R.id.signinText);

        btnSignup.setOnClickListener(this);
        signinText.setOnClickListener(this);

        return view;
    }

    private void SignupUser() {
        //String s1 = username.getText().toString().trim();
       String s1 = email.getText().toString().trim();
       String s2 = password.getText().toString().trim();

        /*if(TextUtils.isEmpty(s1)){
            Toast.makeText(getActivity(), "Please enter User Name", Toast.LENGTH_SHORT).show();
            return;
        }*/

        if (TextUtils.isEmpty(s1)){
            Toast.makeText(getActivity(), "Please enter Email Id", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(s2)){
            Toast.makeText(getActivity(), "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        /*firebaseAuth.createUserWithEmailAndPassword(s1, s2)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user Registered successfully
                            Toast.makeText(getActivity(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                        }else {
                            Toast.makeText(getActivity(), "Could not register, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

        firebaseAuth.createUserWithEmailAndPassword(s1, s2)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(getActivity(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (view == btnSignup){
            SignupUser();
        }

        if (view == signinText){
            //change fragment to signin
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            SignIn signIn = new SignIn();
            fr.replace(R.id.signin_container, signIn);
            fr.commit();

            /*Fragment fragment = new Fragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.signin_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
        }
    }
}
