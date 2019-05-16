package com.example.keepsake;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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


public class SignUp extends Fragment implements View.OnClickListener {

    private static final String SignUp = "signUp";

    private EditText email;
    private EditText password;
    private Button btnSignup;
    private TextView signinText;

    private FirebaseAuth firebaseAuth;

    public interface GoToSignIn{
        public void signInSwitch();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        FirebaseApp.initializeApp(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            getActivity().finish();
            Intent intent = new Intent(getActivity(), NavigationActivity.class);
            startActivity(intent);
        }

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        btnSignup = view.findViewById(R.id.btnSignup);
        signinText = view.findViewById(R.id.signinText);

        btnSignup.setOnClickListener(this);
        signinText.setOnClickListener(this);

        return view;
    }

    private void SignupUser() {

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
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), NavigationActivity.class);
                            startActivity(intent);
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
            GoToSignIn goToSignIn = (GoToSignIn) getActivity();
            goToSignIn.signInSwitch();
        }
    }
}
