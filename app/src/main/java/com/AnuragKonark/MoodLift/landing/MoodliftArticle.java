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

public class MoodliftArticle extends AppCompatActivity implements ValueEventListener {

    TextView fullArticle;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference fullArticleData = databaseReference.child("fullArticle");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodlift_article);
        fullArticle = findViewById(R.id.fullArticle);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.getValue(String.class) != null) {
            String key = snapshot.getKey();
            if (key.equals("fullArticle")) {
                String text = snapshot.getValue(String.class);
                fullArticle.setText(text);
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        fullArticleData.addValueEventListener(this);
    }
}