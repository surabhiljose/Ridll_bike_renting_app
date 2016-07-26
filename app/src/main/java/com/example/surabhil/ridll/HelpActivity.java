package com.example.surabhil.ridll;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * Created by surabhil on 3/10/16.
 */
public class HelpActivity extends AppCompatActivity {

    ListView list;
    String[] itemname ={
            "About",
            "FAQ",
            "Terms and Conditions",
            "Contact us",
            "Call us"


    };

    Integer[] imgid={
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,

    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(HelpActivity.this,MyAccount.class);


            startActivity(intent);
        }
        return  super.onKeyDown(keyCode,event);
    }
    @Override

    protected void onCreate(Bundle splashsavedInstanceState) {
        super.onCreate(splashsavedInstanceState);
        setContentView(R.layout.myaccountlayout);


        AccListMain adapter=new AccListMain(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                if (Slecteditem.equals("About"))
                {
                    Intent intent = new Intent(HelpActivity.this, AboutActivity.class);

                    startActivity(intent);
                }
                if (Slecteditem.equals("FAQ"))
                {
                    Intent intent = new Intent(HelpActivity.this, FAQactivity.class);

                    startActivity(intent);
                }
                if (Slecteditem.equals("Terms and Conditions"))
                {
                    Intent intent = new Intent(HelpActivity.this, TermsActivity.class);

                    startActivity(intent);
                }
                if (Slecteditem.equals("Contact us"))
                {
                    Intent intent = new Intent(HelpActivity.this, ContactActivity.class);

                    startActivity(intent);
                }
                if (Slecteditem.equals("Call us"))
                {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:+911234"));
                    if (ActivityCompat.checkSelfPermission(HelpActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(i);
                }


            }
        });
    }
}

