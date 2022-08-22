package com.example.rahul.gridview.RecyclerHeadFoot;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Generic;

import java.util.ArrayList;

public class HeaderFooterRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById (R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager (this);
        HeaderFooterAdapter adapter = new HeaderFooterAdapter(HeaderFooterRecyclerActivity.this, getListItems());
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setAdapter (adapter);
    }


    public ArrayList<Generic> getListItems () {
        ArrayList<Generic> generics = new ArrayList<Generic> ();
        for (int i = 0; i < 40; i++) {
            Generic item = new Generic();
            item.setName ("list item" + i);
            generics.add (item);
        }
        return generics;
    }
}
