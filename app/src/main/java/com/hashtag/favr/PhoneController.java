package com.hashtag.favr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.Calendar;

public class PhoneController {

    private static PhoneController instance = null;

    private Context context;

    public static PhoneController getInstance(Context context) {
        if (instance == null) {
            instance = new PhoneController(context);
        }

        return instance;
    }

    private PhoneController(Context context) {
        this.context = context;
    }

    public void sendSmsByManager(String number, String body) {
        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, body, null, null);
        } catch (Exception ex) {}
    }

    public void sendDelayedSms(String number, String body, int delay) {
        Intent i = new Intent("SMS_ACTION");
        i.setClass(context, AlarmReceiver.class);
        i.putExtra("number", number);
        i.putExtra("body", body);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, delay);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

}