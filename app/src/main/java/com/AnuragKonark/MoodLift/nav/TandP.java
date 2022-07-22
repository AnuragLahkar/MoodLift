package com.AnuragKonark.MoodLift.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.AnuragKonark.MoodLift.R;

public class TandP extends AppCompatActivity {

    ImageButton findTherapist, findPsychiatrist, back;
    Button call1, call2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tand_p);

        findTherapist = findViewById(R.id.find_therapist);
        findPsychiatrist = findViewById(R.id.find_psychiatrist);
        back = findViewById(R.id.back);

        findTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TandP.this, Therapist.class));

            }
        });

        findPsychiatrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TandP.this, Psychiatrist.class));

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        call1=findViewById(R.id.call1);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callingIntent = new Intent(Intent.ACTION_DIAL);
                callingIntent.setData(Uri.parse("tel:+919101349310"));
                startActivity(callingIntent);
            }
        });
        call2=findViewById(R.id.call2);
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callingIntent = new Intent(Intent.ACTION_DIAL);
                callingIntent.setData(Uri.parse("tel:+919152987821"));
                startActivity(callingIntent);
            }
        });
    }
}