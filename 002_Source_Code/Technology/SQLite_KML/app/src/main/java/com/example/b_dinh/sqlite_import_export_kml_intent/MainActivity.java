package com.example.b_dinh.sqlite_import_export_kml_intent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button btnAddPhoto;
    Button btnShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddPhoto = (Button) findViewById(R.id.btnAddPhoto);
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this, AddPhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("soa", 10);
                bundle.putInt("sob", 15);
                myIntent.putExtra("MyPackage", bundle);
                startActivity(myIntent);
            }
        });

        btnShowData = (Button) findViewById(R.id.btnShowData);
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ShowListItems.class);
                Bundle bundle = new Bundle();
                bundle.putInt("soa", 10);
                bundle.putInt("sob", 15);
                myIntent.putExtra("MyPackage", bundle);
                startActivity(myIntent);
            }
        });


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
