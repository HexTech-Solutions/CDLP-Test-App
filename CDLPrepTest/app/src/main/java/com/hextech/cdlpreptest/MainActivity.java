package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements StateViewAdapter.OnStateListner {

    RecyclerView recyclerView;
    String stateName[];
    String selectedState;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String stateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        loadData();
        if(stateText.equals("")){
            setContentView(R.layout.activity_main);
            recyclerView = findViewById(R.id.stateView);
            stateName = getResources().getStringArray(R.array.state_names);

            StateViewAdapter stateViewAdapter = new StateViewAdapter(this, stateName, this);
            recyclerView.setAdapter(stateViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else {
//            Intent intent = new Intent(this, ReviewActivity.class);
//            startActivity(intent);
        }
    }

    @Override
    public void onStateClick(int position) {
        selectedState = stateName[position];
        saveData(selectedState);
//        Intent intent = new Intent(this, ReviewActivity.class);
//        startActivity(intent);
    }

    public void saveData(String state){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, state);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        stateText = sharedPreferences.getString(TEXT, "");
//        System.out.println("load stateText- " + stateText);
    }
}