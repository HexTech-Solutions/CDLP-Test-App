package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LearnActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String learnTitle[];
    String learnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        recyclerView = findViewById(R.id.learnView);
        learnTitle = getResources().getStringArray(R.array.learn_titles);

        LearnViewAdapter learnViewAdapter = new LearnViewAdapter(this, learnTitle);
        recyclerView.setAdapter(learnViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}