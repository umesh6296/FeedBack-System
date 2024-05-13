package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {
    Spinner spinner;
    RatingBar ratingBar;
    EditText sugg;
    TextView btn,wel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        spinner=findViewById(R.id.spinner);
        ratingBar=findViewById(R.id.ratingBar);
        sugg=findViewById(R.id.suggestion);
        btn=findViewById(R.id.doneBtn);
        wel=findViewById(R.id.welcome);
        String na = getIntent().getStringExtra("nameLo");
        String nam = getIntent().getStringExtra("name");
        if (na != null) {
            wel.setText("Welcome " + na);
        } else if (nam != null) {
            wel.setText("Welcome " + nam);
        } else {
            // Handle the case when neither "nameLo" nor "name" extras are available
            // You might want to set a default welcome message or handle this case differently
        }

        String[] data = getResources().getStringArray(R.array.course);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
    }


    public void insertNext(View view) {
        boolean isEmpty = false;
        if (TextUtils.isEmpty(sugg.getText().toString())) {
            isEmpty = true;
            sugg.setError("Please give a suggestion");
        }
        if (ratingBar.getRating() == 0) { // Check if no rating has been given
            isEmpty = true;
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_LONG).show();
        }
        if (!isEmpty) {
            String email = getIntent().getStringExtra("email");
            String lang = String.valueOf(spinner.getSelectedItem());
            String su = sugg.getText().toString();
            float rat = ratingBar.getRating();

            FeedbackEntity entity = new FeedbackEntity(email, lang, su, rat);
            FeedbackDBHelper dbh = new FeedbackDBHelper(this);

            // Check if feedback for the selected course already exists
            if (!dbh.checkFeedbackExists(email, lang)) {
                dbh.insert(entity);
                Intent in = new Intent(this, ThanksStudent.class);
                in.putExtra("nameLo", getIntent().getStringExtra("nameLo"));
                in.putExtra("name", getIntent().getStringExtra("name"));
                startActivity(in);
            } else {
                Toast.makeText(this, "You have already provided feedback for this course", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please fill in all values", Toast.LENGTH_LONG).show();
        }
    }






}