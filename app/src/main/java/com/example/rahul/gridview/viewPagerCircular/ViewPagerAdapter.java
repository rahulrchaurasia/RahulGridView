package com.example.rahul.gridview.viewPagerCircular;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rahul.gridview.R;

/**
 * Created by Rahul on 30/01/2019.
 */

public class ViewPagerAdapter extends PagerAdapter {

    int[] image;
    LayoutInflater inflater;
    Context context;
    int position = 3;

    public ViewPagerAdapter(Activity mainActivity, int[] img) {
        this.context = mainActivity;
        this.image = img;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView data;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.item, container, false);
        data = (ImageView) itemview.findViewById(R.id.ima1);
        data.setImageResource(image[position]);

        //add item.xml to viewpager
        ((ViewPager) container).addView(itemview);
        return itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

//    @Override
//    public float getPageWidth(int position) {
//        return .20f;   //it is used for set page widht of view pager
//    }


}

