package dev.ehyeon.foregroundserviceexampleapplication;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class ForegroundService extends Service {

    private static final String TAG = "ForegroundService";
    private static final int SERVICE_ID = 1;

    private int count;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    public void onCreate() {
        super.onCreate();

        count = 0;

        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "count = " + count++);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification =
                new Notification.Builder(this, ((EHyeonApplication) getApplication()).getChannelId())
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .build();

        startForeground(SERVICE_ID, notification);

        timer.schedule(timerTask, 0, 1000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        timer.cancel();
    }
}
