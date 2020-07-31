package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;

public class LevelActivity extends AppCompatActivity {

    Button l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18;
    TextView notSeenTV, famTV, masterTV, progressCountTV;
    int familiarCount, masteredCount;
    private ProgressBar progressBar;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        //back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //button and TextView initilizing
        l1 = (Button) findViewById(R.id.level1Btn);
        l2 = (Button) findViewById(R.id.level2Btn);
        l3 = (Button) findViewById(R.id.level3Btn);
        l4 = (Button) findViewById(R.id.level4Btn);
        l5 = (Button) findViewById(R.id.level5Btn);
        l6 = (Button) findViewById(R.id.level6Btn);
        l7 = (Button) findViewById(R.id.level7Btn);
        l8 = (Button) findViewById(R.id.level8Btn);
        l9 = (Button) findViewById(R.id.level9Btn);
        l10 = (Button) findViewById(R.id.level10Btn);
        l11 = (Button) findViewById(R.id.level11Btn);
        l12 = (Button) findViewById(R.id.level12Btn);
        l13 = (Button) findViewById(R.id.level13Btn);
        l14 = (Button) findViewById(R.id.level14Btn);
        l15 = (Button) findViewById(R.id.level15Btn);
        l16 = (Button) findViewById(R.id.level16Btn);
        l17 = (Button) findViewById(R.id.level17Btn);
        l18 = (Button) findViewById(R.id.level18Btn);
        notSeenTV = (TextView) findViewById(R.id.notSeenTView);
        famTV = (TextView) findViewById(R.id.familiarTView);
        masterTV = (TextView) findViewById(R.id.masteredTView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressCountTV = (TextView) findViewById(R.id.percentageTextView);

        buttonEnable();
        disableButtons();
        updateStatus();
        updatePercentage();
    }

    //set button visibility true and false for each Topic
    private void buttonEnable(){
        Integer levelCount = getIntent().getIntExtra("SelectedTest", 0);

        switch(levelCount) {
            case 1:
                String [ ]invisibleButtonString1 = {"level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString1);
                String [ ]invisibleTextViewString1 = {"t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString1);
                break;
            case 2:
                String [ ]invisibleButtonString2 = {"level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString2);
                String [ ]invisibleTextViewString2 = {"t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString2);
                break;
            case 3:
                String [ ]invisibleButtonString3 = {"level9Btn","level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString3);
                String [ ]invisibleTextViewString3 = {"t9", "t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString3);
                break;
            case 4:
                String [ ]invisibleButtonString4 = {"level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString4);
                String [ ]invisibleTextViewString4 = {"t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString4);
                break;
            case 5:
                String [ ]invisibleButtonString5 = {"level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString5);
                String [ ]invisibleTextViewString5 = {"t8", "t9", "t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString5);
                break;
            case 6:
                String [ ]invisibleButtonString6 = {"level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString6);
                String [ ]invisibleTextViewString6 = {"t8", "t9", "t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString6);
                break;
            case 7:
                String [ ]invisibleButtonString7 = {"level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString7);
                String [ ]invisibleTextViewString7 = {"t8", "t9", "t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString7);
                break;
            case 8:
                String [ ]invisibleButtonString8 = {"level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString8);
                String [ ]invisibleTextViewString8 = {"t7", "t8", "t9", "t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString8);
                break;
            case 9:
                String [ ]invisibleButtonString9 = {"level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn","level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
                invisibleButtons(invisibleButtonString9);
                String [ ]invisibleTextViewString9 = {"t7", "t8", "t9", "t10", "t11", "t12", "t13","t14", "t15", "t16", "t17", "t18"};
                invisibleTextView(invisibleTextViewString9);
                break;
            default:
                l1.setVisibility(View.VISIBLE);
                break;
        }
    }

    //Disable buttons when 100% is completed
    private void disableButtons(){
        if(!(l1.getText().equals("100%"))){
            String [ ]arrayString = {"level2Btn", "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l2.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        }   else if(!(l3.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l4.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn", "level3Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l5.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn", "level3Btn", "level4Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l6.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn", "level3Btn", "level4Btn", "level5Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l7.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l8.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l9.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l10.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l11.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level12Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l12.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level13Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l13.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level14Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l14.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level15Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        } else if(!(l15.getText().equals("100%"))) {
            String[] arrayString = {"level1Btn", "level2Btn",  "level3Btn", "level4Btn", "level5Btn", "level6Btn", "level7Btn", "level8Btn", "level9Btn", "level10Btn", "level11Btn", "level12Btn", "level13Btn", "level14Btn", "level16Btn", "level17Btn", "level18Btn"};
            disableButtons(arrayString);
        }
    }

    //details textview update
    public void updateStatus(){
        //Update notseen
        Integer notSeenQuestionCount = getIntent().getIntExtra("QuestionCount1", 0);
        notSeenTV.setText(Integer.toString(notSeenQuestionCount));

        //update familiar
//        famTV.setText();

        //update mastered
//        masterTV.setText();

    }

    //update percentage
    public void updatePercentage(){
        progressStatus = 69;
        progressCountTV.setText(Integer.toString(progressStatus)+ "%");
        progressBar.setProgress(progressStatus);
    }

    //disable buttons for click Level
    public void disableButtons(String[] buttonNames) {
        for (String name : buttonNames) {
            int id = getResources().getIdentifier(name, "id", getPackageName());
            Button button = (Button) findViewById(id);
            button.setEnabled(false);
        }
    }

    //button invisible for selected Topic
    public void invisibleButtons(String[] invisibleButtonNames){
        for (String name : invisibleButtonNames) {
            int id = getResources().getIdentifier(name, "id", getPackageName());
            Button button = (Button) findViewById(id);
            button.setVisibility(View.INVISIBLE);
        }
    }

    //Text View invisible for selected Topic
    public void invisibleTextView(String[] invisibleButtonNames){
        for (String name : invisibleButtonNames) {
            int id = getResources().getIdentifier(name, "id", getPackageName());
            TextView textView = (TextView) findViewById(id);
            textView.setVisibility(View.INVISIBLE);
        }
    }

    //backbutton action
    @Override
    public boolean onSupportNavigateUp(){
        Intent myIntent = new Intent(LevelActivity.this, NavigationTabActivity.class);
        LevelActivity.this.startActivity(myIntent);
        return true;
    }
}