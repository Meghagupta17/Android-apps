package com.example.keepsake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFirstsFragment extends Fragment {

    EditText bath;
    EditText smile;
    EditText teeth;
    EditText word;
    EditText outing;
    Button btnSave;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference myRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_myfirsts, container, false);

        bath = view.findViewById(R.id.bath);
        smile = view.findViewById(R.id.smile);
        teeth = view.findViewById(R.id.teeth);
        word = view.findViewById(R.id.word);
        outing = view.findViewById(R.id.outing);

        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase  = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = firebaseAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("firsts").child(userId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    MyFirsts myFirsts = ds.getValue(MyFirsts.class);
                    bath.setText(myFirsts.bath);
                    smile.setText(myFirsts.smile);
                    teeth.setText(myFirsts.teeth);
                    word.setText(myFirsts.word);
                    outing.setText(myFirsts.outing);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }

        });

        btnSave = view.findViewById(R.id.btnSavefirsts);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstBath = bath.getText().toString();
                String firstSmile = smile.getText().toString();
                String firstTeeth = teeth.getText().toString();
                String firstWord = word.getText().toString();
                String firstOuting = outing.getText().toString();

                String uniqueKey ="firstsKey";
                MyFirsts myFirsts = new MyFirsts(firstBath, firstSmile, firstTeeth, firstWord, firstOuting);
                myRef.child(uniqueKey).setValue(myFirsts);
            }
        });


        return view;

    }

}
