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

public class TheDayFragment extends Fragment {

    EditText leader;
    EditText headline;
    EditText milkCost;
    EditText diaperCost;
    Button btnSave;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theday, container, false);

        leader = view.findViewById(R.id.leader);
        headline = view.findViewById(R.id.headline);
        milkCost = view.findViewById(R.id.milkCost);
        diaperCost = view.findViewById(R.id.diaperCost);

        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase  = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = firebaseAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("theDay").child(userId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    TheDay theDay = ds.getValue(TheDay.class);
                    leader.setText(theDay.leader);
                    headline.setText(theDay.headlines);
                    milkCost.setText(theDay.milkcost);
                    diaperCost.setText(theDay.diapercost);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }

        });

        btnSave = view.findViewById(R.id.btnSaveday);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dayLeader = leader.getText().toString();
                String dayHeadline = headline.getText().toString();
                String dayMilkCost = milkCost.getText().toString();
                String dayDiaperCost = diaperCost.getText().toString();

                String uniqueKey ="theDayKey";
                TheDay theDay = new TheDay(dayLeader, dayHeadline, dayMilkCost, dayDiaperCost);
                myRef.child(uniqueKey).setValue(theDay);
            }
        });


        return view;

    }

    }
