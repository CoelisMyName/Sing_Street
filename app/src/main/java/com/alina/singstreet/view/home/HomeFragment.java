package com.alina.singstreet.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.alina.singstreet.R;
import com.alina.singstreet.ShareViewModel;
import com.alina.singstreet.databinding.FragmentHomeBinding;
import com.alina.singstreet.view.login.LoginFragment;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    ShareViewModel shareViewModel;
    ActionBarDrawerToggle drawerToggle;
    SavedStateHandle savedStateHandle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavController navController = NavHostFragment.findNavController(this);

        NavBackStackEntry navBackStackEntry = navController.getCurrentBackStackEntry();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.appbar.toolbar.setTitle(R.string.app_name);
        drawerToggle = new ActionBarDrawerToggle(requireActivity(),binding.drawer,binding.appbar.toolbar,R.string.open,R.string.close);
        drawerToggle.syncState();
        binding.drawer.addDrawerListener(drawerToggle);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.drawer.removeDrawerListener(drawerToggle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

    }

    void isLogin(){
        NavController navController = NavHostFragment.findNavController(this);

        NavBackStackEntry navBackStackEntry = navController.getCurrentBackStackEntry();
        savedStateHandle.getLiveData(LoginFragment.LOGIN_SUCCESSFUL)
                .observe(navBackStackEntry, (Observer<Object>) success -> {
                    Boolean s = (Boolean) success;
                    if (!s) {
                        int startDestination = navController.getGraph().getStartDestination();
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(startDestination, true)
                                .build();
                        navController.navigate(startDestination, null, navOptions);
                    }
                });
    }
}