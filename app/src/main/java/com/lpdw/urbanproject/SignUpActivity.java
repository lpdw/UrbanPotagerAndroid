package com.lpdw.urbanproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lpdw.urbanproject.Api.UrbanPotagerApi;

/**
 * Created by yassin on 03/05/16.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.sign_up);
        actionBar.setTitle(title);

        Button signInButton = (Button) findViewById(R.id.sign_up_button);
        signInButton.setOnClickListener(this);

        TextView signUpToSignIn = (TextView) findViewById(R.id.sign_up_to_sign_in);
        signUpToSignIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Class targetClass = null;

        switch (v.getId()){
            case R.id.sign_up_button:
                TextView userNameTextView = (TextView) findViewById(R.id.sign_up_name);
                TextView emailTextView = (TextView) findViewById(R.id.sign_up_email);
                TextView passwordTextView = (TextView) findViewById(R.id.sign_up_password);

                User user = new User(userNameTextView.getText().toString(), emailTextView.getText().toString(), passwordTextView.getText().toString());
                UrbanPotagerApi api = new UrbanPotagerApi();
                api.createUser(user, new UrbanPotagerApi.CallbackCreateUser() {
                    @Override
                    public void onFailure(Throwable t) {

                    }

                    @Override
                    public void onResponse(User user) {
                        if (user != null){
                            Log.i("Info", user.username);
                        }
                    }
                });

                targetClass = MainActivity.class;
                break;
            case R.id.sign_up_to_sign_in:
                targetClass = SignInActivity.class;
                break;
        }

        if(targetClass != null){
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        }
    }
}
