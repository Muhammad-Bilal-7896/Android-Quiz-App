package com.example.intermediatequizapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EndQuiz extends AppCompatActivity {
    GraphView graphView;
    Button insertButton;
    public int oscore;
    TextView tvcs;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[0]);
    MyHelper myHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM d,mm:ss a");
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_quiz);
        //////////////////////////
        //geting data from QuizActivity
        oscore=QuizActivity.getINSTANCE().getScore();
        Toast.makeText(EndQuiz.this,"Score from QuizActivity is: "+oscore,Toast.LENGTH_LONG).show();
        //////////////////////////

        /*Intent intent = getIntent();
        int score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        oscore = score;*/
        tvcs = findViewById(R.id.tvcs);
        tvcs.setText("Your score is : " + oscore);
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

    }
    private void exqInsert() {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long xValue = new Date().getTime();//This will save current time value in the xVlaue
                int yValue = oscore;//saves score in the yValue variable
                //good
                myHelper.insertData(xValue, yValue); //we have inserted data in the database now
                /*if (message == true) {
                    Toast.makeText(EndQuiz.this,"Successfully entered the data",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(EndQuiz.this,"Something went wrong",Toast.LENGTH_LONG).show();
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

}
