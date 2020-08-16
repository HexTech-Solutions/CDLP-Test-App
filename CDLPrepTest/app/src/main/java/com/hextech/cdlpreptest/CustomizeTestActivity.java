package com.hextech.cdlpreptest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomizeTestActivity extends AppCompatActivity {

    final int MAX_QUESTIONS = 50;
    final int DEFAULT_QUESTIONS = 50;
    final int MAX_MISTAKES = 19;
    final int DEFAULT_MISTAKES = 10;

    SharedPreferences pref;

    SeekBar seekNumQuestions, seekMaxMistakes;
    TextView textViewNumQuestions, textViewMaxMistakes;
    Switch switchStopOnMaxMistake, switchInstantFeedback;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_test);

        initializeDefaults();
        initializeListeners();
    }

    /**
     * This methods initializes the components and sets the previous/default values
     */
    private void initializeDefaults(){
        seekNumQuestions = this.findViewById(R.id.seekBarNumQuestions);
        seekMaxMistakes = this.findViewById(R.id.seekBarMaxMistakes);
        textViewNumQuestions = this.findViewById(R.id.textViewNumQuestions);
        textViewMaxMistakes = this.findViewById(R.id.textViewMaxMistakes);
        switchStopOnMaxMistake = this.findViewById(R.id.switchStopMistake);
        switchInstantFeedback = this.findViewById(R.id.switchInstantFeedback);
        btnSave = this.findViewById(R.id.btnSave);

        seekNumQuestions.setMax(MAX_QUESTIONS);
        seekMaxMistakes.setMax(MAX_MISTAKES);

        pref = getApplicationContext().getSharedPreferences("cdlpref", Context.MODE_PRIVATE);

        seekNumQuestions.setProgress(pref.getInt("numQuestions", DEFAULT_QUESTIONS));
        textViewNumQuestions.setText(String.format("%s", pref.getInt("numQuestions", DEFAULT_QUESTIONS)));
        seekMaxMistakes.setProgress(pref.getInt("numMistakes", DEFAULT_MISTAKES));
        textViewMaxMistakes.setText(String.format("%s", pref.getInt("numMistakes", DEFAULT_MISTAKES)));
        switchStopOnMaxMistake.setChecked(pref.getBoolean("stopOnMaxMistakes", Boolean.FALSE));
        switchInstantFeedback.setChecked(pref.getBoolean("instantFeedback", Boolean.FALSE));
    }

    /**
     * This method initializes the listeners for the components
     */
    private void initializeListeners(){
        seekNumQuestions.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewNumQuestions.setText(String.format("%s", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekMaxMistakes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewMaxMistakes.setText(String.format("%s", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*
        This saves the values of setting to shared preferences to be fetched later
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("numQuestions", Integer.parseInt(String.valueOf(textViewNumQuestions.getText())));
                editor.putInt("numMistakes", Integer.parseInt(String.valueOf(textViewMaxMistakes.getText())));
                editor.putBoolean("stopOnMaxMistakes", switchStopOnMaxMistake.isChecked());
                editor.putBoolean("instantFeedback", switchInstantFeedback.isChecked());
                editor.apply();
                finish();
            }
        });
    }
}
