package com.alina.singstreet.repository;

import android.app.Application;

import com.alina.singstreet.dao.PostDao;
import com.alina.singstreet.data.Database;
import com.alina.singstreet.domain.Post;

public class PostRepository {
    PostDao postDao;

    public PostRepository(Application application){
        Database database = Database.getInstance(application);
        postDao = database.getPostDao();
    }

    public boolean post(Post post){
        long[] l = postDao.insert(post);
        return false;
    }
}
