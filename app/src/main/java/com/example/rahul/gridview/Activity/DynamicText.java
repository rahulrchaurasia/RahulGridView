package com.example.rahul.gridview.Activity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;

public class DynamicText extends AppCompatActivity implements  View.OnClickListener {

    LinearLayout  layParentlayout;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        layParentlayout = (LinearLayout)findViewById(R.id.layParentlayout);

        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAdd)
        {
            addText();

        }

    }


    private void addText() {
        final int N = 3; // total number of textviews to add

       // final EditText rowEditView;

        //    final EditText[] myTextViews = new EditText[N]; // create an empty array;

        final LinearLayout[] mychildLayout = new LinearLayout[N];


        for (int i = 0; i < N; i++) {
            // create a new textview
            final LinearLayout childLayout = new LinearLayout(this);

            // set some properties of rowTextView or something
            childLayout.setOrientation(LinearLayout.HORIZONTAL);

            childLayout.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            childLayout.setWeightSum(3);

            for (int j = 0; j < N; j++) {

                final EditText rowEditView = new EditText(this);
               // rowEditView.setLayoutParams(new LinearLayoutCompat.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                p.weight = 1;
                rowEditView.setLayoutParams(p);
//            // set some properties of rowTextView or something
                rowEditView.setText(" #" + j);
                rowEditView.setBackgroundResource(R.drawable.roundcorner_red_edittext);

                childLayout.addView(rowEditView);
            }


            // add the textview to the linearlayout
            layParentlayout.addView(childLayout);

            // save a reference to the textview for later
            mychildLayout[i] = childLayout;

        }

//        for (int i = 0; i < N; i++) {
//            // create a new textview
//            final EditText rowEditView = new EditText(this);
//
//            // set some properties of rowTextView or something
//            rowEditView.setText(" #" + i);
//
//            // add the textview to the linearlayout
//            layParentlayout.addView(rowEditView);
//
//            // save a reference to the textview for later
//            myTextViews[i] = rowEditView;
//
//        }
    }


    private void addLay()
    {
        LinearLayout LL = new LinearLayout(this);
        LL.setBackgroundColor(Color.CYAN);
        LL.setOrientation(LinearLayout.VERTICAL);

        Toolbar.LayoutParams LLParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);

       // LL.setWeightSum(6f);
        LL.setLayoutParams(LLParams);


        ImageView ladder = new ImageView(this);
        ladder.setImageResource(R.drawable.ic_launcher);

        FrameLayout.LayoutParams ladderParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        ladder.setLayoutParams(ladderParams);

        FrameLayout ladderFL = new FrameLayout(this);
        LinearLayout.LayoutParams ladderFLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0);
        ladderFLParams.weight = 5f;
        ladderFL.setLayoutParams(ladderFLParams);
        ladderFL.setBackgroundColor(Color.GREEN);
        View dummyView = new View(this);

        LinearLayout.LayoutParams dummyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
        dummyParams.weight = 1f;
        dummyView.setLayoutParams(dummyParams);
        dummyView.setBackgroundColor(Color.RED);



        ladderFL.addView(ladder);
        LL.addView(ladderFL);
        LL.addView(dummyView);
//        RelativeLayout rl=((RelativeLayout) findViewById(R.id.screenRL));
//        rl.addView(LL);

        layParentlayout.addView(LL);
    }
}
