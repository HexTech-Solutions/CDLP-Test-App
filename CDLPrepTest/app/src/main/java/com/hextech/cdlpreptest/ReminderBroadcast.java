package com.hextech.cdlpreptest;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notifiIntent = new Intent(context,NavigationTabActivity.class);
        PendingIntent notifiPendingIntent = PendingIntent.getActivity(context,0,notifiIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyCdlp")
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentTitle("CDL Prep Reminder")
                .setContentText("Hey there, you have a test to prepare")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(notifiPendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200,builder.build());
    }
}
