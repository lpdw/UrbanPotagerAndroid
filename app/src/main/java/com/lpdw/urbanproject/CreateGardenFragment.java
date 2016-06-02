package com.lpdw.urbanproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 03/05/16.
 */
public class CreateGardenFragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View fragContainer = inflater.inflate(R.layout.fragment_garden_create, container, false);
        View fragmentContainer = inflater.inflate(R.layout.fragment_garden_create, container, false);

        //Toolbar toolbar = (Toolbar) container.findViewById((R.id.toolbar));
        //toolbar.setTitle("Create your garden");

        TextView nextButton = (TextView) fragmentContainer.findViewById(R.id.create_garden_next);
        nextButton.setOnClickListener(this);

        return fragmentContainer;
    }

    @Override
    public void onClick(View v) {
        Fragment targetFragment = null;

        switch (v.getId()){
            case R.id.create_garden_next:
                targetFragment = new SelectGardenLocationFragment();
                break;
        }

        if(targetFragment != null){
            //Intent intent = new Intent(this, targetClass);
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, targetFragment).commit();
        }
    }
}
