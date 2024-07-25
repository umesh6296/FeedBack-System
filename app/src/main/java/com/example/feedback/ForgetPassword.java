package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class ForgetPassword extends AppCompatActivity {
    EditText mobile, otp, newPassword;
    LinearLayout otpLayout, newPasswordLayout;
    String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        mobile = findViewById(R.id.mobile);
        otp = findViewById(R.id.otp);
        newPassword = findViewById(R.id.newPassword);
        otpLayout = findViewById(R.id.otpLayout);
        newPasswordLayout = findViewById(R.id.newPasswordLayout);
    }


}
