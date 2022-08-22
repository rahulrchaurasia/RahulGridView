package com.example.rahul.gridview.Activity;

import android.app.Dialog;
import android.os.Bundle;

import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rahul.gridview.R;

import java.util.ArrayList;
import java.util.List;

public class PopUpActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_up);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener

        // Spinner Drop down elements
        List<String>  categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        categories.add("Automobile1");
        categories.add("Business Services1");
        categories.add("Computers1");
        categories.add("Education1");
        categories.add("Personal1");
        categories.add("Travel1");

        categories.add("Automobile2");
        categories.add("Business Services2");
        categories.add("Computers2");
        categories.add("Education2");
        categories.add("Personal2");
        categories.add("Travel2");

        // Creating adapter for spinner
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setPrompt("Select List");
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

}
