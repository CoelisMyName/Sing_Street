package com.alina.singstreet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alina.singstreet.domain.User;

public class ShareViewModel extends AndroidViewModel {
    User user;

    public ShareViewModel(@NonNull Application application) {
        super(application);
    }

    
}
