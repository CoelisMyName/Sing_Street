package com.alina.singstreet.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alina.singstreet.Service;
import com.alina.singstreet.dao.CommentDao;
import com.alina.singstreet.dao.PostDao;
import com.alina.singstreet.data.Database;
import com.alina.singstreet.domain.Comment;
import com.alina.singstreet.domain.Post;
import com.alina.singstreet.model.CommentModel;
import com.alina.singstreet.model.SingCardModel;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class PostRepository {
    ExecutorService service;
    PostDao postDao;
    CommentDao commentDao;

    public PostRepository(Application application) {
        service = Service.getInstance().getExecutorService();
        Database database = Database.getInstance(application);
        postDao = database.getPostDao();
        commentDao = database.getCommentDao();
    }

    public LiveData<List<SingCardModel>> getSingCardByUserUID(@NonNull String userUID) {
        return postDao.getSingCardByUserUID(userUID);
    }

    public LiveData<List<SingCardModel>> searchSingCard(String userUID, String string) {
        return postDao.searchSingCard(userUID, string);
    }

    public LiveData<List<CommentModel>> getCommentByPostUID(String postUID) {
        return commentDao.getCommentByPostUID(postUID);
    }

    public LiveData<Boolean> post(Post post) {
        MutableLiveData<Boolean> b = new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                long[] l = postDao.insert(post);
                b.postValue(l[0] > 0);
            }
        });
        return b;
    }

    public LiveData<Boolean> comment(Comment comment) {
        MutableLiveData<Boolean> b = new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                long[] l = commentDao.insert(comment);
                b.postValue(l[0] > 0);
            }
        });
        return b;
    }

    public LiveData<SingCardModel> getSingCardByPostUID(String postUID) {
        return postDao.getSingCardByPostUID(postUID);
    }
}
