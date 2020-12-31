package com.alina.singstreet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alina.singstreet.domain.Comment;
import com.alina.singstreet.model.CommentModel;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert
    long[] insert(Comment... comments);

    @Query("SELECT * FROM CommentModel WHERE postUID LIKE :postUID")
    LiveData<List<CommentModel>> getCommentByPostUID(String postUID);

    @Query("SELECT * FROM CommentModel WHERE postUID LIKE :postUID")
    List<CommentModel> getCommentsTest(String postUID);

    @Query("SELECT * FROM Comment")
    List<Comment> getCommentTest();

    @Query("SELECT * FROM CommentModel")
    List<CommentModel> getCommentModelTest();
}
