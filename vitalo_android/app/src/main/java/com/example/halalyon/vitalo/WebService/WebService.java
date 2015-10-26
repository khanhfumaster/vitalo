package com.example.halalyon.vitalo.WebService;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by halalyon on 24/10/15.
 */

public class WebService {

    // Response from the HTTP Request
    InputStream httpResponseStream = null;

    public JSONObject request1(String serial_number) {

        final String url = "http://vitalo.remotelab.club/data?" + "serial_number=" + serial_number;
        Log.i("vitaloData", "URL : " + url);
        StringBuilder builder = new StringBuilder();
        try {
            httpResponseStream = new requestTask().execute(new String[]{url}).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try{
            BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(httpResponseStream));
            String line = null;
            while ((line = httpResponseReader.readLine()) != null){
                builder.append(line);
            }
            httpResponseStream.close();
        }catch (Exception e){
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        try {
            JSONObject json = new JSONObject(builder.toString());
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

//    public JSONObject request2(String id) {
//
//        final String url = "http://vitalo.remotelab.club/notifications?" + "id=" + id;
//        Log.i("vitaloAlarm", "URL : " + url);
//        StringBuilder builder = new StringBuilder();
//        ArrayList<JSONObject> list = new ArrayList<JSONObject>();;
//        try {
//            httpResponseStream = new requestTask().execute(new String[]{url}).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        try{
//            BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(httpResponseStream));
//            String line = null;
//            while ((line = httpResponseReader.readLine()) != null){
//                builder.append(line);
//            }
//            httpResponseStream.close();
//        }catch (Exception e){
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//        JSONObject json=null;
//        try {
//            json = new JSONObject(builder.toString());
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }

    private class requestTask extends AsyncTask<String, Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... url) {
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url[0]);
                // Execute the request and fetch Http response
                HttpResponse httpResponse = httpClient.execute(httpGet);
                // Extract the result from the response
                HttpEntity httpEntity = httpResponse.getEntity();
                // Open the result as an input stream for parsing
                InputStream returnStream = httpEntity.getContent();

                return returnStream;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }


}
