package com.hextech.cdlpreptest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestQuestionsActivity extends AppCompatActivity {

    Boolean instantFeedback, stopOnMaxMistakes;
    int currentQuestionNum, maxQuestionNum, maxMistakeNum;
    TextView textViewQuestion;
    Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_questions);

        textViewQuestion = this.findViewById(R.id.textViewQuestion);
        btnAnswer1 = this.findViewById(R.id.btnAnswer1);
        btnAnswer2 = this.findViewById(R.id.btnAnswer2);
        btnAnswer3 = this.findViewById(R.id.btnAnswer3);
        btnAnswer4 = this.findViewById(R.id.btnAnswer4);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("cdlpref", Context.MODE_PRIVATE);
        currentQuestionNum = pref.getInt("currentQuestion", 0);
        maxQuestionNum = pref.getInt("numQuestions", 50);
        maxMistakeNum = pref.getInt("numMistakes", 10);
        instantFeedback = pref.getBoolean("instantFeedback", Boolean.FALSE);
        stopOnMaxMistakes = pref.getBoolean("stopOnMaxMistakes", Boolean.FALSE);
    }
}
