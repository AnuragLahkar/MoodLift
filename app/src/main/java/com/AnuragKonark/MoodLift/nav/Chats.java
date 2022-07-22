package com.AnuragKonark.MoodLift.nav;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.AnuragKonark.MoodLift.R;
import com.AnuragKonark.MoodLift.community.ChatListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Chats extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String myuid;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        actionBar=getSupportActionBar();
        firebaseAuth= FirebaseAuth.getInstance();
        actionBar.setTitle("Chats");
        ChatListFragment listFragment=new ChatListFragment();
        FragmentTransaction fragmentTransaction3=getSupportFragmentManager().beginTransaction();
        fragmentTransaction3.replace(R.id.content,listFragment,"");
        fragmentTransaction3.commit();
    }
}