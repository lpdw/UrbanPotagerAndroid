package com.lpdw.urbanproject;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by mangubu on 04/05/16.
 */

public class SettingsFragment extends PreferenceFragmentCompat
{
    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        android.support.v7.preference.PreferenceManager preferenceManager = getPreferenceManager();
        if (preferenceManager.getSharedPreferences().getBoolean("pref_sync", true)){
            // Your switch is on
        } else {
            // Your switch is off
        }
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preference);
        android.support.v7.preference.PreferenceManager preferenceManager = getPreferenceManager();
        if (preferenceManager.getSharedPreferences().getBoolean("pref_sync", true)){
            // Your switch is on
        } else {
            // Your switch is off
        }
    }
}