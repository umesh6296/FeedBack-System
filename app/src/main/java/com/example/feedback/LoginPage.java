package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
EditText email,password;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email=findViewById(R.id.emailId);
        password=findViewById(R.id.password);

        LinearLayout linearLayout = findViewById(R.id.lineearlayout);
         textView = findViewById(R.id.msgText);

// Check if text is empty or null
        if (textView.getText().toString().isEmpty()) {
            // If text is empty, hide the LinearLayout
            linearLayout.setVisibility(View.GONE);
        } else {
            // If text is not empty, show the LinearLayout
            linearLayout.setVisibility(View.VISIBLE);
        }



    }
    public void goToSecondActivity(View view) {
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);
    }

    public void find(View view) {
        boolean isEmpty = false;
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Please fill email address");
            isEmpty = true;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Please fill password");
            isEmpty = true;
        }

        if (!isEmpty) {
            String em = email.getText().toString();
            String pas = password.getText().toString();
            UserDBHelper dbHelper = new UserDBHelper(this);
            UserDTO dto = dbHelper.find(em, pas);

            if (dto != null && dto.getUserEntity() != null) {
                String name = dto.getUserEntity().getName();
                if (dto.getUserEntity().getRole().equals("A")) {
                    Intent in = new Intent(this, AdminLoogin.class);
                    startActivity(in);
                } else {
                    Intent in = new Intent(this, StudentLogin.class);
                    in.putExtra("email", em);
                    in.putExtra("nameLo", name);
                    startActivity(in);
                }
                textView.setVisibility(View.VISIBLE);
            } else {
                // Data not found, display error
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void register(View view){
        Intent in=new Intent(this, Register.class);
        startActivity(in);
    }

}