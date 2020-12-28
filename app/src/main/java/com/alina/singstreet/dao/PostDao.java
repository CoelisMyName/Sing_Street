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

    @Query(
            "SELECT postUID, SingCardModel.userUID AS userUID, icon, nickname, song, timestamp, description, path, rate, title " +
            "FROM SingCardModel, Follow " +
            "WHERE (:userUID LIKE Follow.followerUID AND Follow.userUID = SingCardModel.userUID) OR SingCardModel.userUID LIKE :userUID"
    )
    LiveData<List<SingCardModel>> getSingCardByUserUID(String userUID);

    @Query(
            "SELECT postUID, SingCardModel.userUID AS userUID, icon, nickname, song, timestamp, description, path, rate, title " +
            "FROM SingCardModel, Follow " +
            "WHERE (:userUID LIKE Follow.followerUID AND Follow.userUID = SingCardModel.userUID AND (song LIKE '%' || :string || '%' OR description LIKE '%' || :string || '%' OR title LIKE '%' || :string || '%'))" +
            "OR (SingCardModel.userUID LIKE :userUID AND (song LIKE '%' || :string || '%' OR description LIKE '%' || :string || '%' OR title LIKE '%' || :string || '%'))"
    )
    LiveData<List<SingCardModel>> searchSingCard(String userUID, String string);

    @Query("SELECT * FROM SingCardModel WHERE :postUID LIKE postUID")
    LiveData<SingCardModel> getSingCardByPostUID(String postUID);
}
