package com.alina.singstreet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.alina.singstreet.databinding.ActivityContainerBinding;

public class ContainerActivity extends AppCompatActivity {
    ActivityContainerBinding binding;
    ShareViewModel shareViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_container);
        shareViewModel = new ViewModelProvider(this).get(ShareViewModel.class);
        shareViewModel.getLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    Navigation.findNavController(binding.fragment).navigate(R.id.login);
                } else {
                    NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home, true).build();
                    Navigation.findNavController(binding.fragment).navigate(R.id.home, null, navOptions);
                }
            }
        });
    }
}
