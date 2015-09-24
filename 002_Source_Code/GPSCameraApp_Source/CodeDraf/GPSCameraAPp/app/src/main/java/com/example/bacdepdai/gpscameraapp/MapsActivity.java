package com.example.bacdepdai.gpscameraapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    public static final int REQUEST_CODE_INPUT = 113;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        // Turn on button current position
        mMap.setMyLocationEnabled(true);
        mMap.setPadding(0, 180, -50, 0);

        ImageButton btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });
        btnMenu.setOnCreateContextMenuListener(this);


        LatLng currentPosition = new LatLng(35.6623, 139.778);
        mMap.addMarker(new MarkerOptions()
                .position(currentPosition)
                .title("Ngọc Trinh")
                .snippet("Quất!")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ham2)));


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuinfo) {
        super.onCreateContextMenu(menu, view, menuinfo);
        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAddPhoto:
                Intent intent = new Intent(MapsActivity.this, ModifyMedia.class);
                startActivity(intent);
                //Toast.makeText(this, R.string.add_photo, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnAddVideo:
                Toast.makeText(this, R.string.add_video, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnChangeLanguage:
                //Toast.makeText(this, R.string.change_language, Toast.LENGTH_LONG).show();
                final String[] items = {
                        getString(R.string.language_english),
                        getString(R.string.language_japanese),
                        getString(R.string.language_cancel)};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.language_title))
                        .setIcon(R.drawable.language)
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
                                        startActivity(new Intent(getBaseContext(), MapsActivity.class));
                                    }
                                }).show();
                break;
            case R.id.mnImportKML:
                Toast.makeText(this, R.string.import_kml, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnExportKML:
                Toast.makeText(this, R.string.export_kml, Toast.LENGTH_LONG).show();
                break;
            case R.id.mnAccountInfo:
                Toast.makeText(this, R.string.account_info, Toast.LENGTH_LONG).show();
                break;
        }
        return true;
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



}
