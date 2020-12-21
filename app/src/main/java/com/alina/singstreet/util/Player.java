package com.alina.singstreet.util;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.URI;

public class Player {
    MediaPlayer player;

    public void start(String path){
        if(player == null){
            player = new MediaPlayer();
            try {
                player.setDataSource(path);
                player.prepare();
                player.start();
            } catch (IOException e) {
                Log.e("Player", "prepare() failed");
            }
            finally {
                player.release();
                player = null;
            }
        }
    }

    public void stop(){
        if(player != null){
            player.release();
            player = null;
        }
    }
}
