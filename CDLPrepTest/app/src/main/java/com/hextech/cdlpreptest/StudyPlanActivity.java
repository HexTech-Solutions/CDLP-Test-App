package com.hextech.cdlpreptest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

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
    TextView examTView,remainDaysTxtView;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_plan);
        //back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Get current date and time
        startDate = Calendar.getInstance().getTime();

        setup();
        calculate();
        changeDetails();
    }

    //setup the buttons,textfields etc.
    public void setup(){
        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        clickButton = (Button) findViewById(R.id.changeCountButton);
        examTView = (TextView) findViewById(R.id.examDateTxtView);
        remainDaysTxtView = (TextView) findViewById(R.id.remainDaysTxtView);

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

    public void changeDetails(){

        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String dtStart = newDate;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd-MM-yyyy"), formattedDate, newDate);
                System.out.println("dateDifference: " + dateDifference);
                remainDaysTxtView.setText( String.valueOf(dateDifference) + " Days");
                examTView.setText(newDate);
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

//    //Back Button to Main Page
//    @Override
//    public boolean onSupportNavigateUp(){
//        Intent intent = new Intent(this, SettingsFragment.class);
//        startActivity(intent);
//        return true;
//    }


}