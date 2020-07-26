package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements StateViewAdapter.OnStateListner {

    RecyclerView recyclerView;
    String s1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.stateView);
        s1 = getResources().getStringArray(R.array.state_names);

        StateViewAdapter stateViewAdapter = new StateViewAdapter(this, s1);
        recyclerView.setAdapter(stateViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStateClick(int position) {
        Intent intent = new Intent();
    }
}