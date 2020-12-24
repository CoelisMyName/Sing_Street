package com.alina.singstreet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alina.singstreet.domain.Comment;
import com.alina.singstreet.model.CommentModel;

@Dao
public interface CommentDao {

    @Insert
    long[] insert(Comment... comments);

    @Query("SELECT * FROM CommentModel WHERE postUID LIKE :postUID")
    LiveData<CommentModel> getCommentByPostUID(String postUID);
}
