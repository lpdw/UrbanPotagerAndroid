package com.lpdw.urbanproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import retrofit2.Call;

/**
 * Created by yassin on 03/05/16.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Me me = Me.get();

        if (me.username != null || me.plainPassword != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

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

                Me me = new Me(userNameTextView.getText().toString(), passwordTextView.getText().toString(), emailTextView.getText().toString());
                UrbanPotagerApi api = new UrbanPotagerApi();
                api.createUser(me, new UrbanPotagerApi.CallbackWrapper() {
                    @Override
                    public void onFailure(Call call, Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onResponse(Object object) {
                        Me me = (Me) object;

                        if (me != null){
                            me.save();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Log.i("Info", me.username);
                        }
                    }
                });

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
