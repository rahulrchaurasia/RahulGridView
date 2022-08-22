package com.example.rahul.gridview.RecyclerHeadFoot;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

public class RecyclerMain extends BaseActivity implements View.OnClickListener {

    private Button btnBoth;
    private Button btnHeader;
    private Button btnFooter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_head_foot);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViews();

    }

    private void findViews() {
        btnBoth = (Button)findViewById( R.id.btn_both );
        btnHeader = (Button)findViewById( R.id.btn_header );
        btnFooter = (Button)findViewById( R.id.btn_footer );

        btnBoth.setOnClickListener(this);
        btnHeader.setOnClickListener(this);
        btnFooter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_both:
                Intent headerFooterIntent = new Intent(RecyclerMain.this,HeaderFooterRecyclerActivity.class);
                startActivity(headerFooterIntent);

                break;

            case R.id.btn_header:

                Intent headerIntent = new Intent(RecyclerMain.this,HeaderRecyclerActivity.class);
                startActivity(headerIntent);

                break;

            case R.id.btn_footer:

                Intent footerIntent = new Intent(RecyclerMain.this,FooterRecyclerActivity.class);
                startActivity(footerIntent);

                break;
        }
    }
}
