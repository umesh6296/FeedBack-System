package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ThanksStudent extends AppCompatActivity {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_student);
        Runnable r=new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(ThanksStudent.this, LoginPage.class);
                startActivity(in);
            }
        };
        Handler h=new Handler();
        h.postDelayed(r,1500);

        tv1=findViewById(R.id.name);
        String na = getIntent().getStringExtra("nameLo");
        String nam = getIntent().getStringExtra("name");
        if (na != null) {
            tv1.setText("Welcome " + na);
        } else if (nam != null) {
            tv1.setText("Welcome " + nam);
        } else {
            // Handle the case when neither "nameLo" nor "name" extras are available
            // You might want to set a default welcome message or handle this case differently
        }

    }
}