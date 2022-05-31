package com.android1.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Notes);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CaptionFragment captionFragment = new CaptionFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.caption_frame, captionFragment)
                .commit();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }
}