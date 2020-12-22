package com.alina.singstreet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.alina.singstreet.domain.Follow;

@Dao
public interface FollowDao {

    @Insert
    int insert(Follow... follows);

    @Delete
    int delete(Follow... follows);
}
