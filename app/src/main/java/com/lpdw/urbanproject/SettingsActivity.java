package com.lpdw.urbanproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.preference.PreferenceGroup;

/**
 * Created by mangubu on 03/05/16.
 */

public class SettingsActivity extends ActionBarActivity {

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

    public class ParametersFragment extends PreferenceFragment  {

        private AppCompatDelegate mDelegate;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        }

        public class MyPreferenceFragment extends PreferenceFragment implements
                SharedPreferences.OnSharedPreferenceChangeListener
        {

            @Override
            public void onCreate(final Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.preference);
                EditText editAirB = ((EditTextIntegerPreference) findPreference("editTemp_Key")).getEditText();
                editAirB.setFilters(new InputFilter[]{ new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        try {
                            int input = Integer.parseInt(dest.toString() + source.toString());
                            if (input<20 && input >-10)
                                return null;
                        } catch (NumberFormatException nfe) { }
                        return "";
                    }
                } });

                EditText editAirH = ((EditTextIntegerPreference) findPreference("editTemp_Key2")).getEditText();
                editAirH.setFilters(new InputFilter[]{ new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        try {
                            int input = Integer.parseInt(dest.toString() + source.toString());
                            if (input<60 && input >0)
                                return null;
                        } catch (NumberFormatException nfe) { }
                        return "";
                    }
                } });

                EditText editEauB = ((EditTextIntegerPreference) findPreference("editEau_Key")).getEditText();
                editEauB.setFilters(new InputFilter[]{ new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        try {
                            int input = Integer.parseInt(dest.toString() + source.toString());
                            if (input<20 && input >-10)
                                return null;
                        } catch (NumberFormatException nfe) { }
                        return "";
                    }
                } });

                EditText editEauE = ((EditTextIntegerPreference) findPreference("editEau_Key2")).getEditText();
                editEauE.setFilters(new InputFilter[]{ new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        try {
                            int input = Integer.parseInt(dest.toString() + source.toString());
                            if (input<60 && input >-0)
                                return null;
                        } catch (NumberFormatException nfe) { }
                        return "";
                    }
                } });
            }

            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                  String key) {
            }
        }
    }

}
