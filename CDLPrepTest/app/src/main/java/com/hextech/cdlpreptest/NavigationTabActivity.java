package com.hextech.cdlpreptest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.hextech.cdlpreptest.ui.main.SectionsPagerAdapter;
import com.hextech.cdlpreptest.util.DBHelper;

public class NavigationTabActivity extends AppCompatActivity {

    int topic = 1;
    int qCount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_tab);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //To initialize the database when the application starts
        DBHelper database = new DBHelper(getApplicationContext());
        SQLiteDatabase database1 = database.getWritableDatabase();
    }
}