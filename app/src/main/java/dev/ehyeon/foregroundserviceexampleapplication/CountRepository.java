package dev.ehyeon.foregroundserviceexampleapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Timer;
import java.util.TimerTask;

public class CountRepository {

    private final MutableLiveData<Integer> count;
    private final Timer timer;
    private final TimerTask timerTask;

    public CountRepository() {
        count = new MutableLiveData<>(0);

        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                count.postValue(count.getValue() + 1);
            }
        };
    }

    public void startCounter() {
        timer.schedule(timerTask, 0, 1000);
    }

    public void stopCounter() {
        timer.cancel();
    }

    public LiveData<Integer> getCount() {
        return count;
    }
}
