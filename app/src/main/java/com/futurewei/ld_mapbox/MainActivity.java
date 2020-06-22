package com.futurewei.ld_mapbox;

//hwlocation kit
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationAvailability;
import com.huawei.hms.location.LocationCallback;
import com.huawei.hms.location.LocationRequest;
import com.huawei.hms.location.LocationResult;
import com.huawei.hms.location.LocationServices;
import com.huawei.hms.location.LocationSettingsRequest;
import com.huawei.hms.location.LocationSettingsResponse;
import com.huawei.hms.location.LocationSettingsStatusCodes;
import com.huawei.hms.location.SettingsClient;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private static final long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private static final long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;

    //HW linda
    public static final String TAG = "LocationUpdatesCallback";
    LocationCallback mLocationCallback;

    LocationRequest mLocationRequest;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private SettingsClient settingsClient;

    private MapboxMap mapboxMap;
    //HW linda

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Mapbox access token is configured here. This needs to be called either in your application
// object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.access_token));

// This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                    //mapView.getLocationOnScreen();
// Map is set up and the style has loaded. Now you can add data or make other map adjustments.
                        //mapboxMap.getLocationComponent().forceLocationUpdate(location);
                    }
                    // this.locationgetLocationComponent().forceLocationUpdate(location);
                });

              //ld

                //ld
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments.
                // create fusedLocationProviderClient linda
                //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient();
                // create settingsClient
                //settingsClient = LocationServices.getSettingsClient(this);
                mLocationRequest = new LocationRequest();
                // Set the interval for location updates, in milliseconds.
                mLocationRequest.setInterval(10000);
                // set the priority of the request
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                try{
                    CameraPosition position = new CameraPosition.Builder()
                            .target(new LatLng(37.7749, -122.4194)) // Sets the new camera position
                            .zoom(17) // Sets the zoom
                            .bearing(0) // Rotate the camera
                            .tilt(0) // Set the camera tilt
                            .build(); // Creates a CameraPosition from the builder


                    mapboxMap.setCameraPosition(position);

                }catch(Exception e){
                    Log.d(TAG, "error changing location >> "+e.toString());
                }




//                if (null == mLocationCallback) {
//                    mLocationCallback = new LocationCallback() {
//                        @Override
//                        public void onLocationResult(LocationResult locationResult) {
//                            if (locationResult != null) {
//                                List<Location> locations = locationResult.getLocations();
//                                //linda hw
//
//                                if (!locations.isEmpty()) {
//                                    for (Location location : locations) {
//                                        Log.i(TAG,
//                                                "onLocationResult location[Longitude,Latitude,Accuracy]:" + location.getLongitude()
//                                                        + "," + location.getLatitude() + "," + location.getAccuracy());
//                                        location.setLatitude(37.7749);
//                                        location.setLongitude(-122.4194);
//                                        location.setAccuracy(200);
//
//                                        mapboxMap.getLocationComponent().forceLocationUpdate(location);
//
//
//                                    }
//                                    //locationEngine.getLastLocation();
//                                    //mapboxMap.getLocationComponent().forceLocationUpdate(location);
//                                    //linda: hw
//                                }
//                            }
//
//                        }
//                    };
//                }

                // void onMapReady(MapboxMap mapboxMap) {
              //  ld

            }
        });

/*
        //linda
        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.
        // create fusedLocationProviderClient linda
        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient();
        // create settingsClient
        // settingsClient = LocationServices.getSettingsClient(this);
        mLocationRequest = new LocationRequest();
        // Set the interval for location updates, in milliseconds.
        mLocationRequest.setInterval(10000);
        // set the priority of the request
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (null == mLocationCallback) {
            mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult != null) {
                        List<Location> locations = locationResult.getLocations();
                        //linda hw

                        if (!locations.isEmpty()) {
                            for (Location location : locations) {
                                Log.i(TAG,
                                        "onLocationResult location[Longitude,Latitude,Accuracy]:" + location.getLongitude()
                                                + "," + location.getLatitude() + "," + location.getAccuracy());
                                location.setLatitude(37.7749);
                                location.setLongitude(-122.4194);
                                location.setAccuracy(200);

                                mapboxMap.getLocationComponent().forceLocationUpdate(location);


                            }
                            //locationEngine.getLastLocation();
                            //mapboxMap.getLocationComponent().forceLocationUpdate(location);
                            //linda: hw
                        }
                    }

                }
            };
        }*/

        // void onMapReady(MapboxMap mapboxMap) {
    }
    //mapView.getMapAsync(new OnMapReadyCallback() );
    // mapView.getMapAsync(this);

    //linda


    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();

            mapView.onStart();
}

        @Override
        protected void onStop() {
            super.onStop();
            mapView.onStop();
        }

        @Override
        public void onPause() {
            super.onPause();
            mapView.onPause();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mapView.onSaveInstanceState(outState);
        }
    }



