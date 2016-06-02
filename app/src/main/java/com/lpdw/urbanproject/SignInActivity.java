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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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

                //Loggin request
                Retrofit retro = new Retrofit.Builder()
                        .baseUrl("https://urbanpotager.labesse.me/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                API api = retro.create(API.class);

                //TODO: Get credentials from login form
                User user = new User("user1", "userpass");

                Call<Token> call = api.createUser(user);
                call.enqueue(new Callback<Token>() {
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        //Save token
                        SharedPreferences prefs = getSharedPreferences("token_file", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("token", response.body().token);
                        editor.commit();
                        Log.d("token", response.body().token);
                    }
                    @Override
                    public void onFailure(Call<Token> call, Throwable t) { Log.d("FAIL", t.getMessage());}
                });

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
