package com.example.b_dinh.sqlite_import_export_kml_intent;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by b_dinh on 2015/09/04.
 */
public class ShowListItems extends Activity {

    private SQLiteDatabase mDb;
    private DatabaseHelper helper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data);

        helper = new DatabaseHelper(this);

        // TODO: Get data from SQLite then show to List

        ArrayList<GPSCameraApp> getListItems = getCameraApp();
        // OK Have been got Data from SQLite Success.

        // TODO: Export data from SQLite to KML File
    }

    public ArrayList<GPSCameraApp> getCameraApp() throws SQLException {
        ArrayList<GPSCameraApp> listCameraApp = new ArrayList<GPSCameraApp>();

        SQLiteDatabase db = helper.getReadableDatabase();
        String[] cols = {"title", "image", "place", "comment"};
        Cursor mCursor = db.query("CameraApp", cols, null, null, null, null, null);

        if (mCursor.moveToFirst()) {
            do {
                GPSCameraApp lCameraApp = new GPSCameraApp();
                lCameraApp.setTitle(mCursor.getString(mCursor.getColumnIndexOrThrow("title")));
                lCameraApp.setImage(mCursor.getString(mCursor.getColumnIndexOrThrow("image")));
                lCameraApp.setPlace(mCursor.getString(mCursor.getColumnIndexOrThrow("place")));
                lCameraApp.setComment(mCursor.getString(mCursor.getColumnIndexOrThrow("comment")));
                listCameraApp.add(lCameraApp);
            } while (mCursor.moveToNext());
        }

        return listCameraApp;
    }
}
