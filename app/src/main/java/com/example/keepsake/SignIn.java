package com.example.keepsake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignIn extends Fragment implements View.OnClickListener {


        private static final String SignIn = "signIn";
        private EditText email;
        private EditText password;
        private Button btnSignin;
        private TextView signuptext;

    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_in, container, false);

        FirebaseApp.initializeApp(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            //getActivity().finish();
            Intent intent = new Intent(getActivity(), NavigationActivity.class);
            startActivity(intent);
        }

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        btnSignin = view.findViewById(R.id.btnSignin);
        signuptext = view.findViewById(R.id.signuptext);

        btnSignin.setOnClickListener(this);
        signuptext.setOnClickListener(this);

        return view;
    }

    private void SigninUser() {

        String s1 = email.getText().toString().trim();
        String s2 = password.getText().toString().trim();

        if (TextUtils.isEmpty(s1)){
            Toast.makeText(getActivity(), "Please enter Email Id", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(s2)){
            Toast.makeText(getActivity(), "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(s1, s2)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Start the navigation Activity
                           // getActivity().finish();
                            Intent intent = new Intent(getActivity(), NavigationActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if (view == btnSignin){
            SigninUser();
        }
        if (view == signuptext){
            //change fragment to signin
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SignUp signUp = new SignUp();
            fragmentTransaction.replace(R.id.signin_container, signUp);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}
