package com.example.intermediatequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

;


public class MainActivity extends AppCompatActivity {
    private Button btnMcqs;
    private Button btnInsert;
    private Animation frombottom,fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.decelerate_interpolator);
        setContentView(R.layout.activity_main);



        btnMcqs=(Button)findViewById(R.id.btnMcqs);
        btnInsert=(Button)findViewById(R.id.btnInsert);
    frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
    fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
    btnInsert.setAnimation(frombottom);
    btnMcqs.setAnimation(fromtop);
    btnMcqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMcqs();
            }
        });
    btnInsert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openInsert();
        }
    });
    }
    private void openMcqs(){
        Intent intent = new Intent(MainActivity.this,StartingScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.decelerate_interpolator);
    }
    public void openInsert(){
        Intent intent = new Intent(MainActivity.this,MainActivityListView.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.decelerate_interpolator);
    }
}
