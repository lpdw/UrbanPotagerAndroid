package com.lpdw.urbanproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by yassin on 15/06/16.
 */
public class Me extends User {

    public boolean refreshed;

    public Me(String username, String plainPassword, String email) {
        super(username, plainPassword, email);
    }

    public Me(String username, String plainPassword) {
        super(username, plainPassword);
    }

    public Me() {

    }

    public void unsetRefreshed(){
        if(this.refreshed){
            this.refreshed = false;
            save();
            Log.d("UNSET REFRESHED", String.valueOf(refreshed));
        }
    }

    public void save(){
        Context context = UrbanPotagerApplication.getAppContext();

        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        prefsEditor.putString("Me", json);
        prefsEditor.commit();
    }

    public static Me get(){
        Context context = UrbanPotagerApplication.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String defaultMe = gson.toJson(new Me());
        String json = sharedPreferences.getString("Me", defaultMe);
        Me me = gson.fromJson(json, Me.class);

        return me;
    }
}
