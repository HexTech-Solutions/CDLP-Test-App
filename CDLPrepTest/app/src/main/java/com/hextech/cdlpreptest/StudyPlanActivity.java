package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ui.fragments.SettingsFragment;

public class StudyPlanActivity extends AppCompatActivity {

    String newDate;
    String formattedDate;
    Button clickButton;
    Date startDate;
    TextView examTView,remainDaysTxtView,detialsTxtView;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_plan);

        //Get current date and time
        startDate = Calendar.getInstance().getTime();

        setup();
        calculate();
        changeDetails();
        emptyDates();
    }

    //setup the buttons,textfields etc.
    public void setup(){
        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        clickButton = (Button) findViewById(R.id.changeCountButton);
        examTView = (TextView) findViewById(R.id.examDateTxtView);
        remainDaysTxtView = (TextView) findViewById(R.id.remainDaysTxtView);
        detialsTxtView = (TextView) findViewById(R.id.detailsTextView);

        //set the minimum date as today
        datePicker.setMinDate(startDate.getTime());

    }

    public void calculate(){

        //get current date
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        formattedDate = df.format(startDate);
        System.out.println("Current time => " + formattedDate);

        //get the selected date
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            //converting in to the format with 0 in front
            @Override
            public void onDateChanged(DatePicker view, int myear, int mmonth, int mdayOfMonth) {
                //Adding 0 if the month is less than 10
                String nd = "" + mdayOfMonth;
                String nm = "" + mmonth ;
                if ( mdayOfMonth < 10 ){
                    nd = "0"+ mdayOfMonth;
                }

                if ( (mmonth + 1) < 10){
                    nm = "0"+ ( mmonth +1 ) ;
                }else {
                    nm = ""+ ( mmonth + 1) ;
                }
                //selected date
                newDate = (mdayOfMonth + "-" + (nm) + "-" + myear);

            }
        });
    }

    //if empty show the default text in details TView
    public void emptyDates(){
        if (remainDaysTxtView.getText().toString().isEmpty()){
            detialsTxtView.setText("Based on the time remaining until your exam date," +
                    " We will calculate the number of questions you should mastered daily to get the best exam results.");
        }
    }

    public void changeDetails(){

        //change remaining dates and details view
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String dtStart = newDate;
                Integer questionPerDay = 0;

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd-MM-yyyy"), formattedDate, newDate);
                System.out.println("dateDifference: " + dateDifference);
                remainDaysTxtView.setText( String.valueOf(dateDifference) + " Days");
                examTView.setText(newDate);

                //change text field of question count
                if (dateDifference <= 5 ){
                    questionPerDay = 150;
                } else if (dateDifference <= 10 ){
                    questionPerDay = 120;
                } else if (dateDifference <= 15 ){
                    questionPerDay = 100;
                }else if (dateDifference <= 20 ){
                    questionPerDay = 80;
                } else{
                    questionPerDay = 50;
                }
                String questCount = "Number of Questions";

                String text = detialsTxtView.getContext().getString(R.string.studyPlan, questCount, questionPerDay);
                detialsTxtView.setText(text);
            }
        });
    }

    //Get date difference method
    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}