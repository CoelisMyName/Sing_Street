package com.alina.singstreet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alina.singstreet.domain.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long[] insert(User... users);

    @Query("SELECT * FROM User")
    List<User> getUsersTest();

    @Update
    int update(User... users);

    @Query("SELECT * FROM User WHERE phoneNumber LIKE :phoneNumber AND password LIKE :password")
    User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
}
