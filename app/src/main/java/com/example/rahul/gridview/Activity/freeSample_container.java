package com.example.rahul.gridview.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rahul.gridview.Fragment.free_sample_fragment;
import com.example.rahul.gridview.R;

public class freeSample_container extends AppCompatActivity {

    int totalSample;
    ActionBar actionBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_sample_container);

        Intent intent = getIntent();
        totalSample = intent.getIntExtra("Sample",0);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Free SampleProd");

        if(findViewById(R.id.fragment_container)!=null){

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            free_sample_fragment freeSampleFragment = new free_sample_fragment();
            this.attachFragment(freeSampleFragment);

            FragmentManager fm = getSupportFragmentManager();
            fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if (getSupportFragmentManager().getBackStackEntryCount() == 0) finish();
//                    if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//                        actionFragmentAttached(getString(R.string.title_activity_wish_list));
//                    }

                    else{
                        Toast.makeText(freeSample_container.this, "Count " + getSupportFragmentManager().getBackStackEntryCount() , Toast.LENGTH_SHORT).show();
                    }
                }
            });

//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, firstFragment).addToBackStack(null).commit();

        }


    }


    private void attachFragment(Fragment fragment) {

        // Create an instance of ExampleFragment
        Bundle bundle = new Bundle();
        bundle.putInt("Sample",totalSample);


        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.add(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                //finish();
                // NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
