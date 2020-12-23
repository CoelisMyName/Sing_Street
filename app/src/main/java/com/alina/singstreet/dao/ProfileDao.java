package com.alina.singstreet.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.alina.singstreet.model.ProfileDetailModel;
import com.alina.singstreet.model.ProfileModel;

import java.util.List;

@Dao
public interface ProfileDao {

    @Query("SELECT userUID, icon, phoneNumber, nickname FROM ProfileModel WHERE :userUID LIKE userUID")
    LiveData<ProfileModel> getProfileByUserUID(String userUID);

    @Query("SELECT ProfileModel.userUID AS userUID, icon, phoneNumber, nickname FROM ProfileModel, Follow WHERE Follow.followerUID = ProfileModel.userUID AND :userUID LIKE Follow.userUID")
    LiveData<List<ProfileModel>> getFollowerProfileByUserUID(String userUID);

    @Query("SELECT ProfileModel.userUID AS userUID, icon, phoneNumber, nickname FROM ProfileModel, Follow WHERE Follow.userUID = ProfileModel.userUID AND :userUID LIKE Follow.followerUID")
    LiveData<List<ProfileModel>> getFollowingProfileByUserUID(String userUID);

    @Transaction
    @Query("SELECT userUID, icon, phoneNumber, nickname FROM ProfileModel WHERE :userUID LIKE userUID")
    LiveData<ProfileDetailModel> getProfileDetailByUserUID(String userUID);
}
