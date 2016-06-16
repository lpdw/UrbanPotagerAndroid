package com.lpdw.urbanproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

/**
 * Created by OBYON on 16/06/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyGardens {
    Garden[] gardens;

    public MyGardens(Garden[] gardens){
        this.gardens = gardens;
    }
    public MyGardens(){}

    public void save(){
        Context context = UrbanPotagerApplication.getAppContext();

        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        prefsEditor.putString("myGardens", json);
        prefsEditor.commit();
    }

    public static MyGardens get(){
        Context context = UrbanPotagerApplication.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String defaultMyGarden = gson.toJson(new MyGardens());
        String json = sharedPreferences.getString("myGardens", defaultMyGarden);
        MyGardens myGardens = gson.fromJson(json, MyGardens.class);

        return myGardens;
    }

    //public myGardens deleteOneGarden(String gardenSlug){};
    //public myGardens updateOneGarden(String gardenSlug){};
}
