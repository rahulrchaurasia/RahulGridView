package com.example.rahul.gridview.Fragment;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.rahul.gridview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class free_sample_detail_fragment extends Fragment {

    private FragmentActivity myContext;
    public free_sample_detail_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);       // For menu only and particular frag
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.free_sample_detail_fragment, container, false);
        View view  = inflater.inflate(R.layout.free_sample_detail_fragment,container,false);

        return view;
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//       // super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.global,menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
               // getActivity().onBackPressed();

              //  getActivity().finish();

                //FragmentManager fm = getFragmentManager();
                FragmentManager fm = myContext.getSupportFragmentManager();
                if (fm.getBackStackEntryCount()>0){
                    //Log.d("Bottom Tab", "popping backstack");
                    fm.popBackStack();
                } else {
                    //Log.d("Bottom Tab", "nothing can pop");
                    getActivity().onBackPressed();
                }
                return true;
//            default:
//                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }
}
