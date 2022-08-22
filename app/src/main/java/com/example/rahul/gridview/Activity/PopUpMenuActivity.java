package com.example.rahul.gridview.Activity;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;

public class PopUpMenuActivity extends BaseActivity implements View.OnClickListener , PopupMenu.OnMenuItemClickListener {

    Button btnPopUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnPopUp = (Button)findViewById(R.id.btnPopUp);
        btnPopUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnPopUp)
        {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(PopUpMenuActivity.this, btnPopUp);
            popup.setOnMenuItemClickListener(PopUpMenuActivity.this);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
            popup.show();//showing popup menu
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(this,"ADD",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(this,"Edit",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
