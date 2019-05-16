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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class SignIn extends Fragment implements View.OnClickListener {


    private static final String SignIn = "signIn";
    private EditText email;
    private EditText password;
    private Button btnSignin;
    private SignInButton googleBtn;
    private TextView signuptext;
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;


    public interface GoToSignUp{
        public void signUpSwitch();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_in, container, false);

        FirebaseApp.initializeApp(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (firebaseAuth.getCurrentUser() != null){
            getActivity().finish();
            Intent intent = new Intent(getActivity(), NavigationActivity.class);
            startActivity(intent);
        }

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        btnSignin = view.findViewById(R.id.btnSignin);
        signuptext = view.findViewById(R.id.signuptext);
        googleBtn = view.findViewById(R.id.googleBtn);

        btnSignin.setOnClickListener(this);
        signuptext.setOnClickListener(this);
        googleBtn.setOnClickListener(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        return view;
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(getActivity(), NavigationActivity.class);
                            startActivity(intent);
                            getActivity().finish();

                            Toast.makeText(getActivity(), getString(R.string.signin_successfull), Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(getActivity(), getString(R.string.authentication_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void SigninUser() {

        String s1 = email.getText().toString().trim();
        String s2 = password.getText().toString().trim();

        if (TextUtils.isEmpty(s1)){
            Toast.makeText(getActivity(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(s2)){
            Toast.makeText(getActivity(), getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(s1, s2)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(getActivity(), NavigationActivity.class);
                            startActivity(intent);
                            getActivity().finish();

                        } else {
                            // If sign in fails, message for user.

                            Toast.makeText(getActivity(), getString(R.string.signin_failed),
                                    Toast.LENGTH_SHORT).show();

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
            GoToSignUp goToSignUp = (GoToSignUp) getActivity();
            goToSignUp.signUpSwitch();
        }

        if (view == googleBtn){
            //add google signin
            signIn();
        }

    }
}
