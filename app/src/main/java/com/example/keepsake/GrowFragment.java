package com.example.keepsake;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class GrowFragment extends Fragment {

    EditText age1;
    EditText age3;
    EditText age6;
    EditText age9;
    EditText age12;
    Button btnSave;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grow, container, false);

        age1 = view.findViewById(R.id.age1);
        age3 = view.findViewById(R.id.age3);
        age6 = view.findViewById(R.id.age6);
        age9 = view.findViewById(R.id.age9);
        age12 = view.findViewById(R.id.age12);

        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase  = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = firebaseAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("grow").child(userId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Grow grow = ds.getValue(Grow.class);
                    age1.setText(grow.age1);
                    age3.setText(grow.age3);
                    age6.setText(grow.age6);
                    age9.setText(grow.age9);
                    age12.setText(grow.age12);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }

        });

        btnSave = view.findViewById(R.id.btnSavegrow);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age1month = age1.getText().toString();
                String age3month = age3.getText().toString();
                String age6month = age6.getText().toString();
                String age9month = age9.getText().toString();
                String age12month = age12.getText().toString();

                String uniqueKey ="growKey";
                Grow grow = new Grow(age1month, age3month, age6month, age9month, age12month);
                myRef.child(uniqueKey).setValue(grow);
            }
        });

        return view;

    }

}
