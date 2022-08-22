package com.example.rahul.gridview.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rahul.gridview.R;

public class Marshmellow_Permission extends AppCompatActivity {

    ActionBar actionBar ;
    Button btnDemo;
    private static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marshmellow__permission);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Permission");

        btnDemo 	= (Button) findViewById(R.id.btn_Demo);

        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (ContextCompat.checkSelfPermission(Marshmellow_Permission.this,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                            .checkSelfPermission(Marshmellow_Permission.this,
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {


                        if (ActivityCompat.shouldShowRequestPermissionRationale(Marshmellow_Permission.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                            //If the user has denied the permission previously your code will come to this block
                            //Here you can explain why you need this permission
                            //Explain here why you need this permission

                            Toast.makeText(Marshmellow_Permission.this,"Permission Required in Apps",Toast.LENGTH_LONG).show();
                        }


                        ActivityCompat.requestPermissions(Marshmellow_Permission.this,new String[]
                                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,  android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);


                    }
                    else {
                        Toast.makeText(Marshmellow_Permission.this,"Permission Accepted",Toast.LENGTH_LONG).show();

                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();

                }
            }
        });
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }

    }

}
