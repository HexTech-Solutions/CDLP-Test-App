package com.hextech.cdlpreptest;

import android.os.Bundle;

import com.hextech.cdlpreptest.util.QuestionResult;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewTestResultsActivity extends AppCompatActivity {

    private RecyclerView testResultsReviewView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test_results);
        ArrayList<QuestionResult> resultsList = (ArrayList<QuestionResult>) getIntent().getSerializableExtra("question_results_extra");

        testResultsReviewView = this.findViewById(R.id.testResultsReviewView);
        testResultsReviewView.setHasFixedSize(true);

        testResultsReviewView.setLayoutManager(new LinearLayoutManager(this));
        testResultsReviewView.setAdapter(new ReviewTestResultsViewAdapter(this, resultsList));
    }
}
