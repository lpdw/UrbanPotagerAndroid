package com.lpdw.urbanproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 03/05/16.
 */
public class CreateGardenFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragContainer = inflater.inflate(R.layout.fragment_garden_create, container, false);

        return fragContainer;
    }
}
