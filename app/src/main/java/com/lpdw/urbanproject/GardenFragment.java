package com.lpdw.urbanproject;

/**
 * Created by OBYON on 04/05/16.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class GardenFragment extends Fragment implements View.OnClickListener{

    android.support.v7.widget.Toolbar toolbar;
    ImageButton settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_garden, container, false);

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("GARDEN DASHBOARD"));
        tabLayout.addTab(tabLayout.newTab().setText("GARDEN JOURNAL"));
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    /*
        //Add settings button in toolbar
        toolbar = (android.support.v7.widget.Toolbar)((View) container.getParent().getParent()).findViewById(R.id.toolbar);
        settings = new ImageButton(getContext());
        settings.setId(123);
        android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        settings.setLayoutParams(params);
        settings.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_settings_white_24dp));
        settings.setBackground(null);
        settings.setPadding(0, 0, 15, 0);
        toolbar.addView(settings);
        ((ImageButton) toolbar.findViewById(123)).setOnClickListener(this);
        */
        setHasOptionsMenu(true);

        return inflatedView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard, menu);
    }

    @Override
    public void onClick(View v) {
        //Get settings button click to lauch settings fragment associated to the current garden
        if(v.getId() == 123){
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings_dashboard:
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    GardenDashboardFragment dashb = new GardenDashboardFragment();
                    return dashb;
                case 1:
                    GardenDiaryFragment diary = new GardenDiaryFragment();
                    return diary;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
