package com.lpdw.urbanproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 03/05/16.
 */
public class NewGardenFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragContainer = inflater.inflate(R.layout.fragment_garden_new, container, false);

        TextView newGardenButton = (TextView) fragContainer.findViewById(R.id.new_garden_button);
        newGardenButton.setOnClickListener(this);

        //Toolbar toolbar = (Toolbar) container.findViewById((R.id.toolbar));
        //toolbar.setTitle("Add a new garden");

        return fragContainer;
    }

    @Override
    public void onClick(View v) {
        Class targetClass = null;

        switch (v.getId()){
            case R.id.new_garden_button:
                targetClass = CreateGardenFragment.class;
                break;
        }

        if(targetClass != null){
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CreateGardenFragment()).commit();
        }
    }
}
