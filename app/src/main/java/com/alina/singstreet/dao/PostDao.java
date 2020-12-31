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

    @Query("SELECT * FROM Post")
    List<Post> getPostsTest();

    @Query("SELECT * FROM SingCardModel")
    List<SingCardModel> getSingCardsTest();

    @Query(
            "SELECT DISTINCT postUID, SingCardModel.userUID AS userUID, icon, nickname, song, timestamp, description, path, rate, title " +
                    "FROM SingCardModel LEFT JOIN Follow ON  Follow.userUID = SingCardModel.userUID " +
                    "WHERE :userUID LIKE Follow.followerUID OR SingCardModel.userUID LIKE :userUID"
    )
    LiveData<List<SingCardModel>> getSingCardByUserUID(String userUID);

    @Query(
            "SELECT DISTINCT postUID, SingCardModel.userUID AS userUID, icon, nickname, song, timestamp, description, path, rate, title " +
                    "FROM SingCardModel LEFT JOIN Follow ON  Follow.userUID = SingCardModel.userUID " +
                    "WHERE :userUID LIKE Follow.followerUID OR SingCardModel.userUID LIKE :userUID"
    )
    List<SingCardModel> getSingCardByUserUIDTest(String userUID);

    @Query(
            "SELECT postUID, SingCardModel.userUID AS userUID, icon, nickname, song, timestamp, description, path, rate, title " +
                    "FROM SingCardModel WHERE song LIKE '%' || :string || '%' OR description LIKE '%' || :string || '%' OR title LIKE '%' || :string || '%'"
    )
    LiveData<List<SingCardModel>> searchSingCard(String string);

    @Query("SELECT * FROM SingCardModel WHERE :postUID LIKE postUID")
    LiveData<SingCardModel> getSingCardByPostUID(String postUID);
}
