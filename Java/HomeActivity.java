package com.example.mymusicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    Boolean doubletap = false;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ViewPagerAdapter viewpageradapter;
    TabLayout tablayout;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tablayout=findViewById(R.id.tablayout);
        viewpager=findViewById(R.id.viewpager);

        viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewpageradapter.addFragment(new DjFragment(),"DJ Songs");
        viewpageradapter.addFragment(new RomanticFragment(),"Love Song");
        viewpageradapter.addFragment(new DevotionalFragment(),"Devotional");
        viewpageradapter.addFragment(new BroSisFragment(),"My Music");


        viewpager.setAdapter(viewpageradapter);
        tablayout.setupWithViewPager(viewpager);

        preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor = preferences.edit();


        preferences = getSharedPreferences("pref",MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("firstTime",true);

        if(isFirstTime)
        {
            welcome();
        }
        setTitle("HomeActivity");
    }

    private void welcome() {

        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("My Music Player");
        ad.setMessage("Welcome to My Music Player");
        ad.setPositiveButton("Thank you", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
        editor = preferences.edit();
        editor.putBoolean("firstTime",false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


     if(item.getItemId() == R.id.home_menu_settings)
        {
            Toast.makeText(HomeActivity.this,"Settings item Clicked",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this,SettingsActivity.class);
            startActivity(i);
        }


        else if(item.getItemId() == R.id.home_menu_texttospeak)
        {
            Toast.makeText(HomeActivity.this,"Text To Speak item Clicked",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this,TextToSpeakActivity.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.home_menu_bluetooth)
        {
            Toast.makeText(HomeActivity.this,"Bluetooth item Clicked",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this,BluetoothActivity.class);
            startActivity(i);
        }


        return true;
    }

    @Override
    public void onBackPressed() {

        if(doubletap) {
            super.onBackPressed();
        }
        else {
            Toast.makeText(HomeActivity.this,"Press again to exit app",Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {

                    doubletap = false;
                }
            },2000);
        }
    }
}
