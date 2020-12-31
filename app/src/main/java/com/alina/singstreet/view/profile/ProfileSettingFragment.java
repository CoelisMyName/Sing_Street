package com.alina.singstreet.view.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.alina.singstreet.R;
import com.alina.singstreet.ShareViewModel;
import com.alina.singstreet.databinding.FragmentProfilesettingBinding;
import com.alina.singstreet.model.ProfileModel;

public class ProfileSettingFragment extends Fragment {
    FragmentProfilesettingBinding binding;
    ShareViewModel shareViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profilesetting, container, false);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        binding.appbar.toolbar.setNavigationIcon(R.drawable.ic_round_clear_24);
        binding.appbar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });

        shareViewModel.getProfileByUserUID(shareViewModel.getUser().getUserUID()).observe(getViewLifecycleOwner(), new Observer<ProfileModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(ProfileModel profileModel) {
                binding.icon.setImageResource(profileModel.icon);
                binding.nickname.setText("昵称：" + profileModel.nickname);
                binding.password.setText("修改密码");
                binding.phoneNumber.setText("电话号码：" + profileModel.phoneNumber);
            }
        });

        return binding.getRoot();
    }
}
