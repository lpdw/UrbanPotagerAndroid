package com.lpdw.urbanproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.lpdw.urbanproject.Api.UrbanPotagerApi.*;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.sign_in);
        actionBar.setTitle(title);

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        TextView signInToSignUp = (TextView) findViewById(R.id.sign_in_to_sign_up);
        signInToSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class targetClass = null;

        switch (v.getId()){
            case R.id.sign_in_button:
                targetClass = MainActivity.class;
                break;
            case R.id.sign_in_to_sign_up:
                targetClass = SignUpActivity.class;
                break;
        }

        if(targetClass != null){
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        }
    }

}
