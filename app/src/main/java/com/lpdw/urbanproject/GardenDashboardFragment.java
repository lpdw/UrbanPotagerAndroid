package com.lpdw.urbanproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lpdw.urbanproject.Api.DataResponse;
import com.lpdw.urbanproject.Api.UrbanPotagerApi;

import retrofit2.Call;

/**
 * Created by OBYON on 04/05/16.
 */
public class GardenDashboardFragment extends Fragment {
    final UrbanPotagerApi api = new UrbanPotagerApi();
    final String gardenSlug = "linkitone-enguerran";
    View fragContainer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragContainer = inflater.inflate(R.layout.fragment_garden_dashboard, container, false);

        getAirTemp();
        getHumidity();
        getLight();
        getWaterLvl();

        return fragContainer;
    }

    private void getAirTemp(){
        api.getMeasure(gardenSlug, "air-temperature", new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onResponse(Object object) {
                DataResponse response = (DataResponse) object;
                if (response != null && response.measures.length>0) {
                    setAirTemp(response.measures[0].value);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void setAirTemp(float value){
        TextView airTemp = (TextView)fragContainer.findViewById(R.id.dashboard_airTemp);
        airTemp.setText(String.valueOf((int)value)+"°");
    }

    private void getWaterLvl(){
        api.getMeasure(gardenSlug, "water-level", new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onResponse(Object object) {
                DataResponse response = (DataResponse) object;
                if (response != null && response.measures.length>0) {
                    setWaterLvl(response.measures[0].value);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void setWaterLvl(float value){
        TextView waterTv = (TextView)fragContainer.findViewById(R.id.dashboard_waterLvlTv);
        ProgressBar waterBar = (ProgressBar)fragContainer.findViewById(R.id.dashboard_waterLvlBar);
        waterTv.setText(String.valueOf((int)value)+"%");
        waterBar.setProgress((int)value);
    }

    private void getHumidity(){
        api.getMeasure(gardenSlug, "humidity-air", new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onResponse(Object object) {
                DataResponse response = (DataResponse) object;
                if (response != null && response.measures.length>0) {
                    setHumidity(response.measures[0].value);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void setHumidity(float value){
        TextView humidity = (TextView)fragContainer.findViewById(R.id.dashboard_humidity);
        humidity.setText(String.valueOf((int)value)+"%");
    }

    private void getLight(){
        api.getMeasure(gardenSlug, "daylight-level", new UrbanPotagerApi.CallbackWrapper() {
            @Override
            public void onResponse(Object object) {
                DataResponse response = (DataResponse) object;
                if (response != null && response.measures.length>0) {
                    setLight(response.measures[0].value);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void setLight(float value){
        TextView light = (TextView)fragContainer.findViewById(R.id.dashboard_light);
        if(value < 25){
            light.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_emoticon_sad, 0, 0, 0);
        } else if(value > 25 && value < 50){
            light.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_emoticon_neutral, 0, 0, 0);
        } else {
            light.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_emoticon_happy, 0, 0, 0);
        }
    }
}
