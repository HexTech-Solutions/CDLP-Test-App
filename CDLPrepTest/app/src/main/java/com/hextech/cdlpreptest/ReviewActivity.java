package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String reviewTitle[], reviewQuestionCount[];
    int icons[] = {R.drawable.icon1, R.drawable.icon1, R.drawable.icon1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.reviewView);

        reviewTitle = getResources().getStringArray(R.array.review_titles);
        reviewQuestionCount = new String[]{"0 questions", "1 question", "2 questions"};

        ReviewViewAdapter reviewViewAdapter = new ReviewViewAdapter(this, reviewTitle, reviewQuestionCount, icons);
        recyclerView.setAdapter(reviewViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}