package com.example.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class tempreture_data_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<tempreture> tempretureList;
    tempAdapter tempAdapter;

    private static String tempURL = "https://thingspeak.com/channels/1371639/field/1.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempreture_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Temperature Data");


        recyclerView = findViewById(R.id.tempRecyclerVoew);
        tempretureList = new ArrayList<>();
        //extractTempData();
        extractTempDataTest();



    }

    private void extractTempDataTest(){
        JsonObjectRequest jsonObject= new JsonObjectRequest(Request.Method.GET, tempURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("feeds");

                    for (int i =0; i < jsonArray.length(); i++){
                        JSONObject tempObject = jsonArray.getJSONObject(i);

                        tempreture tempreture = new tempreture();
                        tempreture.setCreated_at(tempObject.getString("created_at").toString());
                        tempreture.setEntry_ID(tempObject.getString("entry_id").toString());
                        tempreture.setTempValue(tempObject.getString("field1").toString());

                        tempretureList.add(tempreture);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

    }


    private void extractTempData(){

        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, tempURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                /*-------------------Trial JSON fetch code--------------------------------------- */

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject tempObject = response.getJSONObject(i);
                        tempreture tempreture = new tempreture();
                        tempreture.setCreated_at(tempObject.getString("created_at").toString());
                        tempreture.setEntry_ID(tempObject.getString("entry_id").toString());
                        tempreture.setTempValue(tempObject.getString("field1").toString());
                        tempretureList.add(tempreture);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                /*------------------------------------------------------------------------------- */

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                tempAdapter = new tempAdapter(getApplication(),tempretureList);
                recyclerView.setAdapter(tempAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

}