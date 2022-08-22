package com.example.rahul.gridview.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.rahul.gridview.R;

import java.util.Random;

public class BroadCastActivity extends BaseActivity {

    private Context mContext;
    private Random mRandom = new Random();

    RelativeLayout mRelativeLayout;
    private Button mButtonSend;
    private TextView mTextView;

//    private BroadcastReceiver mRandomNumberReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // Get the received random number
//            int receivedNumber = intent.getIntExtra("RandomNumber",-1);
//
//            // Display a notification that the broadcast received
//            Toast.makeText(context,"Received : " + receivedNumber, Toast.LENGTH_SHORT).show();
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // Change the action bar color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FFFF00BF"))
        );

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mTextView = (TextView) findViewById(R.id.tv);

        // Set a click listener for send button
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Generate a random number
                int randomNumber = mRandom.nextInt(500);

                // Initialize a new intent instance
                Intent intent = new Intent("BROADCAST_RANDOM_NUMBER");
                // Put the random number to intent to broadcast it
                intent.putExtra("RandomNumber",String.valueOf(randomNumber));

                /*
                    public boolean sendBroadcast (Intent intent)
                        Broadcast the given intent to all interested BroadcastReceivers. This call
                        is asynchronous; it returns immediately, and you will continue executing
                        while the receivers are run.

                    Parameters
                        intent : The Intent to broadcast; all receivers matching this Intent
                            will receive the broadcast.
                */

                // Send the broadcast
                LocalBroadcastManager.getInstance(BroadCastActivity.this).sendBroadcast(intent);

                // Update the TextView with random number
                mTextView.setText("Random number generated : "
                        + String.valueOf(randomNumber)
                        + "\nApp also broadcast it."
                );
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
