package com.example.b_dinh.sqlite_import_export_kml_intent;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by b_dinh on 2015/09/04.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final private String DBNAME = "GPSCameraApp.sqlite";
    static final private int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //　データベースを作成するとき
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CameraApp (" +
                " title TEXT PRIMARY KEY, image TEXT, place TEXT, comment TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO CameraApp(title, image, place, comment)" +
                " VALUES('Title 1','Image 1','Place 1', 'Comment 1')");
        sqLiteDatabase.execSQL("INSERT INTO CameraApp(title, image, place, comment)" +
                " VALUES('Title 2','Image 2','Place 2', 'Comment 2')");
        sqLiteDatabase.execSQL("INSERT INTO CameraApp(title, image, place, comment)" +
                " VALUES('Title 3','Image 3','Place 3', 'Comment 3')");
        sqLiteDatabase.execSQL("INSERT INTO CameraApp(title, image, place, comment)" +
                " VALUES('Title 4','Image 4','Place 4', 'Comment 4')");
        sqLiteDatabase.execSQL("INSERT INTO CameraApp(title, image, place, comment)" +
                " VALUES('Title 5','Image 5','Place 5', 'Comment 5')");
    }

    //　データベースをバージョンアップした時、テープルを再作成
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CameraApp");
        onCreate(sqLiteDatabase);

    }


}
