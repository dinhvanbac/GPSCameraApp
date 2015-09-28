package com.android.b_dinh.gpscameraapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSCameraApp extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LatLng latLng;
    MarkerOptions markerOptions;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpscamera_app);
        setUpMapIfNeeded();

        //　位置ボタンを有効する
        mMap.setMyLocationEnabled(true);

        //　マップのパディングを設定する
        mMap.setPadding(0, 180, -50, 0);

        //　メニューボタンを設定する
        ImageButton btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });
        btnMenu.setOnCreateContextMenuListener(this);

        //　検索ボタンのイベントを設定する
        ImageButton btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtAddress = (EditText) findViewById(R.id.txtAddress);

                // 入力したアドレースを取得する
                String location = txtAddress.getText().toString();

                if (location != null && !location.equals("")) {
                    new GeocoderTask().execute(location);
                }
            }
        });

        //　現在位置に遷移する
//        getCurrentPosition();
//        latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuinfo) {
        super.onCreateContextMenu(menu, view, menuinfo);
        getMenuInflater().inflate(R.menu.menu_option_dialog, menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
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
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
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
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    public Location getCurrentPosition() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(gpsIsEnabled){
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location;
        } else if(networkIsEnabled){
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return location;
        }
        return null; // GPS and Network is Off
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAddMedia:
                Intent intent = new Intent(GPSCameraApp.this, AddMedia.class);
                startActivity(intent);
                break;
            case R.id.mnChangeLanguage:
                //Toast.makeText(this, R.string.change_language, Toast.LENGTH_LONG).show();
                final String[] items = {
                        getString(R.string.language_english),
                        getString(R.string.language_japanese),
                        getString(R.string.language_cancel)};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.language_title))
                        .setIcon(R.drawable.icon_language)
                        .setItems(items,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Configuration config = new Configuration();
                                        Locale locale;
                                        switch (i) {
                                            case 0:
                                                config.locale = Locale.getDefault();
                                                break;
                                            case 1:
                                                locale = new Locale("jp");
                                                config.locale = locale.JAPANESE;
                                                break;
                                            case 2:
                                                return;
                                            default:
                                                config.locale = Locale.JAPANESE;
                                                break;
                                        }
                                        getResources().updateConfiguration(config, null);
                                        startActivity(new Intent(getBaseContext(), GPSCameraApp.class));
                                    }
                                }).show();
                break;
            case R.id.mnImportKML:
                Toast.makeText(this, R.string.menu_import_kml, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnExportKML:
                Toast.makeText(this, R.string.menu_export_kml, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnAccountInfo:
                Intent intentAccountInfo = new Intent(GPSCameraApp.this, AccountInfo.class);
                startActivity(intentAccountInfo);
                break;
            case R.id.mnLocationHistory:
                Toast.makeText(this, R.string.menu_location_history, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnSetupMapView:
                Toast.makeText(this, R.string.menu_setup_map_view, Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            mMap.clear();

            // Adding Markers on Google Map for each matching address
            for (int i = 0; i < addresses.size(); i++) {

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addressText);

                mMap.addMarker(markerOptions);

                // Locate the first location
                if (i == 0) {
                    //mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                }
            }
        }
    }

}
