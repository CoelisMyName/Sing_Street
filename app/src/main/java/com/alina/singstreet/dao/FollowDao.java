package com.alina.singstreet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.alina.singstreet.domain.Follow;

@Dao
public interface FollowDao {

    @Insert
    long[] insert(Follow... follows);

    @Delete
    int delete(Follow... follows);

    @Query("SELECT CASE WHEN :userUID IN (SELECT followerUID FROM Follow WHERE :targetUID = userUID) THEN 1 ELSE 0 END")
    LiveData<Integer> isFollowed(String userUID, String targetUID);
}
