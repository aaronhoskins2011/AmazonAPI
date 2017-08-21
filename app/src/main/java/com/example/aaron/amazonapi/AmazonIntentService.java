package com.example.aaron.amazonapi;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.aaron.amazonapi.model.AmazonProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AmazonIntentService extends IntentService {
    public static final String TAG = "AMAZON_INTENT_SERVICE";
    public static final String AMAZON_URL = "http://de-coding-test.s3.amazonaws.com/books.json";
    static String responseString = "";
    List<AmazonProfile> amazonList;
    AmazonDynamicBroadcastReciever amazonDynamicBroadcastReciever;
    public AmazonIntentService() {
        super("intent");

    }
    public AmazonIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent passedIntent) {

        Log.d(TAG, "onHandleIntent: " + "On Handle Intent started");

        /*************************************
         **   Client/Request initialization **
         * *********************************/
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(AMAZON_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type list = new TypeToken<List<AmazonProfile>>(){}.getType();
                //Gson gson = new Gson();
                //responseString = response.body().string();
               // Log.d(TAG, "onResponse: Response string set");
                //amazonList = gson.fromJson(responseString, list);

                //Broadcast to Main
                Intent broadcastAmazonInfoIntent = new Intent();
                broadcastAmazonInfoIntent.setAction("amazonListInfo");
                broadcastAmazonInfoIntent.putExtra("info", "responseString");
                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getApplicationContext());
                lbm.sendBroadcast(broadcastAmazonInfoIntent);

            }
        });
    }



}
