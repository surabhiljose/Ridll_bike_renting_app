package com.example.surabhil.ridll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
/**
 * Created by surabhil on 3/10/16.
 */
public class MyAccount extends AppCompatActivity {

        ListView list;
        String[] itemname ={
                "Change Number",
                "Upload License",
                "Get a Custom Quote",
                "Help",
        };

        Integer[] imgid={
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,

        };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(MyAccount.this,MainActivity.class);


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
                    if (Slecteditem.equals("Change Number"))
                    {
                        Intent intent = new Intent(MyAccount.this, NumberChange.class);

                        startActivity(intent);
                    }
                    if (Slecteditem.equals("Upload License"))
                    {
                        Intent intent = new Intent(MyAccount.this, ChangeLicense.class);

                        startActivity(intent);
                    }
                    if (Slecteditem.equals("Help"))
                    {
                        Intent intent = new Intent(MyAccount.this, HelpActivity.class);

                        startActivity(intent);
                    }
                    if (Slecteditem.equals("Get a Custom Quote"))
                    {
                        Intent intent = new Intent(MyAccount.this, CustomQuote.class);

                        startActivity(intent);
                    }

                }
            });
        }
    }

