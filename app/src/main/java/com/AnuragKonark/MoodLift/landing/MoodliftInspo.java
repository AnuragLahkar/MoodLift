package com.AnuragKonark.MoodLift.landing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import com.AnuragKonark.MoodLift.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoodliftInspo extends AppCompatActivity implements ValueEventListener {

    TextView fullInspo;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference fullInspoData = databaseReference.child("fullInspo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodlift_inspo);
        fullInspo = findViewById(R.id.fullInspo);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.getValue(String.class) != null) {
            String key = snapshot.getKey();
            if (key.equals("fullInspo")) {
                String text = snapshot.getValue(String.class);
                fullInspo.setText(text);
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        fullInspoData.addValueEventListener(this);
    }
}