package com.alina.singstreet.view.sing;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alina.singstreet.domain.Post;
import com.alina.singstreet.repository.PostRepository;
import com.alina.singstreet.util.Recorder;
import com.alina.singstreet.util.Utils;

import java.io.IOException;

public class SingViewModel extends AndroidViewModel {
    Recorder recorder = new Recorder();
    MutableLiveData<Integer> recording = new MutableLiveData<>(0);
    Post post = new Post();
    MutableLiveData<Post> postLiveData = new MutableLiveData<>(post);
    PostRepository postRepository;


    public SingViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
    }

    public void start() {
        try {
            post.setTimestamp(Utils.getTimestamp());
            post.setPath(Utils.getAbsolutePath(getApplication(), post.getTimestamp()));
            recorder.start(post.getPath());
        } catch (IOException e) {
            recording.setValue(-1);
            post.setTimestamp(null);
            post.setPath(null);
            return;
        } finally {
            postLiveData.setValue(post);
        }
        recording.setValue(1);
    }

    public void setUserUID(String userUID) {
        post.setUserUID(userUID);
        postLiveData.setValue(post);
    }

    public void setSong(String string) {
        post.setSong(string);
        postLiveData.setValue(post);
    }

    public void setTitle(String string) {
        post.setTitle(string);
        postLiveData.setValue(post);
    }

    public void setDescription(String string) {
        post.setDescription(string);
        postLiveData.setValue(post);
    }

    public void stop() {
        recorder.stop();
        recording.setValue(0);
    }

    public LiveData<Boolean> post() {
        return postRepository.post(post);
    }

    public LiveData<Integer> getRecording() {
        return recording;
    }

    public LiveData<Post> getPostLiveData() {
        return postLiveData;
    }
}
