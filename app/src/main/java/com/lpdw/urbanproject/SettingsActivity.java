package com.lpdw.urbanproject;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

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

//        public boolean onOptionsItemSelected(MenuItem item) {
//            switch (item.getItemId()) {
//                case android.R.id.home:
//                    // app icon in action bar clicked; goto parent activity.
//                    this.finish();
//                    return true;
//                default:
//                    return super.onOptionsItemSelected(item);
//            }
//        }
    }

    public class ParametersFragment extends PreferenceFragment {

        private AppCompatDelegate mDelegate;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();


        }

        public class MyPreferenceFragment extends PreferenceFragment
        {
            @Override
            public void onCreate(final Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.preference);
                PreferenceManager preferenceManager = getPreferenceManager();
                if (preferenceManager.getSharedPreferences().getBoolean("pref_sync", true)){
                    // Your switch is on
                } else {
                    // Your switch is off
                }
            }
        }
    }

}
