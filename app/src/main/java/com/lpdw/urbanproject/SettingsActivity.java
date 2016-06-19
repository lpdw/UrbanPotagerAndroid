package com.lpdw.urbanproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.preference.PreferenceGroup;

/**
 * Created by mangubu on 03/05/16.
 */

public class SettingsActivity extends /*ActionBarActivity*/ AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new ParametersFragment())
                .commit();

        // use action bar here
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        getSupportFragmentManager().popBackStack();
        finish();
        return true;
    }

    public static class ParametersFragment extends PreferenceFragment {

        private AppCompatDelegate mDelegate;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            get();
            getHumidity();
            getLight();
            getWaterLvl();

            getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        }


        public static class MyPreferenceFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
        {

            @Override
            public void onCreate(final Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.preference);

            }

            @Override
            public void onResume() {
                super.onResume();
                getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            }

            @Override
            public void onPause() {
                super.onPause();
                getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            }

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                switch(key) {
                    //Boolean Type
                    case "pref_sync":
                    case "pref_sync2":
                    case "pref_sync3":
                    case "pref_sync4":
                    case "pref_sync5":
                    case "pref_sync6":
                    case "checkboxPref": {
                        Toast.makeText(getActivity().getApplicationContext(), "Changed key: " + key +" : "+sharedPreferences.getBoolean(key, false), Toast.LENGTH_LONG).show();
                        break;
                    }
                    //String Type
                    case "EclairageD_key":
                    case "EclairageFinal_key":
                    case "ArrosageD_key":
                    case "ArrosageFinal_key":
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Changed key: " + key +" : "+sharedPreferences.getString(key, ""), Toast.LENGTH_LONG).show();
                        break;
                    }
                    //Integer Type
                    case "seekbar1":
                    case "seekbar2":
                    case "seekbar3":
                    case "seekTemp_Key":
                    case "seekTemp_Key2":
                    case "seekEau_Key":
                    case "seekEau_Key2":{
                        Toast.makeText(getActivity().getApplicationContext(), "Changed key: " + key +" : "+sharedPreferences.getInt(key, 00), Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        }
    }

}
