package com.jetlightstudio.jetalarm.ToolBox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, RingtoneService.class);
        context.startService(i);
    }
}
