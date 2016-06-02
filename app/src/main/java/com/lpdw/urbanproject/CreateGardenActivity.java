package com.lpdw.urbanproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yassin on 03/05/16.
 */
public class CreateGardenActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.add_garden);
        actionBar.setTitle(title);

        TextView nextButton = (TextView) findViewById(R.id.create_garden_next);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class targetClass = null;

        switch (v.getId()){
            case R.id.create_garden_next:
                targetClass = SelectGardenLocationFragment.class;
                break;
        }

        if(targetClass != null){
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        }
    }
}
