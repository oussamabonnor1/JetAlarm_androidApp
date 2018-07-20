package com.jetlightstudio.jetalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class RingtoneService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("hello, binded!");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("hello, started!");
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.hello);
        //mediaPlayer.start();
        return START_NOT_STICKY;
    }
}
