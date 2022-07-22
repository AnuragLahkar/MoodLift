package com.AnuragKonark.MoodLift.landing.books;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.AnuragKonark.MoodLift.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Book2 extends AppCompatActivity {
    private TextView text2;
    private PDFView pdfBook2;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mref=database.getReference("book2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book2);
        pdfBook2 = (PDFView) findViewById(R.id.pdfBook2);
        text2 = (TextView) findViewById(R.id.text2);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                text2.setText(value);
                Toast.makeText(Book2.this, "Loading...", Toast.LENGTH_SHORT).show();
                String url = text2.getText().toString();
                new Book2.RetrievePdfStream().execute(url);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Book2.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class RetrievePdfStream extends AsyncTask<String,Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode()==200){
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());

                }
            }catch (IOException e){
                return null;
            }
            return  inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfBook2.fromStream(inputStream).load();
        }
    }
}