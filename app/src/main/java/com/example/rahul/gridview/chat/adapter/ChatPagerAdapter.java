package com.example.rahul.gridview.chat.adapter;

import android.os.Bundle;
import android.os.Parcelable;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rahul.gridview.chat.fragment.ChatDisplayFragment;
import com.example.rahul.gridview.chat.fragment.UserListFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IN-RB on 06-08-2018.
 */

public class ChatPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public final static String RTO_LIST = "Chat";
    public final static String NONRTO_LIST = "Users";
   // ServiceMainEntity mMasterData;

    public ChatPagerAdapter(FragmentManager fm ) {
        super(fm);
        //mMasterData = MasterData;
    }


    @Override
    public Fragment getItem(int position) {

     //   return mFragmentList.get(position);
        switch (position) {
            case 0:
                // RTO fragment
                ChatDisplayFragment Qfragment = new ChatDisplayFragment();
//                Bundle bundle = new Bundle();
//                if (mMasterData == null) {
//                    bundle.putParcelableArrayList(RTO_LIST, null);
//                } else {
//                  //  bundle.putParcelableArrayList(RTO_LIST, (ArrayList<? extends Parcelable>) mMasterData.getRTO());
//                    bundle.putParcelableArrayList(RTO_LIST, (ArrayList<? extends Parcelable>) mMasterData.getRTO());
//                }
//                Qfragment.setArguments(bundle);
                return Qfragment;
            case 1:
                // Non RTO fragment
                UserListFragment Afragment = new UserListFragment();
//                Bundle Abundle = new Bundle();
//                if (mMasterData == null) {
//                    Abundle.putParcelableArrayList(NONRTO_LIST, null);
//                } else {
//                    Abundle.putParcelableArrayList(NONRTO_LIST, (ArrayList<? extends Parcelable>) mMasterData.getNONRTO());
//                }
//                Afragment.setArguments(Abundle);
                return Afragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


}
