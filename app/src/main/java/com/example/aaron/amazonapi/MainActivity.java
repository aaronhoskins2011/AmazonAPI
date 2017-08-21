package com.example.aaron.amazonapi;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity  {

    RecyclerView rvAmazonList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    AmazonRVAdaptor amazonRVAdaptor;
    List<AmazonProfile> amazonList;
    public static final String AMAZON_URL = "http://de-coding-test.s3.amazonaws.com/books.json";
    static String responseString = "";

    AmazonDynamicBroadcastReciever amazonDynamicBroadcastReciever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);












        /*************************************
         **   Client/Request initialization **
         * *********************************/
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(AMAZON_URL).build();
        IntentFilter intentFilter = new IntentFilter("amazonListInfo");
        registerReceiver(amazonDynamicBroadcastReciever, intentFilter);
        startAmazonIntentService();
        Intent responceIntent = getIntent();
        responceIntent.setAction("amazonListInfo");
        Bundle b = responceIntent.getBundleExtra("info");
       // String recieved = b.getString("info");
        //Log.d("TAG", "onCreate: " + recieved);


        registerReceiver(amazonDynamicBroadcastReciever , intentFilter);

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //Type list = new TypeToken<List<AmazonProfile>>(){}.getType();
                    //Gson gson = new Gson();
                    //responseString = response.body().string();
                    //amazonList = gson.fromJson(responseString, list);
                   // startAmazonIntentService();
                    //amazonDynamicBroadcastReciever= new AmazonDynamicBroadcastReciever();
                    //intentFilter = new IntentFilter();
                    //intentFilter.addAction("amazonListInfo");

                    //responceIntent.setAction("amazonListInfo");
                    //tring brcstRecv = responceIntent.getStringExtra("info");

                    //gson = new Gson();
                    //Log.d("MAIN_ACTIVITY", "onResponse: " + brcstRecv);
                    //responseString = response.body().string();
                    //amazonList = gson.fromJson(responseString, list);
                    //initRV();



                }
            });


    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(amazonDynamicBroadcastReciever);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //amazonDynamicBroadcastReciever= new AmazonDynamicBroadcastReciever(null);
        //intentFilter = new IntentFilter();
        //intentFilter.addAction("amazonListInfo");
        //registerReceiver(amazonDynamicBroadcastReciever, intentFilter);
    }

    /**********************************
     *  Intent Service Method         *
     *********************************/
    public  void startAmazonIntentService(){
        Intent amazonIntentService = new Intent(this,AmazonIntentService.class);
        amazonIntentService.setAction("recieve_Info");
        startService(amazonIntentService);
    }


    private void initRV(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent responceIntent = getIntent();
                responceIntent.setAction("amazonListInfo");
                String brcstRecv = responceIntent.getStringExtra("info");
                Gson gson = new Gson();
                gson = new Gson();
                Log.d("MAIN_ACTIVITY", "onResponse: " + brcstRecv);
                /*****************************************
                 *     Recycle View initialization       *
                 * **************************************/
               /* rvAmazonList = (RecyclerView)findViewById(R.id.rvAmazonLIst);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                itemAnimator = new DefaultItemAnimator();
                rvAmazonList.setLayoutManager(layoutManager);
                rvAmazonList.setItemAnimator(itemAnimator);
                //Adaptor instantiated
                amazonRVAdaptor = new AmazonRVAdaptor(amazonList,getApplicationContext());
                rvAmazonList.setAdapter(amazonRVAdaptor);
                amazonRVAdaptor.notifyDataSetChanged();*/


            }
        });
    }

}
