package com.AnuragKonark.MoodLift.landing.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.AnuragKonark.MoodLift.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Books extends AppCompatActivity implements ValueEventListener{
    CardView book1, book2, book3;
    ImageView book1image, book2image, book3image;
    TextView book1name, book2name,book3name;
    ImageButton backbooks;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference book1imagedata = databaseReference.child("book1image");
    private DatabaseReference book2imagedata = databaseReference.child("book2image");
    private DatabaseReference book3imagedata = databaseReference.child("book3image");
    private DatabaseReference book1namedata = databaseReference.child("book1name");
    private DatabaseReference book2namedata = databaseReference.child("book2name");
    private DatabaseReference book3namedata = databaseReference.child("book3name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        book1 = findViewById(R.id.book1);
        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, Book1.class);
                startActivity(intent);
            }
        });
        book2 = findViewById(R.id.book2);
        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, Book2.class);
                startActivity(intent);
            }
        });
        book3 = findViewById(R.id.book3);
        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, Book3.class);
                startActivity(intent);
            }
        });
        book1image = findViewById(R.id.book1image);
        book2image = findViewById(R.id.book2image);
        book3image = findViewById(R.id.book3image);

        book1name = findViewById(R.id.book1name);
        book2name = findViewById(R.id.book2name);
        book3name = findViewById(R.id.book3name);

        backbooks = findViewById(R.id.backbooks);
        backbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.getValue(String.class)!=null){
            String key = snapshot.getKey();
            if (key.equals("book1image")){
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(book1image);
            }
            if (key.equals("book2image")) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(book2image);            }
            if (key.equals("book3image")) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(book3image);
            }
            if (key.equals("book1name")) {
                String text = snapshot.getValue(String.class);
                book1name.setText(text);
            }
            if (key.equals("book2name")) {
                String text = snapshot.getValue(String.class);
                book2name.setText(text);
            }
            if (key.equals("book3name")) {
                String text = snapshot.getValue(String.class);
                book3name.setText(text);
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
    @Override
    protected void onStart (){
        super.onStart();
        book1imagedata.addValueEventListener(this);
        book2imagedata.addValueEventListener(this);
        book3imagedata.addValueEventListener(this);
        book1namedata.addValueEventListener(this);
        book2namedata.addValueEventListener(this);
        book3namedata.addValueEventListener(this);
    }
}