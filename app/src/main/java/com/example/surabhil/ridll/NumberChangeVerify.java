package com.example.surabhil.ridll;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by surabhil on 2/23/16.
 */
public class NumberChangeVerify extends AppCompatActivity  {
    Button resendcode,verifyy;
    String mobnumber,realcode="123456",customerid = "$007$133007";
    EditText typecode;






    @Override
    protected void onCreate(Bundle verifysavedInstanceState) {
        super.onCreate(verifysavedInstanceState);
        setContentView(R.layout.numberchangeverify);
        verifyy = (Button) findViewById(R.id.verify);
        resendcode = (Button) findViewById(R.id.resend);
        Bundle bundle = getIntent().getExtras();
        mobnumber  = bundle.getString("messagee");
        typecode = (EditText) findViewById(R.id.typecode);
        typecode.setText("hint: 1 to 6");
        getdata();

        verifyy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (realcode.equals(String.valueOf(typecode.getText()))) {


                    // get the customerid and details from json.

                    //create file baby and call next intent

                    SharedPreferences preff = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = preff.edit();
                    editor.putString("name", "Elena");
                    editor.putInt("key_name", 123);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "number change successful", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(NumberChangeVerify.this, MyAccount.class);
                    startActivity(intent);


                    finish();


                }

            }
        });
        resendcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    getdata();


                } else {
                    Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    public void getdata()
    {
        //intiliaze realpass from api by sending the mobile number;

        //  if(successfull)
        Toast.makeText(getApplicationContext(), "verification code has been send", Toast.LENGTH_SHORT).show();
    }


}
