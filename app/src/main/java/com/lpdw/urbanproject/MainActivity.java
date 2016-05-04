package com.lpdw.urbanproject;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById((R.id.toolbar));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById((R.id.drawer_layout));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, (R.string.navigation_drawer_open), (R.string.navigation_drawer_close));
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById((R.id.nav_view));
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);
        ((TextView) navHeader.findViewById(R.id.menu_name)).setText("PIERRE CYRIL");
        ((TextView) navHeader.findViewById(R.id.menu_email)).setText("pierreciryld@android.com");

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new NewGardenFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_garden1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GardenFragment()).commit();
        } else if (id == R.id.nav_garden2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GardenFragment()).commit();
        } else if (id == R.id.nav_add_garden) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CreateGardenFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
