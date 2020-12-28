package com.alina.singstreet;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alina.singstreet.domain.User;
import com.alina.singstreet.model.ProfileModel;
import com.alina.singstreet.repository.LoginResult;
import com.alina.singstreet.repository.PostRepository;
import com.alina.singstreet.repository.UserRepository;

public class ShareViewModel extends AndroidViewModel {
    static Toast toast;
    User user;
    LiveData<LoginResult> login;
    UserRepository userRepository;


    public ShareViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        login = userRepository.getLoginResult();
    }

    @SuppressLint("ShowToast")
    public void showToast(int resID){
        if(toast == null){
            toast = Toast.makeText(getApplication(), resID, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(resID);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public void showToast(String s){
        if(toast == null){
            toast = Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(s);
        }
        toast.show();
    }

    public LiveData<LoginResult> getLogin() {
        return login;
    }

    public void login(String phoneNumber, String password) {
        userRepository.login(phoneNumber,password);
    }

    public LiveData<Boolean> register(User user){
        return userRepository.register(user);
    }

    public void logout() {
        user = null;
        userRepository.logout();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public LiveData<ProfileModel> getProfileByUserUID(String userUID){
        return userRepository.getProfileByUserUID(userUID);
    }
}
