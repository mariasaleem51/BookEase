package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide action bar if present for full-screen splash look
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Delay for exactly 3 seconds before moving to LoginActivity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close splash activity so user cannot return to it
            }
        }, 3000);
    }
}