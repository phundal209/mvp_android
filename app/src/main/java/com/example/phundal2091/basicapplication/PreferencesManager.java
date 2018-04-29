package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by phundal2091 on 4/29/18.
 */

public class PreferencesManager {
    private SharedPreferences sharedPreferences;
    private Context context;
    public static final String PREF_NAME = "my_prefs";

    public PreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        this.context = context;
    }

    public SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }
}
