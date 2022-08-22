package com.example.rahul.gridview;

import android.content.Context;
import android.content.res.Resources;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.rahul.gridview.Fragment.free_sample_detail_fragment;
import com.example.rahul.gridview.Fragment.free_sample_fragment;

import java.util.ArrayList;

/**
 * Created by rahul on 27/7/16.
 */

// Note: Adapter can create as many as interace req. Depend on
    // requirement we have to implement particular interface for communication.
public class VivzAdapter extends BaseAdapter {


    ArrayList<Country> list;   // main list having all the data
    ArrayList<Integer> sample_list;

    private Context context;
    ViewHolder  holder;
    int prdId = 1000;
    private  int TotalSample;
    LayoutInflater inflater;
    private AdapterCallback mAdapterCallback;   // interface object Declaration;
    private free_sample_fragment fragment_parent;                  // Declare the fragment , used for parent frag refernce

    public interface AdapterCallback            // Interface creation
    {
        void onMethodCallback(String strTyp);
    }

    public VivzAdapter(Context context, AdapterCallback callback, int totalSample , free_sample_fragment fragment)
    {
         inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.TotalSample = totalSample;
        this.mAdapterCallback = callback ;  // Initializing the object
                                            // Note: we pass Activity/Fragment object to assign

        this.fragment_parent = fragment;

        sample_list = new ArrayList<Integer>(totalSample);
        this.context = context;
        list = new ArrayList<Country>();
        Resources res = context.getResources();
        String[] tempCountriesName = res.getStringArray(R.array.country_names);
        int[] countryImages = {R.drawable.axis_nb,R.drawable.empty_cart,R.drawable.ic_action_location_found,
                                R.drawable.ic_action_refresh,R.drawable.ic_action_search,R.drawable.ic_drawer1,
                                R.drawable.ic_drawer2,R.drawable.ic_launcher,R.drawable.share,R.drawable.ic_drawer,

                R.drawable.axis_nb,R.drawable.empty_cart,R.drawable.ic_action_location_found,
                R.drawable.ic_action_refresh,R.drawable.ic_action_search,R.drawable.ic_drawer1,
                R.drawable.ic_drawer2,R.drawable.ic_launcher,R.drawable.share,R.drawable.ic_drawer};

        for(int i = 0; i<20; i++)
        {
           // Country tempCountry = new Country(countryImages[i],tempCountriesName[i]);
            prdId = prdId + i;
            Country tempCountry = new Country(countryImages[i],context.getResources().getString(R.string.Add),prdId);
            list.add(tempCountry);
        }

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Adde below 2 method
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    @Override
    public View getView( int position, final View view, ViewGroup viewGroup) {

        View rowView = view;
           holder = null;
        if(rowView==null)
        {
            holder = new ViewHolder();

            rowView = inflater.inflate(R.layout.grid_row,viewGroup,false);
            holder.myCountry = (ImageView)rowView.findViewById(R.id.imageView);
            holder.myBtnAdd = (Button) rowView.findViewById(R.id.btnAdd);
            holder.myText  = (TextView)rowView.findViewById(R.id.txtDemo);

            holder.myBtnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button btnAdd  = (Button) v;
                    TextView txtDemo = (TextView) v;

                    //   Toast.makeText(context, "Edit button Clicked At ID " + Country_temp.prdID +" Text "+ Country_temp.myBtnAdd, Toast.LENGTH_SHORT).show();

                    // list.remove(position);
                    // Verify the Total free Sample allow to the user


                        if (btnAdd.getText().toString().equals(context.getResources().getString(R.string.Add))) {

                            mAdapterCallback.onMethodCallback("ADD");
                            btnAdd.setText(context.getResources().getString(R.string.Remove));
                            // btnAdd.setBackgroundColor(context.getResources().getColor(R.color.colorRemove));
                            btnAdd.setBackgroundResource(R.color.colorRemove);
                            txtDemo.setText("Clicked");

                        } else {
                            mAdapterCallback.onMethodCallback("REMOVE");
                            btnAdd.setText(context.getResources().getString(R.string.Add));
                            // btnAdd.setBackgroundColor(context.getResources().getColor(R.color.colorAdd));
                            btnAdd.setBackgroundResource(R.color.colorAdd);
                            txtDemo.setText("UnClicked");

                        }


                    }
                   // notifyDataSetChanged();



            });

            rowView.setTag(holder);
        }
        else {
            holder = (ViewHolder)rowView.getTag();
        }

        // Get the  item from ArrayList
        final Country Country_temp = list.get(position);  // current object

        holder.myCountry.setImageResource(Country_temp.imageId);

        holder.myCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                free_sample_detail_fragment detail_fragment = new free_sample_detail_fragment();

//                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container,detail_fragment).addToBackStack(null).commit();


                FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_container, detail_fragment)
                        .addToBackStack(null)
                        .commit();
                fm.executePendingTransactions();

                //  ((Activity) context).setTitle("Action Bar");

            }
        });

//        holder.myBtnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Button btnAdd  = (Button) v;
//                holder.myText.setText("Clicked");
//
//             //   Toast.makeText(context, "Edit button Clicked At ID " + Country_temp.prdID +" Text "+ Country_temp.myBtnAdd, Toast.LENGTH_SHORT).show();
//
//               // list.remove(position);
//                // Verify the Total free Sample allow to the user
//                if(canAddorRemoveSample(Country_temp,btnAdd.getText().toString())) {
//
//
//                    if (btnAdd.getText().toString().equals(context.getResources().getString(R.string.Add))) {
//
//                         mAdapterCallback.onMethodCallback("ADD");
//                        btnAdd.setText(context.getResources().getString(R.string.Remove));
//                        // btnAdd.setBackgroundColor(context.getResources().getColor(R.color.colorRemove));
//                        btnAdd.setBackgroundResource(R.color.colorRemove);
//
//
//                    } else {
//                        mAdapterCallback.onMethodCallback("REMOVE");
//                        btnAdd.setText(context.getResources().getString(R.string.Add));
//                        // btnAdd.setBackgroundColor(context.getResources().getColor(R.color.colorAdd));
//                        btnAdd.setBackgroundResource(R.color.colorAdd);
//                    }
//
//
//                }
//                notifyDataSetChanged();
//            }
//
//
//        });

        return rowView;
    }

    class ViewHolder{

        ImageView myCountry;
        Button myBtnAdd;
        TextView myText;

    }

    private boolean canAddorRemoveSample(Country Country_temp, String action_type)
    {
        boolean blnChk = false;
        try {
            // this will print the size of this list
            int temptotalSample = sample_list.size();


            String strAdd = context.getResources().getString(R.string.Add);
            if (strAdd.equalsIgnoreCase(action_type)) {
                if (TotalSample > temptotalSample) {
                    sample_list.add(Country_temp.prdID);
                    blnChk = true;
                }
            } else {

                sample_list.remove(Integer.valueOf(Country_temp.prdID) );
                blnChk = true;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Log.d("SAMPLE",ex.getMessage());
        }

        return  blnChk;
    }



}
