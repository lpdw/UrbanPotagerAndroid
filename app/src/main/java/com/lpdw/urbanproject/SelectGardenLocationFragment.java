package com.lpdw.urbanproject;

import android.*;
import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by yassin on 03/05/16.
 */
public class SelectGardenLocationFragment extends Fragment implements OnMapReadyCallback {


    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    GoogleMap map;
    Menu menu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /*    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_select_garden_location);

                        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                        String title = getResources().getString(R.string.select_garden_location);
                        actionBar.setTitle(title);

                        //MapView mapView = findViewById(R.id.select_garden_location_map);
                    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentContainer = inflater.inflate(R.layout.activity_select_garden_location, container, false);

        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.select_garden_location_map);
        setHasOptionsMenu(true);

        mapFragment.getMapAsync((OnMapReadyCallback) this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Where are your garden ?");
        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();

        return fragmentContainer;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        this.map = map;

        if (ActivityCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getActivity(), new String[]{ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            this.map.setMyLocationEnabled(true);
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location myLocation = locationManager.getLastKnownLocation(provider);

            if (myLocation != null) {

                double latitude = myLocation.getLatitude();
                double longitude = myLocation.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);

                this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }

            final Menu currentMenu = menu;

            this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    map.clear();
                    map.addMarker(new MarkerOptions().position(latLng));
                }
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SelectGardenLocation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.lpdw.urbanproject/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SelectGardenLocation Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.lpdw.urbanproject/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.select_garden_location, menu);
    }
}
