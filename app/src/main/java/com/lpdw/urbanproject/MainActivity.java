package com.lpdw.urbanproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.lpdw.urbanproject.Api.Response;
import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById((R.id.toolbar));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById((R.id.drawer_layout));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, (R.string.navigation_drawer_open), (R.string.navigation_drawer_close));
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById((R.id.nav_view));
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);

        Me me = Me.get();

        ((TextView) navHeader.findViewById(R.id.menu_name)).setText(me.username);
        ((TextView) navHeader.findViewById(R.id.menu_email)).setText(me.email);

        getMyGardens();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new NewGardenFragment()).commit();
    }

    private void getMyGardens(){
        UrbanPotagerApi api = new UrbanPotagerApi();
        api.myGardens(new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onResponse(Object object) {
                MyGardens myGardens = new MyGardens((Garden[])object);
                if (myGardens != null) {
                    Log.d("MY GARDENS", myGardens.gardens[0].description);
                    myGardens.save();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
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

        if (id == R.id.nav_disconnect) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.disconnect)
                    .setMessage(R.string.disconnect_dialog)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Me.get().disconnect();
                            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                            startActivity(intent);
                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        }
        else if (id == R.id.nav_settings) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SettingsActivity()).commit();
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
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

    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.toolbar_settings){
//            Log.d("testest", "SETTINGS TOOLBAR");
//        }
    }
}
