package com.example.surabhil.ridll;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return  super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onCreate(Bundle splashsavedInstanceState) {
        super.onCreate(splashsavedInstanceState);
        setContentView(R.layout.homepage);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(0);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                finally {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    int idName =  pref.getInt("key_name", -1);

                    if(idName == -1) {

                        Intent intent = new Intent(MainActivity.this, Verification1.class);

                        startActivity(intent);
                        finish();
                    }


                }
           }
        };
        thread.start();
        ImageButton offer = (ImageButton)findViewById(R.id.offers);
        ImageButton myaccount = (ImageButton)findViewById(R.id.myaccount);

        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyAccount.class);
                startActivity(intent);
            }});
       offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Offers.class);
                startActivity(intent);
            }});
        Button viewbikes = (Button)findViewById(R.id.viewbikes);
        viewbikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BikeView.class);
                startActivity(intent);
            }});
    }
}