package com.example.keepsake;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_intro:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IntroFragment()).commit();
                break;

            case R.id.nav_arrival:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ArrivalFragment()).commit();
                break;

            case R.id.nav_theDay:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TheDayFragment()).commit();
                break;

            case R.id.nav_grow:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GrowFragment()).commit();
                break;

            case R.id.nav_monthlypic:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MonthlyPicFragment()).commit();
                break;

            case R.id.nav_myfirsts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyFirstsFragment()).commit();
                break;

            case R.id.nav_familyPic:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FamilyPicFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
