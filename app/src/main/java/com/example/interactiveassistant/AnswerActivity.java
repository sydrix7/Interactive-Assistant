package com.example.interactiveassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AnswerActivity extends AppCompatActivity {

    private TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        // Set up the custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Disable default title

        // Get the TextView for the toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbar_title);


        // Enable the back arrow in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Back arrow
        }

        // Retrieve the question and answer passed from MainActivity
        Intent intent = getIntent();
        String question = intent.getStringExtra("QUESTION"); // Get the question
        String answer = intent.getStringExtra("ANSWER"); // Get the answer

        // Set the question as the toolbar title
        toolbarTitle.setText(question); // Dynamically set title to the question

        // Set the question as the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(question); // Dynamically set title to the question
        }

        // Set the answer in the answerText view
        answerText = findViewById(R.id.answerText);
        answerText.setText(answer);
    }

    // Handle back arrow click in the toolbar
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Close this activity and return to MainActivity
        return true;
    }
}
