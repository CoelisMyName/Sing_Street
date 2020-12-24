package com.alina.singstreet.view.register;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.alina.singstreet.R;
import com.alina.singstreet.ShareViewModel;
import com.alina.singstreet.databinding.FragmentRegisterBinding;
import com.alina.singstreet.domain.User;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    FragmentRegisterBinding binding;
    TextState textState = new TextState();
    ShareViewModel shareViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.cancel.setOnClickListener(this);
        binding.signUp.setOnClickListener(this);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        binding.phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 11) {
                    binding.phoneNumber.setError("请输入正确的电话号码");
                    textState.phoneNumber = false;
                }
                else {
                    textState.phoneNumber = true;
                }
            }
        });

        binding.nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 30) {
                    binding.nickname.setError("昵称字符数最大不能超过30");
                    textState.nickname = false;
                }
                else {
                    textState.nickname = true;
                }
            }
        });

        binding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.repeatPassword.setText("");
                textState.match = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < 6) {
                    binding.password.setError("密码最小需要6个字符");
                    textState.password = false;
                }
                else {
                    textState.password = true;
                }
            }
        });

        binding.repeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals(binding.password.toString())) {
                    binding.repeatPassword.setError("密码与最初输入不相符");
                    textState.match = false;
                }
                else {
                    textState.match = true;
                }
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view == binding.cancel) {
            Navigation.findNavController(binding.getRoot()).navigateUp();
        }
        if (view == binding.signUp){
            if(!textState.phoneNumber && !textState.password && !textState.nickname && !textState.match){
                User user = new User();
                user.setPassword(binding.password.getText().toString());
                user.setNickname(binding.nickname.getText().toString());
                user.setIcon(R.drawable.icon1);
                user.setPhoneNumber(binding.phoneNumber.getText().toString());
                shareViewModel.register(user).observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(true){
                            shareViewModel.login(user.getPhoneNumber(),user.getPassword());
                            shareViewModel.showToast(R.string.register_success);
                        }
                        else {
                            shareViewModel.showToast(R.string.register_fail);
                        }
                    }
                });
            }
            shareViewModel.showToast(R.string.register_warning);
        }
    }

    static class TextState {
        public boolean phoneNumber = false;
        public boolean nickname = false;
        public boolean password = false;
        public boolean match = false;
    }
}
