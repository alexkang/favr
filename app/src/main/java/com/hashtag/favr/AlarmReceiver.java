package com.hashtag.favr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Alex on 7/18/2014.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String number = intent.getStringExtra("number");
        String body = intent.getStringExtra("body");

        Toast.makeText(context, "favr asked!", Toast.LENGTH_SHORT).show();

        PhoneController.getInstance(context).sendSmsByManager(number, body);
    }

}
