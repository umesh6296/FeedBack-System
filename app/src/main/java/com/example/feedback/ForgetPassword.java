package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {
    TextView reset;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        reset = findViewById(R.id.resetBtn);
        email = findViewById(R.id.newEmailId);
        password = findViewById(R.id.newPassword);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();

                if (TextUtils.isEmpty(emailStr)) {
                    email.setError("Please enter your email");
                } else if (TextUtils.isEmpty(passwordStr)) {
                    password.setError("Please enter your new password");
                } else {
                    UserDBHelper dbHelper = new UserDBHelper(ForgetPassword.this);
                    if (dbHelper.checkEmailExists(emailStr)) {
                        dbHelper.updatePassword(emailStr, passwordStr);
                        Toast.makeText(ForgetPassword.this, "Password reset successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgetPassword.this, LoginPage.class);
                        startActivity(intent);
                        finish(); // Optional: Finish this activity to remove it from the back stack
                    } else {
                        Toast.makeText(ForgetPassword.this, "Email not found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
