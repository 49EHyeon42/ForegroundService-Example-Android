package dev.ehyeon.foregroundserviceexampleapplication;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class EHyeonApplication extends Application {

    private String channelId;

    @Override
    public void onCreate() {
        super.onCreate();

        channelId = "channelId";

        NotificationChannel notificationChannel =
                new NotificationChannel(channelId, "channelName", NotificationManager.IMPORTANCE_DEFAULT);

        getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
    }

    public String getChannelId() {
        return channelId;
    }
}
