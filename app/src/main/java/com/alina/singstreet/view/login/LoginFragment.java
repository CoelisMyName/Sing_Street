package com.alina.singstreet.view.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alina.singstreet.R;
import com.alina.singstreet.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements View.OnClickListener {
    FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.signIn.setOnClickListener(this);
        binding.signUp.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.signIn) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id._login);
        }
        if (view == binding.signUp) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id._register);
        }
    }
}
