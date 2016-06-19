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

import static com.lpdw.urbanproject.Api.UrbanPotagerApi.*;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        Me me = Me.get();

        if (me.username != null || me.plainPassword != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.sign_in);
        actionBar.setTitle(title);

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        TextView forgetPasswordButton = (TextView) findViewById(R.id.sign_in_to_forgotten_password);
        forgetPasswordButton.setOnClickListener(this);

        TextView signInToSignUp = (TextView) findViewById(R.id.sign_in_to_sign_up);
        signInToSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class targetClass = null;

        switch (v.getId()){
            case R.id.sign_in_button:
                //targetClass = MainActivity.class;
                TextView userNameTextView = (TextView) findViewById(R.id.sign_in_username);
                TextView passwordTextView = (TextView) findViewById(R.id.sign_in_password);

                final Me me = new Me(userNameTextView.getText().toString(), passwordTextView.getText().toString());
                UrbanPotagerApi api = new UrbanPotagerApi();

                api.signIn(me, new CallbackWrapper() {
                    @Override
                    public void onResponse(Object object) {
                        Token token = (Token) object;

                        if (token != null) {
                            me.token = token.token;
                            me.refreshToken = token.refresh_token;
                            me.save();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                break;
            case R.id.sign_in_to_sign_up:
                targetClass = SignUpActivity.class;
                break;
            case R.id.sign_in_to_forgotten_password:
                Log.d("TEST", "TEST");
                targetClass = ForgetPasswordActivity.class;
                break;
        }

        if(targetClass != null){
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        }
    }

}
