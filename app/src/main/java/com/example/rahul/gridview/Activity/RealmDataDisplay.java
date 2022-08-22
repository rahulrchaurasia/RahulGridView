package com.example.rahul.gridview.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.Adapter.PersonAdapter;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Person;

import java.util.List;

import io.realm.Realm;

public class RealmDataDisplay extends AppCompatActivity {

    private  Realm realm  ;
    RecyclerView recyclerPerson;


    List<Person> lstPerson ;
    PersonAdapter mAdapter;
    private SearchView.OnQueryTextListener queryTextListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_data_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        realm  = Realm.getDefaultInstance();

        initialize();
        lstPerson = getPersonList();
        mAdapter= new PersonAdapter(this,lstPerson);
        recyclerPerson.setAdapter(mAdapter);

    }

    private void initialize()
    {
        recyclerPerson = (RecyclerView)findViewById(R.id.rvPerson);
        recyclerPerson.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RealmDataDisplay.this);
        recyclerPerson.setLayoutManager(layoutManager);

    }

    private  List<Person> getPersonList()
    {
       List<Person> lstPersonListAll = realm.where(Person.class).findAll();

        return lstPersonListAll;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) RealmDataDisplay.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(RealmDataDisplay.this.getComponentName()));
        }

        if (searchView != null && lstPerson.size() > 0) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(RealmDataDisplay.this.getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {

                    if(newText.length()>0) {
                        mAdapter.filer(newText);
                    }else {
                        mAdapter.findAll(getPersonList());
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        return super.onCreateOptionsMenu(menu);

    }
}
