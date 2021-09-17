package com.hyunsoo.every;


import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1ID";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
    }

    public NotificationManager getManager() {
        if (mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    //제목,메세지,아이콘 포함
    public NotificationCompat.Builder getChannelNotification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.time);
    }
}