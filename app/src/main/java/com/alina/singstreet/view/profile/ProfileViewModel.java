package com.alina.singstreet.view.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alina.singstreet.domain.Follow;
import com.alina.singstreet.model.ProfileDetailModel;
import com.alina.singstreet.repository.UserRepository;

public class ProfileViewModel extends AndroidViewModel {
    UserRepository userRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<Integer> isFollowed(String userUID, String targetUID){
        return userRepository.isFollowed(userUID, targetUID);
    }

    public LiveData<ProfileDetailModel> getProfileDetailByUserUID(String userUID){
        return userRepository.getProfileDetailByUserUID(userUID);
    }

    public void unfollow(Follow follow){
        userRepository.unfollow(follow);
    }

    public void follow(Follow follow){
        userRepository.follow(follow);
    }
}
