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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView keyWordView;
    TextView languageView;
    TextView tweets;
    String word;
    String lang;
    String choose;
    Button btn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_search);

        word = getIntent().getExtras().getString("KeyWord");
        lang = getIntent().getExtras().getString("language");
        keyWordView = findViewById(R.id.keyWordBeingSearched);
        languageView = findViewById(R.id.languageUsed);
        tweets = findViewById(R.id.tweets);
        keyWordView.setText(word);
        languageView.setText(lang);
        list = new ArrayList<>();

        btn = findViewById(R.id.searchBTN);
        if (word.equals("eiffel tower") && lang.equals("french")) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        tweets.setText(towerFrenchFile());
                    }catch (Exception e) { }
                }
            });
        }
        else if (word.equals("eiffel tower") && lang.equals("spanish")) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        tweets.setText(towerSpanishFile());
                    }catch (Exception e) { }
                }
            });
        }
        else if (word.equals("barcelona fc") && lang.equals("french")) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        tweets.setText(cataloniaFrenchFile());
                    }catch (Exception e) { }
                }
            });
        }
        else if (word.equals("barcelona fc") && lang.equals("spanish")) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        tweets.setText(cataloniaSpanishFile());
                    }catch (Exception e) { }
                }
            });
        }

        Button btn = findViewById(R.id.chooseBTN);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SearchActivity.this,TranslateActivity.class);
                choose = tweets.getText().toString();
                intent.putExtra("tweets", choose);
                lang = languageView.getText().toString();
                intent.putExtra("language", lang);
                startActivity(intent);
            }
        });

        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_search);
        navigationView = findViewById(R.id.nav_view_search);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(SearchActivity.this, HashTagActivity.class);
            startActivity(intent);
        }
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

    private String towerFrenchFile() {
        File fileEvents = new File(SearchActivity.this.getFilesDir() + "/text/towerFrench.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = Arrays.toString(new ArrayList[]{list});
        return result;
    }
    private String towerSpanishFile() {
        File fileEvents = new File(SearchActivity.this.getFilesDir() + "/text/towerSpanish.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = Arrays.toString(new ArrayList[]{list});
        return result;
    }
    private String cataloniaFrenchFile() {
        File fileEvents = new File(SearchActivity.this.getFilesDir() + "/text/barcelonaFrench.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = Arrays.toString(new ArrayList[]{list});
        return result;
    }
    private String cataloniaSpanishFile() {
        File fileEvents = new File(SearchActivity.this.getFilesDir() + "/text/barcelonaSpanish.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = Arrays.toString(new ArrayList[]{list});
        return result;
    }
}