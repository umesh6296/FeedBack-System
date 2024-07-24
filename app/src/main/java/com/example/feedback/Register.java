package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText email,password,name,mobile;
    RadioGroup radioGroup;
    RadioButton rb1,rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.emailId);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        radioGroup=findViewById(R.id.radioGroup);
        rb1=findViewById(R.id.radioButton);
        rb2=findViewById(R.id.radioButton2);
    }
    public void insert(View view) {
        boolean isEmpty = false;
        if (TextUtils.isEmpty(email.getText().toString())) {
            isEmpty = true;
            email.setError("Please fill email id");
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            isEmpty = true;
            password.setError("Please fill password");
        }
        if (TextUtils.isEmpty(name.getText().toString())) {
            isEmpty = true;
            name.setError("Please fill name");
        }if (TextUtils.isEmpty(mobile.getText().toString())) {
            isEmpty = true;
            name.setError("Please fill mobile no");
        }
        if (!isEmpty) {
            String em = email.getText().toString();
            String pas = password.getText().toString();
            String nam = name.getText().toString();
            String mob = mobile.getText().toString();
            String ro = rb1.isChecked() ? "A" : "S";

            UserDBHelper dbh = new UserDBHelper(this);

            // Check if email is already registered
            if (dbh.checkEmailExists(em)) {
                Toast.makeText(this, "Email already registered", Toast.LENGTH_LONG).show();
            } else {
                UserEntity entity = new UserEntity(em, nam, pas, ro,mob);
                dbh.insert(entity);

                if (ro.equals("A")) {
                    Intent in = new Intent(this, AdminLoogin.class);
                    startActivity(in);
                } else {
                    Intent in = new Intent(this, StudentLogin.class);
                    in.putExtra("name", nam);
                    in.putExtra("email", em);
                    startActivity(in);
                }
            }
        } else {
            Toast.makeText(this, "Please fill in all values", Toast.LENGTH_LONG).show();
        }
    }


}