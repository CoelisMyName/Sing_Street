package com.alina.singstreet.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.alina.singstreet.domain.Comment;

@Dao
public interface CommentDao {

    @Insert
    long[] insert(Comment... comments);
}
