package com.example.keepsake;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;

public class ArrivalFragment extends Fragment {

    EditText dobTime;
    EditText city;
    EditText hospital;
    EditText familyFriends;
    Button btnSave;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arrival, container, false);

        dobTime = view.findViewById(R.id.dobTime);
        city = view.findViewById(R.id.city);
        hospital = view.findViewById(R.id.hospital);
        familyFriends = view.findViewById(R.id.ff);

        dobTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                dobTime.setText(hourOfDay + ":" + minute);
                            }
                        },hour, minute, false);
                timePickerDialog.show();
            }
        });

        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase  = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = firebaseAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("arrival").child(userId);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Arrival arrival = ds.getValue(Arrival.class);
                    dobTime.setText(arrival.dobTime);
                    city.setText(arrival.city);
                    hospital.setText(arrival.hospital);
                    familyFriends.setText(arrival.familyfriends);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        btnSave = view.findViewById(R.id.btnSavearrival);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthTime = dobTime.getText().toString();
                String birthCity = city.getText().toString();
                String birthHospital = hospital.getText().toString();
                String familythere = familyFriends.getText().toString();

                String uniqueKey ="arrivalKey";
                Arrival arrival = new Arrival(birthTime, birthCity, birthHospital, familythere);
                myRef.child(uniqueKey).setValue(arrival);
                Toast.makeText(getActivity(), getString(R.string.savebtnToast),
                        Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }

}
