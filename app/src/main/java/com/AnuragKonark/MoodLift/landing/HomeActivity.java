package com.AnuragKonark.MoodLift.landing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.AnuragKonark.MoodLift.ChatBot.MainActivity;
import com.AnuragKonark.MoodLift.community.ChatListFragment;
import com.AnuragKonark.MoodLift.landing.books.Books;
import com.AnuragKonark.MoodLift.community.DashboardActivity;
import com.AnuragKonark.MoodLift.R;
import com.AnuragKonark.MoodLift.nav.Chats;
import com.AnuragKonark.MoodLift.nav.Settings;
import com.AnuragKonark.MoodLift.nav.TandP;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ValueEventListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button chatBot, trackerbtn;
    CardView todo, Activities, tipCard, inspoCard, articleCard;
    TextView quote, tip, inspo, article;
    ImageButton guide;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference quotedata = databaseReference.child("quote");
    private DatabaseReference tipdata = databaseReference.child("tip");
    private DatabaseReference inspodata = databaseReference.child("inspo");
    private DatabaseReference articledata = databaseReference.child("article");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        chatBot= findViewById(R.id.chatBot);
        chatBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });
        todo= findViewById(R.id.todo);
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        Activities= findViewById(R.id.Activities);
        Activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, com.AnuragKonark.MoodLift.landing.activities.Activities.class);
                startActivity(intent);
            }
        });
        quote = findViewById(R.id.quote);
        tip = findViewById(R.id.tip);
        tipCard = findViewById(R.id.tipCard);
        tipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, MoodliftTips.class);
                startActivity(intent);

            }
        });
        inspo = findViewById(R.id.inspo);
        inspoCard = findViewById(R.id.inspoCard);
        inspoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, MoodliftInspo.class);
                startActivity(intent);

            }
        });
        article = findViewById(R.id.article);
        articleCard = findViewById(R.id.articleCard);
        articleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, MoodliftArticle.class);
                startActivity(intent);

            }
        });
        guide = findViewById(R.id.guide);
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Books.class);
                startActivity(intent);

            }
        });
        trackerbtn = findViewById(R.id.trackerbtn);
        trackerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, com.AnuragKonark.MoodLift.tracker.MainActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else{
            super.onBackPressed();
            finishAffinity();

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_help:
                Intent intent2 = new Intent(HomeActivity.this, TandP.class);
                startActivity(intent2);
                break;
            case R.id.nav_chatlist:
                Intent intent3 = new Intent(HomeActivity.this, Chats.class);
                startActivity(intent3);
                break;
            case R.id.nav_settings:
                Intent intent4 = new Intent(HomeActivity.this, Settings.class );
                startActivity(intent4);
                break;
        }

        return false;

    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.getValue(String.class)!=null){
            String key = snapshot.getKey();
            if (key.equals("quote")){
                String text = snapshot.getValue(String.class);
                quote.setText(text);
            }
            if (key.equals("tip")) {
                String text = snapshot.getValue(String.class);
                tip.setText(text);
            }
            if (key.equals("inspo")) {
                String text = snapshot.getValue(String.class);
                inspo.setText(text);
            }
            if (key.equals("article")) {
                String text = snapshot.getValue(String.class);
                article.setText(text);
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {


    }
    @Override
    protected void onStart (){
        super.onStart();
        quotedata.addValueEventListener(this);
        tipdata.addValueEventListener(this);
        inspodata.addValueEventListener(this);
        articledata.addValueEventListener(this);

    }

}