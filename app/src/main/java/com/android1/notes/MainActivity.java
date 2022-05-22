package com.android1.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CaptionFragment captionFragment = new CaptionFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.caption_frame, captionFragment)
                .commit();

    }
}