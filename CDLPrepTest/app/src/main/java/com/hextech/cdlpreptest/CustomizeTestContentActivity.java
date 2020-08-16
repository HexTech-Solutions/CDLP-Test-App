package com.hextech.cdlpreptest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.hextech.cdlpreptest.util.DBHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CustomizeTestContentActivity extends AppCompatActivity {

    CheckBox cbAirBrakes, cbClassC, cbGenKnowledge, cbClassA;
    Button btnSave;
    SharedPreferences pref;
    String[] contentNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_test_content);

        pref = getApplicationContext().getSharedPreferences("cdlpref", Context.MODE_PRIVATE);

        cbAirBrakes = this.findViewById(R.id.cbContentAirBrakes);
        cbClassC = this.findViewById(R.id.cbContentClassC);
        cbGenKnowledge = this.findViewById(R.id.cbContentGenKnowledge);
        cbClassA = this.findViewById(R.id.cbContentClassA);
        btnSave = this.findViewById(R.id.btnSaveContent);

        contentNames = getResources().getStringArray(R.array.learn_areas);
        cbAirBrakes.setText(contentNames[0]);
        cbClassC.setText(contentNames[1]);
        cbGenKnowledge.setText(contentNames[2]);
        cbClassA.setText(contentNames[3]);

        cbAirBrakes.setChecked(pref.getBoolean("content_air_brakes", Boolean.TRUE));
        cbClassC.setChecked(pref.getBoolean("content_class_c", Boolean.TRUE));
        cbGenKnowledge.setChecked(pref.getBoolean("content_gen_knowledge", Boolean.TRUE));
        cbClassA.setChecked(pref.getBoolean("content_class_a", Boolean.TRUE));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContent();
            }
        });
    }

    /**
     * Constructs the where clause and saves the values in Shared Preferences
     */
    private void saveContent(){
        String whereClause = "";
        if(cbAirBrakes.isChecked() && cbClassC.isChecked() && cbGenKnowledge.isChecked() && cbClassA.isChecked()){
            whereClause = null;
        }else if(!cbAirBrakes.isChecked() && !cbClassC.isChecked() && !cbGenKnowledge.isChecked() && !cbClassA.isChecked()){
            Toast.makeText(this, "Please select at least one area", Toast.LENGTH_SHORT).show();
            return;
        }else{
            whereClause = DBHelper.DatabaseTableColumns.CATEGORY.toString() + " IN (";
            if(cbAirBrakes.isChecked()){
                whereClause += "\"Air Brakes\",";
            }
            if(cbClassC.isChecked()){
                whereClause += "\"Class C\",";
            }
            if(cbGenKnowledge.isChecked()){
                whereClause += "\"General Knowledge\",";
            }
            if(cbClassA.isChecked()){
                whereClause += "\"Class A\",";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 1) + ")";
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("content_air_brakes", cbAirBrakes.isChecked());
        editor.putBoolean("content_class_c", cbClassC.isChecked());
        editor.putBoolean("content_gen_knowledge", cbGenKnowledge.isChecked());
        editor.putBoolean("content_class_a", cbClassA.isChecked());
        editor.putString("where_clause", whereClause);
        editor.apply();
        finish();
    }
}
