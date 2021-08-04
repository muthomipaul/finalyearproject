package com.example.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class sensors extends AppCompatActivity {

    //Button fetch;
    public static TextView tempData, humidityData, lightIntensityData;
    SwipeRefreshLayout sensorsRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sensor Data");

        sensorsRefresh = findViewById(R.id.sensorsRefresh);
        //fetch= findViewById(R.id.fetchsensordata);
        tempData= findViewById(R.id.temperatureData);
        humidityData= findViewById(R.id.humidityData);
        lightIntensityData= findViewById(R.id.lightIntensityData);


        //implementing scrolling
        tempData.setMovementMethod(new ScrollingMovementMethod());
        humidityData.setMovementMethod(new ScrollingMovementMethod());
        lightIntensityData.setMovementMethod(new ScrollingMovementMethod());


        //refreshing to load data
        sensorsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData fetchData= new fetchData();
                fetchData.execute();

                sensorsRefresh.setRefreshing(false);
            }
        });

        /*
        //object for initiating fetching of data
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //fetching from thingspeak

            }
        });
        */
    }
}