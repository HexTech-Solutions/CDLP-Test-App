package com.hextech.cdlpreptest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestResultsActivity extends AppCompatActivity {

    TextView textViewResult;
    Button btnStartAgain;
    PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        textViewResult = findViewById(R.id.textviewResult);
        btnStartAgain = findViewById(R.id.btnStartAgain);
        pieChart = findViewById(R.id.pieChartResult);

        Bundle extras = getIntent().getExtras();
        int correctCount = extras.getInt("correct_count", 0);
        int wrongCount = extras.getInt("wrong_count", 0);
        Boolean passed = extras.getBoolean("passed", Boolean.FALSE);

        List<PieEntry> values = new ArrayList<>();
        values.add(new PieEntry(correctCount, "Correct"));
        values.add(new PieEntry(wrongCount, "Wrong"));

        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setDescription(null);

        PieDataSet pieDataSet = new PieDataSet(values, "");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setValueTextSize(18f);
        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.animateXY(1400, 1400);

        if(passed){
            textViewResult.setText(getResources().getString(R.string.pass_message));
        }else{
            textViewResult.setText(getResources().getString(R.string.fail_message));
        }

        btnStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
