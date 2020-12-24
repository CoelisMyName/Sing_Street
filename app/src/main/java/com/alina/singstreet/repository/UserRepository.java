package com.alina.singstreet.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alina.singstreet.R;
import com.alina.singstreet.dao.FollowDao;
import com.alina.singstreet.dao.ProfileDao;
import com.alina.singstreet.dao.UserDao;
import com.alina.singstreet.data.Database;
import com.alina.singstreet.domain.Follow;
import com.alina.singstreet.domain.User;
import com.alina.singstreet.model.ProfileDetailModel;
import com.alina.singstreet.model.ProfileModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    ExecutorService service = Executors.newCachedThreadPool();
    MutableLiveData<LoginResult> loginResult = new MutableLiveData<>(new LoginResult());
    UserDao userDao;
    ProfileDao profileDao;
    FollowDao followDao;

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public UserRepository(Application application){
        Database database = Database.getInstance(application);
        userDao = database.getUserDao();
        profileDao = database.getProfileDao();
        followDao = database.getFollowDao();
    }

    public LiveData<Boolean> register(User user){
        MutableLiveData<Boolean> b = new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                long[] l = userDao.insert(user);
                b.postValue(l[0] > 0);
            }
        });
        return b;
    }

    public LiveData<Boolean> update(User user){
        MutableLiveData<Boolean> b = new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                int i = userDao.update(user);
                b.postValue(i > 0);
            }
        });
        return b;
    }

    public void login(String phoneNumber, String password){
        service.execute(new Runnable() {
            @Override
            public void run() {
                User user = userDao.getUserByPhoneNumberAndPassword(phoneNumber,password);
                if(user != null ){
                    LoginResult.Success<User> success = new LoginResult.Success<>(user);
                    loginResult.postValue(new LoginResult(success));
                }
                else {
                    LoginResult.Error<Integer> error = new LoginResult.Error<>(R.string.login_fail);
                    loginResult.postValue(new LoginResult(error));
                }
            }
        });
    }

    public void logout(){
        loginResult.setValue(new LoginResult());
    }

    public LiveData<ProfileModel> getProfileByUserUID(String userUID){
        return profileDao.getProfileByUserUID(userUID);
    }

    public LiveData<List<ProfileModel>> getFollowerProfileByUserUID(String userUID){
        return profileDao.getFollowerProfileByUserUID(userUID);
    }

    public LiveData<List<ProfileModel>> getFollowingProfileByUserUID(String userUID){
        return profileDao.getFollowingProfileByUserUID(userUID);
    }

    public LiveData<ProfileDetailModel> getProfileDetailByUserUID(String userUID){
        return profileDao.getProfileDetailByUserUID(userUID);
    }

    public LiveData<List<ProfileModel>> searchProfileByNickname(String string){
        return profileDao.searchProfileByNickname(string);
    }

    public LiveData<Boolean> follow(Follow follow){
        MutableLiveData<Boolean> b = new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                long[] l = followDao.insert(follow);
                b.postValue(l[0] > 0);
            }
        });
        return b;
    }

    public LiveData<Boolean> unfollow(Follow follow){
        MutableLiveData<Boolean> b = new MutableLiveData<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                int i = followDao.delete(follow);
                b.postValue(i > 0);
            }
        });
        return b;
    }
}
