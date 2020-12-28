package com.alina.singstreet.view.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alina.singstreet.model.SingCardModel;
import com.alina.singstreet.repository.PostRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    PostRepository postRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
    }

    public LiveData<List<SingCardModel>> getSingCardByUserUID(@NonNull String userUID){
        return postRepository.getSingCardByUserUID(userUID);
    }
}
