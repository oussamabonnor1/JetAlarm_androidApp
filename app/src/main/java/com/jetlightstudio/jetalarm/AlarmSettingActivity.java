package com.jetlightstudio.jetalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmSettingActivity extends AppCompatActivity {

    TimePicker timePicker;
    AlarmManager alarmManager;
    DataBaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        dbManager = new DataBaseManager(getApplicationContext(), null);
    }

    public void cancelAlarm(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm(View v) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        String time = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        intent.putExtra("time", time);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        dbManager.createAlarm(getApplicationContext(), calendar);
        cancelAlarm(v);
    }

    static String estimatedTime(Calendar calendar) {
        long estimatedTimeLong = calendar.getTimeInMillis() - System.currentTimeMillis();
        estimatedTimeLong /= 60000;
        estimatedTimeLong++;
        int hours = Integer.valueOf(String.format("%02d", estimatedTimeLong / 60));
        int minutes = Integer.valueOf(String.format("%02d", estimatedTimeLong % 60));
        if (minutes < 0) {
            int newMinutes = (24 * 60) + minutes + 3;
            minutes = newMinutes % 60;
            hours = newMinutes / 60 - 1;
        }
        return hours + " hours & " + minutes + " minutes";
    }

}
