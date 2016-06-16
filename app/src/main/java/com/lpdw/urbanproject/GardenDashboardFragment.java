package com.lpdw.urbanproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import retrofit2.Call;

/**
 * Created by OBYON on 04/05/16.
 */
public class GardenDashboardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragContainer = inflater.inflate(R.layout.fragment_garden_dashboard, container, false);

        UrbanPotagerApi api = new UrbanPotagerApi();
        api.getMeasure("jardin1-user1", "air-temperature", new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onResponse(Object object) {
                Measure[] measures = (Measure[]) object;
                if (measures != null) {
                    Log.d("Requete measures OK -> test = ", String.valueOf(measures[0].value));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });

        return fragContainer;
    }
}
