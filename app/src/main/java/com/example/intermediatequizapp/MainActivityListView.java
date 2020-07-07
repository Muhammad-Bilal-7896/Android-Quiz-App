package com.example.intermediatequizapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityListView extends AppCompatActivity {
    GraphView graphView;
    Button insertButton;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    MyHelper myHelper;
    SimpleDateFormat sdf = new SimpleDateFormat(" hh:mm:ss a");
    private SQLiteDatabase sqLiteDatabase;
    Controllerdb db =new Controllerdb(this);
    TextView textViewScore;
    TextView tim;
    SQLiteDatabase database;
    public int score;
    public String Name;
    public String Score;
    //EditText EtName,EtAge;
    private long time;
    Button Submitdatabtn,Showdatabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //see Home selected
        bottomNavigationView.setSelectedItemId(R.id.MainActivityListView);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.StartingScreeenActivity:
                        startActivity(new Intent(getApplicationContext()
                                ,StartingScreenActivity.class));
                        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.decelerate_interpolator);
                        return true;
                    case R.id.MainActivityListView:
                        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.decelerate_interpolator);
                        return true;
                    case R.id.About:
                        startActivity(new Intent(getApplicationContext()
                                ,About.class));
                        overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.decelerate_interpolator);
                        return true;
                }
                return false;
            }
        });


        //////////////////////////
        //geting data from Login
        Name=Login.getINSTANCE().getName();
        Toast.makeText(MainActivityListView.this,"Data from sign up form is: "+Name,Toast.LENGTH_LONG).show();
        //////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////
        //*************Imported from endquiz for testing****************
        //////////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        insertButton = findViewById(R.id.insertButton);
        graphView = (GraphView) findViewById(R.id.graphid);
        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();
        graphView.addSeries(series);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return sdf.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        series.resetData(getDataPoint());
        exqInsert();
        ///////////////////////////////////////////////////////////////////////////////////
        //*************Imported from endquiz for testing****************
        //////////////////////////////////////////////////////////////////////////////////
       /* EtName= (EditText) findViewById(R.id.etName);
        EtAge= (EditText) findViewById(R.id.etAge);
        Submitdatabtn= (Button) findViewById(R.id.btnSave);*/
        Showdatabtn=(Button) findViewById(R.id.btnShow);
        textViewScore=(TextView)findViewById(R.id.textviewScore);
        Showdatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
            }
        });
        /*Submitdatabtn.setOnClickListener(this);
        Showdatabtn.setOnClickListener(this);*/
        /*Intent intent = getIntent();
        score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0);*/
        textViewScore.setText("Your Score: "+score);
        /*Score = Integer.toString(score);*/
        time = new Date().getTime();
        String t= DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(time);
        database=db.getWritableDatabase();
        database.execSQL("INSERT INTO UserDetails(Username,Mailid,Age)VALUES('"+Name+"','"+"Score: "+score+"','"+t+"')" );
        Toast.makeText(this,"Data Inserted To Sqlite Database",Toast.LENGTH_LONG).show();
    }
    ///////////////////////////////////////////////////////////////////////////////////
    //*************Imported from endquiz for testing****************
    //////////////////////////////////////////////////////////////////////////////////
    private void exqInsert() {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long xValue = new Date().getTime();//This will save current time value in the xVlaue
                int yValue = score;//saves score in the yValue variable
                //good
                /*boolean message=*/myHelper.insertData(xValue, yValue); //we have inserted data in the database now
                /*if (message == true) {
                    Toast.makeText(MainActivityListView.this,"Successfully entered the data",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivityListView.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }*/
                series.resetData(getDataPoint());
                graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return sdf.format(new Date((long) value));
                        } else {
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });
            }
        });
    }
    private DataPoint[] getDataPoint() {
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("myTable", columns, null, null, null, null, null);
        //This cursor will contain all the values from the table named mytable
        DataPoint[] dp = new DataPoint[cursor.getCount()]; //array datapoint we will store all the values from the cursor to the database

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getLong(0), cursor.getInt(1));

        }
        return dp;
    }
    ///////////////////////////////////////////////////////////////////////////////////
    //*************Imported from endquiz for testing****************
    //////////////////////////////////////////////////////////////////////////////////
    private void showProgress(){
        Intent intent = new Intent(this,ShowdataListview.class);
        startActivity(intent);
    }

    /*@Override
    public void onClick(View v) {

    }

    /*@Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.btnSave)
        {

        }
        else  if(v.getId()==R.id.btnShow)
        {
            Intent intent=new Intent(this,ShowdataListview.class);
            startActivity(intent);

        }
    }*/
}