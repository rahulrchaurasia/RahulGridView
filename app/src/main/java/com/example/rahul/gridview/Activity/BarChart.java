package com.example.rahul.gridview.Activity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rahul.gridview.R;

public class BarChart extends AppCompatActivity {
    ActionBar actionBar ;
    LinearLayout linearChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        linearChart=(LinearLayout)findViewById(R.id.linearChart);
        drawChart(5);
    }

    public void drawChart(int count) {
        System.out.println(count);

        for (int k = 1; k <= count; k++) {
            View view = new View(this);
            view.setBackgroundColor(Color.parseColor("#ff6233"));
            view.setLayoutParams(new LinearLayout.LayoutParams(30, 400));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                    .getLayoutParams();
            params.setMargins(5, 0, 0, 0); // substitute parameters for left,top, right, bottom
            view.setLayoutParams(params);
            linearChart.addView(view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }


}
