package com.example.rahul.gridview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.rahul.gridview.Activity.freeSample_container;


public class MainActivity extends AppCompatActivity {

    String strID = "";
    String strCountryName = "";
    EditText editTotalSample;
    TextView numOfFreeSample;
    Button btnApply;
    LinearLayout freeSampleView ;
    String StrNo_Sample_avalb;
    int freeSample = 0;
    String strSample_label, strClaim_label;
    TextView txtmxg;

    private VivzAdapter vivzAdapter;
   ActionBar actionBar ;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        actionBar = getActionBar();
//
//        actionBar.setTitle("Free Samples");

//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
         actionBar.setTitle("Eligible Prod");
        editTotalSample = (EditText) findViewById(R.id.editText);
        btnApply = (Button) findViewById(R.id.btnApply);

        freeSampleView = (LinearLayout) findViewById(R.id.free_sample_view);
        txtmxg         = (TextView)findViewById(R.id.free_sample_text) ;
        numOfFreeSample = (TextView) findViewById(R.id.num_of_freeSample);

        if (flag) {
            strSample_label = getResources().getString(R.string.remove_giftcard_text);
            flag=false;
        }else {
            strSample_label = getResources().getString(R.string.apply_giftcard_text);
            flag = true;

        }
        strClaim_label =  getResources().getString(R.string.free_sample_confirm);


        SpannableString text= new SpannableString(strSample_label);
        text.setSpan(new RelativeSizeSpan(1.5f), text.length()-12, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

       // text.setSpan(new RelativeSizeSpan(1.3f), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(Color.RED),text.length()-12, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtmxg.setText(text,TextView.BufferType.SPANNABLE);



        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    strSample_label = getResources().getString(R.string.remove_giftcard_text);
                    SpannableString text= new SpannableString(strSample_label);
                    text.setSpan(new RelativeSizeSpan(1.5f), 0,text.length()-12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    // text.setSpan(new RelativeSizeSpan(1.3f), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setSpan(new ForegroundColorSpan(Color.RED),0,text.length()-12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtmxg.setText(text,TextView.BufferType.SPANNABLE);

                    flag=false;
                }else {
                    strSample_label = getResources().getString(R.string.apply_giftcard_text);
                    SpannableString text= new SpannableString(strSample_label);
                    text.setSpan(new RelativeSizeSpan(1.5f), 0,text.length()-12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    // text.setSpan(new RelativeSizeSpan(1.3f), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setSpan(new ForegroundColorSpan(Color.RED),0,text.length()-12,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtmxg.setText(text,TextView.BufferType.SPANNABLE);

                    flag = true;

                }
                 freeSample =  Integer.parseInt(editTotalSample.getText().toString());
                if(freeSample <= 5  && freeSample >0 )
                {
                    freeSampleView.setVisibility(View.VISIBLE);
                    String strfreeSample = " "+ freeSample + " free Sample ";
                    numOfFreeSample.setText(strfreeSample);
                }
                else {
                    freeSampleView.setVisibility(View.GONE);
                }



            }
        });

        freeSampleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, freeSample_container.class);
                intent.putExtra("Sample",freeSample);
                startActivity(intent);
            }
        });




        // vivzAdapter.setCustomButtonListener(this);


      // Note : Imp here we cast the interface obj to activity obj


        // Set an item click listener for GridView widget

//        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            //    Toast.makeText(getApplicationContext(),"GridView item Clicked " + position, Toast.LENGTH_LONG ).show();
//          //     Button BtnAddItem;
//                try {
//
////                    BtnAddItem = (Button) view.findViewById(R.id.btnAdd);
////                    BtnAddItem.setText("Remove");
////
////
//
//                }
//
//
//                catch (Exception ex)
//                {
//                    Log.d("RAHUL",ex.getMessage().toString());
//                }
//
//              //  Toast.makeText(getApplicationContext(),"GridView item Clicked: "+strID +"  "+ strCountryName +"\nAt " + position,Toast.LENGTH_LONG ).show();
//
//            }
//        });
    }

     // Interface Req Method
//    @Override
//    public void onButtonClickListner() {
//
//        try {
////            Toast.makeText(MainActivity.this, "Button click position " + position + " Value" + value,
////                    Toast.LENGTH_SHORT).show();
//            Toast.makeText(MainActivity.this, "Button click position ", Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception ex)
//        {
//            Log.d("RAHUL" ,ex.getMessage());
//        }
//    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
               // onBackPressed();
                //finish();

                 NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }
}
