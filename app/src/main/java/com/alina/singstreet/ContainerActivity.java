package com.alina.singstreet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.alina.singstreet.databinding.ActivityContainerBinding;
import com.alina.singstreet.repository.LoginResult;

import java.io.File;
import java.io.FileDescriptor;

public class ContainerActivity extends AppCompatActivity {
    ActivityContainerBinding binding;
    ShareViewModel shareViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_container);
        shareViewModel = new ViewModelProvider(this).get(ShareViewModel.class);

        shareViewModel.getLogin().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                if (loginResult.getSuccess() != null){
                    shareViewModel.setUser(loginResult.getSuccess().data);
                    NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home, true).build();
                    Navigation.findNavController(binding.fragment).navigate(R.id.home, null, navOptions);
                    shareViewModel.showToast(R.string.login_success);
                    return;
                }
                if(loginResult.getError() != null){
                    shareViewModel.showToast(loginResult.getError().message);
                    return;
                }
                Navigation.findNavController(binding.fragment).navigate(R.id.login);
            }
        });
    }
}
