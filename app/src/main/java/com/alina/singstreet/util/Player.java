package com.alina.singstreet.util;

import android.media.MediaPlayer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    Timer timer;
    TimerTask timerTask;
    MediaPlayer player;
    MutableLiveData<Integer> duration = new MutableLiveData<>();
    MutableLiveData<Integer> position = new MutableLiveData<>();

    public LiveData<Integer> getDuration() {
        return duration;
    }

    public LiveData<Integer> getPosition() {
        return position;
    }

    public void start(String path) throws IOException {
        if (player == null) {
            player = new MediaPlayer();
            try {
                player.setDataSource(path);
                player.prepare();
                player.start();
            } catch (IOException e) {
                Log.e("Player", "prepare() failed");
                player.release();
                player = null;
                throw e;
            }

            duration.setValue(getCurrentDuration());
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    position.postValue(getCurrentPosition());
                }
            };
            timer.schedule(timerTask, 0, 250);
        }
    }

    int getCurrentPosition() {
        if (player != null) {
            return player.getCurrentPosition();
        }
        return 0;
    }

    int getCurrentDuration() {
        if (player != null) {
            return player.getDuration();
        }
        return 0;
    }

    public void stop() {
        if (player != null) {
            timer.cancel();
            timer = null;
            timerTask = null;
            player.pause();
            player.release();
            player = null;
            position.setValue(0);
        }
    }
}
