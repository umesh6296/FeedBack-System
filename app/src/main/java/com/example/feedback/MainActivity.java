package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Runnable r=new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(MainActivity.this, LoginPage.class);
                startActivity(in);
            }
        };
        Handler h=new Handler();
        h.postDelayed(r,1500);
    }
}