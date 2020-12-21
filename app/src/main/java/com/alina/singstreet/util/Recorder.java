package com.alina.singstreet.util;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

public class Recorder {
    MediaRecorder recorder;

    public void start(String path) {
        if (recorder == null) {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(path);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try {
                recorder.prepare();
                recorder.start();
            } catch (IOException e) {
                Log.e("Recorder", "prepare() failed");
            } finally {
                recorder.release();
                recorder = null;
            }
        }
    }

    public void stop() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }
}
