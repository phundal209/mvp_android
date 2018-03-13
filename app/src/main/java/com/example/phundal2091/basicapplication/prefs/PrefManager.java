package com.example.phundal2091.basicapplication.prefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.phundal2091.basicapplication.ui.root.ContentView;

/**
 * Created by phundal on 3/12/18.
 */

public class PrefManager {
    public static final String LOCATION_KEY = "location";

    public SharedPreferences getPrefs(Context context) {
        return ((Activity)context).getPreferences(Context.MODE_PRIVATE);
    }

    public void writeStringToPrefs(Context context, String key, String value) {
        getPrefs(context).edit()
                .putString(key, value)
                .apply();
    }

    public String readStringFromPrefs(Context context, String key) {
        if (getPrefs(context).contains(key)) {
            return getPrefs(context).getString(key, "");
        }
        return "";
    }
}
