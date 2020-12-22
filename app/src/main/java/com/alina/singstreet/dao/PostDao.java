package com.alina.singstreet.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.alina.singstreet.domain.Post;

@Dao
public interface PostDao {
    @Insert
    int insert(Post... posts);
}
