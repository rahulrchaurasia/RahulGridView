package com.example.rahul.gridview.viewPagerCircular;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class CircleViewPageActivity extends BaseActivity {

//    ViewPager viewpager;
//    PagerAdapter adapter;
//    int[] img;
//
//    private static int currentPage = 0;
//    private static int NUM_PAGES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        img = new int[]{R.drawable.empty_cart, R.drawable.contact,
//                R.drawable.map_romania, R.drawable.ic_drawer2,
//        };//select the image from res/drawable  folder
//
//        viewpager = (ViewPager) findViewById(R.id.pager);
//        adapter = new ViewPagerAdapter(CircleViewPageActivity.this,  img);
//        viewpager.setAdapter(adapter);
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(viewpager);
//        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                currentPage = position;
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//             //   Toast.makeText(getApplicationContext(), "context changed", Toast.LENGTH_SHORT).show();
//
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    int pageCount = img.length;
//                    if (currentPage == 0) {
//                        viewpager.setCurrentItem(pageCount - 1, false);
//                    } else if (currentPage == pageCount - 1) {
//                        viewpager.setCurrentItem(0, false);
//                    }
//                }
//            }
//        });
//        final Handler handler = new Handler();
//        final Runnable update = new Runnable() {
//            @Override
//            public void run() {
//                if (currentPage == NUM_PAGES) {
//                    currentPage = 0;
//                }
//                viewpager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                handler.post(update);
//            }
//        }, 1000, 1000);
    }

}


