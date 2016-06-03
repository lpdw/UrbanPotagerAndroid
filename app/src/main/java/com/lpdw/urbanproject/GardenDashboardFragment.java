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

        ApiCallSender aps = new ApiCallSender(getActivity());
        aps.getData("test");

        //On récupère les données inscrites dans data_file
        while (getActivity().getSharedPreferences("data_file", getActivity().MODE_PRIVATE).getString("test", "no data") == "no data"){
            Log.d("TEST", "fuck");
        }
        Log.d("TEST", getActivity().getSharedPreferences("data_file", getActivity().MODE_PRIVATE).getString("test", "no data"));

        //aps.getGarden("jardin1-user1");
        //Log.d("TEST", getActivity().getSharedPreferences("data_file", getActivity().MODE_PRIVATE).getString("jardin1-user1", "no data"));

        return fragContainer;
    }
}
