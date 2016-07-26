package com.example.surabhil.ridll;
/**
 * Created by surabhil on 2/28/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public  class ChangeLicense extends Activity {

    ImageButton b;
    Button upload ;
    EditText lid;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.changelicense);

        b=(ImageButton)findViewById(R.id.lbutton);

        upload = (Button)findViewById(R.id.lupload);
        lid = (EditText)findViewById(R.id.lno);

        SharedPreferences prefer = getApplicationContext().getSharedPreferences("MyPrefff", 0); // 0 - for private mode
        String licenseimage =  prefer.getString("previmage", "nul");
        upload.setEnabled(false);

        if(licenseimage.equals("nul")) {


        }
        else
        {
            try{
                final int THUMBSIZE = 150;
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(licenseimage, options),
                        THUMBSIZE, THUMBSIZE);

                b.setImageBitmap(ThumbImage);




            }catch(Exception e){
                e.getMessage();

            }
        }
        upload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                {
                    //upoad all the image and stuffs using json
                    SharedPreferences prefff = getApplicationContext().getSharedPreferences("MyPrefff", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = prefff.edit();
                    String temppath =  prefff.getString("liscenceimage", "nul");

                    editor.putString("previmage", temppath);

                    editor.commit();
                    Intent intent = new Intent(ChangeLicense.this, MyAccount.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();

                }



            }
        });


        b.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                selectImage();

            }

        });

    }

    private void selectImage() {



        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(ChangeLicense.this);

        builder.setTitle("Upload your License");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 1);




                }

                else if (options[item].equals("Choose from Gallery"))

                {

                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);



                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds options to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

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


                    final int THUMBSIZE = 150;
                    final BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;

                    Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(f.getAbsolutePath(),options),
                            THUMBSIZE, THUMBSIZE);

                    b.setImageBitmap(ThumbImage);


                    SharedPreferences prefff = getApplicationContext().getSharedPreferences("MyPrefff", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = prefff.edit();
                    editor.putString("liscenceimage", f.getAbsolutePath());

                    editor.commit();

                    upload.setEnabled(true);

                    String path = android.os.Environment

                            .getExternalStorageDirectory()

                            + File.separator

                            + "Phoenix" + File.separator + "default";

                    f.delete();

                    OutputStream outFile = null;

                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {

                        outFile = new FileOutputStream(file);

                        ThumbImage.compress(Bitmap.CompressFormat.JPEG,100, outFile);

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

                String[] filePath = { MediaStore.Images.Media.DATA };

                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                final int THUMBSIZE = 150;

                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(picturePath,options),
                        THUMBSIZE, THUMBSIZE);

                b.setImageBitmap(ThumbImage);


                SharedPreferences prefff = getApplicationContext().getSharedPreferences("MyPrefff", 0); // 0 - for private mode
                SharedPreferences.Editor editor = prefff.edit();
                editor.putString("liscenceimage", picturePath);

                editor.commit();

                upload.setEnabled(true);


            }

        }

    }

}