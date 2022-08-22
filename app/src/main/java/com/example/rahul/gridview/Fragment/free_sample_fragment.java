package com.example.rahul.gridview.Fragment;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.VivzAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link free_sample_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class free_sample_fragment extends Fragment implements VivzAdapter.AdapterCallback {

    GridView myGrid;
    int totalSample = 0;
    View root;
    TextView free_sample_text;
    private FragmentActivity myContext;
    // private OnFragmentInteractionListener mListener;

    public free_sample_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            totalSample = getArguments().getInt("Sample", 0);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //  return inflater.inflate(R.layout.free_sample_fragment, container, false);
        View root = inflater.inflate(R.layout.free_sample_fragment, container, false);
        try {
            free_sample_text = (TextView) root.findViewById(R.id.free_sample_text1);

            String strtxt = "Total Count: " + String.valueOf(totalSample);
            free_sample_text.setText(String.valueOf(strtxt));

            myGrid = (GridView) root.findViewById(R.id.gridView);
            myGrid.setAdapter(new VivzAdapter(getActivity(), this, totalSample, free_sample_fragment.this));    // use this for passing the object of current
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        txtHeader = (TextView) view.findViewById(R.id.header_text);


        return root;
    }


    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.global, menu);
////        showGlobalContextActionBar();
//        super.onCreateOptionsMenu(menu, inflater);
//       // inflater.inflate(R.me);
//    }

//    private void showGlobalContextActionBar() {
//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setDisplayShowCustomEnabled(false);
//        actionBar.setTitle("Free Samples");
//    }
    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    @Override
    public void onMethodCallback(String strTyp) {

        Toast.makeText(getActivity(), "Button Clicked " + strTyp, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
//                // if this doesn't work as desired, another possibility is to call `finish()` here.
//                getActivity().onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }

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

        }
        return super.onOptionsItemSelected(item);

    }

}






