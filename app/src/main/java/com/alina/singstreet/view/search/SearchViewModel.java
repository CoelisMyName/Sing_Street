package com.alina.singstreet.view.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alina.singstreet.model.ProfileModel;
import com.alina.singstreet.model.SingCardModel;
import com.alina.singstreet.repository.PostRepository;
import com.alina.singstreet.repository.UserRepository;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    UserRepository userRepository;
    PostRepository postRepository;
    MutableLiveData<String> query = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        postRepository = new PostRepository(application);
    }

    public LiveData<List<ProfileModel>> searchProfileByString(String string) {
        return userRepository.searchProfileByString(string);
    }

    public LiveData<List<SingCardModel>> searchSingCard(String string) {
        return postRepository.searchSingCard(string);
    }

    public LiveData<String> getQuery() {
        return query;
    }

    public void setQuery(String s) {
        query.setValue(s);
    }
}
