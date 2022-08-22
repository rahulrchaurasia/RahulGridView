package com.example.rahul.gridview.RecyclerHeadFoot;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Generic;

import java.util.ArrayList;

public class FooterRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById (R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        FooterAdapter footerAdapter = new FooterAdapter(FooterRecyclerActivity.this, getListItems ());
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setAdapter (footerAdapter);
    }

    public ArrayList<Generic> getListItems () {
        ArrayList<Generic> generics = new ArrayList<Generic> ();
        for (int i = 0; i < 20; i++) {
            Generic item = new Generic();
            item.setName ("list item" + i +1);
            generics.add (item);
        }
        return generics;
    }

}
