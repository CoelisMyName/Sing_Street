package com.alina.singstreet.view.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alina.singstreet.domain.Comment;
import com.alina.singstreet.model.CommentModel;
import com.alina.singstreet.model.SingCardModel;
import com.alina.singstreet.repository.PostRepository;
import com.alina.singstreet.util.Player;
import com.alina.singstreet.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PostViewModel extends AndroidViewModel {
    PostRepository postRepository;
    Player player;
    Comment comment = new Comment();
    LiveData<Integer> duration;
    LiveData<Integer> position;

    public PostViewModel(@NonNull Application application) {
        super(application);
        player = new Player();
        postRepository = new PostRepository(application);
        duration = player.getDuration();
        position = player.getPosition();
    }

    public LiveData<Integer> getDuration() {
        return duration;
    }

    public LiveData<Integer> getPosition() {
        return position;
    }

    LiveData<SingCardModel> getSingCardByPostUID(String postUID) {
        return postRepository.getSingCardByPostUID(postUID);
    }

    public LiveData<List<CommentModel>> getCommentByPostUID(String postUID) {
        return postRepository.getCommentByPostUID(postUID);
    }

    public void start(String path) throws IOException {
        player.start(path);
    }

    public void stop() {
        player.stop();
    }

    public void setUserUID(String s) {
        comment.setUserUID(s);
    }

    public void setPostUID(String s) {
        comment.setPostUID(s);
    }

    public void setComment(String s) {
        comment.setComment(s);
    }

    public void setRate(float r) {
        comment.setRate(r);
    }

    public LiveData<Boolean> comment(String c, float r) {
        comment.setTimestamp(Utils.getTimestamp());
        comment.setComment(c);
        comment.setRate(r);
        return postRepository.comment(comment);
    }

    public void refreshCommentUID() {
        comment.setCommentUID(UUID.randomUUID().toString());
    }
}
