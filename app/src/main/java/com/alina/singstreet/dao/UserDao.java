package com.alina.singstreet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.alina.singstreet.domain.User;

@Dao
public interface UserDao {

    @Insert
    long[] insert(User... users);

    @Update
    int update(User... users);

    @Query("SELECT * FROM User WHERE phoneNumber LIKE :phoneNumber AND password LIKE :password")
    User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
}
