package com.example.aaron.amazonapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by aaron on 8/19/17.
 */

public class AmazonDynamicBroadcastReciever extends BroadcastReceiver {
    String broadcastString;

    public String getBroadcastString() {
        return broadcastString;
    }

    public void setBroadcastString(String broadcastString) {
        this.broadcastString = broadcastString;
    }

    public AmazonDynamicBroadcastReciever(String broadcastString) {
        this.broadcastString = broadcastString;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       String recievedInto =  intent.getStringExtra("info");
        setBroadcastString(recievedInto);
        Log.d("AMAZON_BRDCST_RCVR", "onReceive: " + recievedInto);
        Intent passToMainIntent = new Intent(context ,MainActivity.class);
        Bundle b = new Bundle();
        b.putString("info", recievedInto);
        passToMainIntent.setAction("amazonListInfo");
        passToMainIntent.putExtra("info", recievedInto);
        context.sendBroadcast(passToMainIntent);
    }
}
