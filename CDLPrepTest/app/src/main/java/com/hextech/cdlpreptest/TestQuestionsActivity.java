package com.hextech.cdlpreptest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestQuestionsActivity extends AppCompatActivity {

    TextView textViewQuestion;
    Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_questions);

        textViewQuestion = this.findViewById(R.id.textViewQuestion);
        btnAnswer1 = this.findViewById(R.id.btnAnswer1);
        btnAnswer2 = this.findViewById(R.id.btnAnswer2);
        btnAnswer3 = this.findViewById(R.id.btnAnswer3);
        btnAnswer4 = this.findViewById(R.id.btnAnswer4);
    }
}
