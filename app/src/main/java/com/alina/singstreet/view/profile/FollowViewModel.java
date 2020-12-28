package com.alina.singstreet.view.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alina.singstreet.model.ProfileModel;
import com.alina.singstreet.repository.UserRepository;

import java.util.List;

public class FollowViewModel extends AndroidViewModel {
    UserRepository userRepository;

    public FollowViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<List<ProfileModel>> getFollowerProfileByUserUID(String userUID) {
        return userRepository.getFollowerProfileByUserUID(userUID);
    }

    public LiveData<List<ProfileModel>> getFollowingProfileByUserUID(String userUID) {
        return userRepository.getFollowingProfileByUserUID(userUID);
    }


}
