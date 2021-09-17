package com.hyunsoo.every;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;


public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "거래하기로한 시간입니다 !!!!!", Toast.LENGTH_SHORT).show();    // AVD 확인용
        Log.e("Alarm","알람입니다.");    // 로그 확인옹
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("에브리띵","거래하기로한 시간입니다!!!!!!!");
        notificationHelper.getManager().notify(1,nb.build());
    }
}
