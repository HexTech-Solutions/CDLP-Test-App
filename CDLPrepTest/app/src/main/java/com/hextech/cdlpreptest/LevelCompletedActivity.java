package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class LevelCompletedActivity extends AppCompatActivity {

    Button goToNextLevelButton,tryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_completed);

        goToNextLevelButton = (Button) findViewById(R.id.goNextBtn);
        tryAgainButton = (Button) findViewById(R.id.tryAgainBtn);

    }
}