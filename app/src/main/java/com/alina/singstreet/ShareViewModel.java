package com.alina.singstreet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alina.singstreet.domain.User;

public class ShareViewModel extends AndroidViewModel {
    User user;
    MutableLiveData<Boolean> login;

    public ShareViewModel(@NonNull Application application) {
        super(application);
        login = new MutableLiveData<>(false);
    }

    public LiveData<Boolean> getLogin() {
        return login;
    }

    public void login(String phoneNumber, String password) {
        login.setValue(true);
    }

    public void logout() {
        login.setValue(false);
    }
}
