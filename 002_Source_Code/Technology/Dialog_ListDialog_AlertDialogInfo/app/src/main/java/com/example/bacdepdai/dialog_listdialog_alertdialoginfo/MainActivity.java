package com.example.bacdepdai.dialog_listdialog_alertdialoginfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAlertInfo(View view){
        //　ダイアル後を生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //　ダイアログの設定
        builder.setTitle("Da Thanh Cong")
                .setMessage("Thanh cong roi nhe em")
                .setIcon(R.drawable.icon)
                .show();
    }

    public void onClickListDialog(View view){
        final String[] items = {"Japanese","English"};
        //　ダイアログを生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please choose language")
                .setIcon(R.drawable.icon)
                        //　リスト項目とクリック時の処理を定義
                .setItems(items,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,
                                        String.format("「%s」が選択されました。", items[i]),
                                        Toast.LENGTH_LONG).show();
                            }
                        }).show();

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
