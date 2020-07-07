package com.example.intermediatequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //see Home selected
        bottomNavigationView.setSelectedItemId(R.id.About);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.StartingScreeenActivity:
                        startActivity(new Intent(getApplicationContext()
                                ,StartingScreenActivity.class));
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        return true;
                    case R.id.About:
                        overridePendingTransition(android.R.anim.accelerate_decelerate_interpolator,android.R.anim.anticipate_overshoot_interpolator);
                        return true;
                    case R.id.MainActivityListView:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivityListView.class));
                        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.cycle_interpolator);
                        return true;
                }
                return false;
            }
        });

    }
}
