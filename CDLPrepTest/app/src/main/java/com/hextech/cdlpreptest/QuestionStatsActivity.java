package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hextech.cdlpreptest.util.DBHelper;
import com.hextech.cdlpreptest.util.Question;

import java.util.ArrayList;

public class QuestionStatsActivity extends AppCompatActivity {

    RecyclerView questionStatsRecyclerView;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_stats);

        String whereClause = getIntent().getStringExtra("q_stat_where_clause");

        database = new DBHelper(getApplicationContext());
        ArrayList<Question> questionList = database.getQuestionAndAnsCountWithSelection(whereClause);

        questionStatsRecyclerView = this.findViewById(R.id.questionStatsRecyclerView);
        questionStatsRecyclerView.setHasFixedSize(true);

        questionStatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionStatsRecyclerView.setAdapter(new QuestionStatsViewAdapter(this, questionList));
    }
}