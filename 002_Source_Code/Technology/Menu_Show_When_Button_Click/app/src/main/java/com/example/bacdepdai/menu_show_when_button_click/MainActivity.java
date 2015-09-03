package com.example.bacdepdai.menu_show_when_button_click;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lấy button ra và add sự kiện long click là Menu cho nó
        Button tv = (Button) findViewById(R.id.btnShowMenu);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });
        tv.setOnCreateContextMenuListener(this);

    }

    //　メニュー選択時にトースト表示
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "onContext item1", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "onContext item2", Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(this, "onContext item3", Toast.LENGTH_LONG).show();
                break;
            case R.id.item4:
                Toast.makeText(this, "onContext item4", Toast.LENGTH_LONG).show();
                break;
            case R.id.item5:
                Toast.makeText(this, "onContext item5", Toast.LENGTH_LONG).show();
                break;
            case R.id.item6:
                Toast.makeText(this, "onContext item6", Toast.LENGTH_LONG).show();
                break;
            case R.id.item7:
                Toast.makeText(this, "onContext item7", Toast.LENGTH_LONG).show();
                break;
            case R.id.item8:
                Toast.makeText(this, "onContext item8", Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }

    // Đoạn xử lý Long Click vào button
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuinfo) {
        super.onCreateContextMenu(menu, view, menuinfo);
//        menu.setHeaderTitle("Set as");
//        menu.add(menu.FIRST, Menu.NONE, 0, "Set as Wallpaper");
//        menu.add(menu.FIRST + 1, Menu.NONE, 0, "Download");
//        menu.add(menu.FIRST + 2, Menu.NONE, 0, "Info");
        getMenuInflater().inflate(R.menu.option_menu, menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }


    //　メニュー選択時にトースト表示
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "item1", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "item2", Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(this, "item3", Toast.LENGTH_LONG).show();
                break;
            case R.id.item4:
                Toast.makeText(this, "item4", Toast.LENGTH_LONG).show();
                break;
            case R.id.item5:
                Toast.makeText(this, "item5", Toast.LENGTH_LONG).show();
                break;
            case R.id.item6:
                Toast.makeText(this, "item6", Toast.LENGTH_LONG).show();
                break;
            case R.id.item7:
                Toast.makeText(this, "item7", Toast.LENGTH_LONG).show();
                break;
            case R.id.item8:
                Toast.makeText(this, "item8", Toast.LENGTH_LONG).show();
                break;

        }
//        Toast toast = Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG);
//        toast.show();
        return true;
    }

}
