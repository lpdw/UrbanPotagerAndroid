package com.lpdw.urbanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import retrofit2.Call;

/**
 * Created by OBYON on 19/06/16.
 */
public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String title = getResources().getString(R.string.forgotten_password);
        actionBar.setTitle(title);

        Button SendButton = (Button) findViewById(R.id.forget_password_send);
        EditText UsernameEV = (EditText) findViewById(R.id.forget_password_username);
        SendButton.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        EditText usernameEV = (EditText) findViewById(R.id.forget_password_username);
        String username = usernameEV.getText().toString();

        UrbanPotagerApi api = new UrbanPotagerApi();
        api.forgetPassword(username, new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onResponse(Object object) {
                String res = (String) object;
                TextView status = (TextView) findViewById(R.id.forget_password_status);
                switch (res){
                    case "ok": status.setText(R.string.password_reset_sent);
                        break;
                    case "unknown user": status.setText(R.string.password_reset_error);
                        break;
                    case "conflict": status.setText(R.string.password_reset_conflict);
                        break;
                    default: status.setText(R.string.error);
                        break;
                }
            }
        });
    }
}
