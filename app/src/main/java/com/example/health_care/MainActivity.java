package com.example.health_care;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.health_care.activities.LoginPage;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView22;
    Animation textViewAnimation;
    private SharedPreferences sharedPreferences;
    private static final String preferencesKey = "OurCw";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        textView1 = findViewById(R.id.textView1);
        textView22 = findViewById(R.id.textView22);
        textViewAnimation = AnimationUtils.loadAnimation(this, R.anim.animate_main_activity);
        textView1.setAnimation(textViewAnimation);
        textView22.setAnimation(textViewAnimation);
        final Runnable startFirstPage = new Runnable() {
            public void run() {
                //Database.getInstance().retrieveAllData(getSharedPreferences(preferencesKey, Context.MODE_PRIVATE));
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(startFirstPage, 3000);
    }
}