package com.example.rahul.gridview.Utility;

import android.content.Context;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.rahul.gridview.R;

import java.util.List;

/**
 * Created by Rahul on 06/03/2019.
 */

public class CustomUISelector extends LinearLayout {



    LinearLayout llTextViewPool;
    Context mContext;
    IToogleClick iToogleClick;
    List<String> listString;

    public interface IToogleClick {
        void getClickedItem(String s);
    }

    public CustomUISelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.custom_selector, this, true);
        //  LayoutInflater.from(context).inflate(R.layout.custom_selector);

        initViews();
    }

    private void initViews() {
        llTextViewPool = findViewById(R.id.llTextViewPool);
    }

    public void showToggles(List<String> listString, IToogleClick iToogleClick) {
        this.iToogleClick = iToogleClick;
        this.listString = listString;
        if (listString != null && listString.size() > 0) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            for (int i = 0; i < listString.size(); i++) {
                TextView txtView;
                if (i == 0) {
                    txtView = new TextView(mContext);
                    txtView.setTag(listString.get(i));
                    txtView.setText(listString.get(i));
                    txtView.setPadding(32, 16, 16, 16);
                    txtView.setBackgroundResource(R.drawable.grey_curve_shape_left);
                    txtView.setOnClickListener(onClickListener);
                } else if (i == listString.size()-1) {
                    txtView = new TextView(mContext);
                    txtView.setTag(listString.get(i));
                    txtView.setText(listString.get(i));
                    txtView.setPadding(16, 16, 32, 16);
                    txtView.setBackgroundResource(R.drawable.grey_curve_shape_right);
                    txtView.setOnClickListener(onClickListener);
                } else {
                    txtView = new TextView(mContext);
                    txtView.setTag(listString.get(i));
                    txtView.setText(listString.get(i));
                    txtView.setPadding(20, 16, 20, 16);
                    txtView.setOnClickListener(onClickListener);
                    txtView.setBackgroundResource(R.drawable.customeborder_grey);
                }
                txtView.setId(i);
                txtView.setTextColor(mContext.getResources().getColor(R.color.black));


                txtView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.text_medium));
                txtView.setTextAppearance(mContext,R.style.TextAppearance_AppCompat_Small);
                llTextViewPool.addView(txtView);

            }
        }
    }

    View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            iToogleClick.getClickedItem(view.getTag().toString());
            view.setBackgroundResource(R.drawable.black_curve_shape_right);

            setCustomBackgroundUI();

            if (view instanceof TextView) {
                TextView txtMain = (TextView)view;
                txtMain.setTextColor(mContext.getResources().getColor(R.color.white));
                if (txtMain.getId() == 0) {

                    txtMain.setBackgroundResource(R.drawable.black_curve_shape_left);


                } else if (txtMain.getId() == listString.size() - 1) {

                    txtMain.setBackgroundResource(R.drawable.black_curve_shape_right);
                } else {
                    txtMain.setBackgroundResource(R.drawable.customeborder_black);
                }

            }
        }

    };

   // public void setCustomBackgroundUI(Context context, View view)
    public void setCustomBackgroundUI()
    {
        final int childCount = llTextViewPool.getChildCount();

        for (int i = 0; i < childCount; i++) {

            View element = llTextViewPool.getChildAt(i);

            // EditText
            if (element instanceof TextView) {
                TextView txtOther = (TextView)element;
                txtOther.setTextColor(mContext.getResources().getColor(R.color.black));

                if (i == 0) {

                    txtOther.setBackgroundResource(R.drawable.grey_curve_shape_left);


                } else if (i == listString.size()-1) {

                    txtOther.setBackgroundResource(R.drawable.grey_curve_shape_right);
                } else {

                    txtOther.setBackgroundResource(R.drawable.customeborder_grey);
                }

            }



        }
    }



//    public void setCustomUISelector(ICustomUIListener customUIListener)
//    {
//        this.customUIListener = customUIListener;
//    }


//    public void setCustomBackgroundUI(Context context, TextView txtApply, TextView txt1, TextView txt2, TextView txt3) {
//
//
//        txtApply.setTextColor(context.getResources().getColor(R.color.white));
//
//        txt1.setTextColor(context.getResources().getColor(R.color.black));
//        txt2.setTextColor(context.getResources().getColor(R.color.black));
//        txt3.setTextColor(context.getResources().getColor(R.color.black));
//
//        //region txtApply
//        if (txtApply.getText().equals("5Y")) {
//            txtApply.setBackgroundResource(R.drawable.black_curve_shape_left);
//        } else if (txtApply.getText().equals("20Y")) {
//            txtApply.setBackgroundResource(R.drawable.black_curve_shape_right);
//        } else {
//            txtApply.setBackgroundResource(R.drawable.customeborder_black);
//        }
//
//        //endregion
//
//        //region txt 1
//        if (txt1.getText().equals("5Y")) {
//            txt1.setBackgroundResource(R.drawable.grey_curve_shape_left);
//        } else if (txt1.getText().equals("20Y")) {
//            txt1.setBackgroundResource(R.drawable.grey_curve_shape_right);
//        } else {
//            txt1.setBackgroundResource(R.drawable.customeborder_grey);
//        }
//
//        //endregion
//
//        //region txt 2
//        if (txt2.getText().equals("5Y")) {
//            txt2.setBackgroundResource(R.drawable.grey_curve_shape_left);
//        } else if (txt2.getText().equals("20Y")) {
//            txt2.setBackgroundResource(R.drawable.grey_curve_shape_right);
//        } else {
//            txt2.setBackgroundResource(R.drawable.customeborder_grey);
//        }
//        //endregion
//
//        //region txt 3
//        if (txt3.getText().equals("5Y")) {
//            txt3.setBackgroundResource(R.drawable.grey_curve_shape_left);
//        } else if (txt3.getText().equals("20Y")) {
//            txt3.setBackgroundResource(R.drawable.grey_curve_shape_right);
//        } else {
//            txt3.setBackgroundResource(R.drawable.customeborder_grey);
//        }
//        //endregion
//
//
//        if (customUIListener != null) {
//            List<String> list = new ArrayList<>();
//
//            customUIListener.onCustomLayoutClick(list);
//        }
//
//
//    }
//

    public interface ICustomUIListener {

        void onCustomLayoutClick(List<String> list);
    }


}



