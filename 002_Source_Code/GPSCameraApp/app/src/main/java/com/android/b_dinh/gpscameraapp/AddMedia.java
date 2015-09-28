package com.android.b_dinh.gpscameraapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class AddMedia extends Activity {

    ImageView imvPhoto;
    VideoView vdoVideo;
    String pathVideo;

    ImageButton btnChoosePlace;

    int REQUEST_CODE_PHOTO_TAKE = 1;
    int REQUEST_CODE_PHOTO_CHOOSE = 2;
    int REQUEST_CODE_VIDEO_RECORD = 3;
    int REQUEST_CODE_VIDEO_CHOOSE = 4;
    int REQUEST_CODE_INPUT_PLACE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_media);

        ImageButton btnAddMedia;
        btnAddMedia = (ImageButton) findViewById(R.id.btnAddMedia);
        btnAddMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMedia();
            }
        });

        imvPhoto = (ImageView) findViewById(R.id.imvPhoto);
        imvPhoto.setVisibility(View.INVISIBLE);
        btnChoosePlace = (ImageButton) findViewById(R.id.btnChoosePlace);
        btnChoosePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedia.this, MapsChoosePlace.class);
                startActivityForResult(intent, REQUEST_CODE_INPUT_PLACE);
            }
        });

        vdoVideo = (VideoView) findViewById(R.id.vdoVideo);
        vdoVideo.setVisibility(View.INVISIBLE);
    }

    private void selectMedia() {

        final CharSequence[] options = {"Take Photo", "Choose Photo", "Record Video", "Choose Video", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddMedia.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CODE_PHOTO_TAKE);
                } else if (options[item].equals("Choose Photo")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CODE_PHOTO_CHOOSE);
                    imvPhoto.setVisibility(View.VISIBLE);

                } else if (options[item].equals("Record Video")) {
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "newVideos");
                    pathVideo = f.getPath();
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CODE_VIDEO_RECORD);
                } else if (options[item].equals("Choose Video")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CODE_VIDEO_CHOOSE);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    imvPhoto.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("Image ", picturePath + "");
                imvPhoto.setImageBitmap(thumbnail);
            } else if (requestCode == 3) {
                VideoView mVideoView = (VideoView) findViewById(R.id.vdoVideo);
                mVideoView.setVideoPath(pathVideo);
                mVideoView.setMediaController(new MediaController(this));
                mVideoView.requestFocus();
                mVideoView.start();

            } else if (requestCode == 4) {
                Uri selectedVideo = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedVideo, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String videoPath = c.getString(columnIndex);
                c.close();
                Log.w("Image ", videoPath + "");
                vdoVideo.setVideoPath(videoPath); // setImageBitmap(thumbnail);

                VideoView mVideoView = (VideoView) findViewById(R.id.vdoVideo);
                mVideoView.setVideoPath(videoPath);
                mVideoView.setMediaController(new MediaController(this));
                mVideoView.requestFocus();
                mVideoView.start();
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_add_media, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
