package com.example.projectn1.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.projectn1.R;

public class NotificationActivity extends AppCompatActivity {
    NotificationManager nM;
    NotificationChannel nCh;
    Notification.Builder builder;
    private String channelId = "i.apps.notifications";
    private String description = "Test notification";

    AppCompatButton sendN;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("RemoteViewLayout")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        final RemoteViews[] contentView = new RemoteViews[1];

        sendN = findViewById(R.id.send_notification);

        nM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        sendN.setOnClickListener(v -> {

            Intent intent = new Intent(this, AfterNotification.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                nCh = new NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH);
                nCh.enableLights(true);
                nCh.setLightColor(Color.GREEN);
                nCh.enableVibration(false);
                nM.createNotificationChannel(nCh);

                builder = new Notification.Builder(this, channelId)
                        .setCustomContentView(contentView[0])
                        .setSmallIcon(R.drawable.ic_alternate_email)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_email_read))
                        .setContentIntent(pendingIntent);

            } else {
               builder = new Notification.Builder(this)
                       .setCustomContentView(contentView[0])
                       .setSmallIcon(R.drawable.ic_alternate_email)
                       .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_email_read))
                       .setContentIntent(pendingIntent);
            }
            nM.notify(27, builder.build());
        });
    }
}
