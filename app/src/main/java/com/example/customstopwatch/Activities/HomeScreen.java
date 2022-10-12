package com.example.customstopwatch.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.customstopwatch.Fragments.Home;
import com.example.customstopwatch.Fragments.Settings;
import com.example.customstopwatch.R;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;


public class HomeScreen extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    String ValuefromSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        if(savedInstanceState == null){
            Bundle bundle = new Bundle();
            ValuefromSetting = "60";
            bundle.putString("Seconds", ValuefromSetting);
            Home home = new Home();
            home.setArguments(bundle);
            bundle.putInt("22", 0);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.Main, Home.class, bundle).commit();
        }

        navigationView = findViewById(R.id.NavigationView);

        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.DrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setTitle("Custom StopWatch");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case (R.id.home):{
                        Bundle bundle = new Bundle();
                        bundle.putString("Seconds", "60");
                        Home home = new Home();
                        home.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.Main, home);
                        fragmentTransaction.commit();
                        break;
                    }

                    case (R.id.nav_settings):{
                        Settings settings = new Settings();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.Main, settings);
                        fragmentTransaction.commit();
                        break;
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}