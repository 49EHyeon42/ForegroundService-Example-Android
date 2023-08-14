package dev.ehyeon.foregroundserviceexampleapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.lifecycle.LifecycleService;

public class ForegroundService extends LifecycleService {

    private static final int SERVICE_ID = 1;

    private CountRepository countRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        countRepository = new CountRepository();

        countRepository.getCount().observe(this, count -> {
            Notification notification =
                    new Notification.Builder(getBaseContext(), ((EHyeonApplication) getApplication()).getChannelId())
                            .setContentTitle("Title")
                            .setContentText("count = " + count)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .build();

            getSystemService(NotificationManager.class).notify(SERVICE_ID, notification);

            if (count != 0 && count % 10 == 0) {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification =
                new Notification.Builder(this, ((EHyeonApplication) getApplication()).getChannelId())
                        .setContentTitle("Title")
                        .setContentText("count = 0")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .build();

        startForeground(SERVICE_ID, notification);

        countRepository.startCounter();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        countRepository.stopCounter();
    }
}
