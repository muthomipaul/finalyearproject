package com.example.finalprojectapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


//for
public class fetchData extends AsyncTask<Void, Void, Void> {

    String tempDataJson, humidityDataJson, lightIntensityDataJson= "";
    String dataParsed ="";
    String singleParsed ="";
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        sensors.tempData.setText(this.tempDataJson);
        sensors.humidityData.setText(this.humidityDataJson);
        sensors.lightIntensityData.setText(this.lightIntensityDataJson);

    }

    @Override
    protected Void doInBackground(Void... voids) {

        //temp data
        //------------------------------------------------------------------------------------------
        try {
            URL url= new URL("https://thingspeak.com/channels/1371639/field/1.json");

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            while(line!=null){
                line = bufferedReader.readLine();
                tempDataJson = tempDataJson + line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------------------------------------

        //humidity data
        //------------------------------------------------------------------------------------------
        try {
            URL url= new URL("https://thingspeak.com/channels/1371639/field/2.json");

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            while(line!=null){
                line = bufferedReader.readLine();
                humidityDataJson = humidityDataJson + line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // -----------------------------------------------------------------------------------------

        //light intensity data
        //------------------------------------------------------------------------------------------
        try {
            URL url= new URL("https://thingspeak.com/channels/1371639/field/3.json");

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));

            String line ="";
            while(line!=null){
                line = bufferedReader.readLine();
                lightIntensityDataJson = lightIntensityDataJson + line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------------------------------------


        return null;


    }
}
