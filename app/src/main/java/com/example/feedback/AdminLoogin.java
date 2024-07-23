package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;

public class AdminLoogin extends AppCompatActivity {
    Spinner spinner;
    TextView showButton,doneBtn;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_loogin);

        spinner = findViewById(R.id.spinneradmin);
        showButton = findViewById(R.id.showButton);
        tableLayout = findViewById(R.id.tableLayout);
        doneBtn = findViewById(R.id.doneBtn);

        String[] data = getResources().getStringArray(R.array.course);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCourse = spinner.getSelectedItem().toString();
                displayFeedbackForCourse(selectedCourse);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLoogin.this, LoginPage.class);
                startActivity(intent);
                finish(); // Optional: Finish this activity to remove it from the back stack
            }
        });
    }



    private void displayFeedbackForCourse(String course) {
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<FeedbackEntity> feedbackList = dbHelper.getFeedbackForCourse(course);

        tableLayout.removeAllViews();

        // Add table headers
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createTextView("Student Name"));
        headerRow.addView(createTextView("Rating"));
        headerRow.addView(createTextView("Suggestion"));
        tableLayout.addView(headerRow);

        // Add data rows
        for (FeedbackEntity feedback : feedbackList) {
            TableRow row = new TableRow(this);
            row.addView(createTextView(dbHelper.getStudentNameByEmail(feedback.getEmail())));
            row.addView(createTextView(String.valueOf(feedback.getRating())));
            row.addView(createTextView(feedback.getSuggestion()));
            tableLayout.addView(row);
        }
    }




    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        return textView;
    }


}
