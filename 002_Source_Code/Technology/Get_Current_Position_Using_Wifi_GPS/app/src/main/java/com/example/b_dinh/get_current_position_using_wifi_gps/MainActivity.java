package com.example.b_dinh.get_current_position_using_wifi_gps;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnGPS_Click(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String positionInfo = "";
        if (locationManager != null) {
            boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsIsEnabled) {
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F,(android.location.LocationListener) this);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                positionInfo += "Using GPS  Latitude: " + location.getLatitude();
                positionInfo += "  Longitude: " + location.getLongitude();
                Toast.makeText(MainActivity.this, positionInfo, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Please turn on GPS", Toast.LENGTH_LONG).show();
            }
        }
        //Toast.makeText(MainActivity.this, "GPS", Toast.LENGTH_LONG).show();
    }

    public void btnWifi_Click(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String positionInfo = "";
        if (locationManager != null) {
            boolean networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (networkIsEnabled) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                positionInfo += "Using Network Latitude: " + location.getLatitude();
                positionInfo += "  Longitude: " + location.getLongitude();
                Toast.makeText(MainActivity.this, positionInfo, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Please turn on Network", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btnCompare_Click(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        double latitudeGPS; // latitude
        double longitudeGPS; // longitude

        double latitudeNetwork; // latitude
        double longitudeNetwork; // longitude

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        latitudeGPS = location.getLatitude();
        longitudeGPS = location.getLongitude();

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        latitudeNetwork = location.getLatitude();
        longitudeNetwork = location.getLongitude();

        latitude = latitudeGPS - latitudeNetwork;
        longitude = longitudeGPS - longitudeNetwork;

        Toast.makeText(MainActivity.this, "Diff  Lat: " + latitude + "    Log: " +  longitude, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
