package com.alina.singstreet.view.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alina.singstreet.model.CommentModel;
import com.alina.singstreet.model.SingCardModel;
import com.alina.singstreet.repository.PostRepository;
import com.alina.singstreet.util.Player;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    PostRepository postRepository;
    Player player;

    public PostViewModel(@NonNull Application application) {
        super(application);
        player = new Player();
        postRepository = new PostRepository(application);
    }

    LiveData<SingCardModel> getSingCardByPostUID(String postUID){
        return postRepository.getSingCardByPostUID(postUID);
    }

    public LiveData<List<CommentModel>> getCommentByPostUID(String postUID){
        return postRepository.getCommentByPostUID(postUID);
    }

    public void start(String path){
        player.start(path);
    }

    public void stop(){
        player.stop();
    }
}
