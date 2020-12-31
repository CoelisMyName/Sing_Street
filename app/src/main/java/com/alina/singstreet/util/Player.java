package com.alina.singstreet.util;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements MediaPlayer.OnPreparedListener {
    Timer timer;
    TimerTask timerTask;
    MediaPlayer player;
    MutableLiveData<Integer> duration = new MutableLiveData<>();
    MutableLiveData<Integer> position = new MutableLiveData<>();
    MutableLiveData<Boolean> playing = new MutableLiveData<>(false);

    public LiveData<Integer> getDuration() {
        return duration;
    }

    public LiveData<Integer> getPosition() {
        return position;
    }

    public LiveData<Boolean> getPlaying() {
        return playing;
    }

    public void start(String path) throws IOException {
        if (player == null) {
            player = new MediaPlayer();
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA);
            player.setAudioAttributes(builder.build());
            try {
                player.setDataSource(path);
                player.setOnPreparedListener(this);
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Player", "prepare() failed");
                player.release();
                player = null;
                throw e;
            }

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });
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
        cancelTimerTask();
        if (player != null) {
            player.pause();
            player.release();
            player = null;
        }
        resetLiveData();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        playing.postValue(true);
        duration.postValue(mp.getDuration());
        startTimerTask();
    }

    void cancelTimerTask(){
        if(timer != null){
            timer.cancel();
            timer = null;
            timerTask = null;
        }
    }

    void startTimerTask(){
        if(timer != null){
            timer.cancel();
            timer = null;
            timerTask = null;
        }
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                position.postValue(getCurrentPosition());
            }
        };
        timer.schedule(timerTask, 0, 250);
    }

    void resetLiveData(){
        position.setValue(0);
        duration.setValue(0);
        playing.setValue(false);
    }
}
