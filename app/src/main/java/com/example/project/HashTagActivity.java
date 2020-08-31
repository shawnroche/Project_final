package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class HashTagActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name;
    EditText keyWord;
    EditText language;
    String user;
    String word;
    String lang;
    Button btn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_hashtag);

        user=getIntent().getStringExtra("username");
        name= findViewById(R.id.NameText);
        name.setText(user);
        keyWord = findViewById(R.id.keyWord);
        language = findViewById(R.id.languageUsed);

        btn = findViewById(R.id.confirmBTN);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyWord.getText().toString().toLowerCase().equals("")){
                    Toast.makeText(HashTagActivity.this, "Please enter a KeyWord", Toast.LENGTH_SHORT).show();
                }
                else if (!language.getText().toString().toLowerCase().equals("french") && !language.getText().toString().equals("spanish")){
                    Toast.makeText(HashTagActivity.this, "there is no tweets available in this language", Toast.LENGTH_SHORT).show();
                }
                else if (!keyWord.getText().toString().toLowerCase().equals("eiffel tower") && !keyWord.getText().toString().equals("barcelona fc")){
                    Toast.makeText(HashTagActivity.this, "there is no tweets available for this hashtag", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent= new Intent(HashTagActivity.this,SearchActivity.class);
                    word = keyWord.getText().toString().toLowerCase();
                    lang = language.getText().toString().toLowerCase();
                    intent.putExtra("KeyWord", word);
                    intent.putExtra("language", lang);
                    startActivity(intent);
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        navigationView.bringToFront();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}