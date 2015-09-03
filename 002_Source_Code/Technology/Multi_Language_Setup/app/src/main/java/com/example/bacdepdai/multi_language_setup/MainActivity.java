package com.example.bacdepdai.multi_language_setup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends Activity {

    private String[] languages = {"English", "Vietnam", "Japanese"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setPrompt("select language");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages); // List language
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Configuration config = new Configuration();
                Locale locale;
                switch (arg2) {
                    case 0:
                        config.locale = Locale.getDefault();
                        Toast.makeText(MainActivity.this,"English",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        locale = new Locale("en");
                        config.locale = locale.ENGLISH;
                        Toast.makeText(MainActivity.this,"Vietnam",Toast.LENGTH_LONG).show();
                        //config.locale = Locale.ITALIAN;
                        break;
                    case 2:
                        locale = new Locale("jp");
                        config.locale = locale.JAPANESE;
                        Toast.makeText(MainActivity.this,"japanese",Toast.LENGTH_LONG).show();
                        //config.locale = Locale.FRENCH;
                        break;
                    default:
                        config.locale = Locale.ENGLISH;
                        Toast.makeText(MainActivity.this,"English",Toast.LENGTH_LONG).show();
                        break;
                }
                getResources().updateConfiguration(config, null);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    //　[ダイアログ表示]ボタンをクリックした時に実行されるコード
    public void onClick(View view) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
//        final String[] items = {"Tiếng Việt", "English", "日本語"};
//        //　ダイアログを生成
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Please select language")
//                .setIcon(R.drawable.icon)
//                        //　リスト項目とクリック時の処理を定義
//                .setItems(items,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                if (items[i] == "Tiếng Việt") {
//
//                                }
//
//                                Toast.makeText(MainActivity.this,
//                                        String.format("「%s」が選択されました。", items[i]),
//                                        Toast.LENGTH_LONG).show();
//                            }
//                        }).show();
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
