package com.alina.singstreet.util;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class Player {
    MediaPlayer player;

    public void start(String path) {
        if (player == null) {
            player = new MediaPlayer();
            try {
                player.setDataSource(path);
                player.prepare();
                player.start();
            } catch (IOException e) {
                Log.e("Player", "prepare() failed");
            } finally {
                player.release();
                player = null;
            }
        }
    }

    public int getCurrentPosition(){
        if(player != null) {
            return player.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration(){
        if(player != null) {
            return player.getDuration();
        }
        return 0;
    }

    public void stop() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
