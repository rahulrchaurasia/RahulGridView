package com.example.rahul.gridview.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.CustomUISelector;

import java.util.ArrayList;
import java.util.List;


public class NotifCountActivity extends BaseActivity {

    EditText etCount;
    Button btnSubmit;
    private String TAG = "BadgeERROR";
    ToggleButton txt5Y, txt10Y, txt15Y, txt20Y;
    CustomUISelector.ICustomUIListener customUIListener;
    CustomUISelector customUISelector;

    CustomUISelector cvToggle;
    List<String> mListToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_count);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mListToggle = new ArrayList<>();
        mListToggle.add("5Y");
        mListToggle.add("10Y");
        mListToggle.add("15Y");
        mListToggle.add("20Y");
      //

        etCount = (EditText) findViewById(R.id.etCount);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);




        cvToggle = findViewById(R.id.cvToggle);
        cvToggle.showToggles(mListToggle, new CustomUISelector.IToogleClick() {
            @Override
            public void getClickedItem(String s) {
                Toast.makeText(NotifCountActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        });




    }


}
