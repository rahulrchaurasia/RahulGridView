package com.example.rahul.gridview.javaScript;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.rahul.gridview.R;

import java.io.File;

public class ViewImageActivity extends AppCompatActivity {

    // Can get From Passing File Path

    ImageView ivUser;
    File file1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ivUser = findViewById(R.id.ivUser);
        //  File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Demo-App" + File.separator + "profile");
        if (getIntent().getExtras() != null) {

            file1 = new File(getIntent().getExtras().getString("PATH", ""));
            if (file1.exists()) {

                //Toast.makeText(this,"file"+ file1.getName(),Toast.LENGTH_SHORT).show();
                Glide.with(this)
                        .asBitmap()
                        .load(file1.getPath())
                        .skipMemoryCache(true)
                        .into(ivUser);
            } else {

                Toast.makeText(this, "No File Found", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
