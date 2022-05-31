package com.android1.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Notes);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbarAndDrawer();

        CaptionFragment captionFragment = new CaptionFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.caption_frame, captionFragment)
                .commit();

    }

    private void initToolbarAndDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
// Находим DrawerLayout
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
// Создаем ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
// Обработка навигационного меню
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new
                                                                 NavigationView.OnNavigationItemSelectedListener() {
                                                                     @Override
                                                                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                         int id = item.getItemId();
                                                                         switch (id) {
                                                                             case R.id.action_drawer_about:
                                                                                 openAboutFragment();
                                                                                 drawer.close();
                                                                                 return true;
                                                                             case R.id.action_drawer_exit:
                                                                                 finish();
                                                                                 return true;
                                                                         }
                                                                         return false;
                                                                     }
                                                                 });
    }

    private void openAboutFragment() {
        AboutFragment fragmentAbout = new AboutFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.caption_frame, fragmentAbout)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("")
                .commit();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }
}