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
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.Header;

public class TranslateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView translatedTweet;
    TextView toTranslate;
    String Tweet;
    String lang;
    Button btn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_translate);

        Tweet = getIntent().getExtras().getString("tweets");
        lang = getIntent().getExtras().getString("language");
        toTranslate = findViewById(R.id.tweetToTranslate);
        toTranslate.setText(Tweet);
        translatedTweet = findViewById(R.id.translatedTweet);

        btn = findViewById(R.id.translateBTN);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String translationString = toTranslate.getText().toString();
                if (lang.equals("french")) {
                    try {
                        Http.post(translationString, "fr", "en", new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    JSONObject serverResp = new JSONObject(response.toString());
                                    JSONObject jsonObject = serverResp.getJSONObject("data");
                                    JSONArray transObject = jsonObject.getJSONArray("translations");
                                    JSONObject transObject2 = transObject.getJSONObject(0);
                                    translatedTweet.setText(transObject2.getString("translatedText"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Http.post(translationString, "es", "en", new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    JSONObject serverResp = new JSONObject(response.toString());
                                    JSONObject jsonObject = serverResp.getJSONObject("data");
                                    JSONArray transObject = jsonObject.getJSONArray("translations");
                                    JSONObject transObject2 = transObject.getJSONObject(0);
                                    translatedTweet.setText(transObject2.getString("translatedText"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        toolbar = findViewById(R.id.toolbar_translate);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_translate);
        navigationView = findViewById(R.id.nav_view_translate);

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
            Intent intent = new Intent(TranslateActivity.this, HashTagActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}