package com.example.interactiveassistant;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.interactiveassistant.FAQAdapter.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements FAQAdapter.OnItemClickListener {

    private RecyclerView questionList;
    // private TextView answerText;
    private List<FAQ> faqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar as the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide the default title
        }

        // Set the title for the activity
        getSupportActionBar().setTitle("Interactive Assistant");

        // Set up greeting texts
        TextView greetingText1 = findViewById(R.id.greetingText1);
        TextView greetingText2 = findViewById(R.id.greetingText2);

        questionList = findViewById(R.id.questionList);
        //answerText = findViewById(R.id.answerText);

        // Load FAQs from JSON
        faqs = loadFAQs();

        // Add divider to RecyclerView for separating questions
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(getResources().getDrawable(R.drawable.divider, null)); // Use the custom divider drawable
//        questionList.addItemDecoration(divider);

        // Set up RecyclerView
        FAQAdapter adapter = new FAQAdapter(faqs, this);

        questionList.setLayoutManager(new LinearLayoutManager(this));
        questionList.setAdapter(adapter);
    }

    // Load FAQs from the JSON file in assets
    private List<FAQ> loadFAQs() {
        List<FAQ> faqList = new ArrayList<>();
        try {
            InputStream is = getAssets().open("faqs.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONObject obj = new JSONObject(json);
            JSONArray faqArray = obj.getJSONArray("faqs");

            for (int i = 0; i < faqArray.length(); i++) {
                JSONObject faqObject = faqArray.getJSONObject(i);
                String question = faqObject.getString("question");
                String answer = faqObject.getString("answer");
                faqList.add(new FAQ(question, answer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return faqList;
    }

    // Show the selected answer
    @Override
    public void onItemClick(String answer) { // Must match the interface
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("ANSWER", answer);
        startActivity(intent);
    }

   /* private void showAnswer(String answer) {
        answerText.setText(answer);
    }*/

}
