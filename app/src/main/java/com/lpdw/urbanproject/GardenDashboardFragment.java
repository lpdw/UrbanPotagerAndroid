package com.lpdw.urbanproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;

/**
 * Created by OBYON on 04/05/16.
 */
public class GardenDashboardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragContainer = inflater.inflate(R.layout.fragment_garden_dashboard, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("token_file", getActivity().MODE_PRIVATE);
        String token = prefs.getString("token", "fail");

        Log.d("test", "token");
        Log.d("test", token);

        //API api = ServiceGenerator.createService(API.class, "Bearer " + token);
        //Call<Data> call = api.getData("test");
        //try{
        //    Data data = call.execute().body();
        //    Log.d("test description", data.description);
        //} catch (Exception e) {
        //    Log.d("FAIL", e.getStackTrace().toString());
        //}

        return fragContainer;
    }
}
