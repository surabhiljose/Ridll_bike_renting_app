package com.example.surabhil.ridll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by surabhil on 3/23/16.
 */
public class TermsActivity extends Activity {
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(TermsActivity.this, HelpActivity.class);


            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.terms);
    }
}
