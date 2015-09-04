package com.example.b_dinh.sqlite_import_export_kml_intent;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

/**
 * Created by b_dinh on 2015/09/04.
 */
public class AddPhotoActivity extends Activity {

    Button btnSave;

    private DatabaseHelper helper = null;
    private EditText txtTitle = null;
    private EditText txtImage = null;
    private EditText txtPlace = null;
    private EditText txtComment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info);

        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtImage = (EditText) findViewById(R.id.txtImage);
        txtPlace = (EditText) findViewById(R.id.txtPlace);
        txtComment = (EditText) findViewById(R.id.txtComment);

        helper = new DatabaseHelper(this);


        btnSave = (Button) findViewById(R.id.btnSave);
        Intent callerIntent = getIntent();

        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");

        final int soa = packageFromCaller.getInt("soa");
        final int sob = packageFromCaller.getInt("sob");

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SQLiteDatabase db= helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("title", txtTitle.getText().toString());
                cv.put("image", txtImage.getText().toString());
                cv.put("place", txtPlace.getText().toString());
                cv.put("comment", txtComment.getText().toString());
                db.insert("CameraApp", null, cv);
                Toast.makeText(AddPhotoActivity.this, "データの登録に成功しました。",Toast.LENGTH_LONG).show();

//                Toast.makeText(getApplicationContext(), "Xong roi " + soa + "    " + sob,
//                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
