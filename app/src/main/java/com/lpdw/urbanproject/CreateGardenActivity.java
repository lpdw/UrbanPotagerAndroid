package com.lpdw.urbanproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yassin on 03/05/16.
 */
public class CreateGardenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_garden);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.add_garden);
        actionBar.setTitle(title);


    }
}
