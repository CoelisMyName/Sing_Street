package com.alina.singstreet.view.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        binding.phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.password.setText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("phoneNumber", binding.phoneNumber.getEditableText().toString());
        outState.putString("password", binding.password.getEditableText().toString());
    }
}
