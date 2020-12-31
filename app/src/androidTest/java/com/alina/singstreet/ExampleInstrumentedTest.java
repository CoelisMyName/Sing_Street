package com.alina.singstreet;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws IOException {
        String path = "/storage/emulated/0/Android/data/com.alina.singstreet/cache/2020-12-30 07:32:34.3gp";
        File file = new File("/storage/emulated/0/Android/data/com.alina.singstreet/cache/2020-12-30 07:32:34.3gp");
        FileInputStream fis = new FileInputStream(file);
        fis.close();
        MediaPlayer player = new MediaPlayer();
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA);
        player.setAudioAttributes(builder.build());
        player.setDataSource(path);
        player.prepare();
        player.start();
    }


}