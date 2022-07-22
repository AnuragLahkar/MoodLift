package com.AnuragKonark.MoodLift.landing.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.AnuragKonark.MoodLift.R;

public class Activities extends AppCompatActivity {
    Button deepBreathing, guidedImagery, pmr, mindfulWalking, thoughtDefusion, selfComparison, beachVisualisation;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        deepBreathing= findViewById(R.id.deepBreathing);
        deepBreathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, DeepBreathing.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        guidedImagery= findViewById(R.id.guidedImagery);
        guidedImagery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, GuidedImagery.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        pmr= findViewById(R.id.pmr);
        pmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, PMR.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        mindfulWalking= findViewById(R.id.mindfulWalking);
        mindfulWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, MindfulWalking.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        thoughtDefusion= findViewById(R.id.thoughtDefusion);
        thoughtDefusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, ThoughtDefusion.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        selfComparison= findViewById(R.id.selfComparison);
        selfComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, SelfCompassion.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        beachVisualisation= findViewById(R.id.beachVisualisation);
        beachVisualisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, BeachVisualisation.class);
                startActivity(intent);
                Toast.makeText(Activities.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        back = findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}