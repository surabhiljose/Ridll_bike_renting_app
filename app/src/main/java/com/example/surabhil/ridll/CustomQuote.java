package com.example.surabhil.ridll;
/**
 * Created by surabhil on 2/28/16.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class CustomQuote extends Activity {
    EditText customquotemessage;
    String quotemessage;
    Button quotesend;
    ImageButton clicktocall;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(CustomQuote.this, MyAccount.class);


            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.customquote);
        customquotemessage = (EditText) findViewById(R.id.quotemessage);
        quotemessage = String.valueOf(customquotemessage.getText());
        quotesend = (Button) findViewById(R.id.quotesend);
        clicktocall = (ImageButton) findViewById(R.id.clicktocall);

        clicktocall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:+911234"));
                if (ActivityCompat.checkSelfPermission(CustomQuote.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(i);
            }
        });

                quotesend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "One of our staffs will be in touch with you shortly. ", Toast.LENGTH_LONG).show();
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    Intent intent = new Intent(CustomQuote.this, MyAccount.class);
                    startActivity(intent);
                    finish();


                } else {
                    Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
