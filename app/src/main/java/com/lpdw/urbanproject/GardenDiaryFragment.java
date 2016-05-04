package com.lpdw.urbanproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by OBYON on 04/05/16.
 */
public class GardenDiaryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragContainer = inflater.inflate(R.layout.fragment_garden_diary, container, false);
        return fragContainer;
    }
}
