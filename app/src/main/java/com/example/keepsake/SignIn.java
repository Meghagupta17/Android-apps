package com.example.keepsake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SignIn extends Fragment implements View.OnClickListener{


        private static final String SignIn = "signIn";
        private EditText email;
        private EditText password;
        private Button btnSignin;
        private TextView signuptext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_in, container, false);

        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        btnSignin =(Button)view.findViewById(R.id.btnSignin);
        signuptext = (TextView)view.findViewById(R.id.signuptext);

        btnSignin.setOnClickListener(this);
        signuptext.setOnClickListener(this);

        return view;
    }

    private void SigninUser() {
    }

    @Override
    public void onClick(View view) {

        if (view == btnSignin){
            SigninUser();
        }

        if (view == signuptext){
            Intent intent = new Intent(getActivity(), NavigationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }

    }
}
