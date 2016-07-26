package com.example.surabhil.ridll;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by surabhil on 2/22/16.
 */
public class Verification1 extends AppCompatActivity {
    Button verify;
    EditText mobilenumber;
    String mobileno = "kl";


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== RESULT_OK && requestCode == 1)
        {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle verifysavedInstanceState) {
        super.onCreate(verifysavedInstanceState);
        setContentView(R.layout.verification1);
        verify = (Button)findViewById(R.id.verify);

        mobilenumber = (EditText)findViewById(R.id.mobilenumber);


        verify.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v)
          {
              mobileno = String.valueOf(mobilenumber.getText());
              if(mobileno.length()== 10) {
                  ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                  if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                  {
                      Intent intent = new Intent(Verification1.this, Waitverify.class);
                      intent.putExtra("message", mobileno);
                      startActivityForResult(intent, 1);


                  }
                  else{
                      Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();

                  }
              }
              else
              {
                  Toast.makeText(getApplicationContext(), "enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
              }

          }
        });
    }

}
