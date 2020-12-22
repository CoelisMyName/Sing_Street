package com.alina.singstreet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alina.singstreet.domain.Post;
import com.alina.singstreet.model.SingCardModel;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    long[] insert(Post... posts);

    @Query("SELECT * FROM SingCardModel, Follow WHERE :userUID LIKE Follow.followerUID AND Follow.userUID = SingCardModel.userUID")
    LiveData<List<SingCardModel>> getSingCardByUserUID(String userUID);
}
