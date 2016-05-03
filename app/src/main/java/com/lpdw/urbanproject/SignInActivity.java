package com.lpdw.urbanproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.SignIn);
        actionBar.setTitle(title);

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button){
            Intent intent = new Intent(this, NewGardenActivity.class);
            startActivity(intent);
        }
    }


}
