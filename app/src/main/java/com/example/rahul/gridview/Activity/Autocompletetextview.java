package com.example.rahul.gridview.Activity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;

public class Autocompletetextview extends AppCompatActivity {

    String[] language ={"C","C++","C1","C2","C3","C4","C55","C66","C12","C22","C32","C44","C551","C661"
            ,"Java",".NET","iPhone","Android","ASP.NET","PHP"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocompletetextview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);

        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);

        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setThreshold(1);//will start working from first character
      //  actv.setTextColor(Color.RED);

    }


}
