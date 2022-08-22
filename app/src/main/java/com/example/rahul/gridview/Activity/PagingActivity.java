package com.example.rahul.gridview.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.Adapter.LeadAdapter;
import com.example.rahul.gridview.Adapter.MyLeadAdapter;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.LeadEntity;

import java.util.List;

import io.realm.Realm;

public class PagingActivity extends AppCompatActivity {

    TextView txtCode, txtCount;
    LinearLayout lvParent;
    RecyclerView recyclerLead;
    TextView mSearchText;
    List<LeadEntity> lsLeadEntity;
    MyLeadAdapter mAdapter;

    private SearchView.OnQueryTextListener queryTextListener;     // for Option Menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAdapter = new MyLeadAdapter(this, lsLeadEntity);
        recyclerLead.setAdapter(mAdapter);
        initialize();
    }

    private void initialize() {
        txtCode = (TextView) findViewById(R.id.txtCode);
        txtCount = (TextView) findViewById(R.id.txtCount);
        lvParent = (LinearLayout)findViewById(R.id.lvParent);

        recyclerLead = (RecyclerView) findViewById(R.id.rvLead);
        recyclerLead.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PagingActivity.this);
        recyclerLead.setLayoutManager(layoutManager);
        recyclerLead.setItemAnimator(new DefaultItemAnimator());

        //  recyclerLead.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

    }
}
