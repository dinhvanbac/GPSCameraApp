package com.example.b_dinh.mapappview03;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LocationManager manager;
    LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // LocationManagerオブジェクトを取得
        manager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        // 位置情報を変更した場合の処理を実装
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(MapsActivity.this, "Location Changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Toast.makeText(MapsActivity.this, "Status Changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String s) {
                Toast.makeText(MapsActivity.this, "Provider Enabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String s) {
                Toast.makeText(MapsActivity.this, "Provider Disabled", Toast.LENGTH_SHORT).show();
            }
        };

//        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        if (mMap != null) {
//            mMap.setMyLocationEnabled(true);
//            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
////            mMap.addMarker(new MarkerOptions().position(latlng).title("Skytree"));
////            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
////            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        }
//
//        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//                LatLng latLng = new LatLng(latitude, longitude);
//                mMap.addMarker(new MarkerOptions().position(latLng));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                mMap.animateCamera(CameraUpdateFactory.zoomTo(21));
//                Toast.makeText(MapsActivity.this,"Latitude:" + latitude + ", Longitude: " + longitude, Toast.LENGTH_LONG).show();
////                locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
//            }
//        });


        // check if GPS enabled

        RadioGroup rdoGroup = (RadioGroup) this.findViewById(R.id.rdoGroup);

        rdoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdo01:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        Toast.makeText(MapsActivity.this,"MAP_TYPE_SATELLITE", Toast.LENGTH_LONG).show();
//                    case R.id.rdo02:
//                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                    case R.id.rdo03:
//                        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                    case R.id.rdo04:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        Toast.makeText(MapsActivity.this,"MAP_TYPE_NORMAL", Toast.LENGTH_LONG).show();
//                    case R.id.rdo05:
//                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });


        //MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
//        MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.mapofme);
//
//        try{
//            GoogleMap map = fragment.getMap();
//            if (map != null) {
//                map.addMarker(new MarkerOptions().position(latlng).title("Skytree"));
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
//            }
//        } catch(Exception e){
//            Toast.makeText(this, "Error ... ", Toast.LENGTH_LONG).show();
//        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public void btnShowClicked(View view){

        String baseFolder;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            baseFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        }
        else {
            baseFolder = MapsActivity.this.getFilesDir().getAbsolutePath();
        }

        //String string = "hello world!";
//        File file = new File(baseFolder + File.separator + "test.kml");
//        file.getParentFile().mkdirs();
//        FileOutputStream fos = new FileOutputStream(file);

        File file = new File(Environment.getExternalStorageDirectory(), "http://dl.dropbox.com/u/32264286/Unggahan/31.kml");
        Intent map_intent = new Intent(Intent.ACTION_VIEW);
        map_intent.setClassName("com.google.earth", "com.google.earth.EarthActivity");
        map_intent.setDataAndType(Uri.fromFile(file), "application/vnd.google-earth.kml+xml");
        startActivity(map_intent);
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapofme))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        //mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Current Position"));
    }
}
