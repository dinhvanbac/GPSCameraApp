package com.example.b_dinh.exportkmlfile;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Parser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnExportClick(View view) {
        Toast.makeText(this, "Start Export", Toast.LENGTH_LONG).show();

        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "kml");
            serializer.attribute(null, "xmlns", "http://earth.google.com/kml/2.2");
            serializer.startTag(null, "Document");
            serializer.startTag(null, "name");
            serializer.text("todo put the name here");
            serializer.endTag(null, "name");
            serializer.startTag(null, "description");
            serializer.cdsect("todo put the description here");
            serializer.endTag(null, "description");
            serializer.endTag(null, "Document");
            serializer.endTag(null, "kml");
            serializer.endDocument();
            serializer.flush();

            //Toast.makeText(this, writer.toString(), Toast.LENGTH_LONG).show();

            TextView txtWriter = (TextView) findViewById(R.id.txtWriter);


            txtWriter.setText(writer.toString());

            String baseFolder;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                baseFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            }
            else {
                baseFolder = MainActivity.this.getFilesDir().getAbsolutePath();
            }

            //String string = "hello world!";
            File file = new File(baseFolder + File.separator + "test.kml");
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(writer.toString().getBytes());
            fos.flush();
            fos.close();


//            BufferedWriter writerXML = new BufferedWriter(
//                    new OutputStreamWriter(openFileOutput("1234.kml", Context.MODE_PRIVATE)));
//            writerXML.write(writer.toString());
//            //　ファイルをクローズ
//            writerXML.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
