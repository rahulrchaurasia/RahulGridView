package com.example.rahul.gridview.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rahul on 25/05/2019.
 */
public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    public static String PROFILE_PATH = "profile_path";

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "ELITE_CUSTOMER";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setProfilePathe(String path) {

        editor.putString(PROFILE_PATH, path);
        editor.commit();
    }

    public String getProfilePath() {
        return pref.getString(PROFILE_PATH, "");
    }


}
