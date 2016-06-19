package com.lpdw.urbanproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by mangubu on 19/06/16.
 */
public class GardenConfigurations {

    public String name;
    public String description;
    public float lightTreshold;
    public int lightingStart;
    public int lightingEnd;
    public boolean isWateringActive;
    public int wateringStart;
    public int wateringEnd;

    public GardenConfigurations (String name, String description, float lightTreshold, int lightingStart, int lightingEnd, boolean isWateringActive, int wateringStart, int wateringEnd) {
        this.name = name;
        this.description = description;
        this.lightTreshold = lightTreshold;
        this.lightingStart = lightingStart;
        this.lightingEnd = lightingEnd;
        this.isWateringActive = isWateringActive;
        this.wateringStart = wateringStart;
        this.wateringEnd = wateringEnd;

    }

    public static GardenConfigurations get(){
        Context context = UrbanPotagerApplication.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String defaultGardenConfig = gson.toJson(new Me());
        String json = sharedPreferences.getString("GardenConfig", defaultGardenConfig);
        GardenConfigurations gardenConfigurations = gson.fromJson(json, GardenConfigurations.class);

        return gardenConfigurations;
    }
}
