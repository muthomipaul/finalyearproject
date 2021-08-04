package com.example.finalprojectapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class sensors extends AppCompatActivity {

    //Button fetch;
    public static TextView tempData, humidityData, lightIntensityData;
    SwipeRefreshLayout sensorsRefresh;

    //test Json code
    private RequestQueue mQueue, mQueueTemp, mQueueLight;
    // end of test json code

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

        /* ----------------------- JSON array parsing test code ------------------------------------ */

        mQueue = Volley.newRequestQueue(this);
        mQueueLight = Volley.newRequestQueue(this);
        mQueueTemp = Volley.newRequestQueue(this);


        /* ----------------------------------------------------------------------------------------- */

        //On click listener for textviews
        tempData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent= new Intent(sensors.this, tempreture_data_activity.class);
                startActivity(intent);

                 */
            }
        });




        //refreshing to load data

        sensorsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //fetchData fetchData= new fetchData();
                //fetchData.execute();

                jsonParseHumidity();
                jsonParsetemp();
                jsonParseLight();
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



    /* ----------------------- JSON test code ------------------------------------------- */

    //fetching humidity data
    private void jsonParseHumidity(){
        String humidityURL = "https://thingspeak.com/channels/1371639/field/2.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, humidityURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("feeds");
                    for (int i = 0; i < jsonArray.length() ; i++){
                        JSONObject feed = jsonArray.getJSONObject(i);

                        String created_at = feed.getString("created_at");
                        int entry_ID = feed.getInt("entry_id");
                        String humidity_value = feed.getString("field2");

                        humidityData.append("Time of recording: " +created_at + "\n" +
                                "Entry ID: " + String.valueOf(entry_ID) + "\n" +
                                "Humidity Value: " + humidity_value + "\n" +
                                "----------------------------------------------- \n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    //fetching temperature data
    private void jsonParsetemp(){
        String tempURL = "https://thingspeak.com/channels/1371639/field/1.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tempURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("feeds");
                    for (int i = 0; i < jsonArray.length() ; i++){
                        JSONObject feed = jsonArray.getJSONObject(i);

                        String created_at = feed.getString("created_at");
                        int entry_ID = feed.getInt("entry_id");
                        String temp_value = feed.getString("field1");

                        tempData.append("Time of recording: " +created_at + "\n" +
                                "Entry ID: " + String.valueOf(entry_ID) + "\n" +
                                "Temperature  Value: " + temp_value + "\n" +
                                "----------------------------------------------- \n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueueTemp.add(request);
    }

    //fetching light intensity data

    private void jsonParseLight(){
        String lightURL = "https://thingspeak.com/channels/1371639/field/3.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, lightURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("feeds");
                    for (int i = 0; i < jsonArray.length() ; i++){
                        JSONObject feed = jsonArray.getJSONObject(i);

                        String created_at = feed.getString("created_at");
                        int entry_ID = feed.getInt("entry_id");
                        String light_intensity_value = feed.getString("field3");

                        lightIntensityData.append("Time of recording: " +created_at + "\n" +
                                "Entry ID: " + String.valueOf(entry_ID) + "\n" +
                                "Light Intensity  Value: " + light_intensity_value + "\n" +
                                "----------------------------------------------- \n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueueLight.add(request);
    }


}